package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ConException;
import negocio.IntervencionQuirurgica;

/**
 * Servlet implementation class IntervencionQuirurgicaServlet
 */
@WebServlet("/IntervencionServlet")
public class IntervencionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntervencionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("actualizar"))
		{
			try 
			{
				//Guarda lista de tipos 
				List<IntervencionQuirurgica> lista = IntervencionQuirurgica.dameListaIntervenciones();
				request.getSession().setAttribute("listaIntervenciones", lista);
								
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoIntervenciones.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("menu.jsp");
			}
		}
		else if(accion.equals("editar"))
		{
				String nombre = (String)request.getParameter("nombre");
				int id = Integer.parseInt(request.getParameter("id"));
				request.getSession().setAttribute("nombre", nombre);
				request.getSession().setAttribute("id", id);
				request.getSession().setAttribute("busqueda", "false");				
				response.sendRedirect("modificarIntervencion.jsp");

		}
		else if(accion.equals("borrar"))
		{
			request.getSession().setAttribute("busqueda", "false");			
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					IntervencionQuirurgica.borrarIntervencion(id);
					request.getSession().setAttribute("mensaje", "Borrado correcto");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("listadoIntervenciones.jsp");
				}
		}	
		else if(accion.equals("buscar"))
		{				
				request.getSession().setAttribute("busqueda", "false");		
				response.sendRedirect("listadoIntervenciones.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//PARA REDIRECCIONAR A LA PAGINA DE RAZAS Y TIPOS DE ANIMALES
		if(accion.equals("IrIntervenciones")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				//Guarda lista de tipos 
				List<IntervencionQuirurgica> lista = IntervencionQuirurgica.dameListaIntervenciones();
				request.getSession().setAttribute("listaIntervenciones", lista);
								
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoIntervenciones.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("menu.jsp");
			}

		}
		//PARA AGREGAR UN NUEVO TIPO
		else if(accion.equals("nuevo")){
			request.getSession().setAttribute("busqueda", "false");		
			IntervencionQuirurgica inter = new IntervencionQuirurgica((String)request.getParameter("nombre"));
			if(inter!=null)
			{
				try{
					boolean rta = IntervencionQuirurgica.agregarIntervencion(inter);
					if(rta==false)
					{
						request.getSession().setAttribute("mensaje", "Ya hay una intervencion con el nombre '"+inter.getNombre().toUpperCase()+"', intente con otro nombre.");
						response.sendRedirect("nuevaIntervencion.jsp");
					}
					else
					{
						request.getSession().setAttribute("mensajeTipo", "Registro correcto");
						response.sendRedirect("listadoIntervenciones.jsp");
					}
				}
				catch (ConException e){
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("nuevaIntervencion.jsp");
				}
			}
		}
		else if(accion.equals("modificar"))
		{
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String nombre = (String)request.getParameter("nombre");
				IntervencionQuirurgica inter = new IntervencionQuirurgica(nombre);
				inter.setId_intervencion(id);
				boolean rta = IntervencionQuirurgica.modificarIntervencion(inter);
				if(rta==true)
				{
					request.getSession().setAttribute("mensaje", "Modificacion correcta");
					response.sendRedirect("listadoIntervenciones.jsp");
				}
				else
				{
					request.getSession().setAttribute("mensaje", "El nombre indicado pertenece a otra");
					response.sendRedirect("modificarIntervencion.jsp");
				}
				

			} catch (Exception e) {	
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("listadoIntervenciones.jsp");
			}

			
		}
		else if(accion.equals("buscar"))
		{	
			try 
			{
				String valor = request.getParameter("b");
				request.getSession().setAttribute("valor", valor);
				
				request.getSession().setAttribute("busqueda", "true");
				
				List<IntervencionQuirurgica> lista = IntervencionQuirurgica.dameListaIntervenciones(valor); 
				request.getSession().setAttribute("listaBusqueda", lista);
				
				response.sendRedirect("listadoIntervenciones.jsp");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("mensaje", "No se pudo realizar la busqueda");
				response.sendRedirect("listadoIntervenciones.jsp");
			}

		}
		
		
	}
	
}
	
	