package negocio;


public class Peso {
	private int id_peso;
	private String fecha;
	private Double peso;
	private Animal animal;
	
	
	public int getId_peso() 
	{return id_peso;}
	public void setId_peso(int id_peso) 
	{this.id_peso = id_peso;}
	
	public String getFecha() 
	{return fecha;}
	public void setFecha(String fecha) 
	{this.fecha = fecha;}
	
	public Double getPeso() 
	{return peso;}	
	public void setPeso(Double peso) 
	{this.peso = peso;}
		
	public Animal getAnimal() 
	{return animal;}
	public void setAnimal(Animal animal) 
	{this.animal = animal;}
	
	public Peso(String f, Double p, Animal a)
	{
		setFecha(f);
		setPeso(p);
		setAnimal(a);
	}
	public Peso() {
	}
	
}
