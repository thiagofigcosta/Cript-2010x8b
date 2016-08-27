package UI;

import Exception.DataException;
import Exception.HashException;
import Exception.MD5Exception;
import java.util.Scanner;
import cript.*;
import java.io.IOException;

public class Terminal {
    public static void main(String[] args) throws IOException, HashException, DataException, MD5Exception { 
        Scanner scanner = new Scanner(System.in);    
        String path;
        String command;
        if(args.length>0){//comando
            command=args[0];
        }else{
            System.out.print("Digite o Comando: ");
            command=scanner.next();
        }
        if(args.length>1){//caminho
           path=args[1];
        }else{
            System.out.print("Digite o caminho do arquivo: ");
            path=scanner.next();
        }
        if(args.length>2){//hash
             new Cript2010x8b(command,path,args[2]);
        }else{
            System.out.print("Digite o caminho da chave: ");
            try{
            new Cript2010x8b(command,path,scanner.next());
            }catch(Exception e){
            new Cript2010x8b(command,path);
            }
        }
    }
}
