package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import negocio.*;
import utilidades.*;


public class PropietarioAdapter {

		public ArrayList<Propietario> getPropietarios() throws ConException
		{
			ArrayList<Propietario> lista = new ArrayList<Propietario>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from propietario order by nombre");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id = Integer.parseInt(result.getString("id_propietario"));
					String nombre = result.getString("nombre");
					String apellido = result.getString("apellido");
					String direccion = result.getString("direccion");
					String email = result.getString("email");
					String telefono_fijo = result.getString("telefono_fijo");
					String celular = result.getString("celular");
					String usr = result.getString("usuario");
					String clave = result.getString("clave");

					Propietario Propietario = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular, usr,clave);
					Propietario.setId_propietario(id);
					lista.add(Propietario);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los Propietarios, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los Propietarios, por favor intente mas tarde.", e);

			}
			return lista;
		}
		
		
		public ArrayList<Propietario> getPropietarios(String parecido) throws ConException
		{
			ArrayList<Propietario> lista = new ArrayList<Propietario>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from propietario where nombre like '%"+parecido.toUpperCase()+"%'"
									+" or apellido like '%"+parecido.toUpperCase()+"%' or direccion like '%"+parecido.toUpperCase()
									+"%' or email like '%"+parecido.toUpperCase()+"%'"
									+"or telefono_fijo like '%"+parecido.toUpperCase()+"%'or celular like '%"+parecido.toUpperCase()+"%'"
									+"or usuario like '%"+parecido.toUpperCase()+"%'");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id = Integer.parseInt(result.getString("id_propietario"));
					String nombre = result.getString("nombre");
					String apellido = result.getString("apellido");
					String direccion = result.getString("direccion");
					String email = result.getString("email");
					String telefono_fijo = result.getString("telefono_fijo");
					String celular = result.getString("celular");
					String usr = result.getString("usuario");
					String clave = result.getString("clave");

					Propietario Propietario = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular, usr,clave);
					Propietario.setId_propietario(id);
					lista.add(Propietario);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los Propietarios, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los Propietarios, por favor intente mas tarde.", e);

			}
			return lista;
		}
		
		public Propietario buscarPropietario(int id) throws ConException
		{
			Propietario propietario = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from propietario where id_propietario = '"+id+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						int idp = Integer.parseInt(result.getString("id_propietario"));
						String nombre = result.getString("nombre");
						String apellido = result.getString("apellido");
						String direccion = result.getString("direccion");
						String email = result.getString("email");
						String telefono_fijo = result.getString("telefono_fijo");
						String celular = result.getString("celular");
						String usr = result.getString("usuario");
						String clave = result.getString("clave");

						propietario = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular, usr,clave);
						propietario.setId_propietario(idp);
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar los Propietarios, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar los Propietarios, por favor intente mas tarde.",w);
			}
			return propietario;
		}
		
		public Propietario buscarPropietarioPorUsuario(String usr) throws ConException
		{
			Propietario propietario = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from propietario where upper(usuario) = '"+usr.toUpperCase()+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						int idp = Integer.parseInt(result.getString("id_propietario"));
						String nombre = result.getString("nombre");
						String apellido = result.getString("apellido");
						String direccion = result.getString("direccion");
						String email = result.getString("email");
						String telefono_fijo = result.getString("telefono_fijo");
						String celular = result.getString("celular");
						String clave = result.getString("clave");

						propietario = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular, usr,clave);
						propietario.setId_propietario(idp);

					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar los Propietarios, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar los Propietarios, por favor intente mas tarde.",w);
			}
			return propietario;
		}

		public void agregarPropietario(Propietario t) throws ConException
		{
			try {
				Connection con = Conexion.getConexion();
				String clave = t.getClave();
				clave = TestEncriptarMD5.md5(clave);
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into propietario (nombre,apellido,direccion,email,telefono_fijo,celular,usuario,clave) values ('"+t.getNombre()+"','"
														+t.getApellido()+"','"+t.getDireccion()+"','"+t.getEmail()+"','"
														+t.getTelefono_fijo()+"','"+t.getCelular()+"','"+t.getUsuario()+"','"+clave+"')");

				statement.execute();
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nueva propietario, por favor intente mas tarde.", e);
				
			}
		}

		public void borrarPropietario(int id) throws Exception
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from propietario where id_propietario ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				throw new ConException("Error al eliminar propietario, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("La propietario no puede ser eliminado porque contiene animales registrados", e);
			}
		}

		public void modificarPropietario(Propietario v) throws Exception {
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("update propietario set nombre = '"+v.getNombre()+"',apellido = '"+v.getApellido()+"',direccion = '"+v.getDireccion()+"'"
																+",email = '"+v.getEmail()+"'"+",telefono_fijo = '"+v.getTelefono_fijo()+"'"
																+",celular = '"+v.getCelular()+"'"+",usuario = '"+v.getUsuario()+"'"+",clave = '"+TestEncriptarMD5.md5(v.getClave())+"'"
																+" where id_propietario ='"+v.getId_propietario()+"'");
				System.out.println(statement);
				statement.execute();
				con.close();
			} catch (ConException es) {
				es.printStackTrace();
				throw new ConException("Error al modificar propietario, por favor intente mas tarde. Error de conección.", es);			
			}			
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al modificar propietario, por favor intente mas tarde.", e);
				
			}			
		}
		
		
		
		public Propietario login(String usr,String pass) throws ConException
		{
			Propietario propietario = null;
			try {
					Connection con = Conexion.getConexion();		
					String clave = TestEncriptarMD5.md5(pass);
					PreparedStatement statement = con.prepareStatement("select * from propietario where usuario = '"+usr+"' and clave = '"+clave+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						int idp = Integer.parseInt(result.getString("id_propietario"));
						String nombre = result.getString("nombre");
						String apellido = result.getString("apellido");
						String direccion = result.getString("direccion");
						String email = result.getString("email");
						String telefono_fijo = result.getString("telefono_fijo");
						String celular = result.getString("celular");
						String tipo = result.getString("tipo");

						propietario = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular, usr,clave);
						propietario.setId_propietario(idp);
						propietario.setTipo(tipo);
					}
					else
					{
						propietario = new Propietario();
						propietario.setId_propietario(0);
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al iniciar sesión, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al iniciar sesión, por favor intente mas tarde.",w);
			}
			return propietario;
		}
}

