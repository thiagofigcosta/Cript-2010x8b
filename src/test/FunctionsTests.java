package test; 
import Exception.DataException;
import Exception.HashException;
import cript.*; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
        if(true)data="abcde";
        String hashToInclude="123456789012345678"; 
 
        int b=0; 
        int maxValueOfX=(data.length()-linear)/angular; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=angular*t+linear; 
            String buff1="",buff2=""; 
            try{buff1=data.substring(0, image-1);}catch(Exception e){} 
            try{buff2=data.substring(image-1);}catch(Exception e){} 
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
            try{buff1=data.substring(0, image-1); }catch(Exception e){} 
            try{buff2=data.substring(image); }catch(Exception e){} 
            try{findedHash+=data.charAt(image-1); }catch(Exception e){} 
            data=buff1+buff2; 
            maxValueOfX=(data.length()-linear)/angular; 
            if(n<17)
                n++;
            else 
                break;
        } 
        
        System.out.println("n: "+n);
        
        while(n<17){ 
            try{findedHash+=data.charAt(data.length()-1);}catch(Exception e){}
            try{data=data.substring(0, data.length()-2);}catch(Exception e){}
            n++; 
        } 
        System.out.println("size: "+findedHash.length());
        if(findedHash.length()>18) System.out.println("Tamanho incorreto");  
        System.out.println(findedHash);
        System.out.println(data);
    }
    
    static void l(){
        int angular=2; 
        int linear=1; 
        String data="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        if(true)data="abcde";
        String hashToInclude="123456789012345678"; 
        
        if(angular==1&&linear==1)linear++;
        String dataBKP=data;
        int b=0; 
        int maxValueOfX=(data.length()-linear)/angular; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=angular*t+linear; 
            String buff1="",buff2=""; 
            try{buff1=data.substring(0, image-1);}catch(Exception e){} 
            try{buff2=data.substring(image-1);}catch(Exception e){} 
            data=buff1+hashToInclude.charAt(b)+buff2; 
            if(b+1<hashToInclude.length())
                b++;
            else 
                break;
        }
        if(b<17){
            data=dataBKP;
            angular=1;
            linear=1;
            for(int i=17;i>=0;i--){ 
                data=data+hashToInclude.charAt(i); 
            }     
        }    
        data=((char)((linear-1)*16+(angular-1)))+data;
        System.out.println("hash incluso:"+hashToInclude);
        System.out.println("data+hash:"+data);
        System.out.println("angular:"+angular+" linear:"+linear);
        
        angular=0;linear=0;
        
        angular=(int)Hash.splitByte((int)data.charAt(0),0,3)+1; 
        linear=(int)Hash.splitByte((int)data.charAt(0),4,7)+1;
        System.out.println("angular:"+angular+" linear:"+linear);
        data=data.substring(1); 
        String findedHash=""; 
        maxValueOfX=(data.length()-linear)/angular; 
        int n=0; 
        if(!(linear==1&&angular==1)){
            for(int t=0;t<=maxValueOfX;t++){ 
                int image=angular*t+linear; 
                image-=n;  
                String buff1="",buff2=""; 
                try{buff1=data.substring(0, image-1);}catch(Exception e){}
                try{buff2=data.substring(image);}catch(Exception e){} 
                try{findedHash+=data.charAt(image-1);}catch(Exception e){} 
                data=buff1+buff2; 
                maxValueOfX=(data.length()-linear)/angular; 
                if(n<17)
                    n++;
                else 
                    break;
            } 
        }else{
            while(n<18){ 
                try{findedHash+=data.charAt(data.length()-1);}catch(Exception e){}
                try{data=data.substring(0, data.length()-1);}catch(Exception e){}
                System.out.println("        hash:"+findedHash);
                System.out.println("        data:"+data);
                n++; 
            }
        }
        if(findedHash.length()>18) System.out.println("Tamanho incorreto("+findedHash.length()+"):"+findedHash);
        System.out.println("hash achado:"+findedHash);
        System.out.println("data limpa:"+data);
    }
    
    static void m(){
        for(int i=0; i<15;i++){
            System.out.println(Hash.genRandom().showData());
        }
    }
    
    static void n() throws HashException{
        for(int i=0; i<15;i++){
            Hash h=Hash.genRandom();
            System.out.println(h);
            Hash a= new Hash(h.compressHash());
            System.out.println(a);
            if(i!=14)
            System.out.println("\n\n");
            if(i==14){
                System.out.println(h.showData()+a.showData());
                String an=Hash.decriptHash(a.compressHash());
                String nd=Hash.decriptHash(h.compressHash());
                for(int nda=0;nda<18;nda++){
                    System.out.print("---"+String.format("%02d", nda)+"---"); 
                    if(nda<17)System.out.print("-");
                }System.out.println();
                int bitCount=0;
                for(boolean y:Operations.toBits(an)){ 
                    if(bitCount%8==0&&bitCount>0)System.out.print("-");
                    if(y) System.out.print("1"); 
                    else System.out.print("0"); 
                    bitCount++;
                }System.out.println(); 
                bitCount=0;
                for(boolean y:Operations.toBits(nd)){ 
                    if(bitCount%8==0&&bitCount>0)System.out.print("-");
                    if(y) System.out.print("1"); 
                    else System.out.print("0"); 
                    bitCount++;
                }System.out.println(); 
                
                System.out.println("diferentes: ");
                for(int m=0;m<an.length();m++){
                    if(!Hash.hashIntegrityBySteeps(an.charAt(m), nd.charAt(m), m)){
                        System.out.print(m+" ");
                    }
                }
            }
        }System.out.println();
        System.out.print("Resultado do teste");
        Hash jk=Hash.genRandom();
        if(Hash.checkHashIntegrity(jk.compressHash(), new Hash(jk.compressHash()).compressHash()))System.out.println("...OK");
        else System.out.println("...fail");
        String e="123456789012345678";
        System.out.println(e.substring(0, 4)+"    "+e.substring(4));
        if("aaaaaaaaaaaa".equals(Hash.decriptHash(Hash.criptHash("aaaaaaaaaaaa", 4, 11, false, -16, false, false, 4), 4, 11, false, -16, false, false, 4))){
            System.out.println("ok");
        }else System.out.println("nan:"+Hash.decriptHash(Hash.criptHash("aaaaaaaaaaaa", 4, 11, false, -16, false, false, 4), 4, 11, false, -16, false, false, 4));
    }
    
    static void o() throws HashException{
        String a=Hash.genRandom().compressHash();
        String b=new Hash(a).compressHash();
        String c=Hash.includeHash(a+b+'d',new Hash(a));
        String d=new Hash(Hash.searchHash(c).hash.compressHash()).compressHash();
        System.out.print("Hash test");
        if(Hash.checkHashIntegrity(a,b)&&Hash.checkHashIntegrity(a,d))System.out.println("...OK");
        else System.out.println("...fail");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d+"\n\n");
        String sub="abcde";
        System.out.println(sub.substring(1));
        
        
    }
    
    static void p(){
        for(int i=0;i<8;i++){
            for(int j=0;j<16;j++)
            if(!(Tables.convert(Tables.convert((char)j, false, i), true, i)==j)) System.out.println("erro(i,j): ("+i+","+j+")");
        }
    }
    
     static void A() throws IOException{
         Path path = Paths.get("mulherjaca.jpg");
        FileOutputStream fos = new FileOutputStream("estudos.jpg");
        byte[] bytes=Files.readAllBytes(path);
        String out="";
        for(byte b:bytes)
            out+=(char)b;
        byte[] x=new byte[out.length()];
        char[] chars=out.toCharArray();
        int i=0;
        for(char c:chars){
            x[i]=(byte) c;
            i++;
        }
        fos.write(x);
        fos.close();
     }
     
     static void B() throws DataException {
         char a=255;
         byte A=(byte)(a);
         System.out.println((int)a+"-"+(int)(A&0xFF));
        String data="sadhasbdnmkaskdhbdfjkpsaddajsdnasjkdmasldasdlmasdasdasda"; 
        byte[] x=new byte[data.length()];
        for(int i=0;i<data.length();i++){
            x[i]=(byte) data.charAt(i);
        }
        String out="";
        for(byte b:x)
            out+=(char)b;
        Cript2010x8b.checkStr(out);
        if(out.equals(data))
            System.out.println("OK");
        else
            System.out.println("fail");
     }
     
     static void C(){
         int[]x={1,2,3,4,5,6,7};
         System.out.println(Arrays.toString(Cript.putInFront(x, 1)));
         System.out.println(Arrays.toString(Cript.putInBack(x, 8)));
         StringBuilder a=new StringBuilder("thiago");
         a.replace(0, 0+1, "x");
         System.out.println(a.toString());
     }
     
     static void D(){
         long startTime = System.nanoTime();
         b();
         long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("t:"+totalTime);
        startTime = System.nanoTime();
         String str="a"; 
        boolean x[]=OperationsSlim.toBits(str); 
        for(boolean y:x) 
            if(y) System.out.print("1"); 
            else System.out.print("0"); 
        System.out.println(); 
        System.out.println(OperationsSlim.fromBits(x)); 
        System.out.println(OperationsSlim.invert("a")); 
        System.out.println(OperationsSlim.invert(Operations.invert("a")));
         endTime   = System.nanoTime();
         totalTime = endTime - startTime;
        System.out.println("t:"+totalTime);
     }
     
     static void E(){
        String a="abcdefghijklmnopqrstuvwxyz";
        System.out.println(a);
        String b=OperationsSlim.includeRand(a, 3, 6);
        System.out.println(b);
        String c=OperationsSlim.removeRand(b, 3, 6);
        System.out.println(c);
     }
     
     static void F(){
         String x="dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajd         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" +
"         String x=\"dsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdsajdhasjkdjashgdhioasdgasjdimjadu fisansmhfashjmi oasjfjasdoifjafimaodfima sjdfsadfasdfasdf\";\n" ;
         for(int i=-255;i<256;i++){
             if(x.equals(OperationsSlim.offset(OperationsSlim.offset(x, true, -i), true, i)))
                 System.out.println("y");
             else
                 System.out.println("n");
         }System.out.println("----------");
         for(int i=-255;i<256;i++){
             if(x.equals(OperationsSlim.offset(OperationsSlim.offset(x, false, -i), false, i)))
                 System.out.println("y");
             else
                 System.out.println("n");
         }
     }
     
     static void G(){
         Hash h;
         Hash t;
         boolean s=true;
         boolean l=true;
         int f=0;
         for(int i=0;i<666;i++){
            h=Hash.genRandom();
            t=Hash.makeCompact(h.compactHash());
            if(!Hash.checkHashIntegrity(h.compressHash(), t.compressHash())){
                s=false;
                f++;
                System.out.println(i+":"+h.compactHash());
                System.out.println(i+":"+t.compactHash());
            }else if(l){
                l=false;
                System.out.println("================================");
                System.out.println(h.compactHash());
                System.out.println(t.compactHash());
                System.out.println("================================");
            }
         }
         if(s)
             System.out.println("deu bom");
         else 
             System.out.println("deu "+f+" ruims");
         h=Hash.makeCompact("6@1@1@false@false@false@5@false@false@5@71346205@true@2@-74@false@5@18@18@true@22@true@6@12@1352647");
         t=Hash.makeCompact(h.compactHash());
         if(Hash.checkHashIntegrity(h.compressHash(), t.compressHash()))
             System.out.println("o bom deu ruim");
         else 
             System.out.println("o ruim deu bom");
     }
     
     static void H(){
         MD5 s=new MD5("senhwwwwwwwwwwasenhwwwwwwwwwwasenhwwwwwwwwwwasenhwwwwwwwwwwa");
         System.out.println("hash:"+s.getHash());
         System.out.println("size:"+s.getHash().length());
         System.out.println("salt:"+MD5.genRandomSalt());
         System.out.println("size:"+MD5.genRandomSalt().length());
         
     }
    
    public static void main(String[] args) throws HashException, IOException, DataException { 
        H(); 
    }     
} 
