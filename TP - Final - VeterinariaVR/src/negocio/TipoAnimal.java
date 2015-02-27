package negocio;

import java.util.*;

import utilidades.ConException;
import datos.TipoAnimalAdapter;

public class TipoAnimal {

	private int id_tipo_animal;
	private String nombre;
	
	public int getId_tipo_animal() 
	{return id_tipo_animal;}
	public void setId_tipo_animal(int id_tipo_animal) 
	{this.id_tipo_animal = id_tipo_animal;}
	
	public String getNombre() 
	{return nombre;}	
	public void setNombre(String nombre) 
	{this.nombre = nombre;}
	
	public TipoAnimal(String n)
	{
		setNombre(n);
	}

	
	public TipoAnimal() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean agregarTipo(TipoAnimal t) throws ConException
	{
		TipoAnimal tip = TipoAnimal.buscarTipo(t.getNombre()); 
		if(tip==null)
		{
			TipoAnimalAdapter adapter = new TipoAnimalAdapter();		
			adapter.agregarTipo(t);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static TipoAnimal buscarTipo(int id) throws ConException
	{
		TipoAnimalAdapter adapter = new TipoAnimalAdapter();
		return adapter.buscarTipo(id);
	}

	public static TipoAnimal buscarTipo(String nombre) throws ConException
	{
		TipoAnimalAdapter adapter = new TipoAnimalAdapter();
		return adapter.buscarTipo(nombre);
	}
	
	public static List<TipoAnimal> dameListaTipos() throws Exception
	{
		try {
			TipoAnimalAdapter adapter = new TipoAnimalAdapter();
			ArrayList<TipoAnimal> lista = adapter.getTipos();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
		
	public static List<TipoAnimal> dameListaTipos(String parecido) throws Exception
	{
		try {
			TipoAnimalAdapter adapter = new TipoAnimalAdapter();
			ArrayList<TipoAnimal> lista = adapter.getTipos(parecido);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean borrarTipo(int id) throws Exception {
		try {
			TipoAnimal tip = TipoAnimal.buscarTipo(id);
			if(tip!=null)
			{
				TipoAnimalAdapter adapter = new TipoAnimalAdapter();		
				adapter.borrarTipo(id);
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

	public static boolean modificarTipo(int id, String nuevoNombre) throws Exception
	{
		try {
			TipoAnimal tip = TipoAnimal.buscarTipo(id);
			TipoAnimal tip2 = TipoAnimal.buscarTipo(nuevoNombre);
			if(tip2==null || ((tip.getNombre().equalsIgnoreCase(tip2.getNombre())))	)
			{
				TipoAnimalAdapter adapter = new TipoAnimalAdapter();		
				adapter.modificarTipo(tip.getId_tipo_animal(),nuevoNombre);
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
