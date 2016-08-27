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
    public static void main(String[] args) {
        h();
    }    
}
