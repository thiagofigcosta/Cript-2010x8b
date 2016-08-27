package cript; 
 
import java.math.BigInteger; 
import java.util.Random;  
import Exception.MultDivException;

public class OperationsSlim { 
     
    public static String intToStr(long value, int size){ 
        if(size<1||value<0) return ""; 
        StringBuilder bytes=new StringBuilder(); 
        for(int i=size-1;i>=0;i--){ 
            char current=0; 
            for(int j=7;j>=0;j--){ 
                if(value>=Math.pow(2, j+i*8)){ 
                   current+=Math.pow(2, j); 
                   value-=Math.pow(2, j+i*8); 
                } 
            } 
            bytes.append((char)current); 
        } 
        return bytes.toString(); 
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
        StringBuilder bytes=new StringBuilder(); 
        for(int i=size-1;i>=0;i--){ 
            char current=0; 
            for(int j=7;j>=0;j--){ 
                if(value.compareTo(BigInteger.valueOf(2).pow(j+i*8))>=0){ 
                   current+=Math.pow(2, j); 
                   value=value.add(BigInteger.valueOf(2).pow(j+i*8).negate()); 
                } 
            } 
            bytes.append((char)current); 
        } 
        return bytes.toString(); 
    } 
     
    /** 
     * @param bytes sequencia de bytes com valor a ser somado 
     * @param size tamanho dos conjuntos de byte 
     * @param value valor a ser somado 
     * @return string com operacao efetuada 
     */ 
    public static String add(String bytes,int size, int value){ 
        if(value==0||value==1||value==-1||size<1||size>bytes.length()) return bytes; 
        StringBuilder out=new StringBuilder(); 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            StringBuilder subStr=new StringBuilder();  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr.append(bytes.charAt(i*size+(size-l-1))); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr.toString()); 
            current=current.add(BigInteger.valueOf(value)); 
            while(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            while(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
  
            out.append(BintToStr(current,size)); 
        } 
        return out.toString(); 
    } 
     
