package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import utilidades.ConException;

import java.io.FileInputStream;
import java.util.Properties;

public class Conexion
{
	public static Connection getConexion() throws ConException
	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql159575-vr-veterinaria.jelastic.servint.net/vetevr","root","i546cI8jqZ");
			Properties prop = new Properties();
	        prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
			//con = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria","root","");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3307/vetevr?autoReconnect=true","vetevr","veterinaria2015");
		}
		catch (Exception e) 
		{
			throw new ConException("Error de conexi�n a la BD", e);			
		}		
		return con;
	}
}
