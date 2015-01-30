package utilidades;

public class ConException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConException(String message, Throwable t){
		super(message,t);
		
	}
	
	//En el catch va:
	// try{...}
	// catch (ConException e)
	// ConException er = new ConException("Error recuperar base de datos",e);
	// throw er
	
	
	//En la capa de presentacion iria.
	// try{...}
	// catch (ConException ae)
	// JOptionPane.show(ae.getText());
	// throw er
		
}
