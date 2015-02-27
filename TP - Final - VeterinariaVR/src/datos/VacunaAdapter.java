package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class VacunaAdapter {

		public ArrayList<Vacuna> getVacunas() throws ConException
		{
			ArrayList<Vacuna> lista = new ArrayList<Vacuna>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from vacuna");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					Vacuna Vacuna = new Vacuna(result.getString("codigo"),result.getString("nombre"),result.getString("marca"),Integer.parseInt(result.getString("duracion")));
					Vacuna.setId_vacuna(Integer.parseInt(result.getString("id_vacuna")));
					lista.add(Vacuna);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las Vacunas, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las Vacunas, por favor intente mas tarde.", e);

			}
			return lista;
		}
		
		
		public ArrayList<Vacuna> getVacunas(String parecido) throws ConException
		{
			ArrayList<Vacuna> lista = new ArrayList<Vacuna>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from vacuna where codigo like '%"+parecido.toUpperCase()+"%' or nombre like '%"+parecido.toUpperCase()+"%' or marca like '%"+parecido.toUpperCase()+"%' or duracion like '%"+parecido.toUpperCase()+"%'");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					Vacuna Vacuna = new Vacuna(result.getString("codigo"),result.getString("nombre"),result.getString("marca"),Integer.parseInt(result.getString("duracion")));
					Vacuna.setId_vacuna(Integer.parseInt(result.getString("id_vacuna")));
					lista.add(Vacuna);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las Vacunas, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las Vacunas, por favor intente mas tarde.", e);

			}
			return lista;
		}
		
		public Vacuna buscarVacuna(int id) throws ConException
		{
			Vacuna vacuna = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from vacuna where id_vacuna = '"+id+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						vacuna = new Vacuna(result.getString("codigo"),result.getString("nombre"),result.getString("marca"),Integer.parseInt(result.getString("duracion")));
						vacuna.setId_vacuna(Integer.parseInt(result.getString("id_vacuna")));
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar las Vacunas, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar las Vacunas, por favor intente mas tarde.",w);
			}
			return vacuna;
		}
		
		public Vacuna buscarVacunaPorCodigo(String codigoVacuna) throws ConException
		{
			Vacuna vacuna = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from vacuna where nombre = '"+codigoVacuna.toUpperCase()+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						vacuna = new Vacuna(result.getString("codigo"),result.getString("nombre"),result.getString("marca"),Integer.parseInt(result.getString("duracion")));
						vacuna.setId_vacuna(Integer.parseInt(result.getString("id_vacuna")));
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar las Vacunas, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar las Vacunas, por favor intente mas tarde.",w);
			}
			return vacuna;
		}
		
		public Vacuna buscarVacunaPorNombre(String codigoVacuna) throws ConException
		{
			Vacuna vacuna = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from vacuna where codigo = '"+codigoVacuna.toUpperCase()+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						vacuna = new Vacuna(result.getString("codigo"),result.getString("nombre"),result.getString("marca"),Integer.parseInt(result.getString("duracion")));
						vacuna.setId_vacuna(Integer.parseInt(result.getString("id_vacuna")));
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar las Vacunas, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar las Vacunas, por favor intente mas tarde.",w);
			}
			return vacuna;
		}

		
		
		public void agregarVacuna(Vacuna t) throws ConException
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into vacuna (codigo,nombre,marca,duracion) values ('"+t.getCodigo()+"','"+t.getNombre()+"','"+t.getMarca()+"','"+t.getDuracion()+"')");
				statement.execute();
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nueva vacuna, por favor intente mas tarde.", e);
				
			}
		}

		public void borrarVacuna(int id) throws Exception
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from vacuna where id_vacuna ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				throw new ConException("Error al eliminar vacuna, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("La vacuna no puede ser eliminado porque contiene vacunaciones realizadas con la misma. Elimine las vacunaciones antes de eliminar la vacuna", e);
			}
		}

		public void modificarVacuna(Vacuna v) throws Exception {
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("update vacuna set codigo = '"+v.getCodigo()+"',nombre = '"+v.getNombre()+"',marca = '"+v.getMarca()+"',duracion = '"+v.getDuracion()+"' where id_vacuna ='"+v.getId_vacuna()+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				es.printStackTrace();
				throw new ConException("Error al modificar vacuna, por favor intente mas tarde. Error de conección.", es);			
			}			
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al modificar vacuna, por favor intente mas tarde.", e);
				
			}			
		}
}

