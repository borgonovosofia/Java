package negocio;

import data.ConsumoAdapter;

public class Consumo {
	public static String consumoDefecto = "F"; 
	private String consumo;
	private double precio;
	
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public Consumo(String con, double pre)
	{
		setPrecio(pre);
		setConsumo(con);
	}
	public Consumo()
	{
		setPrecio(10);
		setConsumo(consumoDefecto);
	}
	public Consumo buscarConsumo(String col)
	{
		ConsumoAdapter adapter = new ConsumoAdapter();
		Consumo consumo = adapter.buscarPorId(col);
		return consumo;
	}

}
