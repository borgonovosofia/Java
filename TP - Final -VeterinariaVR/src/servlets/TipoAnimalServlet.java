package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ConException;
import negocio.Raza;
import negocio.TipoAnimal;

/**
 * Servlet implementation class TipoAnimalServlet
 */
@WebServlet("/TipoAnimalServlet")
public class TipoAnimalServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoAnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("actualizarTipos"))
		{
			try 
			{
				//Guarda lista de tipos 
				List<TipoAnimal> lista = TipoAnimal.dameListaTipos();
				request.getSession().setAttribute("listaTipos", lista);
				
				//Guarda lista de razas
				List<Raza> listaR = Raza.dameRazas();
				request.getSession().setAttribute("listaRazas",listaR);
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("tiposRazas.jsp");

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
				String tipoNom = (String)request.getParameter("nombre");
				int id = Integer.parseInt(request.getParameter("idTipo"));
				request.getSession().setAttribute("nombreTipo", tipoNom);
				request.getSession().setAttribute("idTipo", id);
				request.getSession().setAttribute("busquedaTipo", "false");				
				response.sendRedirect("modificarTipo.jsp");

		}
		else if(accion.equals("borrar"))
		{
			request.getSession().setAttribute("busquedaTipo", "false");			
				try {
					int idTipo = Integer.parseInt(request.getParameter("idTipo"));
					TipoAnimal.borrarTipo(idTipo);
					request.getSession().setAttribute("mensajeTipo", "Borrado correcto");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("TiposRazas.jsp");
				}
		}	
		else if(accion.equals("buscar"))
		{				
				request.getSession().setAttribute("busquedaTipo", "false");		
				response.sendRedirect("tiposRazas.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		request.getSession().setAttribute("busqueda", "false");				
		//PARA REDIRECCIONAR A LA PAGINA DE RAZAS Y TIPOS DE ANIMALES
		if(accion.equals("IrRaza")){
			try 
			{
				//Guarda lista de tipos 
				List<TipoAnimal> lista = TipoAnimal.dameListaTipos();
				request.getSession().setAttribute("listaTipos", lista);
				
				//Guarda lista de razas
				List<Raza> listaR = Raza.dameRazas();
				request.getSession().setAttribute("listaRazas",listaR);
				
				request.getSession().setAttribute("busquedaTipo", "false");				
				request.getSession().setAttribute("busquedaRaza", "false");				

				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("tiposRazas.jsp");

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
			request.getSession().setAttribute("busquedaTipo", "false");		
			TipoAnimal tipo = new TipoAnimal((String)request.getParameter("nombreTipo"));
			if(tipo!=null)
			{
				try{
					boolean rta = TipoAnimal.agregarTipo(tipo);
					if(rta==false)
					{
						request.getSession().setAttribute("mensajeTipo", "Ya hay un tipo de animal con el nombre '"+tipo.getNombre().toUpperCase()+"', intente con otro nombre.");
					}
					else
					{
						request.getSession().setAttribute("mensajeTipo", "Registro correcto");
					}
				}
				catch (ConException e){
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally
				{
					response.sendRedirect("tiposRazas.jsp");
				}
			}
		}
		else if(accion.equals("modificar"))
		{
			try {
				int idTipo = Integer.parseInt(request.getParameter("idTipo"));
				String nombre = (String)request.getParameter("nombreTipo");
				boolean rta = TipoAnimal.modificarTipo(idTipo,nombre);
				if(rta==true)
				{
					response.sendRedirect("tiposRazas.jsp");
					request.getSession().setAttribute("mensaje", "Modificacion correcta");
				}
				else
				{
					request.getSession().setAttribute("mensajeTipo", "El nombre indicado pertenece a otro animal");
					response.sendRedirect("modificarTipo.jsp");
				}
				

			} catch (Exception e) {	
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("modificarTipo.jsp");
			}

			
		}
		else if(accion.equals("buscar"))
		{	
			try 
			{
				String valorBT = request.getParameter("bt");
				request.getSession().setAttribute("valorBT", valorBT);
				
				request.getSession().setAttribute("busquedaTipo", "true");
				
				List<TipoAnimal> lista = TipoAnimal.dameListaTipos(valorBT); 
				request.getSession().setAttribute("listaBusquedaT", lista);
				
				response.sendRedirect("tiposRazas.jsp");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("mensajeTipo", "No se pudo realizar la busqueda");
				response.sendRedirect("tiposRazas.jsp");
			}

		}
		
		
	}
	
}
	
	