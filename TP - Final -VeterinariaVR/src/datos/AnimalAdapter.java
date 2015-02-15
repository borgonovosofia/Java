package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class AnimalAdapter {

		public ArrayList<Animal> getAnimales() throws ConException		{
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
																	+"inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal"
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
		
		public ArrayList<Animal> getAnimales(String parecido) throws ConException		{
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
																	+ "or tipo_animal.nombre like '%"+parecido.toUpperCase()+"%'"
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

		public ArrayList<Animal> getAnimales(int id_pr) throws ConException		{
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
																	+"inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal"
																	+" where animal.id_propietario='"+id_pr+"' group by animal.id_animal "
																	+" order by animal.id_animal asc");	
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
			
			
			/*try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre,"
																	+" raza.id_raza, raza.nombre'nombre_raza',"
																	+" tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo', "
																	+" count(peluqueria.id_peluqueria)'peluquerias', count(consulta.id_consulta)'consultas'"
																	+" from animal"
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+" inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal"
																	+" left join consulta on consulta.id_animal = animal.id_animal "
																	+" left join peluqueria on peluqueria.id_animal = animal.id_animal "
																	+" where animal.id_propietario='"+id_pr+"' group by animal.id_animal "
																	+" order by animal.id_animal asc");							
				
				
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id = Integer.parseInt(result.getString("id_animal"));
					String nombre = result.getString("nombre");				
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");			
					
					int cant_consultas = result.getInt("consultas");
					int cant_peluquerias = result.getInt("peluquerias");

					int id_raza = Integer.parseInt(result.getString("id_raza"));
					String nombre_raza = result.getString("nombre_raza");

					int id_tipo = Integer.parseInt(result.getString("id_tipo_animal"));
					String nombre_tipo = result.getString("nombre_tipo");
					
					TipoAnimal tip = new TipoAnimal(nombre_tipo);
					tip.setId_tipo_animal(id_tipo);
					
					Raza raz = new Raza(nombre_raza,tip);
					raz.setId_raza(id_raza);
					
								
					Animal animal = new Animal();
					animal.setCant_consultas(cant_consultas);
					animal.setCant_peluquerias(cant_peluquerias);
					animal.setId_animal(id);
					animal.setFecha_nac(fecha_nac);
					animal.setSexo(sexo);
					animal.setNombre(nombre);
					animal.setRaza(raz);
									
					lista.add(animal);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los animales, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los animales, por favor intente mas tarde.", e);
			}
			return lista;*/
		}
		
		
		public Animal buscarAnimal(int idA) throws ConException		{
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
				
		public int agregarAnimal(Animal t,int idRaza, int idPropietario) throws ConException		{
			int key = 0;
			try {
				Connection con = Conexion.getConexion();
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into animal (fecha_nac,sexo,nombre,id_propietario,id_raza) values ('"+t.getFecha_nac()+"','"
														+t.getSexo()+"','"+t.getNombre()+"','"+idPropietario+"','"
														+idRaza+"')");
				statement.execute();
				ResultSet rs = statement.getGeneratedKeys();
				while(rs.next())
				{
					   key = rs.getInt(1);
				}
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nueva animal, por favor intente mas tarde.", e);
				
			}
			return key;
		}

		public void agregarPeso(Peso p,int id_animal) throws ConException		{
			try {
				Connection con = Conexion.getConexion();
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into peso (id_animal,peso) values ('"+id_animal+"','"+p.getPeso()+"')");
				statement.execute();				
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nueva animal, por favor intente mas tarde.", e);
				
			}
		}
		
		public void borrarAnimal(int id) throws Exception		{
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
				throw new Exception("La animal no puede ser eliminado porque está siendo utilizado en consultas y/o peluqueria.", e);
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

		public ArrayList<Peso> getPesos(int id) throws ConException		{
			ArrayList<Peso> lista = new ArrayList<Peso>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select peso.* from peso inner join animal on peso.id_animal = animal.id_animal "
																+" where animal.id_animal = '"+id+"' order by peso.fecha");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id_peso = Integer.parseInt(result.getString("id_peso"));
					Date fec = result.getDate("fecha");
					double peso = result.getDouble("peso");
					
					
					String fecha = fec.getDate()+"/"+(fec.getMonth()+1)+"/"+(fec.getYear()+1900);	
					Peso p = new Peso();
					p.setId_peso(id_peso);
					p.setFecha(fecha);
					p.setPeso(peso);
					
					
					lista.add(p);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los pesos, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los pesos, por favor intente mas tarde.", e);
			}
			return lista;
		}
		
		public ArrayList<Peluqueria> getPeluquerias(int id) throws ConException		{
			ArrayList<Peluqueria> lista = new ArrayList<Peluqueria>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select peluqueria.*, propietario.*, animal.id_animal, animal.fecha_nac, animal.sexo, animal.nombre'nombreA',"
																	+ " raza.id_raza, raza.nombre'nombreR', tipo_animal.id_tipo_animal,tipo_animal.nombre'nombreT' "
																	+ " from peluqueria"
																	+ " inner join animal on peluqueria.id_animal = animal.id_animal"
																	+ " inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+ " inner join raza on raza.id_raza  = animal.id_raza "
																	+ " inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal "
																	+ " where animal.id_animal = '"+id+"' ");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id_peluqueria = Integer.parseInt(result.getString("id_peluqueria"));
					String fecha = result.getString("fecha");
					String accion = result.getString("accion");
					String comentarios = result.getString("comentarios");
					
					Peluqueria p = new Peluqueria();
					p.setId_peluqueria(id_peluqueria);
					p.setFecha(fecha);
					p.setAccion(accion);
					p.setComentarios(comentarios);
					
					int id_propietario = Integer.parseInt(result.getString("id_propietario"));
					String nombre = result.getString("nombre");
					String apellido = result.getString("apellido");
					String direccion = result.getString("direccion");
					String email = result.getString("email");
					String telefono_fijo = result.getString("telefono_fijo");
					String celular = result.getString("celular");
					String usuario = result.getString("usuario");
					
					Propietario propietario  = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular,usuario,"");
					propietario.setId_propietario(id_propietario);

					int id_tipo = result.getInt("id_tipo_animal");
					String nombreT = result.getString("nombreT");
					TipoAnimal tipo = new TipoAnimal(nombreT);
					tipo.setId_tipo_animal(id_tipo);
					
					int id_raza = result.getInt("id_raza");
					String nombreR = result.getString("nombreR");
					Raza raza = new Raza(nombreR,tipo);
					raza.setId_raza(id_raza);
				
					
					int id_animal = Integer.parseInt(result.getString("id_animal"));
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");
					String nombreA = result.getString("nombreA");
					
					Animal an = new Animal(fecha_nac,sexo,nombreA,raza,propietario);
					an.setId_animal(id_animal);
					
					p.setAnimal(an);
					
					lista.add(p);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las peluquerias, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las peluquerias, por favor intente mas tarde.", e);
			}
			return lista;
		}

		public int agregarPeluqueria(Peluqueria pelu, int id_animal) throws ConException {
			int key=0;
			try {
				Connection con = Conexion.getConexion();
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into peluqueria (id_animal,comentarios,accion,fecha) values ('"+id_animal+"','"+pelu.getComentarios()+"','"+pelu.getAccion()+"','"+pelu.getFecha()+"')");
				statement.execute();
				ResultSet rs = statement.getGeneratedKeys();
				while(rs.next())
				{
					   key = rs.getInt(1);
				}
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nueva peluqueria, por favor intente mas tarde.", e);
				
			}
			return key;
		}
}

