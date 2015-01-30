package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import java.sql.SQLException;

import utilidades.conException;
import negocio.Pesos;

public class PesosAdapter {

	public Pesos buscarDimension(double peso) throws conException
	{
		try {
			Pesos dim = null;
				
					Connection con = Conexion.getConexion();			
					PreparedStatement statement = 
									con.prepareStatement("select * from pesos where minimo<= '"+peso+"' and maximo >'"+peso+"'");	
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						dim = new Pesos(result.getDouble("minimo"),result.getDouble("maximo"),result.getDouble("precio"));
					}
					

			return dim;
		} catch (SQLException e) {
			throw new conException("Error de conexion al recuperar los pesos", e);
		}
		catch (conException w){
			throw new conException("Error al recuperar los pesos", w);
		}
	}

}
