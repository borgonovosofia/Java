package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class TipoAnimalAdapter {

		public ArrayList<TipoAnimal> getTipos() throws ConException
		{
			ArrayList<TipoAnimal> lista = new ArrayList<TipoAnimal>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from tipo_animal");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					TipoAnimal tipo = new TipoAnimal(result.getString("nombre"));
					tipo.setId_tipo_animal(Integer.parseInt(result.getString("id_tipo_animal")));
					lista.add(tipo);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los tipos de animales, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los tipos de animales, por favor intente mas tarde.", e);

			}
			return lista;
		}
		
		
		public ArrayList<TipoAnimal> getTipos(String parecido) throws ConException
		{
			ArrayList<TipoAnimal> lista = new ArrayList<TipoAnimal>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from tipo_animal where nombre like '%"+parecido.toUpperCase()+"%'");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					TipoAnimal tipo = new TipoAnimal(result.getString("nombre"));
					tipo.setId_tipo_animal(Integer.parseInt(result.getString("id_tipo_animal")));
					lista.add(tipo);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los tipos de animales, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los tipos de animales, por favor intente mas tarde.", e);

			}
			return lista;
		}

		
		public TipoAnimal buscarTipo(int id) throws ConException
		{
			TipoAnimal tipo = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from tipo_animal where id_tipo_animal = '"+id+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						tipo = new TipoAnimal(result.getString("nombre"));
						tipo.setId_tipo_animal(Integer.parseInt(result.getString("id_tipo_animal")));
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar los tipos de animales, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar los tipos de animales, por favor intente mas tarde.",w);
			}
			return tipo;
		}

		
		public TipoAnimal buscarTipo(String nombreTipo) throws ConException
		{
			TipoAnimal tipo = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from tipo_animal where nombre = '"+nombreTipo.toUpperCase()+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						tipo = new TipoAnimal(result.getString("nombre"));
						tipo.setId_tipo_animal(Integer.parseInt(result.getString("id_tipo_animal")));
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar los tipos de animales, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar los tipos de animales, por favor intente mas tarde.",w);
			}
			return tipo;
		}

		
		
		public void agregarTipo(TipoAnimal t) throws ConException
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into tipo_animal (nombre) values ('"+(t.getNombre().toUpperCase())+"')");
				statement.execute();
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nuevo animal, por favor intente mas tarde.", e);
				
			}
		}

		public void borrarTipo(int id) throws Exception
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from tipo_animal where id_tipo_animal ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				throw new ConException("Error al eliminar animal, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("El animal no puede ser eliminado porque contiene razas registradas. Elimine sus razas antes de eliminar el animal", e);
				
			}
		}

		public void modificarTipo(int id, String nuevoNombre) throws Exception {
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("update tipo_animal set nombre = '"+nuevoNombre.toUpperCase()+"' where id_tipo_animal ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				es.printStackTrace();
				throw new ConException("Error al modificar animal, por favor intente mas tarde. Error de conección.", es);			
			}			
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al modificar animal, por favor intente mas tarde.", e);
				
			}			
		}
}

