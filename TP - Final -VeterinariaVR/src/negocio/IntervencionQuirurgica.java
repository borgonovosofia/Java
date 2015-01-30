package negocio;

public class IntervencionQuirurgica {
	private int id_intervencion;
	private String nombre;
	
	public int getId_intervencion() 
	{return id_intervencion;}
	public void setId_intervencion(int id_intervencion) 
	{this.id_intervencion = id_intervencion;}
	
	public String getNombre() 
	{return nombre;}
	public void setNombre(String nombre) 
	{this.nombre = nombre;}
	
	public IntervencionQuirurgica(String n)
	{
		setNombre(n);
	}
	
	
}
