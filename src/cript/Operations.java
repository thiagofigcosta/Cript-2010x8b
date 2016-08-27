package cript;

public class Operations {

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
        for(int i=0;i<Math.ceil(bytes.length()/size);i++){
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
                if(bytes.charAt(i)>=Math.pow(2, j)){
                    bits[j+i*8]=true;
                    replace(bytes,i,(char)(bytes.charAt(i)-Math.pow(2, j)));
                }else{
                    bits[j+i*8]=false;
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
        for(int i=0;i<bytes.length();i++){
            replace(bytes,i,Tables.convert(bytes.charAt(i), signal, table));
        }
        return bytes;
    }
}
