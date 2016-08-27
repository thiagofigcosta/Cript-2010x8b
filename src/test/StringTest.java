package test; 
 
import Exception.HashException;
import cript.*; 
 
public class StringTest { 

    public static void main(String[] args) throws HashException { 
        int nCript=3; 
        
        short size=1; 
        boolean invertBits=false; 
        boolean switchSignal=false; 
        int switchTable=2; 
        int idxAlternativeOfByteGroup[]={7, 5, 2, 1, 3, 6, 4, 0}; 
        boolean useAIOBG=false; 
        int multiplesUsingAIOBG=4; 
        int add=107; 
        boolean addArraySize=false; 
        int pow=2; 
        int mult=0; 
        int offset1=36; 
        boolean isBitOffset1=false; 
        int offset2=-59; 
        boolean isBitOffset2=true; 
        int linearCoefficient=13; 
        int angularCoefficient=6; 
        int steepOrder[]={5, 2, 4, 3, 6, 1, 7};
        Hash hash=new Hash(size,invertBits,switchSignal,switchTable,idxAlternativeOfByteGroup,useAIOBG,multiplesUsingAIOBG,add,addArraySize,pow,mult,offset1,isBitOffset1,offset2,isBitOffset2,linearCoefficient,angularCoefficient,steepOrder); 
        System.out.print("-C==========================\n"); 
        Cript2010x8b C= new Cript2010x8b(nCript,true,null,"criptografia teste, Cript2010x8b"); 
        System.out.println(C.getData());
        System.out.println("-D=========================="); 
        Cript2010x8b D= new Cript2010x8b(nCript,false,null,C.getData()); 
        if(!C.compareResult)System.out.println(D.getHash().showData());
        System.out.println(D.getData()); 
        System.out.println("============================"); 
    } 
} 
