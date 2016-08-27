package cript;

import Exception.HashException;
import java.io.IOException;

public class Cript2010x8b {
    
    public static final byte VERSION=0b0001;
    public static final boolean PRINTEXCEPTIONS=true;
    public static final boolean CHECKDATALOSS=true;
    
    private int nCript=1;//numero de criptografias
    private boolean hashInFile=false;//no chave no arquivo
    private Hash hash=new Hash();//chave
    private String path="";
    private boolean cript=true;//false=decript
    private String data="";
    private String rawFile;
    private String filename="default";

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getData(){
        return this.data;
    }
    
    public void setData(String data){
        this.data=data;
    }

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
    
    public String getRawFile(){
        return this.rawFile;
    }
    
    //TODO: funcao q verifica 100% de corrosao byte a byte
    
    public Cript2010x8b(int nCript,boolean cript, Hash hash,String data){
        this.nCript=nCript;
        this.hash=hash;
        this.cript=cript;    
        this.rawFile=data;
        this.data=data;
        if(hash==null){
            hash=Hash.genRandom();
        }
        for(int i=0;i<this.nCript;i++){
            this.data=new Cript(hash,this.data,cript).run();
        }
        if(cript){
            if(hashInFile){
                includeHash();
            }else{
                System.out.println(hash);
            }
        }
        if(cript)if(!compare())System.out.println(hash.showData());
    }

    public Cript2010x8b(int nCript,boolean cript,boolean hashInFile,Hash hash,String path) throws IOException {
        this.nCript=nCript;
        this.hashInFile=hashInFile;
        this.hash=hash;
        this.path=path;
        this.cript=cript;    
        this.rawFile=IO.readFileAsString(path);
        this.filename=IO.getFileName(path);
        this.data=this.rawFile;
        if(hashInFile&&!cript){
            hash=searchHash();
        }
        if(hash==null){
            hash=Hash.genRandom();
        }
        run();
    }

    public Cript2010x8b(String command,String path) throws IOException, HashException{
        this.path=path;  
        for(int i=0;i<command.length();i++){//TODO: aceitar comando de arquivos tb(nCript\nhash)
            if(Character.toLowerCase(command.charAt(i))=='c'||Character.toLowerCase(command.charAt(i))=='d'){
                if(Character.toLowerCase(command.charAt(i))=='c')this.cript=true;
                else this.cript=false;
                try{
                    this.nCript=Integer.parseInt(command.substring(0, i));
                    if(this.nCript<0) this.nCript=1;
                }catch(Exception e){this.nCript=1;}
                this.hashInFile=Character.toLowerCase(command.charAt(command.length()-1))=='i';
                if(!hashInFile)
                try{
                    this.hash=new Hash(command.substring(i+1, command.length()-2));
                }catch(HashException e){
                    hash=null;
                }
                break;
            }
        }
        this.rawFile=IO.readFileAsString(path);
        this.filename=IO.getFileName(path);
        this.data=this.rawFile;
        if(hashInFile&&!cript){
            hash=searchHash();
        }
        if(hash==null){
            hash=Hash.genRandom();
        }
        if(VERSION!=hash.getVersion()) throw new HashException("Versão incompativel. Atual: "+VERSION+"  -  Versão do arquivo: "+hash.getVersion());
        run();
    }
    
    private void run(){
        for(int i=0;i<this.nCript;i++){
            this.data=new Cript(hash,data,cript).run();//TODO: criptografias diferentes inclusas em outras camadas do for se n>1
        }
        if(cript){
            if(hashInFile){
                includeHash();
            }else{
                System.out.println(hash);
            }
        }
        
        try{IO.saveFile(data, IO.filenamePathHandler(path, filename, cript));}
        catch(Exception e){System.out.println("erro ao salvar arquivo");}
        
        if(CHECKDATALOSS&&cript)compare();
    }
    
    private Hash searchHash(){//TODO
        Hash sHash=new Hash();
        
        return sHash;
    }
    
    private void includeHash(){//TODO
    
    }
    
    private boolean compare(){
        String temp=data;
        for(int i=0;i<this.nCript;i++){
            temp=new Cript(hash,temp,!cript).run();
        }
        long total=Math.abs(rawFile.length()-temp.length()),match=0;
        for(int i=0;i<temp.length();i++){
            total++;
            if(rawFile.charAt(i)==temp.charAt(i))
                match++;
        }
        long losed=total-match;
        double percentage=(double)match/total*100.0;
        System.out.println("Bytes analisados: "+total);
        System.out.println("Bytes perdidos: "+losed);
        System.out.println("Porcentagem de eficacia: "+percentage+"%");
        return percentage==100;
    }
}
