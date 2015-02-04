package negocio;
import java.sql.Date;

public class Consulta {
	private int id_consulta;
	private Date fecha;
	private String comentarios;
	private String motivo;
	private Animal animal;
	private IntervencionQuirurgica intervencion;
	private int cant_intervenciones;
	
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

	private int cant_vacunaciones;
	
	public int getId_consulta() 
	{return id_consulta;}
	public void setId_consulta(int id_consulta) 
	{this.id_consulta = id_consulta;}
	
	public Date getFecha() 
	{return fecha;}
	public void setFecha(Date fecha) 
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
	
	public Consulta(Date f, String c, String m, Animal a, IntervencionQuirurgica i)
	{
		setFecha(f);
		setComentarios(c);
		setMotivo(m);
		setAnimal(a);
		setIntervencion(i);
	}
	public Consulta() {
	}
}
