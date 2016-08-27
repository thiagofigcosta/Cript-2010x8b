package test; 
 
import Exception.HashException;
import cript.*; 
 
public class StringTest { 
     
    private static String decript(String data,int size, int add,boolean invert, boolean isBits, int offset, int table){ 
        data=Operations.switchTable(data, true, table); 
        if(invert) data=Operations.invert(data); 
        data=Operations.offset(data, isBits, -offset); 
        data=Operations.add(data, size, -add); 
        return data; 
    } 
     
    private static String cript(String data,int size, int add,boolean invert, boolean isBits, int offset, int table){ 
        data=Operations.add(data, size, add); 
        data=Operations.offset(data, isBits, offset); 
        if(invert) data=Operations.invert(data); 
        data=Operations.switchTable(data, false, table); 
        return data; 
    } 
     
     
    public static void main(String[] args) throws HashException { 
        int nCript=1; 
        
        short size=5; 
        boolean invertBits=true; 
        boolean switchSignal=true; 
        int switchTable=0; 
        int idxAlternativeOfByteGroup[]={1, 4, 0, 2, 5, 3, 6, 7}; 
        boolean useAIOBG=false; 
        int multiplesUsingAIOBG=6; 
        int add=39; 
        boolean addArraySize=false; 
        int pow=6; 
        int mult=0; 
        int offset1=-11; 
        boolean isBitOffset1=false; 
        int offset2=-48; 
        boolean isBitOffset2=false; 
        int linearCoefficient=1; 
        int angularCoefficient=13; 
        int steepOrder[]={2, 4, 5, 1, 6, 3, 7};
        Hash hash=new Hash(size,invertBits,switchSignal,switchTable,idxAlternativeOfByteGroup,useAIOBG,multiplesUsingAIOBG,add,addArraySize,pow,mult,offset1,isBitOffset1,offset2,isBitOffset2,linearCoefficient,angularCoefficient,steepOrder); 
        hash=Hash.genRandom();
        System.out.print("-C==========================\n"); 
        Cript2010x8b C= new Cript2010x8b(nCript,true,hash,"criptografia teste, Cript2010x8b"); 
        System.out.println(C.getData());
        System.out.println("-D=========================="); 
        Cript2010x8b D= new Cript2010x8b(nCript,false,hash,C.getData()); 
        if(!C.compareResult)System.out.println(D.getHash().showData());
        System.out.println(D.getData()); 
        System.out.println("============================"); 
    } 
} 
