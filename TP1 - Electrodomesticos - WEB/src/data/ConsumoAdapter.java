package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utilidades.conException;
import negocio.Consumo;

public class ConsumoAdapter {
	
	public ArrayList<Consumo> getConsumos() throws conException
	{
		ArrayList<Consumo> lista = new ArrayList<Consumo>();
		try {
			
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select * from consumos");
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Consumo lav = new Consumo(result.getString("consumo"), result.getDouble("precio") );			
				lista.add(lav);
			}
		} catch (conException e) {
			throw new conException("Error en la conexion al recuperar los consumos", e);
		}		
		catch (Exception w)
		{
			throw new conException("Error al recuperar los consumos", w);
		}
		return lista;
	}

	
	public Consumo buscarPorId(String nom)
	{
		Consumo lav = null;
		try
		{
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select * from consumos where consumo = '"+nom+"'");	
			ResultSet result = statement.executeQuery();
			result.next();
			if(result.getRow()!=0)
			{			
				lav = new Consumo(result.getString("consumo"),result.getDouble("precio"));
			}
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null,"No se encuentra el consumo"); 
		}		
		return lav;
	}

}
