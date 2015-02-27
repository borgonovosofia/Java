package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Raza;
import negocio.TipoAnimal;
import utilidades.ConException;
import utilidades.Validaciones;

/**
 * Servlet implementation class RazaServlet
 */
@WebServlet("/RazaServlet")
public class RazaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RazaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("editar"))
		{
				String id = (String)request.getParameter("idRaza");
				request.getSession().setAttribute("idRaza", id);
				
				String razaNom = (String)request.getParameter("razaNom");
				request.getSession().setAttribute("nombreRaza", razaNom);
				request.getSession().setAttribute("busquedaRaza", "false");				
				response.sendRedirect("modificarRaza.jsp");
		}
		else if(accion.equals("borrar"))
		{
			request.getSession().setAttribute("busquedaRaza", "false");				

				try {
					String id = (String)request.getParameter("idRaza");
					int idRaza = Integer.parseInt(id);
					boolean rta = Raza.borrarRaza(idRaza);
					
					if(rta==true)
					{request.getSession().setAttribute("mensajeRaza", "Borrado correcto");}
					else{request.getSession().setAttribute("mensajeRaza", "No se pudo borrar la raza, intentelo mas tarde");}
					
				} catch (ConException e) {
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", "Error al borrar la raza");
				}
				finally{
					response.sendRedirect("tiposRazas.jsp");
				}
		}		
		else if(accion.equals("buscar"))
		{				
				request.getSession().setAttribute("busquedaRaza", "false");		
				response.sendRedirect("tiposRazas.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try{
			if(accion.equals("nuevo"))
			{
				request.getSession().setAttribute("busquedaRaza", "false");								
				Raza raza = this.ObtenerRaza(request);
				if(raza!=null)
				{				
					boolean rta = Raza.agregarRaza(raza);
					if(rta==false)
					{
						request.getSession().setAttribute("mensajeRaza", "Ya hay una raza con el nombre '"+raza.getNombre().toUpperCase()
										+"' para '"+raza.getTipo_animal().getNombre()+"', intente con otro nombre.");
						response.sendRedirect("tiposRazas.jsp");
						
					}
					else
					{
						request.getSession().setAttribute("mensajeRaza", "Registro correcto");
						response.sendRedirect("tiposRazas.jsp");
					}
				}				
			}
			else if (accion.equals("modificar"))
			{
				try {
					String nombreNuevo = Validaciones.validarCodigoDanino((String)request.getParameter("nombreNuevo"));
					int id = Integer.parseInt(request.getParameter("idRaza"));
										
					boolean rta = Raza.modificarRaza(id,nombreNuevo);
					if(rta==true)
					{
						request.getSession().setAttribute("mensajeRaza", "Modificacion correcta");
						response.sendRedirect("tiposRazas.jsp");
					}
					else
					{
						request.getSession().setAttribute("mensajeRaza", "Ya hay una raza con el nombre '"+nombreNuevo +"', intente con otro nombre.");
						response.sendRedirect("modificarRaza.jsp");
					}
					

				} catch (Exception e) {				
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("modificarRaza.jsp");
				}

			}
			else if(accion.equals("buscar"))
			{	
				try 
				{
					String valorBZ = Validaciones.validarCodigoDanino(request.getParameter("br"));
					request.getSession().setAttribute("valorBZ", valorBZ);
					
					request.getSession().setAttribute("busquedaRaza", "true");
					
					List<Raza> lista = Raza.dameRazas(valorBZ);  
					request.getSession().setAttribute("listaBusquedaR", lista);
					
					response.sendRedirect("tiposRazas.jsp");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					request.getSession().setAttribute("mensajeRaza", "No se pudo realizar la busqueda");
					response.sendRedirect("tiposRazas.jsp");
				}

			}
			
		}
		catch (ConException e){
			e.printStackTrace();
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("tiposRazas.jsp");

		}
		
		
	}

	
	
	protected Raza ObtenerRaza(HttpServletRequest request) throws ConException
	{
		 String nombreTipo = Validaciones.validarCodigoDanino(request.getParameter("animal"));
		 TipoAnimal tipo = TipoAnimal.buscarTipo(nombreTipo);
		 String nombreRaza = Validaciones.validarCodigoDanino(request.getParameter("nombreRaza"));
		 Raza raza = new Raza(nombreRaza,tipo);
		 return raza;
	}
}
