package negocio;

import java.sql.Date;

public class Vacunacion {
	private int id_vacunacion;
	private Date fecha;
	private String comentarios;
	private Vacuna vacuna;
	private Consulta consulta;
	
	public int getId_vacunacion() 
	{return id_vacunacion;}
	public void setId_vacunacion(int id_vacunacion) 
	{this.id_vacunacion = id_vacunacion;}
	
	public Date getFecha() 
	{return fecha;}
	public void setFecha(Date fecha) 
	{this.fecha = fecha;}
	
	public String getComentarios() 
	{return comentarios;}
	public void setComentarios(String comentarios) 
	{this.comentarios = comentarios;}
	
	public Vacuna getVacuna() 
	{return vacuna;}
	public void setVacuna(Vacuna vacuna) 
	{this.vacuna = vacuna;}
	
	public Consulta getConsulta() 
	{return consulta;}
	public void setConsulta(Consulta consulta) 
	{this.consulta = consulta;}
	
	public Vacunacion(Date f, String c, Vacuna v, Consulta con)
	{
		setFecha(f);
		setComentarios(c);
		setVacuna(v);
		setConsulta(con);
	}
}
