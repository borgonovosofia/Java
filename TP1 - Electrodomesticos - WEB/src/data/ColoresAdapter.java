package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import utilidades.conException;
import negocio.Color;

public class ColoresAdapter {
	public ArrayList<Color> getColores() throws conException
	{
		ArrayList<Color> lista = new ArrayList<Color>();
		try {
			
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select * from colores");
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Color lav = new Color(result.getString("color"));			
				lista.add(lav);
			}
		} catch (conException e) {
			throw new conException("Error de conexion al recuperar los colores", e);
		}		
		catch (Exception e){
			throw new conException("Error al recuperar los colores", e);

		}
		return lista;
	}

	
	public Color buscarPorColor(String col) throws conException
	{
		Color lav = null;
		try {
			
				Connection con = Conexion.getConexion();		
				PreparedStatement statement = con.prepareStatement("select * from colores where color = '"+col+"'");
				ResultSet result = statement.executeQuery();
				result.next();
				if(result.getRow()!=0)
				{	
					lav = new Color(result.getString("color"));}
				} 
		catch (conException e) 
		{
			throw new conException("Error de conexion al recuperar los colores", e);
		}		
		catch (Exception w)
		{
			throw new conException("Error al recuperar los colores",w);
		}
		return lav;
	}

}
