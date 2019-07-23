package cript; 
 
import Exception.HashException; 
import Exception.DataException;
import Exception.MD5Exception;
import static cript.Hash.splitByte;
import java.io.IOException; 
 
public class Cript2010x8b { 
     
    public static final byte VERSION=0b0101; 
    
    public static final boolean PRINTDIVEXCEPTIONS=false; 
    public static final boolean PRINTEXCEPTIONS=false;
    public static final boolean PRINTSTEEPS=true;
    public static final boolean PRINT=false; 
    
    public static final boolean USEPOWNTHRT=false;//err
    
    public static final boolean ADDARRAYSIZE=true; 
    public static final boolean MULTICRIPTWITHRANDHASH=true; 
    public static final boolean ADDRANDOMBYTES=true;
    public static final boolean CRIPTHASH=true;
    public static final boolean USECUSTOMSTEEPORDER=true;
    public static final boolean USEDIVMULT=true;
    
    public static final boolean OVERWRITETFILES=true;
    public static       boolean CHECKHASHFAIL=true;
    public static final boolean CHECKDATALOSS=true;
    public static final boolean CHECKSTR=true;
    public static final boolean CRIPT=true;

    public static int totalSteeps=1;
    public static int currentSteeps=0;
    public static int computingSteeps=1;
    
    public boolean compareResult=true;
    
    public static int multErr=0;
    public static int addErr=-1;
    
    public double percetage=100;
    
    private int nCript=1;//numero de criptografias 
    private boolean hashInFile=false;//no chave no arquivo 
    private Hash hash=new Hash();//chave 
    private String path=""; 
    private boolean cript=true;//false=decript 
    private String data=""; 
    private String rawFile; 
    private String filename="default"; 
    public static MD5 md5=new MD5();
    public static int salt_hash_size=43;

 
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
         
    public Cript2010x8b(int nCript,boolean cript, Hash hash,String data,boolean hashInFile) throws HashException, DataException, MD5Exception{ 
        Cript2010x8b.totalSteeps=nCript*7;
        Cript2010x8b.computingSteeps=0;
        Cript2010x8b.currentSteeps=0;
        this.nCript=nCript; 
        this.hash=hash; 
        this.cript=cript;     
        this.rawFile=data; 
        this.data=data; 
        this.hashInFile=hashInFile;
        if(hash==null||hashInFile){ 
            if(!cript)
                try{
                    ExportedHashData x=Hash.searchHash(data);
                    this.hash=x.hash;
                    this.data=x.data;
                }catch(Exception e){this.hash=null;}
        }if(hash==null){ 
            if(cript){
                this.hash=Hash.genRandom(); 
                hashInFile=true;
            }
        } 
        checkStr(data);
        run();
    } 
 
    public Cript2010x8b(int nCript,boolean cript,boolean hashInFile,Hash hash,String path) throws IOException, HashException, DataException, MD5Exception { 
        Cript2010x8b.totalSteeps=nCript*7;
        Cript2010x8b.computingSteeps=0;
        Cript2010x8b.currentSteeps=0;
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
        checkStr(data);
        run(); 
    } 
 
    public Cript2010x8b(String command,String path) throws IOException, HashException, DataException, MD5Exception{ 
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
        checkStr(data);
        Cript2010x8b.totalSteeps=nCript*7;
        Cript2010x8b.computingSteeps=0;
        Cript2010x8b.currentSteeps=0;
        run(); 
    }
    
    public Cript2010x8b(String command,String path,String pathhash) throws IOException, HashException, DataException, MD5Exception{ 
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
        checkStr(data);
        Cript2010x8b.totalSteeps=nCript*7;
        Cript2010x8b.computingSteeps=0;
        Cript2010x8b.currentSteeps=0;
        run(); 
    }
     
