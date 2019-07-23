package cript; 
 
import Exception.MultDivException;

public class Cript { 
    private final static boolean one=Cript2010x8b.CRIPT&&true;//invert e switch (java.lang.ArrayIndexOutOfBoundsException: 7)
    private final static boolean two=Cript2010x8b.CRIPT&&true;//use AIOBG (java.lang.ArrayIndexOutOfBoundsException: 7)
    private final static boolean three=Cript2010x8b.CRIPT&&true;//add           (slow)
    private final static boolean four=Cript2010x8b.CRIPT&&true;//mult pow       (slow)
    private final static boolean five=Cript2010x8b.CRIPT&&true;//offset1        
    private final static boolean seven=Cript2010x8b.CRIPT&&true;//include rand
    private final static boolean six=five;//offset2
    private String decripted=""; 
    private String cripted=""; 
    private boolean cript=true; 
     
    private Hash hash; 
     
    public Cript(Hash hash, String data, boolean cript){ 
        if(hash==null){
            if(!cript)
                try{
                    ExportedHashData x=Hash.searchHash(data);
                    this.hash=x.hash;
                    data=x.data;
                }catch(Exception e){this.hash=null;}
            else
                this.hash=Hash.genRandom();
        }else
            this.hash=hash;
        this.cript=cript; 
        if(cript){ 
            this.decripted=data; 
        }else{ 
            this.cripted=data; 
        } 
    } 
 
    public Cript() {} 
     
    public String run(){ 
        Cript2010x8b.computingSteeps=0;
        if(cript) 
            return cript(); 
        else 
            return decript(); 
    } 
    
    public static int[]putInFront(int[] steep,int n){
        int []out=new int[steep.length];
        out[0]=n;
        int idx=1;
        for(int i=0;i<steep.length;i++)
            if(steep[i]!=n){out[idx]=steep[i];idx++;}
        return out;
    }
    
    public static int[]putInBack(int[] steep,int n){
        int []out=new int[steep.length];
        int idx=0;
        for(int i=0;i<steep.length;i++)
            if(steep[i]!=n){out[idx]=steep[i];idx++;}
        out[steep.length-1]=n;
        return out;
    }
     
    public String cript(){ 
        String out=this.decripted; 
        int n=0;
        int m=-1;
        int steep; 
        boolean err;
        int[]steeps={1,2,3,4,5,6,7};
        if(Cript2010x8b.USECUSTOMSTEEPORDER)
            steeps=hash.getSteepOrder();
        if(hash.isAddArraySize())
            steeps=putInFront(steeps,3);
        for(int i=0;i<7;i++){ 
            steep=steeps[i]; 
            if(Cript2010x8b.PRINTSTEEPS) System.out.println("*****SteepC: "+(int)(i+1)+"/7 ("+steep+")");
            switch(steep){ 
                case 1: if(!one)break;
                    if(hash.isInvertBits()) 
                        out=OperationsSlim.invert(out); 
                    out=OperationsSlim.switchTable(out, true, hash.getSwitchTable()); 
                    break; 
                     
                case 2: if(!two)break;
                    if(hash.isUseAIOBG()){ 
                        out=OperationsSlim.applyAIOBG(out, hash.getSize(), hash.getMultiplesUsingAIOBG(), hash.getIdxAlternativeOfByteGroup());
                    } 
                    break; 
                     
                case 3: if(!three)break;
                    String outBKPadd=out;
                    int count;
                    if(hash.isAddArraySize()&&Cript2010x8b.ADDARRAYSIZE){ 
                        count=out.length();
                    }else{ 
                        count=hash.getAdd();
                    }
                    out=OperationsSlim.add(outBKPadd, hash.getSize(), count);
                    err=false;
                    if(!outBKPadd.equals(OperationsSlim.add(out, hash.getSize(), -count))) err=true;
                    count=0;
                    while(err){
                        err=false;
                        m=(int) (hash.getAdd()*0.9);
                        if(m==-1||m==0||count>=8)m=1;
                        out=OperationsSlim.add(outBKPadd, hash.getSize(), m);
                        if(!outBKPadd.equals(OperationsSlim.add(out, hash.getSize(), -m))) err=true;
                        count++;
                    }
                    Cript2010x8b.addErr=m;
                    break; 
                     
                case 4: if(!four)break;
                    out=Operations.pow(out, hash.getSize(), hash.getPow()); 
                    String outBKPmult=out;
                    do{
                    err=false;
                    out=OperationsSlim.mult(outBKPmult, hash.getSize(), hash.getMult()-n); 
                    try{if(!outBKPmult.equals(OperationsSlim.div(out,hash.getSize(),hash.getMult()-n))){err=true;}}
                    catch(MultDivException e){err=true;}
                    if(err){
                        n++;
                    }
                    }while(err);
                    Cript2010x8b.multErr=n;
                    break; 
                     
                case 5: if(!five)break;
                    out=OperationsSlim.offset(out, hash.isIsBitOffset1(), hash.getOffset1()); 
                    break; 
                     
                case 6: if(!six)break;
                    out=OperationsSlim.offset(out, hash.isIsBitOffset2(), hash.getOffset2()); 
                    break; 
                     
                case 7:
                    if(!Cript2010x8b.ADDRANDOMBYTES||!seven) break;
                    out=OperationsSlim.includeRand(out, hash.getAngularCoefficient(), hash.getLinearCoefficient());
                    break; 
            } 
            Cript2010x8b.computingSteeps++;
        } 
        this.cripted=out; 
        return out; 
    } 
     
