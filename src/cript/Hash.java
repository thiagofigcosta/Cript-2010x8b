package cript; 
 
import Exception.HashException; 
import java.util.Arrays; 
 
public class Hash { 
    private byte version=Cript2010x8b.VERSION;//versao do hash 
    private short size=1;//tamanho dos conjuntos de byte 
    //criptografia do hash 
    private int addH=0;//valor a ser somado no hash 
    private int offsetH=1;//casas a serem deslocadas 
    private boolean isBitOffsetH=false;//deslocamento de bits ou bytes 
    private boolean invertBitsH=false;//se vai inverter o bits 
    private boolean switchSignalH=false;//sentido da tabela de equivalencia 
    private int switchTableH=0;//tabela de equivalencia 
    //cripgrafia 
    private boolean invertBits=false;//se vai inverter o bits 
    private boolean switchSignal=false;//sentido da tabela de equivalencia 
    private int switchTable=0;//tabela de equivalencia 
    private int idxAlternativeOfByteGroup[]={0,1,2,3,4,5,6,7};//contem a ordem ALTERNATIVA dos bytes no conjunto 
    private boolean useAIOBG=false;//se vai usar a ordem alternativa dos grupos de byte 
    private int multiplesUsingAIOBG=1;//de quantos em quantos conjuntos de bytes usar a ordem alternativa 
    private int add=0;//valor a ser somado a cada grupode byte 
    private boolean addArraySize=false;//ao inves de somar a variavel add somar o array q contem os bytes do arquivo. SE FOR TRUE ESSA OPERECAO DEVE SER REALIZADA PRIMEIRO NA CRIPTOGRAFIA E POR ULTIMO NA DESCRIPTOGRAFIA INDEPENDENTE DE OUTROS PARAMETROS 
    private int pow=1;//potencia dos grupos de bytes. TESTAR, COMO O LIMITE É 255 O VALOR CORRESPONDENTE TEM Q SER ENTRE 0-255, O ALGORITMO DEVE LIDAR COM ISSO E HÁ RISCO DE CORROSAO SE N FOR EFICIENTE O ALGORITMO 
    private int mult=1;//valor a ser multiplicado. TESTAR, COMO O LIMITE É 255 O VALOR CORRESPONDENTE TEM Q SER ENTRE 0-255, O ALGORITMO DEVE LIDAR COM ISSO E HÁ RISCO DE CORROSAO SE N FOR EFICIENTE O ALGORITMO 
    private int offset1=1;//casas a serem deslocadas 
    private boolean isBitOffset1=false;//deslocamento de bits ou bytes 
    private int offset2=1;//casas a serem deslocadas 
    private boolean isBitOffset2=false;//deslocamento de bits ou bytes 
    private int linearCoefficient=1;//coeficiente linear da equacao do 1º grau que fala as posições dos numeros aleatorios. o valor max de y é o tamanho do array.(y=ax+b) 
    private int angularCoefficient=1;//coeficiente angular da equacao do 1º grau que fala as posições dos numeros aleatorios. o valor max de y é o tamanho do array.(y=ax+b) 
    private int steepOrder[]={1,2,3,4,5,6,7};//ordem da execucao dos passos de criptografia. ignorar ordem do passo 3 caso addArraySize==true 
 
    public Hash() { 
    } 
     
    public Hash(short size,boolean invertBits,boolean switchSignal,int switchTable,int idxAlternativeOfByteGroup[],boolean useAIOBG,int multiplesUsingAIOBG,int add,boolean addArraySize,int pow,int mult,int offset1,boolean isBitOffset1,int offset2,boolean isBitOffset2,int linearCoefficient,int angularCoefficient,int steepOrder[]){ 
        this.size=size; 
        this.invertBits=invertBits; 
        this.switchSignal=switchSignal; 
        this.switchTable=switchTable; 
        this.idxAlternativeOfByteGroup=idxAlternativeOfByteGroup; 
        this.useAIOBG=useAIOBG; 
        this.multiplesUsingAIOBG=multiplesUsingAIOBG; 
        this.add=add; 
        this.addArraySize=addArraySize; 
        this.pow=pow; 
        this.mult=mult; 
        this.offset1=offset1; 
        this.isBitOffset1=isBitOffset1; 
        this.offset2=offset2; 
        this.isBitOffset2=isBitOffset2; 
        this.linearCoefficient=linearCoefficient; 
        this.angularCoefficient=angularCoefficient; 
        this.steepOrder=steepOrder; 
    } 
     
    public String toString(){ 
        return compressHash(); 
    } 
     
