package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ConException;
import negocio.Vacuna;

/**
 * Servlet implementation class VacunaServlet
 */
@WebServlet("/VacunaServlet")
public class VacunaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacunaServlet() {
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
				List<Vacuna> lista = Vacuna.dameVacunas();
				request.getSession().setAttribute("listaVacunas", lista);					
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoVacunas.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("index.jsp");
			}
		}	
		else if(accion.equals("borrar"))
		{
			request.getSession().setAttribute("busqueda", "false");			
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					Vacuna.borrarVacuna(id);
					request.getSession().setAttribute("mensaje", "Borrado correcto");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("listadoVacunas.jsp");
				}
		}
		else if(accion.equals("editar"))
		{
				String nombre = (String)request.getParameter("nombre");
				String codigo = (String)request.getParameter("codigo");
				String marca = (String)request.getParameter("marca");
				int duracion = Integer.parseInt(request.getParameter("duracion"));
				int id = Integer.parseInt(request.getParameter("id"));
				request.getSession().setAttribute("nombre", nombre);
				request.getSession().setAttribute("codigo", codigo);
				request.getSession().setAttribute("marca", marca);
				request.getSession().setAttribute("duracion", duracion);
				request.getSession().setAttribute("id", id);
				request.getSession().setAttribute("busqueda", "false");				
				response.sendRedirect("modificarVacuna.jsp");

		}
		else if(accion.equals("nueva"))
		{
		request.getSession().setAttribute("nombre", null);
		request.getSession().setAttribute("codigo", null);
		request.getSession().setAttribute("marca", null);
		request.getSession().setAttribute("duracion", null);
		response.sendRedirect("nuevaVacuna.jsp");
		}
		else if(accion.equals("buscar"))
		{				
				request.getSession().setAttribute("busqueda", "false");		
				response.sendRedirect("listadoVacunas.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		//PARA REDIRECCIONAR A LA PAGINA DE RAZAS Y TIPOS DE ANIMALES
		if(accion.equals("IrVacuna")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				//Guarda lista de tipos 
				List<Vacuna> lista = Vacuna.dameVacunas();
				request.getSession().setAttribute("listaVacunas", lista);
								
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoVacunas.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("index.jsp");
			}

		}
		
	
	
	//PARA AGREGAR UNA NUEVA VACUNA
			else if(accion.equals("nuevo")){
				request.getSession().setAttribute("busqueda", "false");		
				Integer duracion = Integer.parseInt((String)request.getParameter("duracion"));
				Vacuna v = new Vacuna((String)request.getParameter("codigo"),(String)request.getParameter("nombre"),(String)request.getParameter("marca"),duracion);
				if(v!=null)
				{
					try{
						boolean rta = Vacuna.agregarVacuna(v);
						if(rta==false)
						{
							String nombre = (String)request.getParameter("nombre");
							String codigo = (String)request.getParameter("codigo");
							String marca = (String)request.getParameter("marca");
							request.getSession().setAttribute("nombre", nombre);
							request.getSession().setAttribute("codigo", codigo);
							request.getSession().setAttribute("marca", marca);
							request.getSession().setAttribute("duracion", duracion.toString());
							
							request.getSession().setAttribute("mensaje", "Ya hay una vacuna con el mismo codigo");
							response.sendRedirect("nuevaVacuna.jsp");

						}
						else
						{
							request.getSession().setAttribute("mensaje", "Registro correcto");
							response.sendRedirect("listadoVacunas.jsp");
						}
					}
					catch (ConException e){
						e.printStackTrace();
						request.getSession().setAttribute("error", e.getMessage());
						response.sendRedirect("nuevaVacuna.jsp");
					}					
				}
			}
			else if(accion.equals("modificar"))
			{
				try {
					String nombre = (String)request.getParameter("nombre");
					String codigo = (String)request.getParameter("codigo");
					String marca = (String)request.getParameter("marca");
					int duracion = Integer.parseInt(request.getParameter("duracion"));
					int id = Integer.parseInt(request.getParameter("id"));
					Vacuna vac = new Vacuna(codigo,nombre,marca,duracion);
					vac.setId_vacuna(id);
					boolean rta = Vacuna.modificarVacuna(vac);
					if(rta==true)
					{
						request.getSession().setAttribute("mensaje", "Modificacion correcta");
						response.sendRedirect("listadoVacunas.jsp");						
					}
					else
					{
						request.getSession().setAttribute("mensaje", "El codigo indicado pertenece a otra vacuna");
						response.sendRedirect("modificarVacuna.jsp");
					}		

				} catch (Exception e) {	
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("modificarVacuna.jsp");
				}

				
			}
			else if(accion.equals("buscar"))
			{	
				try 
				{
					String valor = request.getParameter("b");
					request.getSession().setAttribute("valor", valor);
					
					request.getSession().setAttribute("busqueda", "true");
					
					List<Vacuna> lista = Vacuna.dameVacunas(valor); 
					request.getSession().setAttribute("listaBusqueda", lista);
					
					response.sendRedirect("listadoVacunas.jsp");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					request.getSession().setAttribute("mensaje", "No se pudo realizar la busqueda");
					response.sendRedirect("listadoVacunas.jsp");
				}

			}
			
	}

}
