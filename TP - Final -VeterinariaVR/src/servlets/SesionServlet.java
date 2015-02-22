package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ConException;
import negocio.*;

@WebServlet("/SesionServlet")
public class SesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SesionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//#region IrLogin
		if(accion.equals("IrLogin"))
		{
			request.getSession().setAttribute("login", false);
			request.getSession().setAttribute("usr", "");
			request.getSession().setAttribute("idusr", "");
			request.getSession().setAttribute("tipousuario", "");
			response.sendRedirect("login.jsp");
		}
		//#endregion
		//#region CerrarSesión
		if(accion.equals("CerrarSesion"))
		{
			request.getSession().setAttribute("login", false);
			request.getSession().setAttribute("usr", "");
			request.getSession().setAttribute("idusr", "");
			request.getSession().setAttribute("tipousuario", "");
			response.sendRedirect("index.jsp");
		}
		//#endregion
		//#region ModificarUsuario
		if(accion.equals("ModificarUsuario"))
		{

			try {
				String id = (String)request.getSession().getAttribute("idusr");
				Propietario pr = Propietario.buscarPropietario(Integer.parseInt(id));			
				
				request.getSession().setAttribute("nombre", pr.getNombre());
				request.getSession().setAttribute("apellido", pr.getApellido());
				request.getSession().setAttribute("direccion", pr.getDireccion());
				request.getSession().setAttribute("email", pr.getEmail());
				request.getSession().setAttribute("telefono_fijo", pr.getTelefono_fijo());
				request.getSession().setAttribute("celular", pr.getCelular());
				request.getSession().setAttribute("usuario", pr.getUsuario());
				request.getSession().setAttribute("id", id);
				
				request.getSession().setAttribute("busqueda", "false");				
				response.sendRedirect("modificarUsuario.jsp");
			
			} catch (ConException e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
			}

			
		}
		//#endregion
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//#region IniciarSesion
		if(accion.equals("IniciarSesion"))
		{
			try {
				Propietario pr = Propietario.login(request.getParameter("usuario"),request.getParameter("clave"));
				if(pr.getId_propietario()==0)
				{
					request.getSession().setAttribute("mensaje", "El usuario y/o la clave son incorrectos");
					request.getSession().setAttribute("usuario",request.getParameter("usuario"));
					request.getSession().setAttribute("clave", request.getParameter("clave"));
					response.sendRedirect("login.jsp");

				}
				else
				{
					request.getSession().setAttribute("login", true);
					request.getSession().setAttribute("usr", pr.getUsuario());
					request.getSession().setAttribute("idusr", Integer.toString(pr.getId_propietario()));
					request.getSession().setAttribute("tipousuario", pr.getTipo());
					
					response.sendRedirect("index.jsp");
				}	
				
			} catch (ConException e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("index.jsp");
			}
			
		}
		//#endregion	
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
					response.sendRedirect("index.jsp");						
				}
				else
				{
					request.getSession().setAttribute("mensaje", "La clave de acceso es incorrecta. No se pueden guardar los cambios");
					response.sendRedirect("modificarUsuario.jsp");
				}		

			} catch (Exception e) {	
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("modificarUsuario.jsp");
			}

			
		}
	
	}
}
