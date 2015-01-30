package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import utilidades.conException;
import negocio.Television;

public class TelevisorAdapter {

	public ArrayList<Television> getTvs() throws conException
	{
		ArrayList<Television> lista = new ArrayList<Television>();
		try {
			
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select * from televisores");
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Television tv = new Television(	result.getInt("resolucion"),
											result.getBoolean("sintonizador"),
											result.getDouble("precio"),
											result.getString("color"),
											result.getString("consumo"),
											result.getDouble("peso")
										  );
				tv.setId(result.getInt("id"));
				lista.add(tv);
			}
		} catch (Exception e) {
			throw new conException("Error al recuperar televisores", e);
		}		
		return lista;
	}

	public void agregarTelevision(Television l) throws conException
	{
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement(
							"insert into televisores (color, precio, consumo,peso,resolucion,sintonizador)"
							+ "values ('"+l.getColor().getColor()+"',"+l.getPrecio_base()+",'"+l.getConsumo().getConsumo()+"',"+l.getPeso()+","+l.getResolucion()+","+l.getSintonizador()+")");
			statement.execute();			
		} catch (Exception e) {
			throw new conException("Error al insertar televisor", e);

		}	
	}

	public void borrarTelevision(int id) throws conException 
	{
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement(
							"delete from televisores where id = '"+id+"'");		
			statement.execute();	
		} catch (Exception e) {
			throw new conException("Error al borrar televisor", e);			
		}
	}

	public Television buscarPorId(int id) throws conException
	{
		Television lav = null;
		try {
			
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select * from televisores where id = '"+id+"'");	
			ResultSet result = statement.executeQuery();
			lav = new Television(	result.getInt("resolucion"),
										result.getBoolean("sintonizador"),
										result.getDouble("precio"),
										result.getString("color"),
										result.getString("consumo"),
										result.getDouble("peso")
										  );
			lav.setId(result.getInt("id"));
		} catch (Exception e) {
			throw new conException("Error al buscar televisor", e);
			
		}		
		return lav;
	}

	public void modificarTelevision(Television l,int id) throws conException
	{ 
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement(
							"update televisores set color='"+l.getColor().getColor()+"', precio="+l.getPrecio_base()+",consumo='"+l.getConsumo().getConsumo()+"',peso="+l.getPeso()+
							",resolucion="+l.getResolucion()+",sintonizador="+l.getSintonizador()+" WHERE id='"+id+"'");
			statement.execute();
		} catch (Exception e) {
			throw new conException("Error al modificar televisor", e);
			
		}	
	}



}
