package negocio;

import java.util.ArrayList;







import java.util.List;

import presentacion.TableModelNuevo;
import utilidades.conException;
import data.*;

public class Television extends Electrodomestico {
	public static int resolucionDefecto = 20;
	private int resolucion;
	private boolean sintonizador;
	public int getResolucion() {
		return resolucion;
	}
	public void setResolucion(int resolucion) {
		this.resolucion = resolucion;
	}
	public boolean getSintonizador() {
		return sintonizador;
	}
	public void setSintonizador(boolean sintonizador) {
		this.sintonizador = sintonizador;
	}
	
	//CONSTRUCTORES
	public Television() throws conException
	{
		super();
		setResolucion(resolucionDefecto);
		setSintonizador(false);
	}
	public Television(double precio, double peso) throws conException
	{
		super(precio, peso);
		setResolucion(resolucionDefecto);
		setSintonizador(false);		
	}
	public Television(int resol, boolean sint, double precio, String color, String consumo, double peso) throws conException
	{
		super(precio,color,consumo,peso);
		setResolucion(resol);
		setSintonizador(sint);
	}

	//METODOS 
	public double precioFinal() throws conException

	{
		double precio = super.precioFinal();
		if(getResolucion()>40)
		{
			precio = precio + precio*0.30;
		}
		if(getSintonizador())
		{
			precio = precio + 50;
		}
		return precio;
	}
	
	public String dameSintonizador()
	{
		if(getSintonizador())
		{	return "Si"; }
		else
		{ return  "No";	}
	}

	public static TableModelNuevo getTelevisores() throws Exception
	{
		try {
			TelevisorAdapter adapter = new TelevisorAdapter();
			ArrayList<Television> lista = adapter.getTvs();
			TableModelNuevo dtm = new TableModelNuevo();
			dtm.setColumnIdentifiers(new Object[]{"Id","Color","Consumo","Peso","Resolución","Sintonizador","Precio Base","Precio"});
			for (Television televisor : lista) {
				String con = televisor.getConsumo().getConsumo();
				String col = televisor.getColor().getColor();
				dtm.addRow(new Object[] 
						{televisor.getId(), col, con, televisor.getPeso(),televisor.getResolucion(), televisor.dameSintonizador(),televisor.getPrecio_base(),televisor.precioFinal()} );
			}	
			return dtm;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void agregarTelevision(Television tev) throws conException
	{
		TelevisorAdapter adapter = new TelevisorAdapter();
		adapter.agregarTelevision(tev);
	}
	
	public static void modificarTelevision(Television tev, int id) throws conException
	{
		TelevisorAdapter adapter = new TelevisorAdapter();
		adapter.modificarTelevision(tev,id);
	}
	public static List<Television> dameListaTelevisores() throws Exception
	{
		try {
			TelevisorAdapter adapter = new TelevisorAdapter();
			ArrayList<Television> lista = adapter.getTvs();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
}
