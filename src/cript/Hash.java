package cript;

import Exception.HashException;

public class Hash {
    byte version=Cript2010x8b.VERSION;//versao do hash
    short size=1;//tamanho dos conjuntos de byte
    //criptografia do hash
    int addH=0;//valor a ser somado no hash
    int offsetH=1;//casas a serem deslocadas
    boolean isBitOffsetH=false;//deslocamento de bits ou bytes
    boolean invertBitsH=false;//se vai inverter o bits
    boolean switchSignalH=false;//sentido da tabela de equivalencia
    int switchTableH=0;//tabela de equivalencia
    //cripgrafia
    boolean invertBits=false;//se vai inverter o bits
    boolean switchSignal=false;//sentido da tabela de equivalencia
    int switchTable=0;//tabela de equivalencia
    int idxAlternativeOfByteGroup[]={0,1,2,3,4,5,6,7};//contem a ordem ALTERNATIVA dos bytes no conjunto
    boolean useAIOBG=false;//se vai usar a ordem alternativa dos grupos de byte
    int multiplesUsingAIOBG=1;//de quantos em quantos conjuntos de bytes usar a ordem alternativa
    int add=1;//valor a ser somado a cada grupode byte
    boolean addArraySize=false;//ao inves de somar a variavel add somar o array q contem os bytes do arquivo. SE FOR TRUE ESSA OPERECAO DEVE SER REALIZADA PRIMEIRO NA CRIPTOGRAFIA E POR ULTIMO NA DESCRIPTOGRAFIA INDEPENDENTE DE OUTROS PARAMETROS
    int pow=1;//potencia dos grupos de bytes. TESTAR, COMO O LIMITE É 255 O VALOR CORRESPONDENTE TEM Q SER ENTRE 0-255, O ALGORITMO DEVE LIDAR COM ISSO E HÁ RISCO DE CORROSAO SE N FOR EFICIENTE O ALGORITMO
    int mult=1;//valor a ser multiplicado. TESTAR, COMO O LIMITE É 255 O VALOR CORRESPONDENTE TEM Q SER ENTRE 0-255, O ALGORITMO DEVE LIDAR COM ISSO E HÁ RISCO DE CORROSAO SE N FOR EFICIENTE O ALGORITMO
    int offset1=1;//casas a serem deslocadas
    boolean isBitOffset1=false;//deslocamento de bits ou bytes
    int offset2=1;//casas a serem deslocadas
    boolean isBitOffset2=false;//deslocamento de bits ou bytes
    int linearCoefficient=1;//coeficiente linear da equacao do 1º grau que fala as posições dos numeros aleatorios. o valor max de y é o tamanho do array.(y=ax+b)
    int angularCoefficient=1;//coeficiente angular da equacao do 1º grau que fala as posições dos numeros aleatorios. o valor max de y é o tamanho do array.(y=ax+b)
    int steepOrder[]={1,2,3,4,5,6,7};//ordem da execucao dos passos de criptografia. ignorar ordem do passo 3 caso addArraySize==true
    
    String criptedSubHash="";
    String decriptedSubHash="";
    
    
    public Hash(){}
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
                    this.criptedSubHash=hash.substring(4, hash.length()-1);
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
        criptedSubHash=Operations.offset(criptedSubHash, invertBitsH, -offsetH);
        criptedSubHash=Operations.add(decriptedSubHash, size, -addH);//size deve ser 1?
    }
    
    private void criptSubHash(){
        criptedSubHash=Operations.add(decriptedSubHash, size, addH);//size deve ser 1?
        criptedSubHash=Operations.offset(criptedSubHash, invertBitsH, offsetH);
        if(invertBitsH) criptedSubHash=Operations.invert(criptedSubHash);
        criptedSubHash=Operations.switchTable(criptedSubHash, switchSignalH, switchTableH);
    }
}
