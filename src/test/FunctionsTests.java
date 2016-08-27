package test; 
import cript.*; 
import java.math.BigInteger; 
public class FunctionsTests { 
     
    static void a(){ 
        String str="aaaa"; 
        System.out.println(str); 
        str=Operations.replace(str, 0, 'b'); 
        System.out.println(str); 
    } 
     
    static void b(){ 
        String str="a"; 
        boolean x[]=Operations.toBits(str); 
        for(boolean y:x) 
            if(y) System.out.print("1"); 
            else System.out.print("0"); 
        System.out.println(); 
        System.out.println(Operations.fromBits(x)); 
        System.out.println(Operations.invert("a")); 
        System.out.println(Operations.invert(Operations.invert("a"))); 
    } 
     
    static void c(){ 
        String str="ab"; 
        boolean x[]=Operations.toBits(str); 
        for(boolean y:x) 
            if(y) System.out.print("1"); 
            else System.out.print("0"); 
        System.out.println(); 
        for(boolean y:x) 
            if(!y) System.out.print("1"); 
            else System.out.print("0"); 
        System.out.println(); 
        System.out.println(Operations.fromBits(x)); 
         
         System.out.println(Operations.invert("ab")); 
        System.out.println(Operations.invert(Operations.invert("ab"))); 
         
    } 
     
    static void d(){ 
        System.out.println((int)Tables.convert((char)14, true, 3)); 
        System.out.println((int)Tables.convert((char)Tables.convert((char)14, true, 3), false, 3)); 
        System.out.println(Operations.switchTable("alguma coisa mish, omeletão", true, 7)); 
        System.out.println(Operations.switchTable(Operations.switchTable("alguma coisa mish, omeletão", true, 7), false, 7)); 
    } 
     
    static void e(){ 
        System.out.println(Operations.offset("ola", true, -1)); 
        System.out.println(Operations.offset(Operations.offset("ola", true, -2), true, 2)); 
    } 
     
    static void f(){ 
        System.out.println(Operations.strToInt("B")); 
        System.out.println(Operations.intToStr(65, 2)); 
        System.out.println(Operations.strToInt("AA")); 
    } 
     
    static void g(){ 
        System.out.println(Operations.add("A", 1, 1)); 
        System.out.println(Operations.add("BD", 1, 2)); 
        System.out.println(Operations.add("AA", 2, 2)); 
        System.out.println(Operations.add("estou tendo problema com nao divisivei", 3, 20)); 
        System.out.println(Operations.add(Operations.add("estou tendo problema com nao divisivel", 3, 20), 3, -20)); 
    } 
     
    static void h(){ 
        System.out.println(Operations.strToBInt("A")); 
        System.out.println(Operations.BintToStr(BigInteger.valueOf(65), 1)); 
    } 
     
    static void i(){  
         int a=2; 
         int b=3; 
         int idxAlternativeOfByteGroup[]={7,3,0,4,2,1,5,6}; 
         String out="abcdefghijk"; 
 
        int maxValueOfX=(out.length()-b)/a; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=a*t+b; 
            String buff1="",buff2=""; 
            buff1=out.substring(0, image-1);//}catch(Exception e){} 
            System.out.println("    bff1-"+buff1); 
            buff2=out.substring(image-1);//}catch(Exception e){} 
            System.out.println("    bff2-"+buff2); 
            int max=255, min=0; 
            char random=(char) (min + (int)(Math.random() * ((max - min) + 1))); 
            out=buff1+random+buff2; 
        } 
        System.out.println(out); 
        maxValueOfX=(out.length()-b)/a; 
        int n=0; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=t*a+b; 
            image-=n; 
            n++; 
            String buff1="",buff2=""; 
            System.out.println("        out-"+out); 
            buff1=out.substring(0, image-1); 
            System.out.println("    bff1-"+buff1); 
            buff2=out.substring(image); 
            System.out.println("    bff2-"+buff2); 
            out=buff1+buff2; 
            maxValueOfX=(out.length()-b)/a; 
        } 
        System.out.println(out); 
    } 
     
    static void j(){  
        System.out.println(Hash.splitByte(128, 7, 7)); 
        try{Hash hash=new Hash("847YúghNÃiÔÄQ  ["); 
            System.out.println(hash.showData());}catch (Exception e){} 
    } 
     
    
    static void k(){
        
        int angular=2; 
        int linear=1; 
        String data="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        if(false)data="abcdef";
        String hashToInclude="123456789012345678"; 
 
        int b=0; 
        int maxValueOfX=(data.length()-linear)/angular; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=angular*t+linear; 
            String buff1="",buff2=""; 
            buff1=data.substring(0, image-1);//}catch(Exception e){} 
            buff2=data.substring(image-1);//}catch(Exception e){} 
            data=buff1+hashToInclude.charAt(b)+buff2; 
            if(b+1<hashToInclude.length())
                b++;
            else 
                break;
        } 
        for(int i=17;i>b;i--){ 
            data=data+hashToInclude.charAt(i); 
        }         
        data=((char)(linear*16+angular))+data;
        
        System.out.println(data);
        
        System.out.println("--------------------------------");
        
        angular=0;linear=0;
        angular=(int)Hash.splitByte((int)data.charAt(0),0,3); 
        linear=(int)Hash.splitByte((int)data.charAt(0),4,7); 
        System.out.println("angular: "+angular+" linar: "+linear);
        
        data=data.substring(1); 
        String findedHash=""; 
        maxValueOfX=(data.length()-linear)/angular; 
        int n=0; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=angular*t+linear; 
            image-=n;  
            String buff1="",buff2=""; 
            buff1=data.substring(0, image-1); 
            buff2=data.substring(image); 
            findedHash+=data.charAt(image-1); 
            data=buff1+buff2; 
            maxValueOfX=(data.length()-linear)/angular; 
            if(n<17)
                n++;
            else 
                break;
        } 
        
        System.out.println("n: "+n);
        
        while(n<17){ 
            findedHash+=data.charAt(data.length()-1); 
            data=data.substring(0, data.length()-2); 
            n++; 
        } 
        System.out.println("size: "+findedHash.length());
        if(findedHash.length()>18) System.out.println("Tamanho incorreto");  
        System.out.println(findedHash);
        System.out.println(data);
    }
    
    public static void main(String[] args) { 
        //TODO: 
        //      verificar criacao de Hash procedural(rand) 
        //      verificar compressao do hash 
        //      verificar importacao do hash via string 
        //      verficar pow, sqrt(assited test)
        k(); 
    }     
} 
