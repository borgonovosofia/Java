package utilidades;

public class Validaciones {

	static public String validarCodigoDanino(String codigo)
	{
		String description = codigo; 
		description = description . replaceAll ( "<" ,  "" );
		description = description .	replaceAll ( ">" ,  "" ); 
		description = description . replaceAll ( "eval\\((.*)\\)" ,  "" ); 
		description = description . replaceAll ( "[\\\"\\\'][\\s]*((?i)javascript):(.*)[\\\"\\\']" ,  "\"\"" ); 
		description = description . replaceAll ( "((?i)script)" ,  "" );
		return description;
	}
}
