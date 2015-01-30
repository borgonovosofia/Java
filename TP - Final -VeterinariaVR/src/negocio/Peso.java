package negocio;
import java.sql.Date;


public class Peso {
	private int id_peso;
	private Date fecha;
	private float peso;
	private Animal animal;
	
	
	public int getId_peso() 
	{return id_peso;}
	public void setId_peso(int id_peso) 
	{this.id_peso = id_peso;}
	
	public Date getFecha() 
	{return fecha;}
	public void setFecha(Date fecha) 
	{this.fecha = fecha;}
	
	public float getPeso() 
	{return peso;}	
	public void setPeso(float peso) 
	{this.peso = peso;}
		
	public Animal getAnimal() 
	{return animal;}
	public void setAnimal(Animal animal) 
	{this.animal = animal;}
	
	public Peso(Date f, float p, Animal a)
	{
		setFecha(f);
		setPeso(p);
		setAnimal(a);
	}
	
}
