package negocio;

import java.sql.Date;

public class Peluqueria {
	private int id_peluqueria;
	private Date fecha;
	private String accion;
	private String comentarios;
	private Animal animal;
	
	public int getId_peluqueria() 
	{return id_peluqueria;}	
	public void setId_peluqueria(int id_peluqueria) 
	{this.id_peluqueria = id_peluqueria;}

	public Date getFecha() 
	{return fecha;}
	public void setFecha(Date fecha) 
	{this.fecha = fecha;}

	public String getAccion() 
	{return accion;}
	public void setAccion(String accion) 
	{this.accion = accion;}

	public String getComentarios() 
	{return comentarios;}
	public void setComentarios(String comentarios) 
	{this.comentarios = comentarios;}

	public Animal getAnimal() 
	{return animal;}
	public void setAnimal(Animal animal) 
	{this.animal = animal;}

	public Peluqueria(Date d, String a, String c, Animal an)
	{
		setFecha(d);
		setAccion(a);
		setComentarios(c);
		setAnimal(an);
	}
	public Peluqueria() {
	}

}
