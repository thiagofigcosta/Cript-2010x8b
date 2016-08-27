package cript; 
 
import Exception.HashException; 
import Exception.MultDivException;
import java.io.IOException; 
 
public class Cript2010x8b { 
     
    public static final byte VERSION=0b0001; 
    public static final boolean USEPOWNTHRT=false;
    public static final boolean PRINTDIVEXCEPTIONS=false; 
    public static final boolean PRINTEXCEPTIONS=true;
    public static final boolean PRINT=true; 
    public static final boolean ADDARRAYSIZE=true; 
    public static final boolean CHECKDATALOSS=true; 
    public static final boolean MULTICRIPTWITHRANDHASH=true; 
    public static final boolean ADDRANDOMBYTES=true;
    public static final boolean CRIPTHASH=true;
    public static final boolean USECUSTOMSTEEPORDER=true;
    public static final boolean USEDIVMULT=true;
    public static final boolean OVERWRITETFILES=true;

     
    public boolean compareResult=true;
    
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
         
    public Cript2010x8b(int nCript,boolean cript, Hash hash,String data) throws HashException{ 
        this.nCript=nCript; 
        this.hash=hash; 
        this.cript=cript;     
        this.rawFile=data; 
        this.data=data; 
        if(hash==null){ 
            if(!cript)
                try{
                    ExportedHashData x=Hash.searchHash(data);
                    this.hash=x.hash;
                    this.data=x.data;
                }catch(Exception e){this.hash=null;}
            else{
                this.hash=Hash.genRandom(); 
                hashInFile=true;
            }
        } 
        run();
    } 
 
