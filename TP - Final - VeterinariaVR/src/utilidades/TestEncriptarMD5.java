package utilidades;
import java.security.MessageDigest;
 
public class TestEncriptarMD5
{
    public static String md5(String clear)  {
    	    try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] b = md.digest(clear.getBytes());
				int size = b.length;
				StringBuffer h = new StringBuffer(size);
				//algoritmo y arreglo md5
				    for (int i = 0; i < size; i++) {
				        int u = b[i] & 255;
				            if (u < 16) {
				                h.append("0" + Integer.toHexString(u));
				            }
				           else {
				                h.append(Integer.toHexString(u));
				           }
				       }
				  //clave encriptada
				  return h.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
    	 
   }

}