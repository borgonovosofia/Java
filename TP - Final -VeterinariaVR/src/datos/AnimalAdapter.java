package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class AnimalAdapter {

		public ArrayList<Animal> getAnimales() throws ConException
		{
			ArrayList<Animal> lista = new ArrayList<Animal>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre,"
																	+"raza.id_raza, raza.nombre'nombre_raza',"
																	+"tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+"propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+"propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+"propietario.usuario, propietario.clave from animal"
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+"inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal");
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
									
					lista.add(animal);
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
		
		public ArrayList<Animal> getAnimales(String parecido) throws ConException
		{
			ArrayList<Animal> lista = new ArrayList<Animal>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre,"
																	+"raza.id_raza, raza.nombre'nombre_raza',"
																	+"tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+"propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+"propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+"propietario.usuario, propietario.clave from animal"
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+" inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal"
																	+ " where propietario.nombre like '%"+parecido.toUpperCase()+"%' "
																	+" or propietario.apellido like '%"+parecido.toUpperCase()+"%' "
																	+" or direccion like '%"+parecido.toUpperCase()
																	+"%' or animal.sexo like '%"+parecido.toUpperCase()+"%' "
																	+"or animal.nombre like '%"+parecido.toUpperCase()
																	+"%'or animal.fecha_nac like '%"+parecido.toUpperCase()+"%' "
																	+ "or raza.nombre like '%"+parecido.toUpperCase()+"%' "
																	+ "or tipo_animal.nombre like '%"+parecido.toUpperCase()+"%'");
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
									
					lista.add(animal);
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

		public Animal buscarAnimal(int idA) throws ConException
		{
			Animal animal = new Animal();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre,"
																	+" raza.id_raza, raza.nombre'nombre_raza',"
																	+" tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+" propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+" propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+" propietario.usuario, propietario.clave from animal"
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+" inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal "
																	+ " where animal.id_animal='"+idA+"'");
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
					
					animal = new Animal(fecha_nac,sexo,nombre,raz, prop);
					animal.setId_animal(id);
						
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los animales, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los animales, por favor intente mas tarde.", e);
			}
			return animal;
		}
		
		
		public void agregarAnimal(Animal t,int idRaza, int idPropietario) throws ConException
		{
			try {
				Connection con = Conexion.getConexion();
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into animal (fecha_nac,sexo,nombre,id_propietario,id_raza) values ('"+t.getFecha_nac()+"','"
														+t.getSexo()+"','"+t.getNombre()+"','"+idPropietario+"','"
														+idRaza+"')");
				statement.execute();
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nueva animal, por favor intente mas tarde.", e);
				
			}
		}

		public void borrarAnimal(int id) throws Exception
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from animal where id_animal ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				throw new ConException("Error al eliminar animal, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("La animal no puede ser eliminado porque está siendo utilizado en consultas y/o peluqueria y/o pesos.", e);
			}
		}

		public void modificarAnimal(Animal v) throws Exception {
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("update animal set fecha_nac = '"+v.getFecha_nac()+"',sexo = '"+v.getSexo()+"',nombre = '"+v.getNombre()+"'"
												+",id_propietario = '"+v.getPropietario().getId_propietario()+"'"
												+",id_raza = '"+v.getRaza().getId_raza()+"'"
												+" where id_animal ='"+v.getId_animal()+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				es.printStackTrace();
				throw new ConException("Error al modificar animal, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al modificar animal, por favor intente mas tarde.", e);
				
			}			
		}
}