    public String decript(){
        String out=this.cripted; 
        int steep; 
        int[]steeps={1,2,3,4,5,6,7};
        if(Cript2010x8b.USECUSTOMSTEEPORDER)
            steeps=hash.getSteepOrder();
        if(hash.isAddArraySize())
            steeps=putInFront(steeps,3);
        for(int i=6;i>=0;i--){
            steep=steeps[i]; 
            //if(Cript2010x8b.PRINTSTEEPS) System.out.println("*****SteepD: "+(int)(i+1)+"/7 ("+steep+")");
            switch(steep){ 
                case 1: if(!one)break;
                    out=OperationsSlim.switchTable(out, false, hash.getSwitchTable()); 
                    if(hash.isInvertBits()) 
                        out=OperationsSlim.invert(out); 
                    break; 
                     
                case 2: if(!two)break;
                    if(hash.isUseAIOBG()){ 
                        out=OperationsSlim.removeAIOBG(out,hash.getSize(), hash.getMultiplesUsingAIOBG(), hash.getIdxAlternativeOfByteGroup());
                    } 
                    break; 
                     
                case 3: if(!three)break;
                    if(hash.isAddArraySize()&&Cript2010x8b.ADDARRAYSIZE){ 
                        out=OperationsSlim.add(out, hash.getSize(), -out.length()); 
                    }else{ 
                        out=OperationsSlim.add(out, hash.getSize(), -hash.getAdd()); 
                    } 
                    break; 
                     
                case 4: if(!four)break;
                    try{out=OperationsSlim.div(out, hash.getSize(), hash.getMult());}catch(MultDivException e){}
                    out=Operations.nthrt(out, hash.getSize(), hash.getPow()); 
                    break; 
                     
                case 5: if(!five)break;
                    out=OperationsSlim.offset(out, hash.isIsBitOffset1(), -hash.getOffset1()); 
                    break; 
                     
                case 6: if(!six)break;
                    out=OperationsSlim.offset(out, hash.isIsBitOffset2(), -hash.getOffset2()); 
                    break; 
                     
                case 7:
                    if(!Cript2010x8b.ADDRANDOMBYTES||!seven) break;
                    out=OperationsSlim.removeRand(out, hash.getAngularCoefficient(), hash.getLinearCoefficient());
                    break; 
            } 
            Cript2010x8b.computingSteeps++;
        } 
        this.decripted=out; 
        return out; 
    } 
} 
