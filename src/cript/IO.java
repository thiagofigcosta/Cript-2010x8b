package cript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO {
    public static String readFileAsString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            char[] buf = new char[1024];
            int numRead=0;
            while((numRead=reader.read(buf)) != -1){
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
        }
        return fileData.toString();
    }
    
    public static String getFileName(String filePath)throws IOException{
        try{
            File f = new File(filePath);
            return f.getName();
        }catch(Exception e){
            throw new IOException("erro ao pegar nome do arquivo");
        }
    }
    
    public static void saveFile(String data, String path) throws IOException{
        try {
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
        out.write(data);
        out.close();
        }
        catch (IOException e){
            throw new IOException("erro ao salvar arquivo");
        }
    }
    
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
                filenameNoExt=filename.substring(0, i-1)+extraInfo;
                ext=filename.substring(i);
                break;
            }
        }
        String out=path.replace(filename, filenameNoExt+ext);
        int n=0;
        File f = new File(out);
        while(f.exists() && !f.isDirectory()){
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
