package negocio;

import java.sql.Date;

public class Aviso {
	private int id_aviso;
	private Date fecha;
	private Vacunacion vacunacion;
	
	public int getId_aviso() 
	{return id_aviso;}
	public void setId_aviso(int id_aviso) 
	{this.id_aviso = id_aviso;}
	
	public Date getFecha() 
	{return fecha;}
	public void setFecha(Date fecha) 
	{this.fecha = fecha;}
	
	public Vacunacion getVacunacion() 
	{return vacunacion;}
	public void setVacunacion(Vacunacion vacunacion) 
	{this.vacunacion = vacunacion;}
	
	public Aviso(Date f, Vacunacion v)
	{
		setFecha(f);
		setVacunacion(v);
	}
}