    private void run() throws HashException, MD5Exception{
        Hash cdH=hash;
        if(!cript){
            data=findAndCheckPassword(data);
        }
        multErr=0;
        addErr=-1;
        if(CHECKHASHFAIL)Cript2010x8b.totalSteeps*=2;
        for(int i=0;i<this.nCript;i++){
            boolean notLastCript=false; 
            if(MULTICRIPTWITHRANDHASH&&!cript&&nCript>1){
                if(i!=0){
                    try{
                        if(Cript2010x8b.PRINTSTEEPS) System.out.println("***Searching hash");
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
                    if(Cript2010x8b.PRINTSTEEPS) System.out.println("***Generating hash");
                    cdH=Hash.genRandom();
                    notLastCript=true; 
                } 
            }
            String olddata=data;
            String newdata;
            boolean fail;
            do{
                fail=false;
                if(Cript2010x8b.PRINTSTEEPS) System.out.println("***Running:"+(int)(i+1)+"/"+this.nCript);
                this.data=new Cript(cdH,olddata,cript).run();
                if(cdH==hash){
                    hash.setMult(Math.max(hash.getMult()-multErr,1));
                    if(addErr!=-1){
                        hash.setAdd(addErr);
                        hash.setAddArraySize(false);
                    }
                }
                cdH.setMult(Math.max(cdH.getMult()-multErr,1));
                if(addErr!=-1){
                    cdH.setAdd(addErr);
                    cdH.setAddArraySize(false);
                }
                Cript2010x8b.currentSteeps+=Cript2010x8b.computingSteeps;
                Cript2010x8b.computingSteeps=0;
//                if(CHECKHASHFAIL&&cript){
//                    if(Cript2010x8b.PRINTSTEEPS) System.out.println("***Checking Hash");
//                    newdata=new Cript(cdH,data,!cript).run();
//                    if(!olddata.equals(newdata)&&cript){ 
//                        fail=true;
//                        System.out.println("Hash fail:"+cdH.showData());
//                        if(cdH==hash){
//                            hash=Hash.genRandom();
//                            cdH=hash;
//                        }else
//                            cdH=Hash.genRandom();
//                    }
                    if(!fail){
//                        Cript2010x8b.currentSteeps+=Cript2010x8b.computingSteeps;
//                        Cript2010x8b.computingSteeps=0;
//                    }else Cript2010x8b.currentSteeps-=7; 
                }
            }while(fail&&CHECKHASHFAIL&&cript);
            if(notLastCript){ this.data=Hash.includeHash(data, cdH);} 
        }

        if(cript){ 
            if(CHECKDATALOSS)if(!compare())if(PRINT){if(Cript2010x8b.PRINTSTEEPS) System.out.println("***Checking data");System.out.println(hash.showData());}
            data=includePassword(data);
            if(hashInFile){
                this.data=Hash.includeHash(this.data, hash);
            }else{ 
                if(PRINT&&VERSION!=0b0000)System.out.println(hash.showData());
                if(!path.equals("")){
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
        }
        if(!path.equals(""))
            try{IO.saveFile(data, IO.filenamePathHandler(path, filename, cript));} 
            catch(Exception e){if(PRINTEXCEPTIONS)System.out.println("erro ao salvar arquivo");} 
         
    } 
     
    private boolean compare(){ 
        String temp=data; 
        Hash cdH=hash; 
        /*if(hashInFile){
            try{
                ExportedHashData x=Hash.searchHash(data);
                temp=x.data;
                cdH=x.hash;
            }catch(Exception e){}
        }*/
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
            temp=new Cript(cdH,temp,!cript).run();
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
        this.percetage=percentage;
        compareResult=percentage==100;
        return compareResult; 
    }  
    
    public static String findAndCheckPassword(String data) throws MD5Exception{
        int angular=(int)splitByte((int)data.charAt(0),0,3)+1;
        int linear=(int)splitByte((int)data.charAt(0),4,7)+1; 
        data=data.substring(1);
        StringBuilder salt_hash=new StringBuilder(); 
        int maxValueOfX=(data.length()-linear)/angular; 
        int n=0; 
        if(!(linear==1&&angular==1)){
            for(int t=0;t<=maxValueOfX;t++){ 
                int image=angular*t+linear; 
                image-=n;  
                String buff1="",buff2=""; 
                try{buff1=data.substring(0, image-1);}catch(Exception e){}
                try{buff2=data.substring(image);}catch(Exception e){} 
                try{salt_hash.append(data.charAt(image-1));}catch(Exception e){} 
                data=buff1+buff2; 
                maxValueOfX=(data.length()-linear)/angular; 
                if(n<salt_hash_size-1)
                    n++;
                else 
                    break;
            } 
        }else{
            while(n<salt_hash_size){ 
                try{salt_hash.append(data.charAt(data.length()-1));}catch(Exception e){}
                try{data=data.substring(0, data.length()-1);}catch(Exception e){}
                n++; 
            }
        }
        if(salt_hash.length()!=salt_hash_size) throw new MD5Exception("Tamanho incorreto de salt_hash("+salt_hash.length()+")");
        md5.setSalt(salt_hash.substring(0, salt_hash_size-32));
        if(!md5.verify(salt_hash.substring(salt_hash_size-32))){
            throw new MD5Exception("Wrong password");
        }
        return data;
    }
    
    public static String includePassword(String data) throws MD5Exception{
        int angular=(int) (Math.random()*((15)+1))+1; 
        int linear=(int) (Math.random()*((15)+1))+1; 
        if(angular==1&&linear==1)linear++;
        md5.setSalt(MD5.genRandomSalt());
        String toInc=md5.getSalt()+md5.getHash(); 
        if(salt_hash_size!=toInc.length()) throw new MD5Exception("Err size of to include:"+toInc.length());
        String dataBKP=data;
        int b=0; 
        int maxValueOfX=(data.length()-linear)/angular; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=angular*t+linear; 
            String buff1="",buff2=""; 
            try{buff1=data.substring(0, image-1);}catch(Exception e){} 
            try{buff2=data.substring(image-1);}catch(Exception e){} 
            data=buff1+toInc.charAt(b)+buff2; 
            if(b+1<toInc.length())
                b++;
            else 
                break;
        }
        if(b<salt_hash_size-1){
            data=dataBKP;
            angular=1;
            linear=1;
            for(int i=17;i>=0;i--){ 
                data=data+toInc.charAt(i); 
            }     
        }
        return ((char)((linear-1)*16+(angular-1)))+data;
    }
    
    public static void checkStr(String str) throws DataException{
        if(Cript2010x8b.CHECKSTR){
            for(int i=0;i<str.length();i++)
                if(str.charAt(i)>255)
                    throw new DataException("str value >255");
                else if(str.charAt(i)<0)
                        throw new DataException("str value <0");
        }
    }
} 