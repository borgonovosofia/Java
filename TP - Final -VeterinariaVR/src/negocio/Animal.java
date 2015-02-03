package negocio;

import java.util.ArrayList;
import java.util.List;

import utilidades.ConException;
import datos.AnimalAdapter;

public class Animal {
	private int id_animal;
	private String fecha_nac;
	private String sexo;
	private String nombre;
	private Raza raza;
	private Propietario propietario;
	
	public int getId_animal() 
	{return id_animal;}
	public void setId_animal(int id_animal) 
	{this.id_animal = id_animal;}
	
	public String getFecha_nac() 
	{return fecha_nac;}
	public void setFecha_nac(String fecha_nac) 
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
	
	public Animal(String d, String s, String n, Raza r, Propietario pr)
	{
		setFecha_nac(d);
		setSexo(s);
		setNombre(n);
		setRaza(r);
		setPropietario(pr);
	}

	public Animal() {
	}
	public static boolean agregarAnimal(Animal t,int idRaza, int idProp) throws ConException
	{
		AnimalAdapter adapter = new AnimalAdapter();		
		adapter.agregarAnimal(t,idRaza, idProp);
		return true;
	}
	
	public static Animal buscarAnimal(int id) throws ConException
	{
		AnimalAdapter adapter = new AnimalAdapter();
		return adapter.buscarAnimal(id);
	}
	
	public static List<Animal> dameAnimales() throws Exception
	{
		try {
			AnimalAdapter adapter = new AnimalAdapter();
			ArrayList<Animal> lista = adapter.getAnimales();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
		
	public static List<Animal> dameAnimales(String parecido) throws Exception
	{
		try {
			AnimalAdapter adapter = new AnimalAdapter();
			ArrayList<Animal> lista = adapter.getAnimales(parecido);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean borrarAnimal(int id) throws Exception {
		try {
			Animal v= Animal.buscarAnimal(id);
			if(v!=null)
			{
				AnimalAdapter adapter = new AnimalAdapter();		
				adapter.borrarAnimal(id);
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

	public static boolean modificarAnimal(Animal v) throws Exception
	{
		try {
			//Propietario prop = Propietario.buscarPropietario(v.getPropietario().getId_propietario());
			Animal anim = Animal.buscarAnimal(v.getId_animal());
			if(anim!=null)//ACA AGREGAR PARA VALIDAR USUARIO
			{
				AnimalAdapter adapter = new AnimalAdapter();		
				adapter.modificarAnimal(v);
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
