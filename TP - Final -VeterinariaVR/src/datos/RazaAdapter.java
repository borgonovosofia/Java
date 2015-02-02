package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.Raza;
import negocio.TipoAnimal;
import utilidades.ConException;

public class RazaAdapter {
	public ArrayList<Raza> getRazas() throws ConException
	{
		ArrayList<Raza> lista = new ArrayList<Raza>();
		try 
		{	
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement("select raza.id_raza'id_raza',raza.nombre'nombre_raza',tipo_animal.nombre'tipo' "
										+"from raza inner join tipo_animal on raza.id_tipo_animal = tipo_animal.id_tipo_animal"
										+" order by tipo");
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				TipoAnimal t = new TipoAnimal(result.getString("tipo"));
				Raza r = new Raza(result.getString("nombre_raza"), t);	
				r.setId_raza(Integer.parseInt(result.getString("id_raza")));
				lista.add(r);
			}
			con.close();
		} 
		catch (ConException e) 
		{
			throw new ConException("Error de conexion al recuperar las razas, por favor intente mas tarde.", e);
		}		
		catch (Exception e){
			e.printStackTrace();
			throw new ConException("Error al recuperar las razas, por favor intente mas tarde.", e);

		}
		return lista;
	}

	public ArrayList<Raza> getRazas(String parecido) throws ConException
	{
		ArrayList<Raza> lista = new ArrayList<Raza>();
		try 
		{	
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement("select raza.id_raza'id_raza',raza.nombre'nombre_raza',tipo_animal.nombre'tipo' "
										+"from raza inner join tipo_animal on raza.id_tipo_animal = tipo_animal.id_tipo_animal"
										+" where raza.nombre like '%"+parecido+"%' or tipo_animal.nombre like '%"+parecido+"%' order by tipo");
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				TipoAnimal t = new TipoAnimal(result.getString("tipo"));
				Raza r = new Raza(result.getString("nombre_raza"), t);	
				r.setId_raza(Integer.parseInt(result.getString("id_raza")));
				lista.add(r);
			}
			con.close();
		} 
		catch (ConException e) 
		{
			throw new ConException("Error de conexion al recuperar las razas, por favor intente mas tarde.", e);
		}		
		catch (Exception e){
			e.printStackTrace();
			throw new ConException("Error al recuperar las razas, por favor intente mas tarde.", e);

		}
		return lista;
	}
	
	public Raza buscarRaza(String nombre,String tipo) throws ConException
	{
		Raza raza = null;
		try 
		{			
				Connection con = Conexion.getConexion();		
				PreparedStatement statement = con.prepareStatement("select raza.id_raza'id_raza', raza.nombre'nombre_raza', tipo_animal.nombre'tipo'"
																+" from raza inner join tipo_animal on raza.id_tipo_animal = tipo_animal.id_tipo_animal "
																+"where raza.nombre = '"+nombre.toUpperCase()+"' and tipo_animal.nombre = '"+tipo.toUpperCase()+"'");
				ResultSet result = statement.executeQuery();
				result.next();
				if(result.getRow()!=0)
				{	
					TipoAnimal t = new TipoAnimal(tipo);
					raza = new Raza(nombre, t);
				}
				con.close();
		}
		catch (ConException e) 
		{
			throw new ConException("Error de conexion al recuperar las razas, por favor intente mas tarde.", e);
		}		
		catch (Exception w)
		{
			throw new ConException("Error al recuperar las razas, por favor intente mas tarde.",w);
		}
		return raza;
	}
	
	public Raza buscarRaza(int id) throws ConException
	{
		Raza raza = null;
		try 
		{			
				Connection con = Conexion.getConexion();		
				PreparedStatement statement = con.prepareStatement("select raza.id_raza'id_raza', raza.nombre'nombre_raza', tipo_animal.nombre'tipo'"
																+" from raza inner join tipo_animal on raza.id_tipo_animal = tipo_animal.id_tipo_animal "
																+"where raza.id_raza = '"+id+"'");
				ResultSet result = statement.executeQuery();
				result.next();
				if(result.getRow()!=0)
				{	
					TipoAnimal t = new TipoAnimal(result.getString("tipo"));
					raza = new Raza(result.getString("nombre_raza"), t);
					raza.setId_raza(id);
				}
				con.close();
		}
		catch (ConException e) 
		{
			throw new ConException("Error de conexion al recuperar las razas, por favor intente mas tarde.", e);
		}		
		catch (Exception w)
		{
			throw new ConException("Error al recuperar las razas, por favor intente mas tarde.",w);
		}
		return raza;
	}

	
	public void agregarRaza(Raza r) throws ConException
	{
		try 
		{
			Connection con = Conexion.getConexion();	
			
			String nombreTipo = r.getTipo_animal().getNombre();
			TipoAnimal tipoBuscado = TipoAnimal.buscarTipo(nombreTipo);
			
			PreparedStatement statement = 
					con.prepareStatement(
							"insert into raza (nombre,id_tipo_animal) values ('"+r.getNombre().toUpperCase()+"','"+tipoBuscado.getId_tipo_animal()+"')");
			statement.execute();
			con.close();
		} 
		catch (Exception e) 
		{
			throw new ConException("Error al agregar nueva raza, por favor intente mas tarde.", e);			
		}	
	}

	@SuppressWarnings("finally")
	public boolean borrarRaza(int id) throws Exception
	{
		boolean rta = false;
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement("delete from raza where id_raza ='"+id+"'");
			statement.execute();
			con.close();
			rta = true;
		} 
		catch (ConException es) {
			throw new ConException("Error al eliminar la raza, por favor intente mas tarde.", es);	
		}			
		catch (Exception e) {
			throw new Exception("Error al eliminar la raza, por favor intente mas tarde.", e);
		
		}
		finally{
			return rta;
		}
	}

	public void modificarRaza(int id, String nuevoNombre) throws Exception {
		try {
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = 
					con.prepareStatement("update raza set nombre = '"+nuevoNombre.toUpperCase()+"' where id_raza ='"+id+"'");
			statement.execute();
			con.close();
		} catch (ConException es) {
			es.printStackTrace();
			throw new ConException("Error al modificar la raza, por favor intente mas tarde. Error de conección.", es);			
		}			
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al modificar la raza, por favor intente mas tarde.", e);
			
		}			
	}



}
