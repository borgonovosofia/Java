package negocio;

import utilidades.conException;
import data.PesosAdapter;

public class Pesos {

	private double max;
	private double min;
	private double precio;
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public Pesos(double minimo, double maximo, double pre)
	{
		setMax(maximo);
		setMin(minimo);
		setPrecio(pre);
	}

	static public Pesos  buscarPeso(double peso) throws conException
	{
		PesosAdapter adapter = new PesosAdapter();
		return adapter.buscarDimension(peso);
	}
}
