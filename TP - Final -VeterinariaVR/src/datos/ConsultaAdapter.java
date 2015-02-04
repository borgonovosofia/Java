package datos;

import java.sql.Connection;
import java.sql.Date;
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
				PreparedStatement statement = con.prepareStatement("select consulta.*,animal.*,count(id_vacunacion)'vacunas',count(id_intervencion)'intervenciones' from consulta inner join animal on animal.id_animal = consulta.id_animal "
																  +" inner join consulta_intervencion on consulta.id_consulta = consulta_intervencion.id_consulta "
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
					Date fecha = result.getDate("fecha");
					String comentarios = result.getString("comentarios");
					String motivo = result.getString("motivo");			
					int cant_intervenciones = result.getInt("intervenciones");
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
					consulta.setCant_intervenciones(cant_intervenciones);
					consulta.setCant_vacunaciones(cant_vacunaciones);
					consulta.setAnimal(animal);
														
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
					Date fecha = result.getDate("fecha");
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
		

}

