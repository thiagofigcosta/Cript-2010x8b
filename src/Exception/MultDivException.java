package Exception;

import cript.Cript2010x8b;

public class MultDivException extends Exception{

    public MultDivException(String message) {
        super("MultDivException: "+message);
        if(Cript2010x8b.PRINTEXCEPTIONS)System.out.println("MultDivException: "+message);
    }
    
}
