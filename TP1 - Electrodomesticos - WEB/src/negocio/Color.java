package negocio;

import utilidades.conException;
import data.ColoresAdapter;

public class Color {
	public static String colorDefecto = "Blanco";
	
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Color(String col)
	{
		setColor(col);
	}
	public Color()
	{
		setColor(colorDefecto);
	}
	public Color buscarColor(String col) throws conException
	{
		ColoresAdapter adapter = new ColoresAdapter();
		Color color = adapter.buscarPorColor(col);
		return color;
	}
}
