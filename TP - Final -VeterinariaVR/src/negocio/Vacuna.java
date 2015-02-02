package negocio;

import java.util.ArrayList;
import java.util.List;

import utilidades.ConException;
import datos.TipoAnimalAdapter;
import datos.VacunaAdapter;

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
	
	public Vacuna()
	{}
	
	public static boolean agregarVacuna(Vacuna t) throws ConException
	{
		Vacuna v = Vacuna.buscarVacunaPorCodigo(t.getCodigo()); 
		if(v==null)
		{
			VacunaAdapter adapter = new VacunaAdapter();		
			adapter.agregarVacuna(t);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Vacuna buscarVacuna(int id) throws ConException
	{
		VacunaAdapter adapter = new VacunaAdapter();
		return adapter.buscarVacuna(id);
	}

	public static Vacuna buscarVacunaPorNombre(String nombre) throws ConException
	{
		VacunaAdapter adapter = new VacunaAdapter();
		return adapter.buscarVacunaPorNombre(nombre);
	}
	
	public static Vacuna buscarVacunaPorCodigo(String codigo) throws ConException
	{
		VacunaAdapter adapter = new VacunaAdapter();
		return adapter.buscarVacunaPorCodigo(codigo);
	}
		
	public static List<Vacuna> dameVacunas() throws Exception
	{
		try {
			VacunaAdapter adapter = new VacunaAdapter();
			ArrayList<Vacuna> lista = adapter.getVacunas();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
		
	public static List<Vacuna> dameVacunas(String parecido) throws Exception
	{
		try {
			VacunaAdapter adapter = new VacunaAdapter();
			ArrayList<Vacuna> lista = adapter.getVacunas(parecido);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean borrarVacuna(int id) throws Exception {
		try {
			Vacuna v= Vacuna.buscarVacuna(id);
			if(v!=null)
			{
				VacunaAdapter adapter = new VacunaAdapter();		
				adapter.borrarVacuna(id);
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

	public static boolean modificarVacuna(Vacuna v) throws Exception
	{
		try {
			Vacuna vac = Vacuna.buscarVacuna(v.getId_vacuna());
			Vacuna vac2 = Vacuna.buscarVacunaPorCodigo(v.getCodigo());
			if(vac2==null || ((vac.getCodigo().equalsIgnoreCase(vac2.getCodigo()))))
			{
				VacunaAdapter adapter = new VacunaAdapter();		
				adapter.modificarVacuna(v);
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
