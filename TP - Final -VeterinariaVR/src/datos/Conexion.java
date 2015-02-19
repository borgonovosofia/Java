package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import utilidades.ConException;

public class Conexion
{
	public static Connection getConexion() throws ConException
	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria","root","");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/vetevr?autoReconnect=true","vetevr","veterinaria2015");
		}
		catch (Exception e) 
		{
			throw new ConException("Error de conexión a la BD", e);			
		}		
		return con;
	}
}
