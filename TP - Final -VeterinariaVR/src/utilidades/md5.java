package utilidades;

import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;

public class md5 {
   
	    public static String get_md5(String CadenaOriginal){
	        
	          
	        String md5="";
	       
	        try {
	            if (!CadenaOriginal.equalsIgnoreCase("")) {

	                MessageDigest md = MessageDigest.getInstance("MD5");
	                md.reset();
	                md.update(CadenaOriginal.getBytes());
	                byte bytes[] = md.digest();
	                StringBuilder sb = new StringBuilder();
	                for (int i = 0; i < bytes.length; i++) {
	                    String hex = Integer.toHexString(0xff & bytes[i]);
	                    if (hex.length() == 1) {
	                        sb.append('0');
	                    }
	                    sb.append(hex);
	                }

	                md5 = sb.toString();
	            }
	        } catch (NoSuchAlgorithmException e) {
	            md5 = "Error inesperado";
	           
	        }
	        return md5;
	   
	    }
}
