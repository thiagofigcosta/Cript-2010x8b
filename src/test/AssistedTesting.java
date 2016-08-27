package test;

import cript.Operations;

public class AssistedTesting {
    
    public static final String TestData="Som, testando, 1,2,3, cript2010x8b";
    
    public static void main(String[] args) { 
        System.out.println("===Assisted Corruption Testing===");
        System.out.println("String:"+TestData);
        System.out.println("=SubHash Routines:");
        System.out.print("Add(1,5):"+Operations.add(TestData, 1, 5));
        if(TestData==Operations.add(Operations.add(TestData, 1, 5), 1, -5))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Add(3,-6):"+Operations.add(TestData, 3, -6));
        if(TestData==Operations.add(Operations.add(TestData, 3, -6), 3, 6))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Add(5,10):"+Operations.add(TestData, 5, 10));
        if(TestData==Operations.add(Operations.add(TestData, 5, 10), 5, 10))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Invert():"+Operations.invert(TestData)); 
        if(TestData==Operations.invert(Operations.invert(TestData)))System.out.println("...OK");
                                                               else System.out.println("...fail");
        System.out.print("Offset(bits,10):"+Operations.offset(TestData, true, 10));
        if(TestData==Operations.offset(Operations.offset(TestData, true, 10), true, -10))System.out.println("...OK");
                                                                                   else System.out.println("...fail");
        System.out.print("Offset(bytes,-33):"+Operations.offset(TestData, false, -33));
        if(TestData==Operations.offset(Operations.offset(TestData, false, -33), false, 33))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Tables(0):"+Operations.switchTable(TestData, false, 0));
        if(TestData==Operations.switchTable(Operations.switchTable(TestData, false, 0), true, 0))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
        System.out.print("Tables(7):"+Operations.switchTable(TestData, true, 7));
        if(TestData==Operations.switchTable(Operations.switchTable(TestData, true, 7), false, 7))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
    }
}