    public String showData(){ 
        String about=""; 
        about+="\n----------HASH DATA------------\n"; 
        about+="***HASH***: "+this.compressHash()+"\n"; 
        about+="version: "+version+"\n"; 
        about+="size: "+size+"\n"; 
        about+="addH: "+addH+"\n"; 
        about+="offsetH: "+offsetH+"\n"; 
        about+="isBitOffsetH: "+isBitOffsetH+"\n"; 
        about+="invertBitsH: "+invertBitsH+"\n"; 
        about+="switchSignalH: "+switchSignalH+"\n"; 
        about+="switchTableH: "+switchTableH+"\n"; 
        about+="invertBits: "+invertBits+"\n"; 
        about+="switchSignal: "+switchSignal+"\n"; 
        about+="switchTable: "+switchTable+"\n"; 
        about+="idxAlternativeOfByteGroup: "+Arrays.toString(idxAlternativeOfByteGroup)+"\n"; 
        about+="useAIOBG: "+useAIOBG+"\n"; 
        about+="multiplesUsingAIOBG: "+multiplesUsingAIOBG+"\n"; 
        about+="add: "+add+"\n"; 
        about+="addArraySize: "+addArraySize+"\n"; 
        about+="pow: "+pow+"\n"; 
        about+="mult: "+mult+"\n"; 
        about+="offset1: "+offset1+"\n"; 
        about+="isBitOffset1: "+isBitOffset1+"\n"; 
        about+="offset2: "+offset2+"\n"; 
        about+="isBitOffset2: "+isBitOffset2+"\n"; 
        about+="linearCoefficient: "+linearCoefficient+"\n"; 
        about+="angularCoefficient: "+angularCoefficient+"\n"; 
        about+="steepOrder: "+Arrays.toString(steepOrder)+"\n"; 
        about+="-------------------------------\n"; 
        return about; 
    } 
 
    public short getSize() { 
        return size; 
    } 
 
    public void setSize(short size) { 
        this.size = size; 
    } 
 
    public int getAddH() { 
        return addH; 
    } 
 
    public void setAddH(int addH) { 
        this.addH = addH; 
    } 
 
    public int getOffsetH() { 
        return offsetH; 
    } 
 
    public void setOffsetH(int offsetH) { 
        this.offsetH = offsetH; 
    } 
 
    public boolean isIsBitOffsetH() { 
        return isBitOffsetH; 
    } 
 
    public void setIsBitOffsetH(boolean isBitOffsetH) { 
        this.isBitOffsetH = isBitOffsetH; 
    } 
 
    public byte getVersion() { 
        return version; 
    } 
 
    public boolean isInvertBitsH() { 
        return invertBitsH; 
    } 
 
    public void setInvertBitsH(boolean invertBitsH) { 
        this.invertBitsH = invertBitsH; 
    } 
 
    public boolean isSwitchSignalH() { 
        return switchSignalH; 
    } 
 
    public void setSwitchSignalH(boolean switchSignalH) { 
        this.switchSignalH = switchSignalH; 
    } 
 
    public int getSwitchTableH() { 
        return switchTableH; 
    } 
 
    public void setSwitchTableH(int switchTableH) { 
        this.switchTableH = switchTableH; 
    } 
 
    public boolean isInvertBits() { 
        return invertBits; 
    } 
 
    public void setInvertBits(boolean invertBits) { 
        this.invertBits = invertBits; 
    } 
 
    public boolean isSwitchSignal() { 
        return switchSignal; 
    } 
 
    public void setSwitchSignal(boolean switchSignal) { 
        this.switchSignal = switchSignal; 
    } 
 
    public int getSwitchTable() { 
        return switchTable; 
    } 
 
    public void setSwitchTable(int switchTable) { 
        this.switchTable = switchTable; 
    } 
 
    public int[] getIdxAlternativeOfByteGroup() { 
        return idxAlternativeOfByteGroup; 
    } 
 
    public void setIdxAlternativeOfByteGroup(int[] idxAlternativeOfByteGroup) { 
        this.idxAlternativeOfByteGroup = idxAlternativeOfByteGroup; 
    } 
 
    public boolean isUseAIOBG() { 
        return useAIOBG; 
    } 
 
    public void setUseAIOBG(boolean useAIOBG) { 
        this.useAIOBG = useAIOBG; 
    } 
 
    public int getMultiplesUsingAIOBG() { 
        return multiplesUsingAIOBG; 
    } 
 
    public void setMultiplesUsingAIOBG(int multiplesUsingAIOBG) { 
        this.multiplesUsingAIOBG = multiplesUsingAIOBG; 
    } 
 
    public int getAdd() { 
        return add; 
    } 
 
    public void setAdd(int add) { 
        this.add = add; 
    } 
 
    public boolean isAddArraySize() { 
        return addArraySize; 
    } 
 
    public void setAddArraySize(boolean addArraySize) { 
        this.addArraySize = addArraySize; 
    } 
 
    public int getPow() { 
        return pow; 
    } 
 
    public void setPow(int pow) { 
        this.pow = pow; 
    } 
 
    public int getMult() { 
        return mult; 
    } 
 
    public void setMult(int mult) { 
        this.mult = mult; 
    } 
 
    public int getOffset1() { 
        return offset1; 
    } 
 
    public void setOffset1(int offset1) { 
        this.offset1 = offset1; 
    } 
 
    public boolean isIsBitOffset1() { 
        return isBitOffset1; 
    } 
 
    public void setIsBitOffset1(boolean isBitOffset1) { 
        this.isBitOffset1 = isBitOffset1; 
    } 
 
