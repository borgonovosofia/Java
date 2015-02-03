package negocio;

import java.util.ArrayList;
import java.util.List;

import utilidades.ConException;
import datos.RazaAdapter;

public class Raza {

	private int id_raza;
	private String nombre;
	private TipoAnimal tipo_animal;
		
	public int getId_raza()
	{return id_raza;}
	public void setId_raza(int id_raza) 
	{this.id_raza = id_raza;}
	
	public String getNombre() 
	{return nombre;}
	public void setNombre(String nombre) 
	{this.nombre = nombre;}
	
	public TipoAnimal getTipo_animal() 
	{return tipo_animal;}
	public void setTipo_animal(TipoAnimal tipo_animal) 
	{this.tipo_animal = tipo_animal;}
	
	public Raza(String n,TipoAnimal tipo)
	{
		setNombre(n);
		setTipo_animal(tipo);
	}	
	
	public Raza() {
	}
	public static boolean agregarRaza(Raza r) throws ConException
	{
		RazaAdapter adapter = new RazaAdapter();
		Raza razaBuscada = adapter.buscarRaza(r.getNombre(), r.getTipo_animal().getNombre());
		if(razaBuscada != null)
		{return false;}
		else {
			adapter.agregarRaza(r);
			return true;
		}
	}
	
	public static Raza buscarRaza(String nombre,String tipo) throws ConException 
	{
		RazaAdapter adapter = new RazaAdapter();
		return adapter.buscarRaza(nombre, tipo);
	}

	
	public static List<Raza> dameRazas() throws Exception
	{
		try {
			RazaAdapter adapter = new RazaAdapter();
			ArrayList<Raza> lista = adapter.getRazas();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static List<Raza> dameRazas(String parecido) throws Exception
	{
		try {
			RazaAdapter adapter = new RazaAdapter();
			ArrayList<Raza> lista = adapter.getRazas(parecido);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean borrarRaza(int id) throws Exception {
		try {
			RazaAdapter adapter = new RazaAdapter();
			Raza razaBuscada = adapter.buscarRaza(id);
			if(razaBuscada!=null)
			{
				adapter.borrarRaza(id);
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

	public static boolean modificarRaza(int id,String nombreNuevo) throws Exception
	{
		try {
			RazaAdapter adapter = new RazaAdapter();
			Raza razaBuscada = adapter.buscarRaza(id);
				
			if(razaBuscada!=null)
			{
				Raza razaBuscada2 = adapter.buscarRaza(nombreNuevo, razaBuscada.getTipo_animal().getNombre());
				if(razaBuscada2== null || (razaBuscada.getNombre().equals(razaBuscada2.getNombre())))
				{
					adapter.modificarRaza(id,nombreNuevo);
					return true;
				}
				else
				{return false;}
			}
			else
			{
				return false;
			}
		} catch (ConException e) {
			throw e;
		}	
	}
	public static List<Raza> dameRazasTipo(int id_tipo) throws Exception {
		try {
			RazaAdapter adapter = new RazaAdapter();
			ArrayList<Raza> lista = adapter.getRazasTipo(id_tipo);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}

}
