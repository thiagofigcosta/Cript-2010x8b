package test;

import cript.*;

public class AssistedTesting_OperationsS {
    
    public static final String TestData="Som, testando, 1,2,3, cript2010x8b";
    
    public static void main(String[] args) throws Exception { 
        System.out.println("===Assisted Corruption Testing===");
        System.out.println("String:"+TestData);
        System.out.println("=SubHash Routines:");
        System.out.print("Add(1,5):"+OperationsSlim.add(TestData, 1, 5));
        if(TestData.equals(OperationsSlim.add(Operations.add(TestData, 1, 5), 1, -5)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Add(3,-6):"+OperationsSlim.add(TestData, 3, -6));
        if(TestData.equals(OperationsSlim.add(Operations.add(TestData, 3, -6), 3, 6)))System.out.println("...OK");
                                                                      else System.out.println("...fail");  
        System.out.print("Add(5,10):"+OperationsSlim.add(TestData, 5, 10));
        if(TestData.equals(OperationsSlim.add(Operations.add(TestData, 5, 10), 5, -10)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("AddB(15,69):"+OperationsSlim.add(TestData, 15, 69));
        if(TestData.equals(OperationsSlim.add(Operations.add(TestData, 15, 69), 15, -69)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Invert():"+OperationsSlim.invert(TestData)); 
        if(TestData.equals(OperationsSlim.invert(Operations.invert(TestData))))System.out.println("...OK");
                                                               else System.out.println("...fail");
        System.out.print("Offset(bits,10):"+OperationsSlim.offset(TestData, true, 10));
        if(TestData.equals(OperationsSlim.offset(Operations.offset(TestData, true, 10), true, -10)))System.out.println("...OK");
                                                                                   else System.out.println("...fail");
        System.out.print("Offset(bytes,-33):"+OperationsSlim.offset(TestData, false, -33));
        if(TestData.equals(OperationsSlim.offset(Operations.offset(TestData, false, -33), false, 33)))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Offset(bits,50):"+OperationsSlim.offset(TestData, true, 50));
        if(TestData.equals(OperationsSlim.offset(Operations.offset(TestData, true, 50), true, -50)))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Offset(bytes,50):"+OperationsSlim.offset(TestData, false, 50));
        if(TestData.equals(OperationsSlim.offset(Operations.offset(TestData, false, 50), false, -50)))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Tables(3):"+OperationsSlim.switchTable(TestData, false, 3));
        if(TestData.equals(OperationsSlim.switchTable(Operations.switchTable(TestData, false, 3), true, 3)))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
        System.out.print("Tables(7):"+OperationsSlim.switchTable(TestData, true, 7));
        if(TestData.equals(OperationsSlim.switchTable(Operations.switchTable(TestData, true, 7), false, 7)))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
        System.out.print("Mult(1,7):"+OperationsSlim.mult(TestData, 1, 7));
        if(TestData.equals(OperationsSlim.div(OperationsSlim.mult(TestData, 1, 7), 1, 7)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Mult(2,15):"+OperationsSlim.mult(TestData, 2, 15));
        if(TestData.equals(OperationsSlim.div(OperationsSlim.mult(TestData, 2, 15), 2, 15)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Mult(3,15):"+OperationsSlim.mult(TestData, 3, 15));
        if(TestData.equals(OperationsSlim.div(OperationsSlim.mult(TestData, 3, 15), 3, 15)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        }
}