    public int getOffset2() { 
        return offset2; 
    } 
 
    public void setOffset2(int offset2) { 
        this.offset2 = offset2; 
    } 
 
    public boolean isIsBitOffset2() { 
        return isBitOffset2; 
    } 
 
    public void setIsBitOffset2(boolean isBitOffset2) { 
        this.isBitOffset2 = isBitOffset2; 
    } 
 
    public int getLinearCoefficient() { 
        return linearCoefficient; 
    } 
 
    public void setLinearCoefficient(int linearCoefficient) { 
        this.linearCoefficient = linearCoefficient; 
    } 
 
    public int getAngularCoefficient() { 
        return angularCoefficient; 
    } 
 
    public void setAngularCoefficient(int angularCoefficient) { 
        this.angularCoefficient = angularCoefficient; 
    } 
 
    public int[] getSteepOrder() { 
        return steepOrder; 
    } 
 
    public void setSteepOrder(int[] steepOrder) { 
        this.steepOrder = steepOrder; 
    } 
     
    public Hash(String hash) throws HashException{ 
        if(hash.length()!=18) throw new HashException("Tamanho incorreto("+hash.length()+"):"+hash); 
        for(int i=0;i<hash.length();i++){ 
            char current=hash.charAt(i); 
            switch(i){ 
                case 0: 
                    this.version=splitByte((int)current,0,3); 
                    this.size=(short) (splitByte((int)current,4,7)+1); 
                    break; 
                case 1: 
                    this.addH=splitByte((int)current,0,3)-splitByte((int)current,4,7); 
                    break; 
                case 2: 
                    this.offsetH=splitByte((int)current,4,7)+1; 
                    int typeOfOffset=(int)splitByte((int)current,0,3); 
                    this.isBitOffsetH=typeOfOffset%2!=0; 
                    if(typeOfOffset>8) 
                        this.offsetH*=-1; 
                    if(typeOfOffset==8)this.offsetH=0; 
                    break; 
                case 3: 
                    this.invertBitsH=splitByte((int)current,4,7)%2==0; 
                    this.switchSignalH=splitByte((int)current,0,0)!=0; 
                    this.switchTableH=(int)splitByte((int)current,1,3);  
                    hash=hash.substring(0,4)+this.decriptSHash(hash).substring(4); 
                    break; 
                     
                case 4://passo 1 
                    this.invertBits=splitByte((int)current,0,3)%2==0; 
                    this.switchSignal=splitByte((int)current,4,4)!=0; 
                    this.switchTable=(int)splitByte((int)current,5,7); 
                    break; 
                case 5: 
                    this.idxAlternativeOfByteGroup[0]=(int)splitByte((int)current,0,3); 
                    this.idxAlternativeOfByteGroup[1]=(int)splitByte((int)current,4,7); 
                    break;   
                case 6: 
                    this.idxAlternativeOfByteGroup[2]=(int)splitByte((int)current,0,3); 
                    this.idxAlternativeOfByteGroup[3]=(int)splitByte((int)current,4,7); 
                    break;   
                case 7: 
                    this.idxAlternativeOfByteGroup[4]=(int)splitByte((int)current,0,3); 
                    this.idxAlternativeOfByteGroup[5]=(int)splitByte((int)current,4,7); 
                    break;   
                case 8: 
                    this.idxAlternativeOfByteGroup[6]=(int)splitByte((int)current,0,3); 
                    this.idxAlternativeOfByteGroup[7]=(int)splitByte((int)current,4,7); 
                    break;   
                case 9://passo 2 
                    this.useAIOBG=splitByte((int)current,4,4)==1; 
                    this.multiplesUsingAIOBG=(int)splitByte((int)current,5,7)+1; 
                    break; 
                case 10://passo 3 
                    this.add=(int)splitByte((int)current,1,7);
                    if(this.add==0){ 
                        this.addArraySize=true; 
                    }else{ 
                        this.addArraySize=false; 
                        if(splitByte((int)current,0,0)==1) 
                            this.add*=-1; 
                    } 
                    break; 
                case 11://passo 4 
                    this.pow=(int)splitByte((int)current,0,2)+1; 
                    this.mult=current/8+1;
                    if(this.mult>20)this.mult=1;
                    break; 
                case 12://passo 5 
                    this.isBitOffset1=splitByte((int)current,0,0)==1; 
                    this.offset1=(int)splitByte((int)current,2,7); 
                    if(splitByte((int)current,1,1)==1) 
                        this.offset1*=-1; 
                    break; 
                case 13://passo 6 
                    this.isBitOffset2=splitByte((int)current,0,0)==1; 
                    this.offset2=(int)splitByte((int)current,2,7); 
                    if(splitByte((int)current,1,1)==1) 
                        this.offset2*=-1; 
                    break; 
                case 14://passo 7 
                    this.linearCoefficient=(int)splitByte((int)current,0,3)+1; 
                    this.angularCoefficient=(int)splitByte((int)current,4,7); 
                    break; 
                     
                case 15: 
                    this.steepOrder[0]=(int)splitByte((int)current,0,2); 
                    this.steepOrder[1]=(int)splitByte((int)current,3,5); 
                    this.steepOrder[2]=0; 
                    if(splitByte((int)current,6,6)==1) this.steepOrder[2]+=4; 
                    if(splitByte((int)current,7,7)==1) this.steepOrder[2]+=2; 
                    break; 
                case 16: 
                    if(splitByte((int)current,0,0)==1) this.steepOrder[2]+=1; 
                    this.steepOrder[3]=(int)splitByte((int)current,1,3); 
                    this.steepOrder[4]=(int)splitByte((int)current,4,6);
                    this.steepOrder[5]=0; 
                    if(splitByte((int)current,7,7)==1) this.steepOrder[5]+=4; 
                    break; 
                case 17: 
                    if(splitByte((int)current,0,0)==1) this.steepOrder[5]+=1; 
                    if(splitByte((int)current,1,1)==1) this.steepOrder[5]+=2; 
                    this.steepOrder[6]=(int)splitByte((int)current,2,4); 
                    break; 
            } 
        }      
    }
     
