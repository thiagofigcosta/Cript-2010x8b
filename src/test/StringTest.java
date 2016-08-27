package test; 
 
import Exception.DataException;
import Exception.HashException;
import Exception.MD5Exception;
import cript.*; 
import java.io.IOException;
 
public class StringTest { 

    public static void main(String[] args) throws HashException, IOException, DataException, MD5Exception { 
        int nCript=6;
//Hash hash=new Hash(size,invertBits,switchSignal,switchTable,idxAlternativeOfByteGroup,useAIOBG,multiplesUsingAIOBG,add,addArraySize,pow,mult,offset1,isBitOffset1,offset2,isBitOffset2,linearCoefficient,angularCoefficient,steepOrder); 
        System.out.print("-C==========================\n"); 
        Cript2010x8b C= new Cript2010x8b(nCript,true,null,"criptografia teste, Cript2010x8b",true); 
        //Cript2010x8b K=new Cript2010x8b(1,true,true,null,"cript.txt");
        System.out.println(C.getData());
        System.out.println("-D=========================="); 
        //Cript2010x8b E=new Cript2010x8b("1Di","cript-cripted.txt");
        Cript2010x8b D= new Cript2010x8b(nCript,false,null,C.getData(),true); 
        //if(!C.compareResult)System.out.println(D.getHash().showData());
        System.out.println(D.getData());
        System.out.println("============================"); 
    } 
} 
