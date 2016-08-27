package cript;

import Exception.HashException;

public class Cript2010x8b {
    
    public static final byte VERSION=0b0001;
    public static final boolean PRINTEXCEPTIONS=true;
    
    private int nCript=1;//numero de criptografias
    private boolean hashInFile=false;//no chave no arquivo
    private Hash hash=new Hash();//chave
    private String path="";
    private boolean cript=true;//false=decript

    public int getnCript() {
        return nCript;
    }

    public void setnCript(int nCript) {
        this.nCript = nCript;
    }

    public boolean isHashInFile() {
        return hashInFile;
    }

    public void setHashInFile(boolean hashInFile) {
        this.hashInFile = hashInFile;
    }

    public Hash getHash() {
        return hash;
    }

    public void setHash(Hash hash) {
        this.hash = hash;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    //TODO: funcao q verifica 100% de corrosao byte a byte

    public Cript2010x8b(int nCript,boolean cript,boolean hashInFile,Hash hash,String path) {
        this.nCript=nCript;
        this.hashInFile=hashInFile;
        this.hash=hash;
        this.path=path;
        this.cript=cript;
    }

    public Cript2010x8b(String command,String path){
        this.path=path;  
        for(int i=0;i<command.length();i++){
            if(Character.toLowerCase(command.charAt(i))=='c'||Character.toLowerCase(command.charAt(i))=='d'){
                if(Character.toLowerCase(command.charAt(i))=='c')this.cript=true;
                else this.cript=false;
                try{
                    this.nCript=Integer.parseInt(command.substring(0, i));
                    if(this.nCript<0) this.nCript=1;
                }catch(Exception e){this.nCript=1;}
                try{
                    this.hash=new Hash(command.substring(i+1, command.length()-2));
                }catch(HashException e){
                
                }
                this.hashInFile=Character.toLowerCase(command.charAt(command.length()-1))=='i';
                break;
            }
        }
    }
    
    
}
