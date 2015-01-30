package negocio;

import java.util.ArrayList;






import java.util.List;

import presentacion.TableModelNuevo;
import utilidades.conException;
import data.LavarropasAdapter;

public class Lavarropas extends Electrodomestico {
	public static double cargaDefecto = 5;
	private double carga;
	public double getCarga() {
		return carga;
	}
	public void setCarga(double carga) {
		this.carga = carga;
	}

	//CONSTRUCTORES
	public Lavarropas() throws conException
	{
		super();
		setCarga(cargaDefecto);
	}
	public Lavarropas(double precio, double peso) throws conException
	{
		super(precio,peso);
		setCarga(cargaDefecto);
	}
	public Lavarropas(double cargaNueva, double precio, String color, String consumo, double peso) throws conException
	{
		super(precio,color,consumo,peso);
		setCarga(cargaNueva);

	}

	//METODOS
	public double precioFinal() throws conException
	{
		double precio = super.precioFinal();
		if(getCarga()>30)
		{ precio = precio + 50.00;}		
		return precio;
	}
	
	public static TableModelNuevo getLavarropas() throws conException
	{
		
			LavarropasAdapter adapter = new LavarropasAdapter();
			ArrayList<Lavarropas> lista = adapter.getLavarropas();
			TableModelNuevo dtm = new TableModelNuevo();
			dtm.setColumnIdentifiers(new Object[]{"Id","Color","Consumo","Peso","Carga","Precio Base","Precio"});
			for (Lavarropas lavarropas : lista) {
				dtm.addRow(new Object[] {lavarropas.getId(),lavarropas.getColor().getColor(), lavarropas.getConsumo().getConsumo(), 
						 		lavarropas.getPeso(),lavarropas.getCarga(),lavarropas.getPrecio_base(),lavarropas.precioFinal()} );
			}
			return dtm;
	
	}


	public static void agregarLavarropas(Lavarropas lav) throws conException
	{
		LavarropasAdapter adapter = new LavarropasAdapter();
		adapter.agregarLavarropas(lav);
	}
	public static void modificarLavarropas(Lavarropas lav, int id) throws conException
	{
		LavarropasAdapter adapter = new LavarropasAdapter();
		adapter.modificarLavarropas(lav,id);
	}
	public static List<Lavarropas> dameListaLavarropas() throws Exception
	{
		try {
			LavarropasAdapter adapter = new LavarropasAdapter();
			ArrayList<Lavarropas> lista = adapter.getLavarropas();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
}
