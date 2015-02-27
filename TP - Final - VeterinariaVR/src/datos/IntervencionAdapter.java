package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import negocio.*;
import utilidades.*;


public class IntervencionAdapter {

		public ArrayList<IntervencionQuirurgica> getIntervenciones() throws ConException
		{
			ArrayList<IntervencionQuirurgica> lista = new ArrayList<IntervencionQuirurgica>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from intervencion_quirurgica");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					IntervencionQuirurgica intervencion = new IntervencionQuirurgica(result.getString("nombre"));
					intervencion.setId_intervencion(Integer.parseInt(result.getString("id_intervencion")));
					lista.add(intervencion);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las intervenciones, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las intervenciones, por favor intente mas tarde.", e);

			}
			return lista;
		}
				
		public ArrayList<IntervencionQuirurgica> getIntervenciones(String parecido) throws ConException
		{
			ArrayList<IntervencionQuirurgica> lista = new ArrayList<IntervencionQuirurgica>();
			try {
				
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = con.prepareStatement("select * from intervencion_quirurgica where nombre like '%"+parecido.toUpperCase()+"%'");
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					IntervencionQuirurgica intervencion = new IntervencionQuirurgica(result.getString("nombre"));
					intervencion.setId_intervencion(Integer.parseInt(result.getString("id_intervencion")));
					lista.add(intervencion);
				}
				con.close();
			} catch (ConException e) {
				throw new ConException("Error de conexion al recuperar las intervenciones, por favor intente mas tarde.", e);
			}		
			catch (Exception e){
				throw new ConException("Error al recuperar las intervenciones, por favor intente mas tarde.", e);

			}
			return lista;
		}
		
		public IntervencionQuirurgica buscarIntervencion(int id) throws ConException
		{
			IntervencionQuirurgica intervencion = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from intervencion_quirurgica where id_intervencion= '"+id+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						intervencion = new IntervencionQuirurgica(result.getString("nombre"));
						intervencion.setId_intervencion(Integer.parseInt(result.getString("id_intervencion")));
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar las intervenciones, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar las intervenciones, por favor intente mas tarde.",w);
			}
			return intervencion;
		}
		
		public IntervencionQuirurgica buscarIntervencion(String nombreTipo) throws ConException
		{
			IntervencionQuirurgica intervencion = null;
			try {
					Connection con = Conexion.getConexion();		
					PreparedStatement statement = con.prepareStatement("select * from intervencion_quirurgica where nombre = '"+nombreTipo.toUpperCase()+"'");
					ResultSet result = statement.executeQuery();
					result.next();
					if(result.getRow()!=0)
					{	
						intervencion = new IntervencionQuirurgica(result.getString("nombre"));
						intervencion.setId_intervencion(Integer.parseInt(result.getString("id_intervencion")));
					}
					con.close();
				} 
			catch (ConException e) 
			{
				throw new ConException("Error de conexion al recuperar las intervenciones, por favor intente mas tarde.", e);
			}		
			catch (Exception w)
			{
				throw new ConException("Error al recuperar las intervencions, por favor intente mas tarde.",w);
			}
			return intervencion;
		}
		
		public void agregarIntervencion(IntervencionQuirurgica t) throws ConException
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement(
								"insert into intervencion_quirurgica (nombre) values ('"+(t.getNombre().toUpperCase())+"')");
				statement.execute();
				con.close();
			} catch (Exception e) {
				throw new ConException("Error al agregar nueva intervención quirúrgica, por favor intente mas tarde.", e);
				
			}
		}

		public void borrarIntervencion(int id) throws Exception
		{
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("delete from intervencion_quirurgica where id_intervencion ='"+id+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				throw new ConException("Error al eliminar intervención quirúrgica, por favor intente mas tarde.", es);			
			}			
			catch (Exception e) {
				throw new Exception("La intervención no puede ser eliminada porque hay consultas donde se utiliza la misma.", e);
				
			}
		}

		public void modificarIntervencion(IntervencionQuirurgica inter) throws Exception {
			try {
				Connection con = Conexion.getConexion();			
				PreparedStatement statement = 
						con.prepareStatement("update intervencion_quirurgica set nombre = '"+inter.getNombre().toUpperCase()+"' where id_intervencion='"+inter.getId_intervencion()+"'");
				statement.execute();
				con.close();
			} catch (ConException es) {
				es.printStackTrace();
				throw new ConException("Error al modificar la intervención quirúrgica, por favor intente mas tarde. Error de conección.", es);			
			}			
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al modificar la intervención quirúrgica, por favor intente mas tarde.", e);
				
			}			
		}
}

