package negocio;

import java.util.ArrayList;
import java.util.List;

import utilidades.ConException;
import datos.PeluqueriaAdapter;


public class Peluqueria {
	private int id_peluqueria;
	private String fecha;
	private String accion;
	private String comentarios;
	private Animal animal;
	
	public int getId_peluqueria() 
	{return id_peluqueria;}	
	public void setId_peluqueria(int id_peluqueria) 
	{this.id_peluqueria = id_peluqueria;}

	public String getFecha() 
	{return fecha;}
	public void setFecha(String fecha) 
	{this.fecha = fecha;}

	public String getAccion() 
	{return accion;}
	public void setAccion(String accion) 
	{this.accion = accion;}

	public String getComentarios() 
	{return comentarios;}
	public void setComentarios(String comentarios) 
	{this.comentarios = comentarios;}

	public Animal getAnimal() 
	{return animal;}
	public void setAnimal(Animal animal) 
	{this.animal = animal;}

	public Peluqueria(String d, String a, String c, Animal an)
	{
		setFecha(d);
		setAccion(a);
		setComentarios(c);
		setAnimal(an);
	}
	public Peluqueria() {
	}
	public static List<Peluqueria> damePeluquerias(String valor) throws Exception {
		try {
			PeluqueriaAdapter adapter = new PeluqueriaAdapter();
			ArrayList<Peluqueria> lista = adapter.getPeluquerias(valor);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	public static List<Peluqueria> damePeluquerias() throws Exception {
		try {
			PeluqueriaAdapter adapter = new PeluqueriaAdapter();
			ArrayList<Peluqueria> lista = adapter.getPeluquerias();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static Peluqueria buscarPeluqueria(int id) throws ConException
	{
		PeluqueriaAdapter adapter = new PeluqueriaAdapter();
		return adapter.buscarPeluqueria(id);
	}

	public static boolean borrarPeluqueria(int id) throws Exception {
		try {
			Peluqueria v= Peluqueria.buscarPeluqueria(id);
			if(v!=null)
			{
				PeluqueriaAdapter adapter = new PeluqueriaAdapter();		
				adapter.borrarPeluqueria(id);
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
	
	
	public static boolean modificarPeluqueria(Peluqueria p) throws Exception
	{
		try {
			Peluqueria pelu = Peluqueria.buscarPeluqueria(p.getId_peluqueria());
			if(pelu!=null)
			{
				PeluqueriaAdapter adapter = new PeluqueriaAdapter();		
				adapter.modificarPeluqueria(p);
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
