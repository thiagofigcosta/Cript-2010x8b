package cript;

public class Operations {

    //TODO: funcao q converte um inteiro pra string(passando quantos chars deve ter)
    //TODO: funcao q converter string para um inteiro
    
    
    public static String intToStr(int value, int size){
        if(size<1) return "";
        if(value>255*size) value=255*size;
        if(value<-255*size) value=-255*size;
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
    
    public static int strToInt(String bytes, int size){
        int out=0;
        boolean[] bits=toBits(bytes);
        for(int i=0;i<bits.length;i++)
            if(bits[i])out+=Math.pow(2, size-(i+1));
        return out;
    }
    
    /**
     * @param bytes sequencia de bytes com valor a ser somado
     * @param size tamanho dos conjuntos de byte
     * @param value valor a ser somado
     * @return string com operacao efetuada
     */
    public static String add(String bytes,int size, int value){
        if(value==0||size<1||size>bytes.length()) return bytes;
        if(value>255) value=255;
        if(value<-255) value=-255;
                        while(bytes.length()%size!=0){//TODO: retirar se possivel
                            bytes+=0;
                        }
        String out="";
        for(int i=0;i<(int)bytes.length()/size;i++){
            String subStr=""; 
            for(int l=0;l<size;l++)
                try{
                    subStr+=bytes.charAt(i*size+l);
                }catch(Exception e){
                    subStr+=0;
                }
            int current=strToInt(subStr,size);
            current+=value;
            if(current>255*size)current-=(255*size)+1;
            if(current<0)current=255*size-(current+1); 
            out+=intToStr(current,size);
        }
        return out;
    }
    
    public static String pow(String bytes,int size, int value){//TODO: do it
        return bytes;
    }
    
    public static String sqrt(String bytes,int size, int value){//TODO: do it
        return bytes;
    }
    
    public static String mult(String bytes,int size, int value){//TODO: do it
        return bytes;
    }
    
    public static String div(String bytes,int size, int value){//TODO: do it
        return bytes;
    }
    
    public static String addOld(String bytes,int size, int value){//TODO: formar inteiros e efetuar operacoes
        if(value==0||size<1||size>bytes.length()) return bytes;
        if(value>255) value=255;
        if(value<-255) value=-255;
        for(int i=0;i<(int)bytes.length()/size;i++){
            int values[]=new int[size];
            for(int j:values){
                values[j]=0;
                try{values[j]=bytes.charAt(j+i*size);}catch(Exception e){}
            }
            int carry=value;
            int index=0;
            do{
                if(values[index]+carry>255||values[index]+carry<0){
                    if(carry>0){
                        values[index]=0;
                        carry=values[index]-value;
                    }else{
                        values[index]=255-(values[index]-value+1);
                        carry=-1;
                    }
                }else{
                    values[index]+=value;
                    carry=0;
                }
                index++;
                if(index>size-1)index=0;
            }while(carry!=0);
        }
        
        return bytes;
    }
    
    
    public static String offset(String bytes,boolean bits, int value){
        boolean actualBits[]=toBits(bytes);
        boolean newBits[]=new boolean[bytes.length()*8];
        if(!bits)value*=8;
        for(int i=0;i<actualBits.length;i++){
            int index=i+value;
            if(index>=actualBits.length)index-=(actualBits.length-2);
            if(index<0)index+=actualBits.length;
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
                 bits[j+i*8]=false;
                if(bytes.charAt(i)>=Math.pow(2, j)){
                    bits[j+i*8]=true;
                    replace(bytes,i,(char)(bytes.charAt(i)-Math.pow(2, j)));
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
                if(bits[j+i*8])
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
        for(int i=0;i<sCs.length;i++){
            if(i%2==0)newChar=(char) (sCs[i]*16);
            else{
                newChar+=sCs[i];
                replace(bytes,i-1,newChar);
            }
            
        }
        return bytes;
    }
}
