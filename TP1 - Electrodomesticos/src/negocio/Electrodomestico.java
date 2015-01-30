package negocio;

import utilidades.conException;
import data.ColoresAdapter;
import data.ConsumoAdapter;

public class Electrodomestico {
	public static double precioDefecto = 100;
	private double precio_base;
	private Color color;
	private Consumo consumo;
	private double peso;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrecio_base() {
		return precio_base;
	}
	public void setPrecio_base(double precio_base) {
		this.precio_base = precio_base;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Consumo getConsumo() {
		return consumo;
	}
	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	//CONSTRUCTORES
	public Electrodomestico() throws conException
	{
		setPrecio_base(precioDefecto);
		Color col = new Color();
		setColor(col.buscarColor(Color.colorDefecto));
		Consumo con = new Consumo();
		setConsumo(con.buscarConsumo(Consumo.consumoDefecto));
		setPeso(5);
	}
	public Electrodomestico(double nuevoPrecio, double nuevoPeso) throws conException
	{
		setPrecio_base(nuevoPrecio);
		Color col = new Color();
		setColor(col.buscarColor(Color.colorDefecto));
		Consumo con = new Consumo();
		setConsumo(con.buscarConsumo(Consumo.consumoDefecto));
		setPeso(nuevoPeso);
	}
	public Electrodomestico(double nuevoPrecio, String nuevoColor, String nuevoConsumo, double nuevoPeso) throws conException
	{
		setPrecio_base(nuevoPrecio);
		setPeso(nuevoPeso);
		
		Consumo consumo2 = comprobarConsumoEnergetico(nuevoConsumo);
		setConsumo(consumo2);
		Color color2 = comprobarColor(nuevoColor);
		setColor(color2);
	}
	
	//METODOS
	//VOLVER A PRIVADO
	Consumo comprobarConsumoEnergetico(String nuevoConsumo)
	{
		String cons = nuevoConsumo.toUpperCase();
		ConsumoAdapter adapter = new ConsumoAdapter();
		Consumo consumoActual = adapter.buscarPorId(cons);
		if(consumoActual==null)
		{
			consumoActual = adapter.buscarPorId(Consumo.consumoDefecto);
		}
		return consumoActual;
	}	
	
	
	Color comprobarColor(String nuevoColor) throws conException
	{
		String col = nuevoColor.toUpperCase();
		
		ColoresAdapter adapter = new ColoresAdapter();
		Color colorActual = adapter.buscarPorColor(col);
		if(colorActual==null)
		{	
			colorActual = adapter.buscarPorColor(Color.colorDefecto);
		}
		return colorActual;

	}		
	
	public double precioFinal() throws conException
	{
		double precio = getPrecio_base();
		precio = precio + getConsumo().getPrecio();
		Pesos pesoActual = Pesos.buscarPeso(getPeso());
		precio = precio + pesoActual.getPrecio();		
		return precio;
		
	}
		
}
	

	
	