    /** 
     * @param current byte a ser separado 
     * @param start index inicial 
     * @param end index final 
     * @return byte "cortado" 
     */ 
    public static byte splitByte(int current,int start,int end){ 
        byte out=0; 
        int bits[]={0,0,0,0,0,0,0,0}; 
        for(int i=7;i>=0;i--){ 
            if(current>=Math.pow(2, i)){ 
                bits[i]=1; 
                current-=Math.pow(2, i); 
            } 
        } 
        int init=0; 
        for(int i=0;i<=end-start;i++){ 
            out+=bits[start+i]*Math.pow(2, init); 
            init++; 
        } 
        return out; 
    }  
    
    /**
     *
     * @param hash hash to be cripted
     * @return hash cripted
     */
    public static String criptHash(String hash){
        if(Cript2010x8b.CRIPTHASH){
            Hash h=null;
            try{h=new Hash(hash);}catch(Exception e){}
            return criptHash(hash,h.size,h.addH,h.isBitOffsetH,h.offsetH,h.invertBitsH,h.switchSignalH,h.switchTableH);
        }else
            return hash;
    }
    
    /**
     *
     * @param hash hash to be decripted
     * @return hash decripted
     */
    public static String decriptHash(String hash){
        if(Cript2010x8b.CRIPTHASH){
            Hash h=null;
            try{h=new Hash(hash);}catch(Exception e){}
            return decriptHash(hash,h.size,h.addH,h.isBitOffsetH,h.offsetH,h.invertBitsH,h.switchSignalH,h.switchTableH);
        }else
            return hash;
    }
    
    /**
     *
     * @param hash hash to be cripted
     * @return hash cripted
     */
    public String criptSHash(String hash){
        if(Cript2010x8b.CRIPTHASH){
            Hash h=this;
            return criptHash(hash,h.size,h.addH,h.isBitOffsetH,h.offsetH,h.invertBitsH,h.switchSignalH,h.switchTableH);
        }else
            return hash;
    }
    
    /**
     *
     * @param hash hash to be decripted
     * @return hash decripted
     */public String decriptSHash(String hash){
        if(Cript2010x8b.CRIPTHASH){
            Hash h=this;
            return decriptHash(hash,h.size,h.addH,h.isBitOffsetH,h.offsetH,h.invertBitsH,h.switchSignalH,h.switchTableH);
        }else
            return hash;
    }
    
    /**
    *
    * @param hash hash to be cripted
    * @return hash cripted
    */
    public static String criptHash(String hash,int size, int add, boolean isBitOffset, int offset,boolean invertBits, boolean switchSignal, int switchTable){
        if(Cript2010x8b.CRIPTHASH){
            String sub=hash.substring(4);
            sub=Operations.add(sub, size, add); 
            sub=Operations.offset(sub, isBitOffset, offset); 
            if(invertBits) sub=Operations.invert(sub); 
            sub=Operations.switchTable(sub, switchSignal, switchTable); 
            return hash.substring(0, 4)+sub;
        }else
            return hash;
    }
    
    /**
     *
     * @param hash hash to be cripted
     * @return hash cripted
     */
    public static String criptHash(Hash hash){
        return criptHash(hash.compressHash());
    }
    
    /**
     *
     * @param hash hash to be decripted
     * @return hash decripted
     */
    public static String decriptHash(String hash,int size, int add, boolean isBitOffset, int offset,boolean invertBits, boolean switchSignal, int switchTable){
        if(Cript2010x8b.CRIPTHASH){
            String sub=hash.substring(4);
            sub=Operations.switchTable(sub, !switchSignal, switchTable);
            sub=Operations.offset(sub, isBitOffset, -offset); 
            if(invertBits) sub=Operations.invert(sub); 
            sub=Operations.add(sub, size, -add); 
            return hash.substring(0, 4)+sub;
        }else
            return hash;
    }
    
