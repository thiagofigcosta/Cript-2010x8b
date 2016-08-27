package cript; 
 
import java.math.BigInteger; 
import java.util.Random; 
 
public class Operations {     
     
    public static String intToStr(long value, int size){ 
        if(size<1||value<0) return ""; 
        String bytes=""; 
        for(int i=size-1;i>=0;i--){ 
            char current=0; 
            for(int j=7;j>=0;j--){ 
                if(value>=Math.pow(2, j+i*8)){ 
                   current+=Math.pow(2, j); 
                   value-=Math.pow(2, j+i*8); 
                } 
            } 
            bytes+=""+current; 
        } 
        return bytes; 
    } 
     
    public static long strToInt(String bytes){ 
        long out=0; 
        for(int i=0;i<bytes.length();i++) 
            out+=bytes.charAt(i)*Math.pow(2, 8*i); 
        return out; 
    } 
     
    public static BigInteger strToBInt(String bytes){ 
        BigInteger out=BigInteger.ZERO; 
        for(int i=0;i<bytes.length();i++) 
            out=out.add(BigInteger.valueOf(bytes.charAt(i)).multiply(BigInteger.valueOf(2).pow(8*i))); 
        return out; 
    } 
     
    public static String BintToStr(BigInteger value, int size){ 
        if(size<1||value.compareTo(BigInteger.ZERO)<0) return ""; 
        String bytes=""; 
        for(int i=size-1;i>=0;i--){ 
            char current=0; 
            for(int j=7;j>=0;j--){ 
                if(value.compareTo(BigInteger.valueOf(2).pow(j+i*8))>=0){ 
                   current+=Math.pow(2, j); 
                   value=value.add(BigInteger.valueOf(2).pow(j+i*8).negate()); 
                } 
            } 
            bytes+=""+current; 
        } 
        return bytes; 
    } 
     
    /** 
     * @param bytes sequencia de bytes com valor a ser somado 
     * @param size tamanho dos conjuntos de byte 
     * @param value valor a ser somado 
     * @return string com operacao efetuada 
     */ 
    public static String add(String bytes,int size, int value){ 
        if(value==0||size<1||size>bytes.length()) return bytes; 
        String out=""; 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            String subStr="";  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr+=bytes.charAt(i*size+(size-l-1)); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr); 
            current=current.add(BigInteger.valueOf(value)); 
            if(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            if(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
  
            out+=BintToStr(current,size); 
        } 
        return out; 
    } 
     
