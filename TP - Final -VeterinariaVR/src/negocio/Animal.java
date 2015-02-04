package negocio;

import java.util.ArrayList;
import java.util.List;
import utilidades.ConException;
import datos.AnimalAdapter;
import datos.ConsultaAdapter;

public class Animal {
	private int id_animal;
	private String fecha_nac;
	private String sexo;
	private String nombre;
	private Raza raza;
	private Propietario propietario;
	private int cant_peluquerias;
	private int cant_consultas;
	
	
	public int getCant_peluquerias() {
		return cant_peluquerias;
	}
	public void setCant_peluquerias(int cant_peluquerias) {
		this.cant_peluquerias = cant_peluquerias;
	}
	
	public int getCant_consultas() {
		return cant_consultas;
	}
	public void setCant_consultas(int cant_consultas) {
		this.cant_consultas = cant_consultas;
	}
	
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
	public static int agregarAnimal(Animal t,int idRaza, int idProp) throws ConException
	{
		AnimalAdapter adapter = new AnimalAdapter();		
		return adapter.agregarAnimal(t,idRaza, idProp);
	}
	
	public static void agregarPeso(Peso p,int id_animal) throws ConException
	{
		AnimalAdapter adapter = new AnimalAdapter();		
		adapter.agregarPeso(p,id_animal);
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
	
	public List<Consulta> dameConsultas() throws Exception {
		try {
			ConsultaAdapter adapter = new ConsultaAdapter();
			ArrayList<Consulta> lista = adapter.getConsultas(this.getId_animal());
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static List<Consulta> dameTodasConsultas() throws Exception {
		try {
			ConsultaAdapter adapter = new ConsultaAdapter();
			ArrayList<Consulta> lista = adapter.getConsultas();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Peso> damePesos() throws Exception {
		try {
			AnimalAdapter adapter = new AnimalAdapter();
			ArrayList<Peso> lista = adapter.getPesos(this.getId_animal());
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Peluqueria> damePeluquerias() throws Exception {
		try {
			AnimalAdapter adapter = new AnimalAdapter();
			ArrayList<Peluqueria> lista = adapter.getPeluquerias(this.getId_animal());
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
}