    /**
     *
     * @param hash hash to be decripted
     * @return hash decripted
     */
    public static String decriptHash(Hash hash){
        return decriptHash(hash.compressHash());
    }
    
    /**
     *
     * @param data string to include hash
     * @param hash hash to be included
     * @return data with the hash mixed in it
     */
    public static String includeHash(String data, Hash hash){
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
        return ((char)((linear-1)*16+(angular-1)))+data;
    }
    
    /**
     *
     * @param data string to search for a hash
     * @return a struct with the hash and the string whiout hash
     * @throws HashException
     */
    public static ExportedHashData searchHash(String data) throws HashException{
        int angular=(int)splitByte((int)data.charAt(0),0,3)+1;
        int linear=(int)splitByte((int)data.charAt(0),4,7)+1; 
        data=data.substring(1);
        StringBuilder findedHash=new StringBuilder(); 
        int maxValueOfX=(data.length()-linear)/angular; 
        int n=0; 
        if(!(linear==1&&angular==1)){
            for(int t=0;t<=maxValueOfX;t++){ 
                int image=angular*t+linear; 
                image-=n;  
                String buff1="",buff2=""; 
                try{buff1=data.substring(0, image-1);}catch(Exception e){}
                try{buff2=data.substring(image);}catch(Exception e){} 
                try{findedHash.append(data.charAt(image-1));}catch(Exception e){} 
                data=buff1+buff2; 
                maxValueOfX=(data.length()-linear)/angular; 
                if(n<17)
                    n++;
                else 
                    break;
            } 
        }else{
            while(n<18){ 
                try{findedHash.append(data.charAt(data.length()-1));}catch(Exception e){}
                try{data=data.substring(0, data.length()-1);}catch(Exception e){}
                n++; 
            }
        }
        if(findedHash.length()!=18) throw new HashException("Tamanho incorreto("+findedHash.length()+")");
        Hash sHash=new Hash(findedHash.toString());     
        return   new ExportedHashData(data,sHash);
    }
     
    /**
     *
     * @return a random hash
     */
    public static Hash genRandom(){ 
        int max, min; 
        Hash hash=new Hash(); 
        hash.version=Cript2010x8b.VERSION; 
        max=8;min=1; 
        hash.size=(short)(min+(int)(Math.random()*((max-min)+1))); 
        max=15;min=0; 
        hash.addH=(min+(int)(Math.random()*((max-min)+1)))-(min+(int)(Math.random()*((max-min)+1))); 
        hash.offsetH=(min+(int)(Math.random()*((max-min)+1)))+1; 
        max=63;min=0; 
        hash.isBitOffsetH=(min+(int)(Math.random()*((max-min)+1)))==1; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offsetH*=-1; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offsetH=0; 
        hash.invertBitsH=(min+(int)(Math.random()*((max-min)+1)))==1; 
        hash.switchSignalH=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=7;min=0; 
        hash.switchTableH=(min+(int)(Math.random()*((max-min)+1))); 
         
        max=1;min=0; 
        hash.invertBits=(min+(int)(Math.random()*((max-min)+1)))==1; 
        hash.switchSignal=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=7;min=0; 
        hash.switchTable=(min+(int)(Math.random()*((max-min)+1)));
        hash.idxAlternativeOfByteGroup=Operations.sort(hash.idxAlternativeOfByteGroup); 
        max=1;min=0; 
        hash.useAIOBG=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=8;min=1; 
        hash.multiplesUsingAIOBG=(min+(int)(Math.random()*((max-min)+1))); 
        max=127;min=0; 
        hash.add=(min+(int)(Math.random()*((max-min)+1))); 
        max=18;min=1;
        hash.addArraySize=hash.add==0; 
        if((min+(int)(Math.random()*((max-min)+1)))==1){
            hash.addArraySize=true;
            hash.add=0;
        }
        max=1;min=0; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.add*=-1;
        max=8;min=1; 
        hash.pow=(min+(int)(Math.random()*((max-min)+1))); 
        max=20;min=1; 
        hash.mult=(min+(int)(Math.random()*((max-min)+1)));
        max=1;min=0; 
        hash.isBitOffset1=(min+(int)(Math.random()*((max-min)+1)))==1; 
        hash.isBitOffset2=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=63;min=0; //max63
        hash.offset1=(min+(int)(Math.random()*((max-min)+1))); 
        hash.offset2=(min+(int)(Math.random()*((max-min)+1))); 
        max=1;min=0; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offset1*=-1; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offset2*=-1; 
        max=16;min=1; 
        hash.linearCoefficient=(min+(int)(Math.random()*((max-min)+1))); 
        max=15;min=0; 
        hash.angularCoefficient=(min+(int)(Math.random()*((max-min)+1))); 
        hash.steepOrder=Operations.sort(hash.steepOrder); 
        
        Hash scnd=Hash.makeCompact(hash.compactHash());
        if(Hash.checkHashIntegrity(hash.compressHash(), scnd.compressHash()))
            return hash; 
        else
            return genRandom();
    } 
    
