package cript; 
 
import Exception.HashException; 
import java.io.IOException; 
 
public class Cript2010x8b { 
     
    public static final byte VERSION=0b0000; 
    public static final boolean PRINT=true; 
    public static final boolean PRINTEXCEPTIONS=true; 
    public static final boolean USEPOWNTHRT=false;
    public static final boolean CHECKDATALOSS=true; 
    public static final boolean MULTICRIPTWITHRANDHASH=true; 
    public static final boolean ADDRANDOMBYTES=true;
    public static final boolean USEDIVMULT=false;  
    public static final boolean CRIPTHASH=false;
    public static final boolean USECUSTOMSTEEPORDER=false;
     
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
            else
                this.hash=Hash.genRandom(); 
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
        if(hashInFile&&!cript){ 
            try{ 
                ExportedHashData x=Hash.searchHash(this.data);
                this.data=x.data;
                hash=x.hash; 
            }catch(HashException ex){ 
                if(PRINTEXCEPTIONS)System.out.println("error: "+ex.getMessage()); 
            } 
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
            ExportedHashData x=Hash.searchHash(this.data);
            hash=x.hash;
            this.data=x.data;
        } 
        if(hash==null){ 
            hash=Hash.genRandom(); 
        } 
        if(VERSION!=hash.getVersion()) throw new HashException("Versão incompativel. Atual: "+VERSION+"  -  Versão do arquivo: "+hash.getVersion()); 
        run(); 
    } 
     
    private void run() throws HashException{
        Hash cdH=hash; 
                    //this.data=this.rawFile;
        for(int i=0;i<this.nCript;i++){ 
            boolean notLastCript=false; 
            if(MULTICRIPTWITHRANDHASH&&!cript&&nCript>1){
                if(i!=0){
                    try{
                        ExportedHashData x=Hash.searchHash(data);
                        cdH=x.hash;
                        this.data=x.data; 
                    }catch(HashException e){if(PRINTEXCEPTIONS)System.out.println("erro: "+e);}
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
            boolean err;
            do{
            err=false;
            try{this.data=new Cript(cdH,data,cript).run();}catch(HashException e){
                err=true;
                cdH.setMult(cdH.getMult()-1);
                System.out.println(e.getMessage()+"\n"+cdH.getMult());
            }
            }while(err);
            if(notLastCript){ this.data=Hash.includeHash(data, cdH);} 
        }
        if(cript){ 
            if(hashInFile){ 
                this.data=Hash.includeHash(data, hash); 
            }else{ 
                if(PRINT&&VERSION!=0b0000)System.out.println(hash.showData());
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
        for(int i=0;i<this.nCript;i++){ 
            boolean notLastCript=false; 
            if(MULTICRIPTWITHRANDHASH&&cript&&nCript>1){
                if(i!=0){
                    try{
                        ExportedHashData x=Hash.searchHash(temp);
                        cdH=x.hash;
                        temp=x.data; 
                    }catch(HashException e){if(PRINTEXCEPTIONS)System.out.println("erro: "+e);}
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
            try{temp=new Cript(cdH,temp,!cript).run();}catch(Exception e){}
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