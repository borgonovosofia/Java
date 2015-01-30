package negocio;

public class Propietario {
	private int id_propietario; 
	private String nombre;
	private String apellido;
	private String direccion;
	private String email;
	private String telefono_fijo;
	private String celular;
	private String usuario;
	private String clave;
	
	public int getId_propietario() 
	{return id_propietario;}
	public void setId_propietario(int id_propietario) 
	{this.id_propietario = id_propietario;}
	
	public String getNombre() 
	{return nombre;}
	public void setNombre(String nombre) 
	{this.nombre = nombre;}
	
	public String getApellido() 
	{return apellido;}
	public void setApellido(String apellido) 
	{this.apellido = apellido;}
	
	public String getDireccion() 
	{return direccion;}
	public void setDireccion(String direccion) 
	{this.direccion = direccion;}
	
	public String getEmail() 
	{return email;}
	public void setEmail(String email) 
	{this.email = email;}
	
	public String getTelefono_fijo() 
	{return telefono_fijo;}
	public void setTelefono_fijo(String telefono_fijo) 
	{this.telefono_fijo = telefono_fijo;}
	
	public String getCelular() 
	{return celular;}
	public void setCelular(String celular) 
	{this.celular = celular;}
	
	public String getUsuario() 
	{return usuario;}
	public void setUsuario(String usuario) 
	{this.usuario = usuario;}
	
	public String getClave() 
	{return clave;}
	public void setClave(String clave) 
	{this.clave = clave;}
	
	public Propietario(String n, String a, String d, String e, String t,String c, String usr, String clave)
	{
		setNombre(n);
		setApellido(a);
		setDireccion(d);
		setEmail(e);
		setTelefono_fijo(t);
		setCelular(c);
		setUsuario(usr);
		setClave(clave);
	}
}
