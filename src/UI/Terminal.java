package UI;

import java.util.Scanner;

public class Terminal {
    public static void main(String[] args) { 
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
            System.out.print("Digite o Caminho de Trabalho: ");
            path=scanner.next();
        }
    }
}
