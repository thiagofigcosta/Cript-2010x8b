package Exception;

import cript.Cript2010x8b;

public class MD5Exception extends Exception{

    public MD5Exception(String message) {
        super(message);
        if(Cript2010x8b.PRINTEXCEPTIONS)System.out.println("MD5Exception: "+message);
    }
    
}
