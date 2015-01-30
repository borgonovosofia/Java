package data;

import java.sql.Connection;
import java.sql.DriverManager;

import utilidades.conException;

public class Conexion
{
	public static Connection getConexion() throws conException
	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/electrodomesticos","root","");

		}
		catch (Exception e) 
		{
			throw new conException("Error de conexión a la BD", e);
			
		}
		
		return con;
	}
}
