package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ConException;
import negocio.Propietario;

@WebServlet("/PropietarioServlet")
public class PropietarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropietarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("actualizar"))
		{
			try 
			{
				List<Propietario> lista = Propietario.damePropietarios();
				request.getSession().setAttribute("listaPropietarios", lista);					
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoPropietarios.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("menu.jsp");
			}
		}	
		else if(accion.equals("borrar"))
		{
			request.getSession().setAttribute("busqueda", "false");			
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					Propietario.borrarPropietario(id);
					request.getSession().setAttribute("mensaje", "Borrado correcto");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("listadoPropietarios.jsp");
				}
		}
		else if(accion.equals("editar"))
		{
				String nombre = (String)request.getParameter("nombre");
				String apellido = (String)request.getParameter("apellido");
				String direccion = (String)request.getParameter("direccion");
				String email = (String)request.getParameter("email");
				String telefono_fijo = (String)request.getParameter("telefono_fijo");
				String celular = (String)request.getParameter("celular");
				String usuario = (String)request.getParameter("usuario");
				String clave = (String)request.getParameter("clave");
				String claveNueva = (String)request.getParameter("claveNueva");
			
				int id = Integer.parseInt(request.getParameter("id"));
				
				request.getSession().setAttribute("nombre", nombre);
				request.getSession().setAttribute("apellido", apellido);
				request.getSession().setAttribute("direccion", direccion);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("telefono_fijo", telefono_fijo);
				request.getSession().setAttribute("celular", celular);
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("clave", clave);
				request.getSession().setAttribute("claveNueva", claveNueva);
				request.getSession().setAttribute("id", id);
				
				request.getSession().setAttribute("busqueda", "false");				
				response.sendRedirect("modificarPropietario.jsp");

		}
		else if(accion.equals("nueva"))
		{
			request.getSession().setAttribute("nombre", null);
			request.getSession().setAttribute("apellido", null);
			request.getSession().setAttribute("direccion", null);
			request.getSession().setAttribute("email", null);
			request.getSession().setAttribute("telefono_fijo", null);
			request.getSession().setAttribute("celular", null);
			request.getSession().setAttribute("usuario", null);
			request.getSession().setAttribute("claveNueva", null);
			
		response.sendRedirect("nuevoPropietario.jsp");
		}
		else if(accion.equals("buscar"))
		{				
				request.getSession().setAttribute("busqueda", "false");		
				response.sendRedirect("listadoPropietarios.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		//PARA REDIRECCIONAR A LA PAGINA DE RAZAS Y TIPOS DE ANIMALES
		if(accion.equals("IrPropietario")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				//Guarda lista de tipos 
				List<Propietario> lista = Propietario.damePropietarios();
				request.getSession().setAttribute("listaPropietarios", lista);
								
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoPropietarios.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("menu.jsp");
			}

		}	
	//PARA AGREGAR 
			else if(accion.equals("nuevo")){
				request.getSession().setAttribute("busqueda", "false");		
				
				String nombre = (String)request.getParameter("nombre");
				String apellido = (String)request.getParameter("apellido");
				String direccion = (String)request.getParameter("direccion");
				String email = (String)request.getParameter("email");
				String telefono_fijo = (String)request.getParameter("telefono_fijo");
				String celular = (String)request.getParameter("celular");
				String usuario = (String)request.getParameter("usuario");
				String clave = (String)request.getParameter("clave");
			
				
				Propietario v = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular,usuario,clave);
				if(v!=null)
				{
					try{
						boolean rta = Propietario.agregarPropietario(v);
						if(rta==false)
						{
							request.getSession().setAttribute("nombre", nombre);
							request.getSession().setAttribute("apellido", apellido);
							request.getSession().setAttribute("direccion", direccion);
							request.getSession().setAttribute("email", email);
							request.getSession().setAttribute("telefono_fijo", telefono_fijo);
							request.getSession().setAttribute("celular", celular);
							request.getSession().setAttribute("usuario", usuario);									
							
							request.getSession().setAttribute("mensaje", "Ya hay una propietario con el mismo usuario");
							response.sendRedirect("nuevoPropietario.jsp");

						}
						else
						{
							request.getSession().setAttribute("mensaje", "Registro correcto");
							response.sendRedirect("listadoPropietarios.jsp");
						}
					}
					catch (ConException e){
						e.printStackTrace();
						request.getSession().setAttribute("error", e.getMessage());
						response.sendRedirect("nuevoPropietario.jsp");
					}					
				}
			}
			else if(accion.equals("modificar"))
			{
				try {

					String nombre = (String)request.getParameter("nombre");
					String apellido = (String)request.getParameter("apellido");
					String direccion = (String)request.getParameter("direccion");
					String email = (String)request.getParameter("email");
					String telefono_fijo = (String)request.getParameter("telefono_fijo");
					String celular = (String)request.getParameter("celular");
					String usuario = (String)request.getParameter("usuario");
					String clave = (String)request.getParameter("clave");
					String claveNueva = (String)request.getParameter("claveNueva");
					int id = Integer.parseInt(request.getParameter("id"));
					
					if(claveNueva.equals(""))
					{claveNueva=clave;}	
					Propietario vac = new Propietario(nombre,apellido,direccion,email,telefono_fijo,celular,usuario,claveNueva);					
					vac.setId_propietario(id);
					
					boolean rta = Propietario.modificarPropietario(vac,clave);
					if(rta==true)
					{
						request.getSession().setAttribute("mensaje", "Modificacion correcta");
						response.sendRedirect("listadoPropietarios.jsp");						
					}
					else
					{
						request.getSession().setAttribute("mensaje", "La clave de acceso es incorrecta. No se pueden guardar los cambios");
						response.sendRedirect("modificarPropietario.jsp");
					}		

				} catch (Exception e) {	
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("modificarPropietario.jsp");
				}

				
			}
			else if(accion.equals("buscar"))
			{	
				try 
				{
					String valor = request.getParameter("b");
					request.getSession().setAttribute("valor", valor);
					
					request.getSession().setAttribute("busqueda", "true");
					
					List<Propietario> lista = Propietario.damePropietarios(valor); 
					request.getSession().setAttribute("listaBusqueda", lista);
					
					response.sendRedirect("listadoPropietarios.jsp");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					request.getSession().setAttribute("mensaje", "No se pudo realizar la busqueda");
					response.sendRedirect("listadoPropietarios.jsp");
				}

			}
			
	}

}
