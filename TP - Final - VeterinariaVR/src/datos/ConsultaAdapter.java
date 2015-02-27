package datos;

import java.util.Calendar;
import java.util.List;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class ConsultaAdapter {

	public ArrayList<Consulta> getConsultas() throws ConException	{
		ArrayList<Consulta> lista = new ArrayList<Consulta>();
		try {
			
			Connection con = Conexion.getConexion();			
			PreparedStatement statement = con.prepareStatement("select consulta.*,animal.*,propietario.nombre'nombreP',propietario.apellido'apellidoP',count(id_vacunacion)'vacunas',"
															 +" consulta.id_intervencion,intervencion_quirurgica.nombre'nombre_intervencion' "
															  +"from consulta inner join animal on animal.id_animal = consulta.id_animal "
															  + "inner join propietario on propietario.id_propietario = animal.id_propietario"
															  +" left join intervencion_quirurgica on consulta.id_intervencion = intervencion_quirurgica.id_intervencion "
															  +" left join vacunacion on vacunacion.id_consulta = consulta.id_consulta "															  
															  +" group by id_consulta order by consulta.id_consulta asc");
																
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				int id_animal = Integer.parseInt(result.getString("id_animal"));
				String nombre = result.getString("nombre");		
				
				String nombreP = result.getString("nombreP");				
				String apellidoP = result.getString("apellidoP");				

				
				String fecha_nac = result.getString("fecha_nac");
				String sexo = result.getString("sexo");			
				
				int id_consulta = Integer.parseInt(result.getString("id_consulta"));
				String fecha = result.getString("fecha");
				String comentarios = result.getString("comentarios");
				String motivo = result.getString("motivo");			
				int id_intervencion = result.getInt("id_intervencion");
				int cant_vacunaciones = result.getInt("vacunas");
							
				Propietario p = new Propietario();
				p.setNombre(nombreP);
				p.setApellido(apellidoP);
				
				Animal animal = new Animal();
				animal.setId_animal(id_animal);
				animal.setNombre(nombre);
				animal.setFecha_nac(fecha_nac);
				animal.setSexo(sexo);
				animal.setPropietario(p);

				Consulta consulta = new Consulta();
				consulta.setId_consulta(id_consulta);
				consulta.setComentarios(comentarios);
				consulta.setFecha(fecha);
				consulta.setMotivo(motivo);
				consulta.setCant_vacunaciones(cant_vacunaciones);
				consulta.setAnimal(animal);
							
				String nombre_intervencion = result.getString("nombre_intervencion");
				if(nombre_intervencion!=null)
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
	
		
		public ArrayList<Consulta> getConsultas(String valor) throws ConException		{
			ArrayList<Consulta> lista = new ArrayList<Consulta>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select consulta.*,animal.*,count(id_vacunacion)'vacunas',propietario.nombre'nombreP',propietario.apellido'apellidoP',consulta.id_intervencion,intervencion_quirurgica.nombre'nombre_intervencion'"
																  +"from consulta inner join animal on animal.id_animal = consulta.id_animal "
																  +" left join intervencion_quirurgica on consulta.id_intervencion = intervencion_quirurgica.id_intervencion "
																  +" left join vacunacion on vacunacion.id_consulta = consulta.id_consulta "
																  + "left join propietario on propietario.id_propietario = animal.id_propietario"
																  + " where consulta.fecha like '%"+valor+"%' or consulta.comentarios like  '%"+valor+"%' "
																  + " or consulta.motivo like  '%"+valor+"%' or animal.nombre like '%"+valor+"%'  or animal.sexo like '%"+valor+"%' "
																  + " or intervencion_quirurgica.nombre  like  '%"+valor+"%' "	
																  +" group by id_consulta order by consulta.id_consulta asc");
																	
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id_animal = Integer.parseInt(result.getString("id_animal"));
					String nombre = result.getString("nombre");		
					
					String nombreP = result.getString("nombreP");				
					String apellidoP = result.getString("apellidoP");				

					
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");			
					
					int id_consulta = Integer.parseInt(result.getString("id_consulta"));
					String fecha = result.getString("fecha");
					String comentarios = result.getString("comentarios");
					String motivo = result.getString("motivo");			
					int id_intervencion = result.getInt("id_intervencion");
					int cant_vacunaciones = result.getInt("vacunas");
								
					Propietario p = new Propietario();
					p.setNombre(nombreP);
					p.setApellido(apellidoP);
					
					Animal animal = new Animal();
					animal.setId_animal(id_animal);
					animal.setNombre(nombre);
					animal.setFecha_nac(fecha_nac);
					animal.setSexo(sexo);
					animal.setPropietario(p);

					Consulta consulta = new Consulta();
					consulta.setId_consulta(id_consulta);
					consulta.setComentarios(comentarios);
					consulta.setFecha(fecha);
					consulta.setMotivo(motivo);
					consulta.setCant_vacunaciones(cant_vacunaciones);
					consulta.setAnimal(animal);
								
					String nombre_intervencion = result.getString("nombre_intervencion");
					if(nombre_intervencion!=null)
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
		
		public ArrayList<Consulta> getConsultas(int id) throws ConException		{
			ArrayList<Consulta> lista = new ArrayList<Consulta>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select consulta.*,animal.*,propietario.nombre'nombreP',propietario.apellido'apellidoP',count(id_vacunacion)'vacunas',"
																 +" consulta.id_intervencion,intervencion_quirurgica.nombre'nombre_intervencion' "
																  +"from consulta inner join animal on animal.id_animal = consulta.id_animal "
																  + "inner join propietario on propietario.id_propietario = animal.id_propietario"
																  +" left join intervencion_quirurgica on consulta.id_intervencion = intervencion_quirurgica.id_intervencion "
																  +" left join vacunacion on vacunacion.id_consulta = consulta.id_consulta "															  
																  +"  where animal.id_animal = '"+id+"' group by id_consulta order by consulta.id_consulta asc");
																	
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					int id_animal = Integer.parseInt(result.getString("id_animal"));
					String nombre = result.getString("nombre");		
					
					String nombreP = result.getString("nombreP");				
					String apellidoP = result.getString("apellidoP");				

					
					String fecha_nac = result.getString("fecha_nac");
					String sexo = result.getString("sexo");			
					
					int id_consulta = Integer.parseInt(result.getString("id_consulta"));
					String fecha = result.getString("fecha");
					String comentarios = result.getString("comentarios");
					String motivo = result.getString("motivo");			
					int id_intervencion = result.getInt("id_intervencion");
					int cant_vacunaciones = result.getInt("vacunas");
								
					Propietario p = new Propietario();
					p.setNombre(nombreP);
					p.setApellido(apellidoP);
					
					Animal animal = new Animal();
					animal.setId_animal(id_animal);
					animal.setNombre(nombre);
					animal.setFecha_nac(fecha_nac);
					animal.setSexo(sexo);
					animal.setPropietario(p);

					Consulta consulta = new Consulta();
					consulta.setId_consulta(id_consulta);
					consulta.setComentarios(comentarios);
					consulta.setFecha(fecha);
					consulta.setMotivo(motivo);
					consulta.setCant_vacunaciones(cant_vacunaciones);
					consulta.setAnimal(animal);
								
					String nombre_intervencion = result.getString("nombre_intervencion");
					if(nombre_intervencion!=null)
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
				
		public Consulta buscarConsulta(int idA) throws ConException		{
			Consulta consulta = new Consulta();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select consulta.fecha, consulta.id_consulta,consulta.motivo,consulta.comentarios, intervencion_quirurgica.id_intervencion, "
																	+" animal.id_animal, animal.fecha_nac, intervencion_quirurgica.nombre'nombre_intervencion',"
																	+"	animal.sexo, animal.nombre,"
																	+"  raza.id_raza, raza.nombre'nombre_raza',"
																	+"  tipo_animal.id_tipo_animal, tipo_animal.nombre'nombre_tipo',"
																	+"  propietario.id_propietario, propietario.nombre'nombreP', propietario.apellido'apellidoP', "
																	+"  propietario.direccion, propietario.email,propietario.telefono_fijo, propietario.celular,"
																	+"  propietario.usuario, propietario.clave "
																	+"  from consulta "
																	+ " inner join animal on animal.id_animal = consulta.id_animal "
																	+"  inner join propietario on propietario.id_propietario = animal.id_propietario"
																	+"  inner join raza on raza.id_raza = animal.id_raza "
																	+"  inner join tipo_animal on tipo_animal.id_tipo_animal = raza.id_tipo_animal"
																	+ " left join intervencion_quirurgica on intervencion_quirurgica.id_intervencion = consulta.id_intervencion"
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
					if(result.getString("nombre_intervencion")!=null)
					{
						IntervencionQuirurgica intervencion = new IntervencionQuirurgica(result.getString("nombre_intervencion"));
						intervencion.setId_intervencion(result.getInt("id_intervencion"));
						consulta.setIntervencion(intervencion);
					}							
				}
				
				
				statement = con.prepareStatement("select vacunacion.id_vacunacion, vacunacion.fecha'fecha_vacunacion',vacunacion.comentarios,"
																	+ "vacuna.id_vacuna, vacuna.codigo,vacuna.nombre'nombre_vacuna',vacuna.marca,vacuna.duracion,"
																	+ "vacunacion.dias_aviso"
																	+" from consulta"
																	+" inner join vacunacion on vacunacion.id_consulta = consulta.id_consulta"
																	+"  inner join vacuna on vacuna.id_vacuna =vacunacion.id_vacuna"																	
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
					vacunacion.setVacuna(vac);
					listaVacunaciones.add(vacunacion);
				}
				 consulta.setVacunaciones(listaVacunaciones);			
				
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las consultas, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las consultas, por favor intente mas tarde.", e);
			}
			return consulta;
		}
		
		public boolean borrarConsulta(int id) throws Exception		{	
			boolean c = false;
			try{
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from vacunacion where id_consulta ='"+id+"'");
				statement.execute();
				statement = con.prepareStatement("delete from consulta where id_consulta ='"+id+"'");
					statement.execute();			
				con.close();
				c= true;
			} catch (ConException es) {
				throw new ConException("Error al eliminar Consulta, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("La Consulta no puede ser eliminada porque está siendo utilizada en registro de vacunaciones.", e);
			}
			finally{return c;}
		}

		public List<Vacunacion> buscarVacunaciones(int idA) throws ConException		{
			List<Vacunacion> listaVacunaciones = new ArrayList<Vacunacion>();

			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select vacunacion.id_vacunacion, vacunacion.fecha'fecha_vacunacion',vacunacion.comentarios,"
																	+ "vacuna.id_vacuna, vacuna.codigo,vacuna.nombre'nombre_vacuna',vacuna.marca,vacuna.duracion,"
																	+ "vacunacion.dias_aviso"
																	+" from consulta"
																	+" inner join vacunacion on vacunacion.id_consulta = consulta.id_consulta"
																	+"  inner join vacuna on vacuna.id_vacuna =vacunacion.id_vacuna"																	
																	+ " where consulta.id_animal='"+idA+"'");
				ResultSet result = statement.executeQuery();
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
					vacunacion.setVacuna(vac);
					listaVacunaciones.add(vacunacion);
				}
				
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las vacunaciones, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las vacunaciones, por favor intente mas tarde.", e);
			}
			return listaVacunaciones;
		}
				

		public void modificarConsulta(Consulta p) throws Exception {
				try {
					Connection con = Conexion.getConexion();	
					PreparedStatement statement;
					if(p.getIntervencion().getId_intervencion()==0)
					{
						statement = 
								con.prepareStatement("update consulta set id_intervencion =NULL, fecha = '"+p.getFecha()+"',comentarios = '"+p.getComentarios()+"',motivo = '"+p.getMotivo()+"'"													
														+" where id_consulta ='"+p.getId_consulta()+"'");
					}else
					{
						statement = 
								con.prepareStatement("update consulta set id_intervencion = '"+p.getIntervencion().getId_intervencion()+"',fecha = '"+p.getFecha()+"',comentarios = '"+p.getComentarios()+"',motivo = '"+p.getMotivo()+"'"													
														+" where id_consulta ='"+p.getId_consulta()+"'");
					}
					
					
					statement.execute();
					statement = 
							con.prepareStatement("delete from vacunacion where id_consulta ='"+p.getId_consulta()+"'");
					statement.execute();
					if(p.getVacunaciones().size()!=0)
					{
						List<Vacunacion> vacunaciones = p.getVacunaciones();
						for (int i=0; i<p.getVacunaciones().size() ; i++)
						{
								statement = con.prepareStatement("insert into vacunacion (id_consulta,fecha,comentarios,dias_aviso,id_vacuna) values ('"+p.getId_consulta()+"','"
												+p.getFecha()+"','"+vacunaciones.get(i).getComentarios()+"','"+vacunaciones.get(i).getDias_aviso()+"','"+vacunaciones.get(i).getVacuna().getId_vacuna()+"')");
								statement.execute();
						}
					
					}
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

		public void agregarConsulta(Consulta consulta) throws Exception {
			int key = 0;			
			try {
				Connection con = Conexion.getConexion();
				PreparedStatement statement;
				if(consulta.getIntervencion().getId_intervencion()==0)
				{
					statement = con.prepareStatement("insert into consulta (fecha,comentarios,motivo,id_animal) values ('"+consulta.getFecha()+"','"+consulta.getComentarios()+"','"+consulta.getMotivo()+"','"+consulta.getAnimal().getId_animal()+"')");				
				}else
				{
					statement = con.prepareStatement("insert into consulta (fecha,comentarios,motivo,id_animal,id_intervencion) values ('"+consulta.getFecha()+"','"+consulta.getComentarios()+"','"+consulta.getMotivo()+"','"+consulta.getAnimal().getId_animal()+"','"+consulta.getIntervencion().getId_intervencion()+"')");
				}
				
				statement.execute();
				/*
				ResultSet rs = statement.getGeneratedKeys();
				while(rs.next())
				{	   key = rs.getInt(1);		}	
				*/	
				statement = con.prepareStatement("select max(id_consulta)'id' from consulta");
				ResultSet res = statement.executeQuery();
				res.next();
				key = res.getInt("id"); 
				if(consulta.getVacunaciones()!= null && consulta.getVacunaciones().size()!=0)
				{
					List<Vacunacion> vacunaciones = consulta.getVacunaciones();
					for (int i=0; i<consulta.getVacunaciones().size() ; i++)
					{
							statement = con.prepareStatement("insert into vacunacion (id_consulta,fecha,comentarios,dias_aviso,id_vacuna) values ('"+key+"','"
											+consulta.getFecha()+"','"+vacunaciones.get(i).getComentarios()+"','"+vacunaciones.get(i).getDias_aviso()+"','"+vacunaciones.get(i).getVacuna().getId_vacuna()+"')");
							statement.execute();
					}
				
				}
				
				con.close();
			} catch (ConException es) {
				es.printStackTrace();
				throw new ConException("Error al agregar consulta, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al agregar consulta, por favor intente mas tarde.", e);
				
			}				
		}
		
		public List<Aviso> getAlertas(int dias) throws Exception		{	
			List<Aviso> avisos = new ArrayList<Aviso>();
			List<Consulta> listadoCompleto = this.getConsultas();
			for(int i=0;i<listadoCompleto.size();i++)
			{
				Consulta c = listadoCompleto.get(i);
				String[] fecha = c.getFecha().split("/");
				Date fechaDate = new Date(Integer.parseInt(fecha[2])-1900,Integer.parseInt(fecha[1])-1,Integer.parseInt(fecha[0])); 
				Date hoy = new Date();
				
				Date limite = sumarRestarDiasFecha(hoy, dias);
				c = this.buscarConsulta(c.getId_consulta());
				if(c.getVacunaciones()!=null)
				{
					for(int j=0;j<c.getVacunaciones().size();j++)
					{	int diasAviso = c.getVacunaciones().get(j).getDias_aviso();
						if(diasAviso!=0)													
						{
							Date fechaAviso = sumarRestarDiasFecha(fechaDate, diasAviso);
							
							if(fechaAviso.after(hoy) && fechaAviso.before(limite))
							{	
								Aviso av = new Aviso(fechaAviso,fechaDate,c.getVacunaciones().get(j).getVacuna(),c.getAnimal());
								avisos.add(av);
							}
						}
					}
				}
			}
			return avisos;
		}
		
		 public Date sumarRestarDiasFecha(Date fecha, int dias){			 			  	
			  Calendar calendar = Calendar.getInstance();			 
			  calendar.setTime(fecha); // Configuramos la fecha que se recibe			 
			  calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
			  return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos			 				 	
		 }
}

