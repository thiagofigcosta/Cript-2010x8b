package cript;

import Exception.HashException;

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
    private int add=1;//valor a ser somado a cada grupode byte
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
    
    private String criptedSubHash="";
    private String decriptedSubHash="";

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
        return criptedSubHash;
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

    public String getCriptedSubHash() {
        return criptedSubHash;
    }

    public void setCriptedSubHash(String criptedSubHash) {
        this.criptedSubHash = criptedSubHash;
    }

    public String getDecriptedSubHash() {
        return decriptedSubHash;
    }

    public void setDecriptedSubHash(String decriptedSubHash) {
        this.decriptedSubHash = decriptedSubHash;
    }
    
    public Hash(String hash) throws HashException{
        if(hash.length()!=18) throw new HashException("Tamanho incorreto");
        for(int i=0;i<hash.length();i++){
            char current=hash.charAt(i);
            switch(i){
                case 0:
                    this.version=splitByte((int)current,0,3);
                    this.size+=splitByte((int)current,4,7);
                    break;
                case 1:
                    this.addH=splitByte((int)current,0,3)-splitByte((int)current,4,7);
                    break;
                case 2:
                    this.offsetH+=splitByte((int)current,4,7);
                    int typeOfOffset=(int)splitByte((int)current,0,3);
                    this.isBitOffsetH=typeOfOffset%2!=0;
                    if(typeOfOffset>8)
                        typeOfOffset*=-1;
                    if(typeOfOffset==8)this.offsetH=0;
                    break;
                case 3:
                    this.invertBitsH=splitByte((int)current,4,7)%2==0;
                    this.switchSignalH=splitByte((int)current,0,0)!=0;
                    this.switchTableH=(int)splitByte((int)current,1,3);
                    this.criptedSubHash=hash.substring(4);
                    decriptSubHash();
                    hash=hash.substring(0,3)+this.decriptedSubHash;
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
                    this.multiplesUsingAIOBG+=(int)splitByte((int)current,5,7);
                    break;
                case 10://passo 3
                    this.add+=(int)splitByte((int)current,1,7);
                    if(this.add==1){
                        this.addArraySize=true;
                    }else{
                        this.addArraySize=false;
                        if(splitByte((int)current,0,0)==1)
                            this.add*=-1;
                    }
                    break;
                case 11://passo 4
                    this.pow=(int)splitByte((int)current,0,2);
                    this.mult=(int)splitByte((int)current,3,7);
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
                    this.linearCoefficient=(int)splitByte((int)current,0,3);
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
                    if(splitByte((int)current,7,7)==1) this.steepOrder[5]+=4;
                    break;
                case 17:
                    if(splitByte((int)current,0,0)==1) this.steepOrder[5]+=2;
                    if(splitByte((int)current,1,1)==1) this.steepOrder[5]+=1;
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
    
    private void decriptSubHash(){
        criptedSubHash=Operations.switchTable(criptedSubHash, !switchSignalH, switchTableH);
        if(invertBitsH) criptedSubHash=Operations.invert(criptedSubHash);
        criptedSubHash=Operations.offset(criptedSubHash, isBitOffsetH, -offsetH);
        criptedSubHash=Operations.add(decriptedSubHash, size, -addH);//size deve ser 1?
    }
    
    private void criptSubHash(){
        criptedSubHash=Operations.add(decriptedSubHash, size, addH);//size deve ser 1?
        criptedSubHash=Operations.offset(criptedSubHash, isBitOffsetH, offsetH);
        if(invertBitsH) criptedSubHash=Operations.invert(criptedSubHash);
        criptedSubHash=Operations.switchTable(criptedSubHash, switchSignalH, switchTableH);
    }
    
    public static Hash genRandom(){//TODO
        Hash hash=new Hash();
        
        return hash;
    }
}
