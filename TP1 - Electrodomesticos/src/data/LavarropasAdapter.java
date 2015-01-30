package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utilidades.conException;
import negocio.Lavarropas;

public class LavarropasAdapter {

	public ArrayList<Lavarropas> getLavarropas() throws conException
	{
		ArrayList<Lavarropas> lista = new ArrayList<Lavarropas>();
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select * from lavarropas");
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Lavarropas lav = new Lavarropas(	result.getDouble("carga"),
													result.getDouble("precio"),
													result.getString("color"),
													result.getString("consumo"),
													result.getDouble("peso")
										  	    );
				lav.setId(result.getInt("id"));
				lista.add(lav);
			}
		} catch (SQLException e) {
			throw new conException("Error al recuperar lavarropas", e);
		}		
		return lista;
	}

	public void agregarLavarropas(Lavarropas l) throws conException
	{
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement(
							"insert into lavarropas (color, precio, consumo,peso,carga)"
							+ "values ('"+l.getColor().getColor()+"',"+l.getPrecio_base()+",'"+l.getConsumo().getConsumo()+"',"+l.getPeso()+","+l.getCarga()+")");
			statement.execute();			
		} catch (Exception e) {
			throw new conException("Error al agregar nuevo lavarropas", e);
			
		}	
	}

	public void borrarLavarropas(int id) throws conException
	{
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement(
							"delete from lavarropas where id = '"+id+"'");		
			statement.execute();	
		} catch (Exception e) {
			throw new conException("Error al borrar lavarropas", e);
			
		}
	}

	public Lavarropas buscarPorId(int id) throws conException
	{
		Lavarropas lav = null;
		try {
			
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select * from lavarropas where id = '"+id+"'");	
			ResultSet result = statement.executeQuery();
			lav = new Lavarropas(	result.getDouble("carga"),
												result.getDouble("precio"),
												result.getString("color"),
												result.getString("consumo"),
												result.getDouble("peso")
										  );
			lav.setId(result.getInt("id"));
		} catch (Exception e) {
			throw new conException("Error al buscar lavarropas", e);
		}		
		return lav;
	}

	public void modificarLavarropas(Lavarropas l,int id) throws conException
	{
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement(
							"update lavarropas set color='"+l.getColor().getColor()+"', precio="+l.getPrecio_base()+",consumo='"+l.getConsumo().getConsumo()+"',peso="+l.getPeso()+
							",carga="+l.getCarga()+" WHERE id='"+id+"'");
			statement.execute();
		} catch (Exception e) {
			throw new conException("Error al modificar lavarropas", e);
			
		}	
	}

}
