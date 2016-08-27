package cript; 
 
import Exception.HashException; 
import static cript.Hash.splitByte; 
import java.io.IOException; 
 
public class Cript2010x8b { 
     
    public static final byte VERSION=0b0001; 
    public static final boolean PRINTEXCEPTIONS=true; 
    public static final boolean CHECKDATALOSS=true; 
    public static final boolean MULTICRIPTWITHRANDHASH=true; 
     
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
            hash=Hash.genRandom(); 
        } 
        boolean useCustomRun=false;
        if(useCustomRun){
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
        }else{run();}
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
                hash=searchHash(); 
            }catch(HashException ex){ 
                System.out.println("error: "+ex.getMessage()); 
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
            hash=searchHash(); 
        } 
        if(hash==null){ 
            hash=Hash.genRandom(); 
        } 
        if(VERSION!=hash.getVersion()) throw new HashException("Versão incompativel. Atual: "+VERSION+"  -  Versão do arquivo: "+hash.getVersion()); 
        run(); 
    } 
     
    private void run() throws HashException{
        Hash cdH=hash; 
        this.data=this.rawFile;
        for(int i=0;i<this.nCript;i++){ 
            boolean notLastCript=false; 
            if(MULTICRIPTWITHRANDHASH&&!cript&&nCript>1&&i!=0){ 
                try{cdH=searchHash();}catch(HashException e){System.out.println("erro: "+e);} 
            } 
            if(MULTICRIPTWITHRANDHASH&&cript&&nCript>1){ 
                if(i+1==nCript){ 
                    cdH=hash; 
                }else{ 
                    cdH=Hash.genRandom(); 
                    notLastCript=true; 
                } 
            } 
            this.data=new Cript(cdH,data,cript).run(); 
            if(notLastCript) includeHash(); 
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
     
    private Hash searchHash() throws HashException{ 
        int angular=(int)splitByte((int)data.charAt(0),0,3)+1; 
        int linear=(int)splitByte((int)data.charAt(0),4,7)+1; 
        data=data.substring(1); 
        String findedHash=""; 
        int maxValueOfX=(data.length()-linear)/angular; 
        int n=0; 
        if(!(linear==1&&angular==1)){
            for(int t=0;t<=maxValueOfX;t++){ 
                int image=angular*t+linear; 
                image-=n;  
                String buff1="",buff2=""; 
                try{buff1=data.substring(0, image-1);}catch(Exception e){}
                try{buff2=data.substring(image);}catch(Exception e){} 
                try{findedHash+=data.charAt(image-1);}catch(Exception e){} 
                data=buff1+buff2; 
                maxValueOfX=(data.length()-linear)/angular; 
                if(n<17)
                    n++;
                else 
                    break;
            } 
        }else{
            while(n<18){ 
                try{findedHash+=data.charAt(data.length()-1);}catch(Exception e){}
                try{data=data.substring(0, data.length()-1);}catch(Exception e){}
                n++; 
            }
        }
        if(findedHash.length()>18) throw new HashException("Tamanho incorreto("+findedHash.length()+")");
        Hash sHash=new Hash(findedHash);        
        return sHash; 
    } 
     
    private void includeHash(){ 
        int angular=(int) (Math.random()*((15)+1))+1; 
        int linear=(int) (Math.random()*((15)+1))+1; 
        if(angular==1&&linear==1)linear++;
        String hashToInclude=hash.compressHash(); 
        String dataBKP=data;
        int b=0; 
        int maxValueOfX=(data.length()-linear)/angular; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=angular*t+linear; 
            String buff1="",buff2=""; 
            try{buff1=data.substring(0, image-1);}catch(Exception e){} 
            try{buff2=data.substring(image-1);}catch(Exception e){} 
            data=buff1+hashToInclude.charAt(b)+buff2; 
            if(b+1<hashToInclude.length())
                b++;
            else 
                break;
        }
        if(b<17){
            data=dataBKP;
            angular=1;
            linear=1;
            for(int i=17;i>=0;i--){ 
                data=data+hashToInclude.charAt(i); 
            }     
        }    
        data=((char)((linear-1)*16+(angular-1)))+data;
    } 
     
    private boolean compare(){ 
        String temp=data; 
        for(int i=0;i<this.nCript;i++){ 
            temp=new Cript(hash,temp,!cript).run(); 
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
        return percentage==100; 
    } 
} 