    public static Hash genWOincludeBytes(){ 
        int max, min; 
        Hash hash=new Hash(); 
        hash.version=Cript2010x8b.VERSION; 
        max=8;min=1; 
        hash.size=(short)(min+(int)(Math.random()*((max-min)+1))); 
        max=15;min=0; 
        hash.addH=(min+(int)(Math.random()*((max-min)+1)))-(min+(int)(Math.random()*((max-min)+1))); 
        hash.offsetH=(min+(int)(Math.random()*((max-min)+1)))+1; 
        max=63;min=0; 
        hash.isBitOffsetH=(min+(int)(Math.random()*((max-min)+1)))==1; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offsetH*=-1; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offsetH=0; 
        hash.invertBitsH=(min+(int)(Math.random()*((max-min)+1)))==1; 
        hash.switchSignalH=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=7;min=0; 
        hash.switchTableH=(min+(int)(Math.random()*((max-min)+1))); 
         
        max=1;min=0; 
        hash.invertBits=(min+(int)(Math.random()*((max-min)+1)))==1; 
        hash.switchSignal=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=7;min=0; 
        hash.switchTable=(min+(int)(Math.random()*((max-min)+1)));
        hash.idxAlternativeOfByteGroup=Operations.sort(hash.idxAlternativeOfByteGroup); 
        max=1;min=0; 
        hash.useAIOBG=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=8;min=1; 
        hash.multiplesUsingAIOBG=(min+(int)(Math.random()*((max-min)+1))); 
        max=127;min=0; 
        hash.add=(min+(int)(Math.random()*((max-min)+1))); 
        max=18;min=1;
        hash.addArraySize=hash.add==0; 
        if((min+(int)(Math.random()*((max-min)+1)))==1){
            hash.addArraySize=true;
            hash.add=0;
        }
        max=1;min=0; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.add*=-1;
        max=8;min=1; 
        hash.pow=(min+(int)(Math.random()*((max-min)+1))); 
        max=20;min=1; 
        hash.mult=(min+(int)(Math.random()*((max-min)+1)));
        max=1;min=0; 
        hash.isBitOffset1=(min+(int)(Math.random()*((max-min)+1)))==1; 
        hash.isBitOffset2=(min+(int)(Math.random()*((max-min)+1)))==1; 
        max=63;min=0; //max63
        hash.offset1=(min+(int)(Math.random()*((max-min)+1))); 
        hash.offset2=(min+(int)(Math.random()*((max-min)+1))); 
        max=1;min=0; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offset1*=-1; 
        if((min+(int)(Math.random()*((max-min)+1)))==1)hash.offset2*=-1; 
        max=16;min=1; 
        hash.linearCoefficient=(min+(int)(Math.random()*((max-min)+1))); 
        max=15;min=0; 
        hash.angularCoefficient=0; 
        hash.steepOrder=Operations.sort(hash.steepOrder); 
        
        Hash scnd=Hash.makeCompact(hash.compactHash());
        if(Hash.checkHashIntegrity(hash.compressHash(), scnd.compressHash()))
            return hash; 
        else
            return genWOincludeBytes();
    } 
    
    public String compactHash(){
        StringBuilder compact=new StringBuilder();
        compact.append(size+"@");
        compact.append(addH+"@");
        compact.append(offsetH+"@");
        compact.append(isBitOffsetH+"@");
        compact.append(invertBitsH+"@");
        compact.append(switchSignalH+"@");
        compact.append(switchTableH+"@");
        compact.append(invertBits+"@");
        compact.append(switchSignal+"@");
        compact.append(switchTable+"@");
        compact.append(""+idxAlternativeOfByteGroup[0]+idxAlternativeOfByteGroup[1]+idxAlternativeOfByteGroup[2]+idxAlternativeOfByteGroup[3]+idxAlternativeOfByteGroup[4]+idxAlternativeOfByteGroup[5]+idxAlternativeOfByteGroup[6]+idxAlternativeOfByteGroup[7]+"@");
        compact.append(useAIOBG+"@");
        compact.append(multiplesUsingAIOBG+"@");
        compact.append(add+"@");
        compact.append(addArraySize+"@");
        compact.append(pow+"@");
        compact.append(mult+"@");
        compact.append(offset1+"@");
        compact.append(isBitOffset1+"@");
        compact.append(offset2+"@");
        compact.append(isBitOffset2+"@");
        compact.append(linearCoefficient+"@");
        compact.append(angularCoefficient+"@");
        compact.append(""+steepOrder[0]+steepOrder[1]+steepOrder[2]+steepOrder[3]+steepOrder[4]+steepOrder[5]+steepOrder[6]);
        return compact.toString();
    }
     
