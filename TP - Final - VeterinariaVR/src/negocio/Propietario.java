package negocio;

import java.util.ArrayList;
import java.util.List;

import utilidades.ConException;
import utilidades.TestEncriptarMD5;
import datos.AnimalAdapter;
import datos.PropietarioAdapter;

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
	private String tipo;

	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
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
	public Propietario()
	{
		
	}

	public static boolean agregarPropietario(Propietario t) throws ConException
	{
		Propietario v = Propietario.buscarPropietarioPorUsuario(t.getUsuario());
		if(v==null)
		{
			PropietarioAdapter adapter = new PropietarioAdapter();		
			adapter.agregarPropietario(t);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Propietario buscarPropietario(int id) throws ConException
	{
		PropietarioAdapter adapter = new PropietarioAdapter();
		return adapter.buscarPropietario(id);
	}

	public static Propietario buscarPropietarioPorUsuario(String usuario) throws ConException
	{
		PropietarioAdapter adapter = new PropietarioAdapter();
		return adapter.buscarPropietarioPorUsuario(usuario);
	}
		
	public static List<Propietario> damePropietarios() throws Exception
	{
		try {
			PropietarioAdapter adapter = new PropietarioAdapter();
			ArrayList<Propietario> lista = adapter.getPropietarios();
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
		
	public static List<Propietario> damePropietarios(String parecido) throws Exception
	{
		try {
			PropietarioAdapter adapter = new PropietarioAdapter();
			ArrayList<Propietario> lista = adapter.getPropietarios(parecido);
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean borrarPropietario(int id) throws Exception {
		try {
			Propietario v= Propietario.buscarPropietario(id);
			if(v!=null)
			{
				PropietarioAdapter adapter = new PropietarioAdapter();		
				adapter.borrarPropietario(id);
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

	public static boolean modificarPropietario(Propietario v) throws Exception
	{
		try {
				PropietarioAdapter adapter = new PropietarioAdapter();		
				adapter.modificarPropietario(v);
				return true;
		
		} catch (ConException e) {
			throw e;
		}	
	}
	
	public static boolean modificarPropietario(Propietario v,String pass) throws Exception
	{
		try {
			Propietario vac = Propietario.buscarPropietario(v.getId_propietario());
			Propietario vac2 = Propietario.buscarPropietarioPorUsuario(v.getUsuario());
			if(vac2==null || ((vac.getUsuario().equalsIgnoreCase(vac2.getUsuario()))))
			{
				if(vac.getClave().equals(TestEncriptarMD5.md5(pass)))
				{
					PropietarioAdapter adapter = new PropietarioAdapter();		
					adapter.modificarUsuario(v);
					return true;
				}
				else
				{return false;}
			}
			else
			{
				return false;
			}
		} catch (ConException e) {
			throw e;
		}	
	}
	
	public List<Animal> dameAnimales() throws ConException {
		AnimalAdapter adapter = new AnimalAdapter();
		return adapter.getAnimales(this.getId_propietario());
	}
	
	
	public static Propietario login(String usr, String pass) throws ConException
	{
		PropietarioAdapter adapter = new PropietarioAdapter();
		return adapter.login(usr,pass);
	}


}
