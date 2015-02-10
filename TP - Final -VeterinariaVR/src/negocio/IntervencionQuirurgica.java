package negocio;

import java.util.ArrayList;
import java.util.List;

import utilidades.ConException;
import datos.IntervencionAdapter;

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
	

	public IntervencionQuirurgica() {
	}
	public static boolean agregarIntervencion(IntervencionQuirurgica t) throws ConException
	{
		IntervencionQuirurgica inter = IntervencionQuirurgica.buscarIntervencion(t.getNombre());  
		if(inter==null)
		{
			IntervencionAdapter adapter = new IntervencionAdapter();		
			adapter.agregarIntervencion(t);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static IntervencionQuirurgica buscarIntervencion(int id) throws ConException
	{
		IntervencionAdapter adapter = new IntervencionAdapter();
		return adapter.buscarIntervencion(id);
	}

	public static IntervencionQuirurgica buscarIntervencion(String nombre) throws ConException
	{
		IntervencionAdapter adapter = new IntervencionAdapter();
		return adapter.buscarIntervencion(nombre);
	}
	
	public static List<IntervencionQuirurgica> dameListaIntervenciones() throws Exception
	{
		try {
			IntervencionAdapter adapter = new IntervencionAdapter();
			ArrayList<IntervencionQuirurgica> lista = adapter.getIntervenciones();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
		
	public static List<IntervencionQuirurgica> dameListaIntervenciones(String parecido) throws Exception
	{
		try {
			IntervencionAdapter adapter = new IntervencionAdapter();
			ArrayList<IntervencionQuirurgica> lista = adapter.getIntervenciones(parecido);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean borrarIntervencion(int id) throws Exception {
		try {
			IntervencionQuirurgica tip = IntervencionQuirurgica.buscarIntervencion(id);
			if(tip!=null)
			{
				IntervencionAdapter adapter = new IntervencionAdapter();		
				adapter.borrarIntervencion(id);
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

	public static boolean modificarIntervencion(IntervencionQuirurgica inter) throws Exception
	{
		try {
			IntervencionQuirurgica inter1 = IntervencionQuirurgica.buscarIntervencion(inter.getId_intervencion());
			IntervencionQuirurgica inter2 = IntervencionQuirurgica.buscarIntervencion(inter.getNombre());
			if(inter2==null || ((inter1.getNombre().equalsIgnoreCase(inter2.getNombre())))	)
			{
				IntervencionAdapter adapter = new IntervencionAdapter();		
				adapter.modificarIntervencion(inter);
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
