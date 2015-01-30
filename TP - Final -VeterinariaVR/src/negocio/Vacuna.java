package negocio;

public class Vacuna {
	private int id_vacuna;
	private String codigo;
	private String nombre;
	private String marca;
	private int duracion;
	
	public int getId_vacuna() 
	{return id_vacuna;}
	public void setId_vacuna(int id_vacuna) 
	{this.id_vacuna = id_vacuna;}
	
	public String getCodigo() 
	{return codigo;}
	public void setCodigo(String codigo) 
	{this.codigo = codigo;}
	
	public String getNombre() 
	{return nombre;}
	public void setNombre(String nombre) 
	{this.nombre = nombre;}
	
	public String getMarca() 
	{return marca;}
	public void setMarca(String marca) 
	{this.marca = marca;}
	
	public int getDuracion() 
	{return duracion;}
	public void setDuracion(int duracion) 
	{this.duracion = duracion;}
	
	public Vacuna(String c, String n, String m, int d)
	{
		setCodigo(c);
		setNombre(n);
		setMarca(m);
		setDuracion(d);
	}
}
