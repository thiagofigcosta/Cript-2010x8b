package Exception;

import cript.Cript2010x8b;

public class DataException extends Exception{

    public DataException(String message) {
        super("DataException: "+message);
        if(Cript2010x8b.PRINTEXCEPTIONS)System.out.println("DataException: "+message);
    }
    
}
