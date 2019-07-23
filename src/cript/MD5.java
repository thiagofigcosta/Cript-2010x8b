package cript;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class MD5 {
    private String pass="";
    private String salt="";
    
    public MD5(){
    }
    
    public void setPass(String p){
        pass=p;
    }
    
    public void setSalt(String s){
        salt=s;
    }
    
    public String getSalt(){
        return salt;
    }
    public String getPass(){
        return pass;
    }
    
    public MD5(String p, String h){
        pass=p;
        salt=h;
    }
    
    public MD5(String p){
        pass=p;
        salt=genRandomSalt();
    }
    
    public static String genRandomSalt(){
        String out;
        SecureRandom r = null;
        try{
            r=SecureRandom.getInstance("SHA1PRNG");
            r.setSeed(r.generateSeed(6));
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            out=salt.toString();
        }catch(Exception e){
            return "[B@45ee1357";
        }
        return out;
    }
    
    public boolean verify(String hash){
        if(hash==null) return true;
        return hash.equals(md5(pass+salt));
    }
    
    public String getHash(){
        return md5(pass+salt);
    }
    
    private static String md5(String input) {
        String md5 = null;
        if(null==input) return null;
        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return md5;
    }
}
