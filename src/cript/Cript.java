package cript; 
 
import Exception.MultDivException;

public class Cript { 
    private final static boolean one=true;//invert e switch
    private final static boolean two=true;//use AIOBG
    private final static boolean three=true;//add
    private final static boolean four=true;//mult pow
    private final static boolean five=true;//offset1
    private final static boolean six=five;//offset2
    private final static boolean seven=true;//include rand
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
     
    public String run() throws MultDivException { 
        if(cript) 
            return cript(); 
        else 
            return decript(); 
    } 
     
    public String cript() throws MultDivException { 
        String out=this.decripted; 
        int steep; 
        boolean firstSteep=false; 
        for(int i=1;i<=7;i++){ 
            if(Cript2010x8b.USECUSTOMSTEEPORDER)
                steep=hash.getSteepOrder()[i-1]; 
            else
                steep=i;
            
            if(hash.isAddArraySize()&&i==1&&!firstSteep){ 
                steep=3; 
                i--; 
                firstSteep=true; 
            }else if(steep==3&&hash.isAddArraySize()){ 
                  steep=-1; 
            }
            switch(steep){ 
                case 1: if(!one)break;
                    if(hash.isInvertBits()) 
                        out=Operations.invert(out); 
                    out=Operations.switchTable(out, true, hash.getSwitchTable()); 
                    break; 
                     
                case 2: if(!two)break;
                    if(hash.isUseAIOBG()){ 
                        out=Operations.applyAIOBG(out, hash.getSize(), hash.getMultiplesUsingAIOBG(), hash.getIdxAlternativeOfByteGroup());
                    } 
                    break; 
                     
                case 3: if(!three)break;
                    if(hash.isAddArraySize()&&Cript2010x8b.ADDARRAYSIZE){ 
                        out=Operations.add(out, hash.getSize(), out.length()); 
                    }else{ 
                        out=Operations.add(out, hash.getSize(), hash.getAdd()); 
                    } 
                    break; 
                     
                case 4: if(!four)break;
                    String outBKPmult=out;
                    out=Operations.pow(out, hash.getSize(), hash.getPow()); 
                    out=Operations.mult(out, hash.getSize(), hash.getMult()); 
                    try{if(!outBKPmult.equals(Operations.div(out,hash.getSize(),hash.getMult()))){throw new MultDivException("Erro no caminho de volta. Via:Mult(desigualdade)");}}
                    catch(MultDivException e){throw new MultDivException("Erro no caminho de volta. Via:Mult(exception)");}
                    
                    break; 
                     
                case 5: if(!five)break;
                    out=Operations.offset(out, hash.isIsBitOffset1(), hash.getOffset1()); 
                    break; 
                     
                case 6: if(!six)break;
                    out=Operations.offset(out, hash.isIsBitOffset2(), hash.getOffset2()); 
                    break; 
                     
                case 7:
                    if(!Cript2010x8b.ADDRANDOMBYTES||!seven) break;
                    out=Operations.includeRand(out, hash.getAngularCoefficient(), hash.getLinearCoefficient());
                    break; 
            } 
        } 
        this.cripted=out; 
        return out; 
    } 
     
    public String decript(){
        String out=this.cripted; 
        int steep; 
        boolean lastSteep=false; 
        for(int i=7;i>=1;i--){
            if(Cript2010x8b.USECUSTOMSTEEPORDER)
                steep=hash.getSteepOrder()[i-1]; 
            else
                steep=i;
            
            if(hash.isAddArraySize()&&steep==3&&i!=1){
                steep=-1;
            }
            if(hash.isAddArraySize()&&i==1&&!lastSteep&&steep!=3){
                i=2;
                lastSteep=true;
            }
            if(lastSteep&&i==1){
                steep=3;
            }
            switch(steep){ 
                case 1: if(!one)break;
                    out=Operations.switchTable(out, false, hash.getSwitchTable()); 
                    if(hash.isInvertBits()) 
                        out=Operations.invert(out); 
                    break; 
                     
                case 2: if(!two)break;
                    if(hash.isUseAIOBG()){ 
                        out=Operations.removeAIOBG(out,hash.getSize(), hash.getMultiplesUsingAIOBG(), hash.getIdxAlternativeOfByteGroup());
                    } 
                    break; 
                     
                case 3: if(!three)break;
                    if(hash.isAddArraySize()&&Cript2010x8b.ADDARRAYSIZE){ 
                        out=Operations.add(out, hash.getSize(), -out.length()); 
                    }else{ 
                        out=Operations.add(out, hash.getSize(), -hash.getAdd()); 
                    } 
                    break; 
                     
                case 4: if(!four)break;
                    try{out=Operations.div(out, hash.getSize(), hash.getMult());}catch(MultDivException e){}
                    out=Operations.nthrt(out, hash.getSize(), hash.getPow()); 
                    break; 
                     
                case 5: if(!five)break;
                    out=Operations.offset(out, hash.isIsBitOffset1(), -hash.getOffset1()); 
                    break; 
                     
                case 6: if(!six)break;
                    out=Operations.offset(out, hash.isIsBitOffset2(), -hash.getOffset2()); 
                    break; 
                     
                case 7:
                    if(!Cript2010x8b.ADDRANDOMBYTES||!seven) break;
                    out=Operations.removeRand(out, hash.getAngularCoefficient(), hash.getLinearCoefficient());
                    break; 
            } 
        } 
        this.decripted=out; 
        return out; 
    } 
} 
