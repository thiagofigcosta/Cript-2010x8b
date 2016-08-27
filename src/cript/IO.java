package cript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO {

    /**
     *
     * @param filePath file path
     * @return the string inside a file
     * @throws IOException file not found
     */
    public static String readFileAsString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(trueNameFile(filePath)))) {
            char[] buf = new char[1024];
            int numRead=0;
            while((numRead=reader.read(buf)) != -1){
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
        }
        return fileData.toString();
    }
    
    /**
     *
     * @param filePath file path
     * @return the name of a file
     * @throws IOException
     */
    public static String getFileName(String filePath)throws IOException{
        try{
            File f = new File(trueNameFile(filePath));
            return f.getName();
        }catch(Exception e){
            throw new IOException("erro ao pegar nome do arquivo");
        }
    }
    
    /**
     * saves a string into a file
     * @param data string to be saved
     * @param path file path
     * @throws IOException cant create file
     */
    public static void saveFile(String data, String path) throws IOException{
        try {
        BufferedWriter out = new BufferedWriter(new FileWriter(trueNameFile(path)));
        out.write(data);
        out.close();
        }
        catch (IOException e){
            throw new IOException("erro ao salvar arquivo");
        }
    }
    
    private static String trueNameFile(String path){
        return "res/"+path;
        //return path;
    }
    
    /**
     *
     * @param path folder file path
     * @param filename file name
     * @param cript cript or decript
     * @return file path to cript or decripted file 
     * @throws IOException
     */
    public static String filenamePathHandler(String path, String filename, boolean cript) throws IOException{
        String extraInfo="-cripted or decripted";
        if(cript){
            extraInfo="-cripted";
        }else{
            extraInfo="-decripted";
        }
        String filenameNoExt=filename;
        String ext="";
        for(int i=0;i<filename.length();i++){
            if(filename.charAt(i)=='.'){
                filenameNoExt=filename.substring(0, i);
                ext=filename.substring(i);
                break;
            }
        }
        String out=path.replace(filename, filenameNoExt+extraInfo+ext);
        int n=0;
        if(!Cript2010x8b.OVERWRITETFILES) return out;
        File f = new File(out);
        while(f.exists()&&!f.isDirectory()){
            n++;
            out=path.replace(filename, filenameNoExt+n+ext);
            f = new File(out);
            if(n>100){
                throw new IOException("cant handle filename");
            }
        }
        return out;
    }
}
