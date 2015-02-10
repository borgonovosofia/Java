package negocio;
import java.util.ArrayList;
import java.util.List;

import utilidades.ConException;
import datos.ConsultaAdapter;

public class Consulta {
	private int id_consulta;
	private String fecha;
	private String comentarios;
	private String motivo;
	private Animal animal;
	private IntervencionQuirurgica intervencion;
	private int cant_intervenciones;
	private int cant_vacunaciones;
	private List<Vacunacion> vacunaciones;
	
	public List<Vacunacion> getVacunaciones() {
		return vacunaciones;
	}
	public void setVacunaciones(List<Vacunacion> vacunaciones) {
		this.vacunaciones = vacunaciones;
	}
	public int getCant_intervenciones() {
		return cant_intervenciones;
	}
	public void setCant_intervenciones(int cant_intervenciones) {
		this.cant_intervenciones = cant_intervenciones;
	}
	public int getCant_vacunaciones() {
		return cant_vacunaciones;
	}
	public void setCant_vacunaciones(int cant_vacunaciones) {
		this.cant_vacunaciones = cant_vacunaciones;
	}
	public int getId_consulta() 
	{return id_consulta;}
	public void setId_consulta(int id_consulta) 
	{this.id_consulta = id_consulta;}
	public String getFecha() 
	{return fecha;}
	public void setFecha(String fecha) 
	{this.fecha = fecha;}
	public String getComentarios() 
	{return comentarios;}
	public void setComentarios(String comentarios) 
	{this.comentarios = comentarios;}
	public String getMotivo() 
	{return motivo;}
	public void setMotivo(String motivo) 
	{this.motivo = motivo;}
	public Animal getAnimal() 
	{return animal;}
	public void setAnimal(Animal animal) 
	{this.animal = animal;}
	public IntervencionQuirurgica getIntervencion() 
	{return intervencion;}
	public void setIntervencion(IntervencionQuirurgica intervencion) 
	{this.intervencion = intervencion;}
	
	public Consulta(String f, String c, String m, Animal a)
	{
		setFecha(f);
		setComentarios(c);
		setMotivo(m);
		setAnimal(a);
	}
	public Consulta() {
	}
	
	
	public static List<Consulta> dameConsultas(String valor) throws Exception {
		try {
			ConsultaAdapter adapter = new ConsultaAdapter();
			ArrayList<Consulta> lista = adapter.getConsultas(valor);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	public static List<Consulta> dameConsultas() throws Exception {
		try {
			ConsultaAdapter adapter = new ConsultaAdapter();
			ArrayList<Consulta> lista = adapter.getConsultas();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Consulta buscarConsulta(int id) throws ConException
	{
		ConsultaAdapter adapter = new ConsultaAdapter();
		return adapter.buscarConsulta(id);
	}

	public static boolean borrarConsulta(int id) throws Exception {
		try {
			Consulta v= Consulta.buscarConsulta(id);
			if(v!=null)
			{
				ConsultaAdapter adapter = new ConsultaAdapter();		
				adapter.borrarConsulta(id);
				return true;
			}
			else
			{
				return false;
			}
		} catch (ConException e) {
			throw e;
		}		
	}
	
	public static boolean modificarConsulta(Consulta p) throws Exception
	{
		try {
			Consulta pelu = Consulta.buscarConsulta(p.getId_consulta());
			if(pelu!=null)
			{
				ConsultaAdapter adapter = new ConsultaAdapter();		
				adapter.modificarConsulta(p);
				return true;				
			}
			else
			{
				return false;
			}
		} catch (ConException e) {
			throw e;
		}	
	}
}