    public static String addLong(String bytes,int size, int value){ 
        if(value==0||size<1||size>bytes.length()) return bytes; 
        StringBuilder out=new StringBuilder(); 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            StringBuilder subStr=new StringBuilder();  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr.append(bytes.charAt(i*size+(size-l-1))); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=add(bug,1,value);                
                    return out+bug; 
                }  
            long current=strToInt(subStr.toString()); 
            current+=value; 
            while(current>Math.pow(2, 8*size))current-=Math.pow(2, 8*size); 
            while(current<0)current+=Math.pow(2, 8*size);   
            out.append(intToStr(current,size)); 
        } 
        return out.toString(); 
    } 
     
     
    public static String mult(String bytes,int size, int value){//work with fix(size must be <=2) 
        if(value<=1||size<1||size>bytes.length()||!Cript2010x8b.USEDIVMULT) return bytes; 
            size=OperationsSlim.multAndDivFixer(size);
        StringBuilder out=new StringBuilder(); 
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            StringBuilder subStr=new StringBuilder();  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr.append(bytes.charAt(i*size+(size-l-1))); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr.toString()); 
            current=current.multiply(BigInteger.valueOf(value)); 
            while(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            while(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
            
            out.append(BintToStr(current,size)); 
        }       
        return out.toString(); 
    } 
     
    public static String div(String bytes,int size, int value) throws MultDivException { //work with fix(size must be <=2) 
        if(value<=1||size<1||size>bytes.length()||(!Cript2010x8b.USEDIVMULT)) return bytes; 
            size=OperationsSlim.multAndDivFixer(size);
        StringBuilder out=new StringBuilder();
        int max=bytes.length()/size; 
        if(bytes.length()%size!=0) 
            max++; 
        for(int i=0;i<max;i++){ 
            StringBuilder subStr=new StringBuilder();  
            for(int l=0;l<size;l++) 
                try{ 
                    subStr.append(bytes.charAt(i*size+(size-l-1))); 
                }catch(Exception e){ 
                    String bug=bytes.substring(i*size);  
                    bug=addLong(bug,1,value);                
                    return out+bug; 
                }  
            BigInteger current=strToBInt(subStr.toString()); 
            int n=0;int MAX=151;
                while(n<MAX&&current.mod(BigInteger.valueOf(value)).compareTo(BigInteger.ZERO)!=0){ 
                    current=current.add(BigInteger.valueOf(2).pow(8*size)); n++;
                } 
                if(n>=MAX){
                    throw new MultDivException("Erro while(true) Via:Div");
                }
            current=current.divide(BigInteger.valueOf(value)); 
            while(current.compareTo(BigInteger.valueOf(2).pow(8*size))>0)current=current.add(BigInteger.valueOf(2).pow(8*size).negate()); 
            while(current.compareTo(BigInteger.ZERO)<0)current=current.add(BigInteger.valueOf(2).pow(8*size)); 
  
            out.append(BintToStr(current,size)); 
        } 
        return out.toString(); 
    } 
     
     
    public static String offset(String bytes,boolean bits, int value){ 
        if(bits){
            boolean actualBits[]=toBits(bytes); 
            if(value>0){
                value=value%actualBits.length;
                if(value==0)return bytes;
               
            }else{
                value*=-1;
                value=value%actualBits.length;
                if(value==0)return bytes;
                value*=-1;
            }
            boolean newBits[]=new boolean[bytes.length()*8]; 
            for(int i=0;i<actualBits.length;i++){ 
                int index=i+value; 
                while(index>=actualBits.length){index-=(actualBits.length);} 
                while(index<0){index+=actualBits.length;} 
                newBits[i]=actualBits[index]; 
            } 
            return fromBits(newBits);
        }else{
            if(value>0){
                value=value%bytes.length();
                if(value==0)return bytes;
                return bytes.substring(value)+bytes.substring(0, value);
            }else{
                value*=-1;
                value=value%bytes.length();
                if(value==0)return bytes;
                return bytes.substring(bytes.length()-value)+bytes.substring(0, bytes.length()-value);
            }
        }
    } 
     
    
    public static String includeRand(String bytes,int angular, int linear){
        StringBuilder out=new StringBuilder(bytes);
        if(angular==0)return bytes; 
        int maxValueOfX=(out.length()-linear)/angular; 
        for(int t=0;t<=maxValueOfX;t++){
            int max=255, min=0; 
            char random=(char)(min+(int)(Math.random()*((max-min)+1))); 
            int image=angular*t+linear; 
            out.insert(image-1, random);
        } 
        return out.toString();
    }
    
    public static String removeRand(String bytes,int angular, int linear){
        StringBuilder out=new StringBuilder(bytes);
        if(angular==0)return bytes; 
        int maxValueOfX=(out.length()-linear)/angular; 
        int n=0; 
        for(int t=0;t<=maxValueOfX;t++){ 
            int image=angular*t+linear; 
            image-=n; 
            n++; 
            if(image>0)
                out.deleteCharAt(image-1);
            maxValueOfX=(out.length()-linear)/angular; 
        }
        return out.toString();
    }
    
    public static String applyAIOBG(String bytes,int size, int multiples, int AIOBG[]){
        StringBuilder out=new StringBuilder(bytes);
        for(int j=0;j<(int)out.length()/size;j++){ 
            if(j%multiples==0){ 
                char temp[]=new char[8]; 
                int forMax=size;
                if(size>8)forMax=8;
                for(int l=0;l<forMax;l++){ 
                    temp[l]=out.charAt(j*size+l); 
                } 
                int t=0; 
                for(int l=0;l<8;l++){ 
                    if(AIOBG[l]<size){ 
                        out.replace(j*size+t, (j*size+t)+1, ""+temp[AIOBG[l]]);
                        t++; 
                    } 
                    if(t>=size)break; 
                } 
            } 
        } 
        return out.toString();
    }
    
    public static String removeAIOBG(String bytes,int size, int multiples, int AIOBG[]){
        StringBuilder out=new StringBuilder(bytes);
        for(int j=0;j<(int)out.length()/size;j++){ 
            if(j%multiples==0){ 
                char temp[]=new char[8]; 
                int maxFor=size;
                if(size>8)maxFor=8;
                for(int l=0;l<maxFor;l++){ 
                    temp[l]=out.charAt(j*size+l); 
                } 
                int t=0; 
                for(int l=0;l<8;l++){ 
                    if(AIOBG[l]<size){ 
                        out.replace(j*size+AIOBG[l], (j*size+AIOBG[l])+1, temp[t]+"");
                        t++; 
                    } 
                    if(t>=size)break; 
                } 
            } 
        } 
        return out.toString();
    }
    
    public static String invert(String bytes){ 
        StringBuilder out=new StringBuilder();
        for(int i=0;i<bytes.length();i++){ 
            out.append((char)(255-bytes.charAt(i)));
        } 
        return out.toString(); 
    } 
     
    public static boolean[] toBits(String bytes){ 
        boolean bits[]=new boolean[bytes.length()*8];
        for(int i=0;i<bytes.length();i++){ 
            int x= bytes.charAt(i);
            for(int j=7;j>=0;j--){ 
                bits[7-j+i*8]=((x>>j)&1)==1;
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
    
    public static String fromBitsStr(boolean[] bits){ 
        StringBuilder out=new StringBuilder();
        StringBuilder x=new StringBuilder();
        for(int i=0;i<bits.length;i++){
            if(bits[i])
                x.append('1');
            else
                x.append('0');
            if((i+1)%8==0){
                out.append((char)Integer.parseInt(x.toString(), 2));
                x=new StringBuilder();
            }
        } 
        return out.toString();
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
        StringBuilder out=new StringBuilder(); 
        char semiChar=0;
        for(int i=0;i<bytes.length()*2;i++){ 
            if(i%2==0){
                semiChar=Tables.convert((char) (bytes.charAt(i/2)& 0x0F), signal, table);
            }else{
                semiChar+=16*Tables.convert((char) (bytes.charAt(i/2)>>4), signal, table);
                out.append(semiChar);
            }
        } 
        return out.toString(); 
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
    
    private static int multAndDivFixer(int size){
        if(size%2==0) return 1;
        else return 2;
    }
} 
