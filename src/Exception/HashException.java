package Exception;

import cript.Cript2010x8b;

public class HashException extends Exception{

    public HashException(String message) {
        super("HashException: "+message);
        if(Cript2010x8b.PRINTEXCEPTIONS)System.out.println("HashException: "+message);
    }
    
}