    public static String addLong(String bytes,int size, int value){ 
        if(value==0||size<1||size>bytes.length()) return bytes; 
        String out=""; 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            String subStr="";  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr+=bytes.charAt(i*size+(size-l-1)); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=add(bug,1,value);                
                    return out+bug; 
                }  
            long current=strToInt(subStr); 
            current+=value; 
            if(current>Math.pow(2, 8*size))current-=Math.pow(2, 8*size); 
            if(current<0)current+=Math.pow(2, 8*size);   
            out+=intToStr(current,size); 
        } 
        return out; 
    } 
     
    public static String pow(String bytes,int size, int value){ 
        if(value==1||value==0||size<1||size>bytes.length()) return bytes; 
        String out=""; 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            String subStr="";  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr+=bytes.charAt(i*size+(size-l-1)); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr); 
            current=current.pow(value); 
            while(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            while(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
  
            out+=BintToStr(current,size); 
        } 
        return out; 
    } 
     
    public static String nthrt(String bytes,int size, int value){ 
        if(value==1||value==0||size<1||size>bytes.length()) return bytes; 
        String out=""; 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            String subStr="";  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr+=bytes.charAt(i*size+(size-l-1)); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr); 
                while(Operations.nthRootFloor(current, value).pow(value).compareTo(current)!=0){ 
                    current=current.add(BigInteger.valueOf(2).pow(8*size)); 
                }       
            current=Operations.nthRootFloor(current, value); 
            while(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            while(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
  
            out+=BintToStr(current,size); 
        } 
        return out; 
    } 
     
    public static String mult(String bytes,int size, int value){ 
        if(value==1||value==0||size<1||size>bytes.length()) return bytes; 
        String out=""; 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            String subStr="";  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr+=bytes.charAt(i*size+(size-l-1)); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr); 
            current=current.multiply(BigInteger.valueOf(value)); 
            while(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            while(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
  
            out+=BintToStr(current,size); 
        } 
        return out; 
    } 
     
    public static String div(String bytes,int size, int value){ 
        if(value==1||value==0||size<1||size>bytes.length()) return bytes; 
        String out=""; 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            String subStr="";  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr+=bytes.charAt(i*size+(size-l-1)); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr); 
                while(current.mod(BigInteger.valueOf(value)).compareTo(BigInteger.ZERO)!=0){ 
                    current=current.add(BigInteger.valueOf(2).pow(8*size)); 
                } 
            current=current.divide(BigInteger.valueOf(value)); 
            while(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            while(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
  
            out+=BintToStr(current,size); 
        } 
        return out; 
    } 
     
     
    public static String offset(String bytes,boolean bits, int value){ 
        if(value>bytes.length())value-=bytes.length(); 
        if(value==bytes.length())return bytes; 
        boolean actualBits[]=toBits(bytes); 
        boolean newBits[]=new boolean[bytes.length()*8]; 
        if(!bits)value*=8; 
        for(int i=0;i<actualBits.length;i++){ 
            int index=i+value; 
            while(index>=actualBits.length){index-=(actualBits.length);} 
            while(index<0){index+=actualBits.length;} 
            newBits[i]=actualBits[index]; 
        } 
        return fromBits(newBits); 
    } 
     
    public static String invert(String bytes){ 
        boolean bits[]=toBits(bytes); 
        for(int i=0;i<bits.length;i++){ 
            bits[i]=!bits[i]; 
        } 
        return fromBits(bits); 
    } 
     
    public static boolean[] toBits(String bytes){ 
        boolean bits[]=new boolean[bytes.length()*8]; 
         for(int i=0;i<bytes.length();i++){ 
            for(int j=7;j>=0;j--){ 
                 bits[7-j+i*8]=false; 
                if(bytes.charAt(i)>=Math.pow(2, j)){ 
                    bits[7-j+i*8]=true; 
                    bytes=replace(bytes,i,(char)(bytes.charAt(i)-Math.pow(2, j))); 
                } 
            } 
        } 
        return bits; 
    } 
     
    public static String fromBits(boolean[] bits){ 
        char newBytes[]=new char[bits.length/8]; 
        for(int i=0;i<newBytes.length;i++){ 
            newBytes[i]=0; 
            for(int j=7;j>=0;j--){ 
                if(bits[7-j+i*8]) 
                    newBytes[i]+=Math.pow(2, j); 
            } 
        } 
        return String.valueOf(newBytes); 
    } 
     
    public static String replace(String str, int index, char replace){      
        if(str==null){ 
            return str; 
        }else if(index<0 || index>=str.length()){ 
            return str; 
        } 
        char[] chars = str.toCharArray(); 
        chars[index] = replace; 
        return String.valueOf(chars);        
    } 
     
    public static String switchTable(String bytes, boolean signal, int table){ 
        boolean bits[]=toBits(bytes); 
        char sCs[]=new char[bits.length/4]; 
        for(int i=0;i<bits.length/4;i++){ 
            char semiChar=0; 
            if(bits[i*4+0])semiChar+=8; 
            if(bits[i*4+1])semiChar+=4; 
            if(bits[i*4+2])semiChar+=2; 
            if(bits[i*4+3])semiChar+=1; 
            sCs[i]=Tables.convert(semiChar, signal, table); 
        } 
        char newChar=0; 
        bytes=""; 
        for(int i=0;i<sCs.length;i++){ 
            if(i%2==0){newChar=(char) (sCs[i]*16); 
            }else{ 
                newChar+=sCs[i]; 
                bytes+=newChar; 
            } 
             
        } 
        return bytes; 
    } 
     
    public static int[] sort(int [] v) {   
        Random random = new Random(); 
        for(int i=0;i<v.length-1;i++){ 
            int j=random.nextInt(v.length);  
            int temp=v[i]; 
            v[i]=v[j]; 
            v[j]=temp; 
        } 
        return v; 
    } 
     
    public static BigInteger nthRootFloor(BigInteger x, int n) { 
        int sign = x.signum(); 
        if (n <= 0 || (sign < 0)) 
            throw new IllegalArgumentException(); 
        if (sign == 0) 
            return BigInteger.ZERO; 
        if (n == 1) 
            return x; 
        BigInteger a; 
        BigInteger bigN = BigInteger.valueOf(n); 
        BigInteger bigNMinusOne = BigInteger.valueOf(n - 1); 
        BigInteger b = BigInteger.ZERO.setBit(1 + x.bitLength() / n); 
        do { 
            a = b; 
            b = a.multiply(bigNMinusOne).add(x.divide(a.pow(n - 1))).divide(bigN); 
        } while (b.compareTo(a) == -1); 
        return a; 
    } 
} 
