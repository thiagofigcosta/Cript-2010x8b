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
        short size=8; 
        boolean invertBits=false; 
        boolean switchSignal=true; 
        int switchTable=2; 
        int idxAlternativeOfByteGroup[]={7,3,0,4,2,1,5,6}; 
        boolean useAIOBG=false; 
        int multiplesUsingAIOBG=2; 
        int add=2; 
        boolean addArraySize=false; 
        int pow=1; 
        int mult=1; 
        int offset1=3; 
        boolean isBitOffset1=false; 
        int offset2=6; 
        boolean isBitOffset2=false; 
        int linearCoefficient=1; 
        int angularCoefficient=2; 
        int steepOrder[]={3,6,2,1,5,4,7};
        Hash hash=new Hash(size,invertBits,switchSignal,switchTable,idxAlternativeOfByteGroup,useAIOBG,multiplesUsingAIOBG,add,addArraySize,pow,mult,offset1,isBitOffset1,offset2,isBitOffset2,linearCoefficient,angularCoefficient,steepOrder); 
         
        hash=Hash.genRandom(); 
        
        System.out.print("-C==========================\n"); 
        Cript2010x8b C= new Cript2010x8b(nCript,true,null,"criptografia teste, Cript2010x8b"); 
        System.out.println(C.getData());
        System.out.println("-D=========================="); 
        Cript2010x8b D= new Cript2010x8b(nCript,false,null,C.getData()); 
        //if(!C.compareResult)System.out.println(D.getHash().showData());
        System.out.println(D.getData()); 
        System.out.println("============================");         
        /*
        int nCript=2; 
        short size=2; 
        boolean invertBits=false; 
        boolean switchSignal=true; 
        int switchTable=2; 
        int idxAlternativeOfByteGroup[]={7,3,0,4,2,1,5,6}; 
        boolean useAIOBG=false; 
        int multiplesUsingAIOBG=2; 
        int add=30; 
        boolean addArraySize=false; 
        int pow=3; 
        int mult=1; 
        int offset1=3; 
        boolean isBitOffset1=true; 
        int offset2=6; 
        boolean isBitOffset2=true; 
        int linearCoefficient=2; 
        int angularCoefficient=3; 
        int steepOrder[]={3,6,2,1,5,4,7};
        
        
        int nCript=2; 
        short size=7; 
        boolean invertBits=true; 
        boolean switchSignal=false; 
        int switchTable=5; 
        int idxAlternativeOfByteGroup[]={5, 3, 4, 1, 0, 6, 7, 2}; 
        boolean useAIOBG=false; 
        int multiplesUsingAIOBG=2; 
        int add=-86; 
        boolean addArraySize=false; 
        int pow=1; 
        int mult=10; 
        int offset1=32; 
        boolean isBitOffset1=true; 
        int offset2=-53; 
        boolean isBitOffset2=false; 
        int linearCoefficient=10; 
        int angularCoefficient=7; 
        int steepOrder[]={1, 2, 5, 4, 3, 7, 6};
        
        */ 
         
    } 
} 
