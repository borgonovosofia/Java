package datos;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class ConsultaAdapter {

	public ArrayList<Consulta> getConsultas() throws ConException
	{
		ArrayList<Consulta> lista = new ArrayList<Consulta>();
		try {
			
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select consulta.*,animal.*,count(id_vacunacion)'vacunas',id_intervencion,intervencion_quirurgica.nombre'nombre_intervencion'"
															  +"from consulta inner join animal on animal.id_animal = consulta.id_animal "
															  +" left join intervencion_quirurgica on consulta.id_intervencion = intervencion_quirurgica.id_intervencion "
															  +" inner join vacunacion on vacunacion.id_consulta = consulta.id_consulta "															  
															  +" group by id_consulta order by consulta.id_consulta asc");
																
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				int id_animal = Integer.parseInt(result.getString("id_animal"));
				String nombre = result.getString("nombre");				
				String fecha_nac = result.getString("fecha_nac");
				String sexo = result.getString("sexo");			
				
				int id_consulta = Integer.parseInt(result.getString("id_consulta"));
				String fecha = result.getString("fecha");
				String comentarios = result.getString("comentarios");
				String motivo = result.getString("motivo");			
				int id_intervencion = result.getInt("id_intervencion");
				int cant_vacunaciones = result.getInt("vacunas");
							
				Animal animal = new Animal();
				animal.setId_animal(id_animal);
				animal.setNombre(nombre);
				animal.setFecha_nac(fecha_nac);
				animal.setSexo(sexo);
				

				Consulta consulta = new Consulta();
				consulta.setId_consulta(id_consulta);
				consulta.setComentarios(comentarios);
				consulta.setFecha(fecha);
				consulta.setMotivo(motivo);
				consulta.setCant_vacunaciones(cant_vacunaciones);
				consulta.setAnimal(animal);
							
				String nombre_intervencion = result.getString("nombre_intervencion");
				if(!nombre_intervencion.equals(""))
				{
					IntervencionQuirurgica in = new IntervencionQuirurgica(nombre_intervencion);
					in.setId_intervencion(id_intervencion);
					consulta.setIntervencion(in);
				}
									
				
				lista.add(consulta);
			}
			con.close();
		} catch (ConException e) {
			throw new ConException("Error de conexion al recuperar las consultas, por favor intente mas tarde.", e);
		}		
		catch (Exception e){
			throw new ConException("Error al recuperar las consultas, por favor intente mas tarde.", e);
		}
		return lista;
	}
	
		
		public ArrayList<Consulta> getConsultas(String valor) throws ConException
		{
			ArrayList<Consulta> lista = new ArrayList<Consulta>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select consulta.*,animal.*,count(id_vacunacion)'vacunas',id_intervencion,intervencion_quirurgica.nombre'nombre_intervencion'"
																  +"from consulta inner join animal on animal.id_animal = consulta.id_animal "
																  +" left join intervencion_quirurgica on consulta.id_intervencion = intervencion_quirurgica.id_intervencion "
																  +" inner join vacunacion on vacunacion.id_consulta = consulta.id_consulta "
																  + " where consulta.fecha like  '%"+valor+"'% or consulta.comentarios like  '%"+valor+"'% "
																  + " or consulta.motivo like  '%"+valor+"'% or animal.nombre like  '%"+valor+"'%  or animal.sexo like like  '%"+valor+"'% "
																  + " or intervencion_quirurgica.nombre  like  '%"+valor+"'%  "	
																  +" group by id_consulta order by consulta.id_consulta asc");
																	
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id_animal = Integer.parseInt(result.getString("id_animal"));
					String nombre = result.getString("nombre");				
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");			
					
					int id_consulta = Integer.parseInt(result.getString("id_consulta"));
					String fecha = result.getString("fecha");
					String comentarios = result.getString("comentarios");
					String motivo = result.getString("motivo");			
					int id_intervencion = result.getInt("id_intervencion");
					int cant_vacunaciones = result.getInt("vacunas");
								
					Animal animal = new Animal();
					animal.setId_animal(id_animal);
					animal.setNombre(nombre);
					animal.setFecha_nac(fecha_nac);
					animal.setSexo(sexo);
					

					Consulta consulta = new Consulta();
					consulta.setId_consulta(id_consulta);
					consulta.setComentarios(comentarios);
					consulta.setFecha(fecha);
					consulta.setMotivo(motivo);
					consulta.setCant_vacunaciones(cant_vacunaciones);
					consulta.setAnimal(animal);
								
					String nombre_intervencion = result.getString("nombre_intervencion");
					if(!nombre_intervencion.equals(""))
					{
						IntervencionQuirurgica in = new IntervencionQuirurgica(nombre_intervencion);
						in.setId_intervencion(id_intervencion);
						consulta.setIntervencion(in);
					}
										
					
					lista.add(consulta);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las consultas, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las consultas, por favor intente mas tarde.", e);
			}
			return lista;
		}
		
		
		public ArrayList<Consulta> getConsultas(int id) throws ConException
		{
			ArrayList<Consulta> lista = new ArrayList<Consulta>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select consulta.*,count(id_vacunacion)'vacunas',count(id_intervencion)'intervenciones' from consulta inner join animal on animal.id_animal = consulta.id_animal "
																  +" inner join consulta_intervencion on consulta.id_consulta = consulta_intervencion.id_consulta "
																  +" inner join vacunacion on vacunacion.id_consulta = consulta.id_consulta "	
																  +"  where animal.id_animal = '"+id+"' group by id_consulta order by consulta.id_consulta asc");
																	
				ResultSet result = statement.executeQuery();
				while(result.next())
				{					
					int id_consulta = Integer.parseInt(result.getString("id_consulta"));
					String fecha = result.getString("fecha");
					String comentarios = result.getString("comentarios");
					String motivo = result.getString("motivo");			
					int cant_intervenciones = result.getInt("intervenciones");
					int cant_vacunaciones = result.getInt("vacunas");
								
				
					Consulta consulta = new Consulta();
					consulta.setId_consulta(id_consulta);
					consulta.setComentarios(comentarios);
					consulta.setFecha(fecha);
					consulta.setMotivo(motivo);
					consulta.setCant_intervenciones(cant_intervenciones);
					consulta.setCant_vacunaciones(cant_vacunaciones);
														
					lista.add(consulta);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las consultas, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las consultas, por favor intente mas tarde.", e);
			}
			return lista;
		}
		
		public Consulta buscarConsulta(int idA) throws ConException
		{
			Consulta consulta = new Consulta();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select consulta.fecha, consulta.id_consulta,consulta.motivo,consulta.comentarios, animal.id_animal, animal.fecha_nac,"
																	+"	animal.sexo, animal.nombre,"
																	+" raza.id_raza, raza.nombre'nombre_raza',"
																	+" tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+" propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+" propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+" propietario.usuario, propietario.clave, "
																	+" from consulta"
																	+ "inner join animal on animal.id_animal = consulta.id_animal "
																	+" inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+" inner join raza on raza.id_raza = animal.id_raza "
																	+" inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal "
																	+ " where consulta.id_consulta='"+idA+"'");
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
					int id_consulta = Integer.parseInt(result.getString("id_consulta"));
					String comentarios = result.getString("comentarios");
					String motivo = result.getString("motivo");
					
					consulta = new Consulta(fecha,comentarios,motivo,animal);
					consulta.setId_consulta(id_consulta);
						
				}
				statement = con.prepareStatement("select intervencion_quirurgica.id_intervencion, intervencion_quirurgica.nombre'nombre_intervencion'"																	
																	+" from consulta"																
																	+" inner join intervencion_quirurgica on intervencion_quirurgica.id_intervencion = consulta.id_intervencion"																
																	+ " where consulta.id_consulta='"+idA+"'");
				result = statement.executeQuery();
				while(result.next())
				{
					IntervencionQuirurgica intervencion = new IntervencionQuirurgica(result.getString("nombre_intervencion"));
					intervencion.setId_intervencion(result.getInt("id_intervencion"));
					consulta.setIntervencion(intervencion);
						
				}
				
				statement = con.prepareStatement("select vacunacion.id_vacunacion, vacunacion.fecha'fecha_vacunacion',vacunacion.comentarios,"
																	+ "vacuna.id_vacuna, vacuna.codigo,vacuna.nombre'nombre_vacuna',vacuna.marca,vacuna.duracion,"
																	+ "vacunacion.dias_aviso"
																	+" from consulta"
																	+" inner join vacunacion on vacunacion on vacunacion.id_consulta = consulta.id_consulta"
																	+" inner join vacuna on vacuna.id_vacuna vacunacion.id_vacuna"
																	+ "left join aviso on aviso.id_vacunacion = vacunacion.id_vacunacion"
																	+ " where consulta.id_consulta='"+idA+"'");
				result = statement.executeQuery();
				List<Vacunacion> listaVacunaciones = new ArrayList<Vacunacion>();
				while(result.next())
				{
					int id = result.getInt("id_vacunacion");
					String fecha_vacunacion = result.getString("fecha_vacunacion");
					String comentarios = result.getString("comentarios");
					int dias_aviso = result.getInt("dias_aviso");
					
					int id_vacuna = result.getInt("id_vacuna");				
					String codigo = result.getString("codigo");
					String nombre = result.getString("nombre_vacuna");
					String marca = result.getString("marca");
					int duracion = result.getInt("duracion");
					
					Vacuna vac = new Vacuna(codigo,nombre,marca,duracion);
					vac.setId_vacuna(id_vacuna);
					
					Vacunacion vacunacion = new Vacunacion();
					vacunacion.setComentarios(comentarios);
					vacunacion.setId_vacunacion(id);
					vacunacion.setDias_aviso(dias_aviso);
					vacunacion.setFecha(fecha_vacunacion);

					listaVacunaciones.add(vacunacion);
				}
				 consulta.setVacunaciones(listaVacunaciones);			
				
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar los animales, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar los animales, por favor intente mas tarde.", e);
			}
			return consulta;
		}
		
		public void borrarConsulta(int id) throws Exception
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from consulta where id_consulta ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				throw new ConException("Error al eliminar Consulta, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("La Consulta no puede ser eliminada porque está siendo utilizada en registro de vacunaciones.", e);
			}
		}

		public void modificarConsulta(Consulta p) throws Exception {
				try {
					Connection con = Conexion.getConexion();			
					PreparedStatement statement = 
							con.prepareStatement("update consulta set fecha = '"+p.getFecha()+"',comentarios = '"+p.getComentarios()+"',motivo = '"+p.getMotivo()+"'"													
													+" where id_consulta ='"+p.getId_consulta()+"'");
					statement.execute();
					con.close();
				} catch (ConException es) {
					es.printStackTrace();
					throw new ConException("Error al modificar consulta, por favor intente mas tarde.", es);			
				}			
				catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error al modificar consulta, por favor intente mas tarde.", e);
					
				}			
						
		}
}

