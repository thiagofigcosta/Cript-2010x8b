package cript;

public class Tables {
    public static char convert(char c, boolean signal, int table){
        char a=0,b=0;
        for(int j=7;j>=4;j--){
            if(c>=Math.pow(2, j)){
                a+=Math.pow(2, j);
                c-=Math.pow(2, j);
            }
        }
        for(int j=3;j>=0;j--){
            if(c>=Math.pow(2, j)){
                b+=Math.pow(2, j);
                c-=Math.pow(2, j);
            }
        }
        a=convertBCD(a,signal,table);
        b=convertBCD(b,signal,table);
        for(int j=7;j>=4;j--){
            if(a>=Math.pow(2, j)){
                b+=Math.pow(2, j);
                a-=Math.pow(2, j);
            }
        }
        return b;
    }
    
    private static char convertBCD(char c, boolean signal, int table){
        if(signal){
            switch(table){
                default:
                    switch(c){
                        default: return 2;
                        case 1: return 11;
                        case 2: return 12;
                        case 3: return 3;
                        case 4: return 5;
                        case 5: return 4;
                        case 6: return 15;
                        case 7: return 6;
                        case 8: return 13;
                        case 9: return 7;
                        case 10: return 10;
                        case 11: return 9;
                        case 12: return 8;
                        case 13: return 0;
                        case 14: return 14;
                        case 15: return 1;
                    }
                case 1:
                   switch(c){
                        default: return 15;
                        case 1: return 13;
                        case 2: return 0;
                        case 3: return 1;
                        case 4: return 7;
                        case 5: return 3;
                        case 6: return 6;
                        case 7: return 5;
                        case 8: return 2;
                        case 9: return 14;
                        case 10: return 4;
                        case 11: return 8;
                        case 12: return 10;
                        case 13: return 9;
                        case 14: return 11;
                        case 15: return 12;
                    }
                case 2:
                    switch(c){
                        default: return 0;
                        case 1: return 1;
                        case 2: return 3;
                        case 3: return 2;
                        case 4: return 4;
                        case 5: return 7;
                        case 6: return 12;
                        case 7: return 10;
                        case 8: return 6;
                        case 9: return 5;
                        case 10: return 8;
                        case 11: return 11;
                        case 12: return 13;
                        case 13: return 15;
                        case 14: return 14;
                        case 15: return 9;
                    }
                case 3:
                    switch(c){
                        default: return 13;
                        case 1: return 8;
                        case 2: return 4;
                        case 3: return 12;
                        case 4: return 15;
                        case 5: return 3;
                        case 6: return 5;
                        case 7: return 2;
                        case 8: return 14;
                        case 9: return 1;
                        case 10: return 10;
                        case 11: return 11;
                        case 12: return 6;
                        case 13: return 0;
                        case 14: return 7;
                        case 15: return 9;
                    }
                case 4:
                    switch(c){
                        default: return 15;
                        case 1: return 9;
                        case 2: return 1;
                        case 3: return 13;
                        case 4: return 10;
                        case 5: return 11;
                        case 6: return 6;
                        case 7: return 0;
                        case 8: return 5;
                        case 9: return 4;
                        case 10: return 12;
                        case 11: return 2;
                        case 12: return 14;
                        case 13: return 7;
                        case 14: return 3;
                        case 15: return 8;
                    }
                case 5:
                    switch(c){
                        default: return 15;
                        case 1: return 14;
                        case 2: return 13;
                        case 3: return 12;
                        case 4: return 1;
                        case 5: return 0;
                        case 6: return 2;
                        case 7: return 3;
                        case 8: return 7;
                        case 9: return 8;
                        case 10: return 10;
                        case 11: return 4;
                        case 12: return 11;
                        case 13: return 5;
                        case 14: return 6;
                        case 15: return 9;
                    }
                case 6:
                    switch(c){
                        default: return 2;
                        case 1: return 4;
                        case 2: return 7;
                        case 3: return 11;
                        case 4: return 1;
                        case 5: return 9;
                        case 6: return 13;
                        case 7: return 8;
                        case 8: return 12;
                        case 9: return 3;
                        case 10: return 10;
                        case 11: return 15;
                        case 12: return 6;
                        case 13: return 0;
                        case 14: return 5;
                        case 15: return 14;
                    }
                case 7:
                    switch(c){
                        default: return 15;
                        case 1: return 12;
                        case 2: return 1;
                        case 3: return 6;
                        case 4: return 11;
                        case 5: return 9;
                        case 6: return 13;
                        case 7: return 3;
                        case 8: return 7;
                        case 9: return 4;
                        case 10: return 2;
                        case 11: return 10;
                        case 12: return 5;
                        case 13: return 8;
                        case 14: return 14;
                        case 15: return 0;
                    }
            }
        }else{
            switch(table){
                default:
                    switch(c){
                        case 2: return 0;
                        case 11: return 1;
                        case 12: return 2;
                        case 3: return 3;
                        case 5: return 4;
                        case 4: return 5;
                        case 15: return 6;
                        case 6: return 7;
                        case 13: return 8;
                        case 7: return 9;
                        case 10: return 10;
                        case 9: return 11;
                        case 8: return 12;
                        default: return 13;
                        case 14: return 14;
                        case 1: return 15;
                    }
                case 1:
                   switch(c){
                        case 15: return 0;
                        case 13: return 1;
                        default: return 2;
                        case 1: return 3;
                        case 7: return 4;
                        case 3: return 5;
                        case 6: return 6;
                        case 5: return 7;
                        case 2: return 8;
                        case 14: return 9;
                        case 4: return 10;
                        case 8: return 11;
                        case 10: return 12;
                        case 9: return 13;
                        case 11: return 14;
                        case 12: return 15;
                    }
                case 2:
                    switch(c){
                        default: return 0;
                        case 1: return 1;
                        case 3: return 2;
                        case 2: return 3;
                        case 4: return 4;
                        case 7: return 5;
                        case 12: return 6;
                        case 10: return 7;
                        case 6: return 8;
                        case 5: return 9;
                        case 8: return 10;
                        case 11: return 11;
                        case 13: return 12;
                        case 15: return 13;
                        case 14: return 14;
                        case 9: return 15;
                    }
                case 3:
                    switch(c){
                        case 13: return 0;
                        case 8: return 1;
                        case 4: return 2;
                        case 12: return 3;
                        case 15: return 4;
                        case 3: return 5;
                        case 5: return 6;
                        case 2: return 7;
                        case 14: return 8;
                        case 1: return 9;
                        case 10: return 10;
                        case 11: return 11;
                        case 6: return 12;
                        default: return 13;
                        case 7: return 14;
                        case 9: return 15;
                    }
                case 4:
                    switch(c){
                        case 15: return 0;
                        case 9: return 1;
                        case 1: return 2;
                        case 13: return 3;
                        case 10: return 4;
                        case 11: return 5;
                        case 6: return 6;
                        default: return 7;
                        case 5: return 8;
                        case 4: return 9;
                        case 12: return 10;
                        case 2: return 11;
                        case 14: return 12;
                        case 7: return 13;
                        case 3: return 14;
                        case 8: return 15;
                    }
                case 5:
                    switch(c){
                        case 15: return 0;
                        case 14: return 1;
                        case 13: return 2;
                        case 12: return 3;
                        case 1: return 4;
                        default: return 5;
                        case 2: return 6;
                        case 3: return 7;
                        case 7: return 8;
                        case 8: return 9;
                        case 10: return 10;
                        case 4: return 11;
                        case 11: return 12;
                        case 5: return 13;
                        case 6: return 14;
                        case 9: return 15;
                    }
                case 6:
                    switch(c){
                        case 2: return 0;
                        case 4: return 1;
                        case 7: return 2;
                        case 11: return 3;
                        case 1: return 4;
                        case 9: return 5;
                        case 13: return 6;
                        case 8: return 7;
                        case 12: return 8;
                        case 3: return 9;
                        case 10: return 10;
                        case 15: return 11;
                        case 6: return 12;
                        default: return 13;
                        case 5: return 14;
                        case 14: return 15;
                    }
                case 7:
                    switch(c){
                        case 15: return 0;
                        case 12: return 1;
                        case 1: return 2;
                        case 6: return 3;
                        case 11: return 4;
                        case 9: return 5;
                        case 13: return 6;
                        case 3: return 7;
                        case 7: return 8;
                        case 4: return 9;
                        case 2: return 10;
                        case 10: return 11;
                        case 5: return 12;
                        case 8: return 13;
                        case 14: return 14;
                        default: return 15;
                    }
            }
        }      
    }
}
