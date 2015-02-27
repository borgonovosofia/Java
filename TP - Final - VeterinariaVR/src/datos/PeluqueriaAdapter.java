package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class PeluqueriaAdapter {

		public ArrayList<Peluqueria> getPeluquerias() throws ConException
		{
			ArrayList<Peluqueria> lista = new ArrayList<Peluqueria>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select peluqueria.*, animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre,"
																	+"raza.id_raza, raza.nombre'nombre_raza',"
																	+"tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+"propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+"propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+"propietario.usuario, propietario.clave from peluqueria "
																	+ "inner join animal on peluqueria.id_animal = animal.id_animal"
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+"inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal"
																	+ " order by peluqueria.id_peluqueria asc");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id = Integer.parseInt(result.getString("id_animal"));
					String nombre = result.getString("nombre");				
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");			
					
					String nombreP = result.getString("nombreP");
					String apellidoP = result.getString("apellidoP");
					int id_propietario = Integer.parseInt(result.getString("id_propietario"));
					String direccion = result.getString("direccion");
					String email = result.getString("email");
					String telefono_fijo = result.getString("telefono_fijo");
					String celular = result.getString("celular");
					String usuario = result.getString("usuario");
					String clave = result.getString("clave");

					int id_peluqueria = result.getInt("id_peluqueria");
					String fecha = result.getString("fecha");
					String accion = result.getString("accion");
					String comentarios = result.getString("comentarios");

					int id_raza = Integer.parseInt(result.getString("id_raza"));
					String nombre_raza = result.getString("nombre_raza");

					int id_tipo = Integer.parseInt(result.getString("id_tipo_animal"));
					String nombre_tipo = result.getString("nombre_tipo");
					
					TipoAnimal tip = new TipoAnimal(nombre_tipo);
					tip.setId_tipo_animal(id_tipo);
					
					Raza raz = new Raza(nombre_raza,tip);
					raz.setId_raza(id_raza);
					
					Propietario prop  = new Propietario(nombreP,apellidoP,direccion,email,telefono_fijo,celular,usuario,clave);
					prop.setId_propietario(id_propietario);
					
					Animal animal = new Animal(fecha_nac,sexo,nombre,raz, prop);
					animal.setId_animal(id);
							
					Peluqueria peluqueria = new Peluqueria(fecha,accion,comentarios,animal);
					peluqueria.setId_peluqueria(id_peluqueria);
					lista.add(peluqueria);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las peluquerias, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los animales, por favor intente mas tarde.", e);
			}
			return lista;
		}
		
		public ArrayList<Peluqueria> getPeluquerias(String parecido) throws ConException
		{
			ArrayList<Peluqueria> lista = new ArrayList<Peluqueria>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select peluqueria.*, animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre,"
																	+"raza.id_raza, raza.nombre'nombre_raza',"
																	+"tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+"propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+"propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+"propietario.usuario, propietario.clave from peluqueria "
																	+ "inner join animal on peluqueria.id_animal = animal.id_animal"
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+"inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal"
																	+ " where propietario.nombre like '%"+parecido.toUpperCase()+"%' "
																	+" or propietario.apellido like '%"+parecido.toUpperCase()+"%' "
																	+" or animal.nombre like '%"+parecido.toUpperCase()+"%' "
																	+ "or raza.nombre like '%"+parecido.toUpperCase()+"%' "
																	+ "or tipo_animal.nombre like '%"+parecido.toUpperCase()+"%'"
																	+ "or peluqueria.id_peluqueria like '%"+parecido.toUpperCase()+"%'"
																	+ "or peluqueria.fecha like '%"+parecido.toUpperCase()+"%'"
																	+ "or peluqueria.comentarios like '%"+parecido.toUpperCase()+"%'"
																	+ "or peluqueria.accion like '%"+parecido.toUpperCase()+"%'"
																	
																	+ " order by animal.id_animal asc");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id = Integer.parseInt(result.getString("id_animal"));
					String nombre = result.getString("nombre");				
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");			
					
					String nombreP = result.getString("nombreP");
					String apellidoP = result.getString("apellidoP");
					int id_propietario = Integer.parseInt(result.getString("id_propietario"));
					String direccion = result.getString("direccion");
					String email = result.getString("email");
					String telefono_fijo = result.getString("telefono_fijo");
					String celular = result.getString("celular");
					String usuario = result.getString("usuario");
					String clave = result.getString("clave");

					int id_peluqueria = result.getInt("id_peluqueria");
					String fecha = result.getString("fecha");
					String accion = result.getString("accion");
					String comentarios = result.getString("comentarios");

					int id_raza = Integer.parseInt(result.getString("id_raza"));
					String nombre_raza = result.getString("nombre_raza");

					int id_tipo = Integer.parseInt(result.getString("id_tipo_animal"));
					String nombre_tipo = result.getString("nombre_tipo");
					
					TipoAnimal tip = new TipoAnimal(nombre_tipo);
					tip.setId_tipo_animal(id_tipo);
					
					Raza raz = new Raza(nombre_raza,tip);
					raz.setId_raza(id_raza);
					
					Propietario prop  = new Propietario(nombreP,apellidoP,direccion,email,telefono_fijo,celular,usuario,clave);
					prop.setId_propietario(id_propietario);
					
					Animal animal = new Animal(fecha_nac,sexo,nombre,raz, prop);
					animal.setId_animal(id);
							
					Peluqueria peluqueria = new Peluqueria(fecha,accion,comentarios,animal);
					peluqueria.setId_peluqueria(id_peluqueria);
					lista.add(peluqueria);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los animales, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los animales, por favor intente mas tarde.", e);
			}
			return lista;
		}

		public Peluqueria buscarPeluqueria(int idA) throws ConException
		{
			Peluqueria peluqueria = new Peluqueria();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select peluqueria.fecha, peluqueria.id_peluqueria,peluqueria.accion,peluqueria.comentarios, animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre,"
																	+" raza.id_raza, raza.nombre'nombre_raza',"
																	+" tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+" propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+" propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+" propietario.usuario, propietario.clave from peluqueria"
																	+ " inner join animal on animal.id_animal = peluqueria.id_animal "
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+" inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal "
																	+ " where peluqueria.id_peluqueria='"+idA+"'");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id = Integer.parseInt(result.getString("id_animal"));
					String nombre = result.getString("nombre");
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");
					
					
					String nombreP = result.getString("nombreP");
					String apellidoP = result.getString("apellidoP");
					int id_propietario = Integer.parseInt(result.getString("id_propietario"));
					String direccion = result.getString("direccion");
					String email = result.getString("email");
					String telefono_fijo = result.getString("telefono_fijo");
					String celular = result.getString("celular");
					String usuario = result.getString("usuario");
					String clave = result.getString("clave");


					int id_raza = Integer.parseInt(result.getString("id_raza"));
					String nombre_raza = result.getString("nombre_raza");

					int id_tipo = Integer.parseInt(result.getString("id_tipo_animal"));
					String nombre_tipo = result.getString("nombre_tipo");
					
					TipoAnimal tip = new TipoAnimal(nombre_tipo);
					tip.setId_tipo_animal(id_tipo);
					
					Raza raz = new Raza(nombre_raza,tip);
					raz.setId_raza(id_raza);
					
					Propietario prop  = new Propietario(nombreP,apellidoP,direccion,email,telefono_fijo,celular,usuario,clave);
					prop.setId_propietario(id_propietario);
					
					Animal animal = new Animal(fecha_nac,sexo,nombre,raz, prop);
					animal.setId_animal(id);
					
					String fecha = result.getString("fecha");
					int id_peluqueria = Integer.parseInt(result.getString("id_peluqueria"));
					String accion = result.getString("accion");
					String comentarios = result.getString("comentarios");

					
					peluqueria = new Peluqueria(fecha,accion,comentarios,animal);
					peluqueria.setId_peluqueria(id_peluqueria);
						
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los animales, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los animales, por favor intente mas tarde.", e);
			}
			return peluqueria;
		}
		
		public void borrarPeluqueria(int id) throws Exception
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from peluqueria where id_peluqueria ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				throw new ConException("Error al eliminar Peluqueria, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("La Peluqueria no puede ser eliminado porque está siendo utilizado en consultas y/o peluqueria.", e);
			}
		}

		public void modificarPeluqueria(Peluqueria p) throws Exception {
				try {
					Connection con = Conexion.getConexion();			
					PreparedStatement statement = 
							con.prepareStatement("update peluqueria set fecha = '"+p.getFecha()+"',accion = '"+p.getAccion()+"',comentarios = '"+p.getComentarios()+"'"													
													+" where id_peluqueria ='"+p.getId_peluqueria()+"'");
					statement.execute();
					con.close();
				} catch (ConException es) {
					es.printStackTrace();
					throw new ConException("Error al modificar peluqueria, por favor intente mas tarde.", es);			
				}			
				catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error al modificar peluqueria, por favor intente mas tarde.", e);
					
				}			
						
		}
		
		
}