    public static Hash makeCompact(String compact){
        Hash out=new Hash();
        String tmp[]=compact.split("@");
        out.size= (short) Integer.parseInt(tmp[0]);
        out.addH=Integer.parseInt(tmp[1]);
        out.offsetH=Integer.parseInt(tmp[2]);
        out.isBitOffsetH=Boolean.parseBoolean(tmp[3]);
        out.invertBitsH=Boolean.parseBoolean(tmp[4]);
        out.switchSignalH=Boolean.parseBoolean(tmp[5]);
        out.switchTableH=Integer.parseInt(tmp[6]);
        out.invertBits=Boolean.parseBoolean(tmp[7]);
        out.switchSignal=Boolean.parseBoolean(tmp[8]);
        out.switchTable=Integer.parseInt(tmp[9]);
        out.idxAlternativeOfByteGroup[0]=Integer.parseInt(tmp[10].charAt(0)+"");
        out.idxAlternativeOfByteGroup[1]=Integer.parseInt(tmp[10].charAt(1)+"");
        out.idxAlternativeOfByteGroup[2]=Integer.parseInt(tmp[10].charAt(2)+"");
        out.idxAlternativeOfByteGroup[3]=Integer.parseInt(tmp[10].charAt(3)+"");
        out.idxAlternativeOfByteGroup[4]=Integer.parseInt(tmp[10].charAt(4)+"");
        out.idxAlternativeOfByteGroup[5]=Integer.parseInt(tmp[10].charAt(5)+"");
        out.idxAlternativeOfByteGroup[6]=Integer.parseInt(tmp[10].charAt(6)+"");
        out.idxAlternativeOfByteGroup[7]=Integer.parseInt(tmp[10].charAt(7)+"");
        out.useAIOBG=Boolean.parseBoolean(tmp[11]);
        out.multiplesUsingAIOBG=Integer.parseInt(tmp[12]);
        out.add=Integer.parseInt(tmp[13]);
        out.addArraySize=Boolean.parseBoolean(tmp[14]);
        out.pow=Integer.parseInt(tmp[15]);
        out.mult=Integer.parseInt(tmp[16]);
        out.offset1=Integer.parseInt(tmp[17]);
        out.isBitOffset1=Boolean.parseBoolean(tmp[18]);
        out.offset2=Integer.parseInt(tmp[19]);
        out.isBitOffset2=Boolean.parseBoolean(tmp[20]);
        out.linearCoefficient=Integer.parseInt(tmp[21]);
        out.angularCoefficient=Integer.parseInt(tmp[22]);
        out.steepOrder[0]=Integer.parseInt(tmp[23].charAt(0)+"");
        out.steepOrder[1]=Integer.parseInt(tmp[23].charAt(1)+"");
        out.steepOrder[2]=Integer.parseInt(tmp[23].charAt(2)+"");
        out.steepOrder[3]=Integer.parseInt(tmp[23].charAt(3)+"");
        out.steepOrder[4]=Integer.parseInt(tmp[23].charAt(4)+"");
        out.steepOrder[5]=Integer.parseInt(tmp[23].charAt(5)+"");
        out.steepOrder[6]=Integer.parseInt(tmp[23].charAt(6)+"");
        return out;
    }
    
