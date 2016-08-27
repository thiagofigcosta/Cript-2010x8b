package cript; 
 
public class Cript { 
    private String decripted=""; 
    private String cripted=""; 
    private boolean cript=true; 
     
    private Hash hash; 
     
    public Cript(Hash hash, String data, boolean cript){ 
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
        if(cript) 
            return cript(); 
        else 
            return decript(); 
    } 
     
    public String cript(){ 
        String out=this.decripted; 
        int steep; 
        boolean firstSteep=false; 
        for(int i=0;i<7;i++){ 
            steep=hash.getSteepOrder()[i]; 
            if(hash.isAddArraySize()&&i==0&&!firstSteep){ 
                steep=3; 
                i--; 
                firstSteep=true; 
            }else if(!(steep==3&&hash.isAddArraySize())){ 
            }else{ 
                steep=-1; 
            } 
             
            switch(steep){ 
                case 1: 
                    if(hash.isInvertBits()) 
                        out=Operations.invert(out); 
                    out=Operations.switchTable(out, true, hash.getSwitchTable()); 
                    break; 
                     
                case 2: 
                    if(hash.isUseAIOBG()){ 
                        for(int j=0;j<(int)out.length()/hash.getSize();j++){ 
                            if(j%hash.getMultiplesUsingAIOBG()==0){ 
                                char temp[]=new char[8]; 
                                for(int l=0;l<hash.getSize();l++){ 
                                    temp[l]=out.charAt(j*hash.getSize()+l); 
                                } 
                                int t=0; 
                                for(int l=0;l<8;l++){ 
                                    if(hash.getIdxAlternativeOfByteGroup()[l]<hash.getSize()){ 
                                        out=Operations.replace(out, j*hash.getSize()+t, temp[hash.getIdxAlternativeOfByteGroup()[l]]); 
                                        t++; 
                                    } 
                                    if(t>=hash.getSize())break; 
                                } 
                            } 
                        } 
                    } 
                    break; 
                     
                case 3: 
                    if(hash.isAddArraySize()){ 
                        out=Operations.add(out, hash.getSize(), out.length()); 
                    }else{ 
                        out=Operations.add(out, hash.getSize(), hash.getAdd()); 
                    } 
                    break; 
                     
                case 4: 
                    out=Operations.pow(out, hash.getSize(), hash.getPow()); 
                    out=Operations.mult(out, hash.getSize(), hash.getMult()); 
                    break; 
                     
                case 5: 
                    out=Operations.offset(out, hash.isIsBitOffset1(), hash.getOffset1()); 
                    break; 
                     
                case 6: 
                    out=Operations.offset(out, hash.isIsBitOffset2(), hash.getOffset2()); 
                    break; 
                     
                case 7: 
                    if(hash.getAngularCoefficient()==0)break; 
                    int maxValueOfX=(out.length()-hash.getLinearCoefficient())/hash.getAngularCoefficient(); 
                    for(int t=0;t<=maxValueOfX;t++){ 
                        int image=hash.getAngularCoefficient()*t+hash.getLinearCoefficient(); 
                        String buff1="",buff2=""; 
                        try{buff1=out.substring(0, image-1);}catch(Exception e){} 
                        try{buff2=out.substring(image-1);}catch(Exception e){} 
                        int max=255, min=0; 
                        char random=(char)(min+(int)(Math.random()*((max-min)+1))); 
                        out=buff1+random+buff2; 
                    } 
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
        for(int i=6;i>=0;i--){ 
            steep=hash.getSteepOrder()[i]; 
            if(hash.isAddArraySize()&&i==1&&!lastSteep){ 
                steep=3; 
                lastSteep=true; 
                i++; 
            }else if(!(steep==3&&hash.isAddArraySize())){ 
            }else{ 
                steep=-1; 
            } 
             
            switch(steep){ 
                case 1: 
                    out=Operations.switchTable(out, false, hash.getSwitchTable()); 
                    if(hash.isInvertBits()) 
                        out=Operations.invert(out); 
                    break; 
                     
                case 2: 
                    if(hash.isUseAIOBG()){ 
                        for(int j=0;j<(int)out.length()/hash.getSize();j++){ 
                            if(j%hash.getMultiplesUsingAIOBG()==0){ 
                                char temp[]=new char[8]; 
                                for(int l=0;l<hash.getSize();l++){ 
                                    temp[l]=out.charAt(j*hash.getSize()+l); 
                                } 
                                int t=0; 
                                for(int l=0;l<8;l++){ 
                                    if(hash.getIdxAlternativeOfByteGroup()[l]<hash.getSize()){ 
                                        out=Operations.replace(out, j*hash.getSize()+hash.getIdxAlternativeOfByteGroup()[l], temp[t]); 
                                        t++; 
                                    } 
                                    if(t>=hash.getSize())break; 
                                } 
                            } 
                        } 
                    } 
                    break; 
                     
                case 3: 
                    if(hash.isAddArraySize()){ 
                        out=Operations.add(out, hash.getSize(), -out.length()); 
                    }else{ 
                        out=Operations.add(out, hash.getSize(), -hash.getAdd()); 
                    } 
                    break; 
                     
                case 4: 
                    out=Operations.div(out, hash.getSize(), hash.getMult()); 
                    out=Operations.nthrt(out, hash.getSize(), hash.getPow()); 
                    break; 
                     
                case 5: 
                    out=Operations.offset(out, hash.isIsBitOffset1(), -hash.getOffset1()); 
                    break; 
                     
                case 6: 
                    out=Operations.offset(out, hash.isIsBitOffset2(), -hash.getOffset2()); 
                    break; 
                     
                case 7: 
                    if(hash.getAngularCoefficient()==0)break; 
                    int maxValueOfX=(out.length()-hash.getLinearCoefficient())/hash.getAngularCoefficient(); 
                    int n=0; 
                    for(int t=0;t<=maxValueOfX;t++){ 
                        int image=hash.getAngularCoefficient()*t+hash.getLinearCoefficient(); 
                        image-=n; 
                        n++; 
                        String buff1="",buff2=""; 
                        buff1=out.substring(0, image-1); 
                        buff2=out.substring(image); 
                        out=buff1+buff2; 
                        maxValueOfX=(out.length()-hash.getLinearCoefficient())/hash.getAngularCoefficient(); 
                    } 
                    break; 
            } 
        } 
        this.decripted=out; 
        return out; 
    } 
} 
