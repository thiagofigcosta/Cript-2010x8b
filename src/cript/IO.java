package cript;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class IO {

    /**
     *
     * @param filePath file path
     * @return the string inside a file
     * @throws IOException file not found
     */
    public static String readFileAsString(String filePath) throws IOException {
        Path path = Paths.get(trueNameFile(filePath));
        byte[] bytes=Files.readAllBytes(path);
        char[] out=new char[bytes.length];
        for(int i=0;i<bytes.length;i++)
            out[i]=(char)(bytes[i]&0xFF);
        return String.valueOf(out);
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
        byte[] x=new byte[data.length()];
        for(int i=0;i<data.length();i++){
            x[i]=(byte) data.charAt(i);
        }
        FileOutputStream fos = new FileOutputStream(trueNameFile(path));
        fos.write(x);
        fos.close();
    }
    
    
    private static String trueNameFile(String path){
        //return "res/"+path;
        return path;
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