    /**
     *
     * @return the compressed string of the hash
     */
    public String compressHash(){ 
        String hash=""; 
        char zero=(char) (this.version+(this.size-1)*16); 
        char one; 
        if(this.addH>0){ 
            one=(char) this.addH; 
            if(((int)(Math.random()*((1)+1)))==1) 
                    one=(char) (15+16*(15-this.addH)); 
        }else{ 
            one=(char) (this.addH*-16); 
            if(((int)(Math.random()*((1)+1)))==1) 
                    one=(char) (16*15+(15+this.addH)); 
        } 
        char two; 
        if(this.offsetH==1||this.offsetH==-1){ 
            two=(char) (8+16*((int)(Math.random()*((15)+1)))); 
        }else{ 
            two=(char) ((Math.abs(this.offsetH)-1)*16); 
            int n=((int)(Math.random()*((3)+1))); 
            if(this.isBitOffsetH) 
                n=n*2+1; 
            else 
                n=n*2; 
            if(this.offsetH<0){
                n+=8; 
                if(n==8)n+=2; 
            }
            two+=n; 
        } 
        char three=(char) ((int)(Math.random()*((7)+1))); 
        if(this.invertBitsH) 
            three*=32; 
        else 
            three=(char) ((three*2+1)*16); 
        three+=this.switchTableH*2; 
        if(this.switchSignalH)three+=1; 
         
         
        char four=(char) ((int)(Math.random()*((7)+1))); 
        if(this.invertBits) 
            four*=2; 
        else 
            four=(char) (four*2+1); 
        if(this.switchSignal)four=(char) (four+16); 
        four=(char) (four+switchTable*32); 
        char five =(char) (this.idxAlternativeOfByteGroup[0]+this.idxAlternativeOfByteGroup[1]*16); 
        char six  =(char) (this.idxAlternativeOfByteGroup[2]+this.idxAlternativeOfByteGroup[3]*16); 
        char seven=(char) (this.idxAlternativeOfByteGroup[4]+this.idxAlternativeOfByteGroup[5]*16); 
        char eight=(char) (this.idxAlternativeOfByteGroup[6]+this.idxAlternativeOfByteGroup[7]*16); 
        char nine=(char) ((int)(Math.random()*((15)+1))); 
        if(this.useAIOBG)nine+=16; 
        nine+=(this.multiplesUsingAIOBG-1)*32; 
        char ten; 
        if(this.addArraySize) 
            ten=(char) (int)(Math.random()*2); //0x1
        else{ 
            ten=(char) ((Math.abs(this.add))*2); 
            if(this.add<0)ten++; 
        } 
        char eleven=(char) ((this.pow-1)+(this.mult-1)*8); 
        char twelve=(char) (Math.abs(this.offset1)*4); 
        if(this.offset1<0)twelve+=2; 
        if(this.isBitOffset1)twelve++; 
        char thirteen=(char) (Math.abs(this.offset2)*4); 
        if(this.offset2<0)thirteen+=2; 
        if(this.isBitOffset2)thirteen++; 
        char fourteen=(char) (this.angularCoefficient*16+(this.linearCoefficient-1)); 
        char fifteen=(char) (this.steepOrder[0]+this.steepOrder[1]*8); 
        if(splitByte(steepOrder[2], 2,2)==1){fifteen+=64;} 
        if(splitByte(steepOrder[2], 1,1)==1){fifteen+=128;} 
        char sixteen=0; 
        if(splitByte(steepOrder[2], 0,0)==1){sixteen+=1;}      
        if(splitByte(steepOrder[5], 2,2)==1){sixteen+=128;}
        sixteen+=(this.steepOrder[3]*2)+(this.steepOrder[4]*16);
        char seventeen=0; 
        if(splitByte(steepOrder[5], 1,1)==1){seventeen+=2;} 
        if(splitByte(steepOrder[5], 0,0)==1){seventeen+=1;}
        seventeen+=(this.steepOrder[6]*4);
        if(((int)(Math.random()*((1)+1)))==1)seventeen+=32; 
        if(((int)(Math.random()*((1)+1)))==1)seventeen+=64; 
        if(((int)(Math.random()*((1)+1)))==1)seventeen+=128; 
        hash=""+zero+one+two+three+four+five+six+seven+eight+nine+ten+eleven+twelve+thirteen+fourteen+fifteen+sixteen+seventeen; 
        if(!Cript2010x8b.CRIPTHASH)return hash;
        hash=hash.substring(0, 4)+this.criptSHash(hash).substring(4); 
        return hash; 
    } 
    
    /**
     *
     * @param a the first hash compressed
     * @param b the second hash compressed
     * @return true if the two compressed are the same else return false
     */
    public static boolean checkHashIntegrity(String a, String b){
        a=Hash.decriptHash(a);
        b=Hash.decriptHash(b);
        for(int i=0;i<Math.min(a.length(), b.length());i++)
            if(!hashIntegrityBySteeps(a.charAt(i),b.charAt(i),i))return false;
        return true;
    }
    
    /**
     *
     * @param a the first byte
     * @param b the second byte
     * @param steep steep of the byte
     * @return true if the the bytes are equivalent else return false
     */
    public static boolean hashIntegrityBySteeps(char a, char b, int steep){       
        switch(steep){
            default: return a==b;
            case 1: return (splitByte((int)a, 0, 3)-splitByte((int)a, 4, 7))==(splitByte((int)b, 0, 3)-splitByte((int)b, 4, 7));
            case 2: return (splitByte((int)a, 0, 3)==8&&splitByte((int)b, 0, 3)==8)  ||  ((splitByte((int)a, 4, 7)==splitByte((int)b, 4, 7)) && ((splitByte((int)a, 0, 3)>8&&(splitByte((int)b, 0, 3)>8)) || (splitByte((int)a, 0, 3)<8&&(splitByte((int)b, 0, 3)<8))));
            case 3: return ((splitByte((int)a, 4, 7)%2==0&&splitByte((int)b, 4, 7)%2==0)||(splitByte((int)a, 4, 7)%2!=0&&splitByte((int)b, 4, 7)%2!=0)) && (splitByte((int)a, 0, 0)==splitByte((int)b, 0, 0)) && (splitByte((int)a, 1, 3)==splitByte((int)b, 1, 3));
            case 4: return ((splitByte((int)a, 0, 3)%2==0&&splitByte((int)b, 0, 3)%2==0)||(splitByte((int)a, 0, 3)%2!=0&&splitByte((int)b, 0, 3)%2!=0)) && (splitByte((int)a, 4, 4)==splitByte((int)b, 4, 4)) && (splitByte((int)a, 5, 7)==splitByte((int)b, 5, 7));
            case 5: return a==b;
            case 6: return a==b;
            case 7: return a==b;
            case 8: return a==b;
            case 9: return (splitByte((int)a, 4, 4)==splitByte((int)b, 4, 4))&&(splitByte((int)a, 5, 7)==splitByte((int)b, 5, 7));
            case 10: return a==b;
            case 11: return a==b;
            case 12: return a==b;
            case 13: return a==b;
            case 14: return a==b;
            case 15: return a==b;
            case 16: return a==b;
            case 17: return splitByte((int)a, 0, 4)==splitByte((int)b, 0, 4);
        }
    }
} 
