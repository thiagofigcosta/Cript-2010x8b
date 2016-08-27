package test;

import cript.*;

public class AssistedTesting_Operations {
    
    public static final String TestData="Som, testando, 1,2,3, cript2010x8b";
    
    public static void main(String[] args) { 
        System.out.println("===Assisted Corruption Testing===");
        System.out.println("String:"+TestData);
        System.out.println("=SubHash Routines:");
        System.out.print("Add(1,5):"+Operations.add(TestData, 1, 5));
        if(TestData.equals(Operations.add(Operations.add(TestData, 1, 5), 1, -5)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Add(3,-6):"+Operations.add(TestData, 3, -6));
        if(TestData.equals(Operations.add(Operations.add(TestData, 3, -6), 3, 6)))System.out.println("...OK");
                                                                      else System.out.println("...fail");  
        System.out.print("Add(5,10):"+Operations.add(TestData, 5, 10));
        if(TestData.equals(Operations.add(Operations.add(TestData, 5, 10), 5, -10)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("AddB(15,69):"+Operations.add(TestData, 15, 69));
        if(TestData.equals(Operations.add(Operations.add(TestData, 15, 69), 15, -69)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Invert():"+Operations.invert(TestData)); 
        if(TestData.equals(Operations.invert(Operations.invert(TestData))))System.out.println("...OK");
                                                               else System.out.println("...fail");
        System.out.print("Offset(bits,10):"+Operations.offset(TestData, true, 10));
        if(TestData.equals(Operations.offset(Operations.offset(TestData, true, 10), true, -10)))System.out.println("...OK");
                                                                                   else System.out.println("...fail");
        System.out.print("Offset(bytes,-33):"+Operations.offset(TestData, false, -33));
        if(TestData.equals(Operations.offset(Operations.offset(TestData, false, -33), false, 33)))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Tables(3):"+Operations.switchTable(TestData, false, 3));
        if(TestData.equals(Operations.switchTable(Operations.switchTable(TestData, false, 3), true, 3)))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
        System.out.print("Tables(7):"+Operations.switchTable(TestData, true, 7));
        if(TestData.equals(Operations.switchTable(Operations.switchTable(TestData, true, 7), false, 7)))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
        System.out.print("Mult(1,7):"+Operations.mult(TestData, 1, 7));
        if(TestData.equals(Operations.div(Operations.mult(TestData, 1, 7), 1, 7)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Mult(2,15):"+Operations.mult(TestData, 2, 15));
        if(TestData.equals(Operations.div(Operations.mult(TestData, 2, 15), 2, 15)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Mult(3,15):"+Operations.mult(TestData, 3, 15));
        if(TestData.equals(Operations.div(Operations.mult(TestData, 3, 15), 3, 15)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Pow(1,7):"+"doesnt work 4 this type of compression");//+Operations.pow(TestData, 1, 7));
        if(TestData.equals(Operations.nthrt(Operations.pow(TestData, 1, 7), 1, 7)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Pow(1,2):"+"doesnt work 4 this type of compression");//+Operations.pow(TestData, 1, 2));
        if(TestData.equals(Operations.nthrt(Operations.pow(TestData, 1, 2), 1, 2)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Pow(2,2):"+"doesnt work 4 this type of compression");//Operations.pow(TestData, 2, 2));
        if(TestData.equals(Operations.nthrt(Operations.pow(TestData, 2, 2), 2, 2)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        Hash test=Hash.genRandom();
        test.setDecriptedSubHash(TestData);
        test.criptSubHash();
        System.out.print("Hash Cript:"+test.getCriptedSubHash());
        test.decriptSubHash();
        if(TestData.equals(test.getDecriptedSubHash()))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        }
}
