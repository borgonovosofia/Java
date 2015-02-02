package negocio;

import java.sql.Date;

public class Animal {
	private int id_animal;
	private Date fecha_nac;
	private String sexo;
	private String nombre;
	private Raza raza;
	private Propietario propietario;
	
	public int getId_animal() 
	{return id_animal;}
	public void setId_animal(int id_animal) 
	{this.id_animal = id_animal;}
	
	public Date getFecha_nac() 
	{return fecha_nac;}
	public void setFecha_nac(Date fecha_nac) 
	{this.fecha_nac = fecha_nac;}
	
	public String getSexo() 
	{return sexo;}
	public void setSexo(String sexo) 
	{this.sexo = sexo;}
	
	public String getNombre() 
	{return nombre;}
	public void setNombre(String nombre) 
	{this.nombre = nombre;}
	
	public Raza getRaza() 
	{return raza;}
	public void setRaza(Raza raza) 
	{this.raza = raza;}
	
	
	public Propietario getPropietario() 
	{return propietario;}
	public void setPropietario(Propietario propietario) 
	{this.propietario = propietario;}
	
	public Animal(Date d, String s, String n, Raza r, Propietario pr)
	{
		setFecha_nac(d);
		setSexo(s);
		setNombre(n);
		setRaza(r);
		setPropietario(pr);
	}

	
}