    public Cript2010x8b(int nCript,boolean cript,boolean hashInFile,Hash hash,String path) throws IOException, HashException { 
        this.nCript=nCript; 
        this.hashInFile=hashInFile; 
        this.hash=hash; 
        this.path=path; 
        this.cript=cript;     
        this.rawFile=IO.readFileAsString(path); 
        this.filename=IO.getFileName(path); 
        this.data=this.rawFile; 

        if(hash==null){ 
             if(!cript)
                try{
                    ExportedHashData x=Hash.searchHash(data);
                    this.hash=x.hash;
                    this.data=x.data;
                }catch(Exception e){this.hash=Hash.genRandom();}
            else{
                this.hash=Hash.genRandom(); 
            }
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
                }catch(Exception e){
                    this.hash=null;
                }}
        } 
        this.rawFile=IO.readFileAsString(path); 
        this.filename=IO.getFileName(path); 
        this.data=this.rawFile; 
        if(hashInFile&&!cript){ 
            ExportedHashData x=Hash.searchHash(this.data);
            hash=x.hash;
            this.data=x.data;
        } 
        if(hash==null){ 
             if(!cript)
                try{
                    ExportedHashData x=Hash.searchHash(data);
                    this.hash=x.hash;
                    this.data=x.data;
                }catch(Exception e){this.hash=Hash.genRandom();}
            else{
                this.hash=Hash.genRandom(); 
            }
        }  
        if(VERSION!=hash.getVersion()) System.out.println("Versão incompativel. Atual: "+VERSION+"  -  Versão do arquivo: "+hash.getVersion());//throw new HashException("Versão incompativel. Atual: "+VERSION+"  -  Versão do arquivo: "+hash.getVersion()); 
        run(); 
    }
    
    public Cript2010x8b(String command,String path,String pathhash) throws IOException, HashException{ 
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
                }catch(Exception e){
                    this.hash=null;
                }
            }
        } 
        this.rawFile=IO.readFileAsString(path); 
        this.filename=IO.getFileName(path); 
        this.data=this.rawFile; 
        if(hashInFile&&!cript){ 
            ExportedHashData x=Hash.searchHash(this.data);
            hash=x.hash;
            this.data=x.data;
        } 
        if(hash==null){ 
            this.hash=new Hash(IO.readFileAsString(pathhash));
        }
        if(VERSION!=hash.getVersion()) System.out.println("Versão incompativel. Atual: "+VERSION+"  -  Versão do arquivo: "+hash.getVersion());//throw new HashException("Versão incompativel. Atual: "+VERSION+"  -  Versão do arquivo: "+hash.getVersion()); 
        run(); 
    }
     
    private void run() throws HashException{
        Hash cdH=hash;
        for(int i=0;i<this.nCript;i++){ 
            boolean notLastCript=false; 
            if(MULTICRIPTWITHRANDHASH&&!cript&&nCript>1){
                if(i!=0){
                    try{
                        ExportedHashData x=Hash.searchHash(data);
                        cdH=x.hash;
                        this.data=x.data; 
                    }catch(HashException e){if(PRINTEXCEPTIONS)System.out.println("erro1: "+e);}
                }else{
                    cdH=hash; 
                }
            } 
            if(MULTICRIPTWITHRANDHASH&&cript&&nCript>1){ 
                if(i+1==nCript){ 
                    cdH=hash;
                }else{ 
                    cdH=Hash.genRandom();
                    notLastCript=true; 
                } 
            }
            String olddata=data;
            String newdata="";
            boolean err;
            boolean fail;
            do{
                fail=false;
                do{
                    err=false;
                    try{this.data=new Cript(cdH,olddata,cript).run();}catch(MultDivException e){err=true;if(cdH==hash){hash.setMult(Math.max(hash.getMult()-1,1));}cdH.setMult(Math.max(cdH.getMult()-1,1));}
                }while(err);
                do{
                    err=false;
                    try{newdata=new Cript(cdH,data,!cript).run();}catch(MultDivException e){err=true;if(cdH==hash){hash.setMult(Math.max(hash.getMult()-1,1));}cdH.setMult(Math.max(cdH.getMult()-1,1));}
                }while(err);
                if(!olddata.equals(newdata)){ 
                    fail=true;
                    if(cdH==hash){
                        hash=Hash.genRandom();
                        cdH=hash;
                    }else
                        cdH=Hash.genRandom();
                    System.out.println("Hash fail:"+cdH.compressHash());
                }
            }while(fail);
            if(notLastCript){ this.data=Hash.includeHash(data, cdH);} 
        }
        if(cript){ 
            if(hashInFile){
                this.data=Hash.includeHash(this.data, hash);
            }else{ 
                if(PRINT&&VERSION!=0b0000)System.out.println(hash.showData());
                String ext="";
                for(int i=0;i<filename.length();i++){
                    if(filename.charAt(i)=='.'){
                        filename=filename.substring(0, i);
                        ext=filename.substring(i);
                        break;
                    }
                }
                String out=path.replace(filename, filename+"-hash"+ext);
                try{IO.saveFile(hash.compressHash(), out);}
                catch(Exception e){if(PRINTEXCEPTIONS)System.out.println("erro ao salvar arquivo");} 
            } 
        }
        if(!path.equals(""))
            try{IO.saveFile(data, IO.filenamePathHandler(path, filename, cript));} 
            catch(Exception e){if(PRINTEXCEPTIONS)System.out.println("erro ao salvar arquivo");} 
         
        if(CHECKDATALOSS&&cript)if(cript)if(!compare())if(PRINT)System.out.println(hash.showData()); 
    } 
     
    private boolean compare(){ 
        String temp=data; 
        Hash cdH=hash; 
        if(hashInFile){
            try{
                ExportedHashData x=Hash.searchHash(data);
                temp=x.data;
                cdH=x.hash;
            }catch(Exception e){}
        }
        for(int i=0;i<this.nCript;i++){ 
            boolean notLastCript=false; 
            if(MULTICRIPTWITHRANDHASH&&cript&&nCript>1){
                if(i!=0){
                    try{
                        ExportedHashData x=Hash.searchHash(temp);
                        cdH=x.hash;
                        temp=x.data; 
                    }catch(HashException e){if(PRINTEXCEPTIONS)System.out.println("erro2: "+e);}
                }else{
                    cdH=hash; 
                }
            } 
            if(MULTICRIPTWITHRANDHASH&&!cript&&nCript>1){ 
                if(i+1==nCript){ 
                    cdH=hash;
                }else{ 
                    cdH=Hash.genRandom();
                    notLastCript=true; 
                } 
            }
            try{temp=new Cript(cdH,temp,!cript).run();}catch(MultDivException e){}
            if(notLastCript){ temp=Hash.includeHash(temp, cdH);} 
        }
        long total=Math.abs(rawFile.length()-temp.length()),match=0; 
        long minor=temp.length();
        if(temp.length()>rawFile.length())
            minor=rawFile.length();
        for(int i=0;i<minor;i++){ 
            total++; 
            if(rawFile.charAt(i)==temp.charAt(i)) 
                match++; 
        } 
        long losed=total-match; 
        double percentage=(double)match/total*100.0; 
        System.out.println("Bytes analisados: "+total); 
        System.out.println("Bytes perdidos: "+losed); 
        System.out.println("Porcentagem de eficacia: "+percentage+"%"); 
        compareResult=percentage==100;
        return compareResult; 
    }  
} 