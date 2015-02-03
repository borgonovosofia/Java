package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ConException;
import negocio.*;

@WebServlet("/AnimalServlet")
public class AnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("actualizar"))
		{
			try 
			{
				List<Animal> lista = Animal.dameAnimales();
				request.getSession().setAttribute("listaAnimales", lista);					
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoAnimales.jsp");

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
					Animal.borrarAnimal(id);
					request.getSession().setAttribute("mensaje", "Borrado correcto");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("listadoAnimales.jsp");
				}
		}
		else if(accion.equals("editar"))
		{
				String sexo = (String)request.getParameter("sexo");
				String fecha_nac = (String)request.getParameter("fecha_nac");
				String nombre = (String)request.getParameter("nombre");
				int id_propietario = Integer.parseInt(request.getParameter("id_propietario"));
				int id_raza = Integer.parseInt(request.getParameter("id_raza"));
				int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));			
				int id_animal = Integer.parseInt(request.getParameter("id"));
				
				request.getSession().setAttribute("id_animal", id_animal);
				request.getSession().setAttribute("sexo", sexo);
				request.getSession().setAttribute("nombre", nombre);
				request.getSession().setAttribute("fecha_nac", fecha_nac);
				request.getSession().setAttribute("id_propietario", id_propietario);
				request.getSession().setAttribute("id_raza", id_raza);
				request.getSession().setAttribute("id_tipo", id_tipo);
				
				this.actualizarCombos(request);
				
				request.getSession().setAttribute("busqueda", "false");				
				response.sendRedirect("modificarAnimal.jsp");

		}
		else if(accion.equals("nuevo"))
		{
			request.getSession().setAttribute("id_animal", null);
			request.getSession().setAttribute("sexo", null);
			request.getSession().setAttribute("nombre", null);
			request.getSession().setAttribute("fecha_nac", null);
			
			String id_propietario = (String)request.getParameter("id_propietario");
			String nombreP = (String)request.getParameter("nombreP");
			String apellidoP =(String)request.getParameter("apellidoP");

			request.getSession().setAttribute("id_propietario", id_propietario);
			request.getSession().setAttribute("nombreP", nombreP);
			request.getSession().setAttribute("apellidoP", apellidoP);			
			request.getSession().setAttribute("id_raza", null);
			request.getSession().setAttribute("id_tipo", null);
			request.getSession().setAttribute("busqueda", "false");		
			
			try {
				List<TipoAnimal> lista = TipoAnimal.dameListaTipos();
				request.getSession().setAttribute("listaTipos", lista);
				List<Raza> listaR = Raza.dameRazasTipo(lista.get(0).getId_tipo_animal());
				request.getSession().setAttribute("listaRazas",listaR);
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());		
				}
			
			response.sendRedirect("nuevoAnimal.jsp");
		}
		else if(accion.equals("buscar"))
		{				
				request.getSession().setAttribute("busqueda", "false");		
				response.sendRedirect("listadoAnimales.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//PARA REDIRECCIONAR 
		if(accion.equals("IrAnimales")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				//Guarda lista  
				List<Animal> lista = Animal.dameAnimales();
				request.getSession().setAttribute("listaAnimales", lista);
								
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoAnimales.jsp");

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
				
				String sexo = (String)request.getParameter("sexo");				
				String fecha_nac = (String)request.getParameter("fecha_nac");
			   	String nombre = (String)request.getParameter("nombre");
				int id_propietario = Integer.parseInt(request.getParameter("id_propietario"));
				int id_raza = Integer.parseInt(request.getParameter("id_raza"));
				int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));			
			
				TipoAnimal t = new TipoAnimal();
				t.setId_tipo_animal(id_tipo);
				
				Raza r = new Raza();
				r.setId_raza(id_raza);
				r.setTipo_animal(t);
				
				Propietario p = new Propietario();
				p.setId_propietario(id_propietario);
				
				Animal animal = new Animal(fecha_nac,sexo,nombre,r,p);
				if(animal!=null)
				{
					try{
						Animal.agregarAnimal(animal,id_raza,id_propietario);
						request.getSession().setAttribute("mensaje", "Registro correcto");
						response.sendRedirect("listadoAnimales.jsp");
					}
					catch (ConException e){
						e.printStackTrace();
						request.getSession().setAttribute("error", e.getMessage());
						response.sendRedirect("nuevoAnimal.jsp");
					}					
				}
			}
			else if(accion.equals("modificar"))
			{
				try {

					String sexo = (String)request.getParameter("sexo");					
					String fecha_nac = (String)request.getParameter("fecha_nac");								
					String nombre = (String)request.getParameter("nombre");
					int id_propietario = Integer.parseInt(request.getParameter("id_propietario"));
					int id_raza = Integer.parseInt(request.getParameter("id_raza"));
					int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));			
					int id_animal = Integer.parseInt(request.getParameter("id_animal"));
					
					TipoAnimal t = new TipoAnimal();
					t.setId_tipo_animal(id_tipo);
					
					Raza r = new Raza();
					r.setId_raza(id_raza);
					r.setTipo_animal(t);
					
					Propietario p = new Propietario();
					p.setId_propietario(id_propietario);
					
					Animal animal = new Animal(fecha_nac,sexo,nombre,r,p);
					animal.setId_animal(id_animal);
					
					boolean rta = Animal.modificarAnimal(animal);
					if(rta==true)
					{
						request.getSession().setAttribute("mensaje", "Modificacion correcta");
						response.sendRedirect("listadoAnimales.jsp");						
					}
					else
					{
						request.getSession().setAttribute("mensaje", "No se pudo modificar el animal, intente mas tarde");
						response.sendRedirect("modificarAnimal.jsp");
					}		

				} catch (Exception e) {	
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("modificarAnimal.jsp");
				}

				
			}
			else if(accion.equals("buscar"))
			{	
				try 
				{
					String valor = request.getParameter("b");
					request.getSession().setAttribute("valor", valor);
					
					request.getSession().setAttribute("busqueda", "true");
					
					List<Animal> lista = Animal.dameAnimales(valor); 
					request.getSession().setAttribute("listaBusqueda", lista);
					
					response.sendRedirect("listadoAnimales.jsp");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					request.getSession().setAttribute("mensaje", "No se pudo realizar la busqueda");
					response.sendRedirect("listadoAnimales.jsp");
				}

			}
			else if(accion.equals("ActualizarCombos"))
			{
					this.actualizarCombos(request);
					response.sendRedirect("nuevoAnimal.jsp");
			}
			
	}
	
	
	
	private void actualizarCombos(HttpServletRequest request)
	{
		try {
			List<TipoAnimal> lista = TipoAnimal.dameListaTipos();
			request.getSession().setAttribute("listaTipos", lista);
			int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));								
			List<Raza> listaR = Raza.dameRazasTipo(id_tipo);					
			request.getSession().setAttribute("listaRazas",listaR);
			
			String sexo = (String)request.getParameter("sexo");
			String nombre = (String)request.getParameter("nombre");
			String nombreP = (String)request.getParameter("nombreP");
			String apellidoP = (String)request.getParameter("apellidoP");
			String fecha_nac = (String)request.getParameter("fecha_nac");
			String id_pro = request.getParameter("id_propietario");
			Integer id_propietario = Integer.parseInt(id_pro);
			
			request.getSession().setAttribute("sexo", sexo);
			request.getSession().setAttribute("fecha_nac", fecha_nac);
			request.getSession().setAttribute("nombre", nombre);
			request.getSession().setAttribute("id_tipo", id_tipo);
			request.getSession().setAttribute("id_propietario",id_propietario);
			request.getSession().setAttribute("nombreP", nombreP);
			request.getSession().setAttribute("apellidoP", apellidoP);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("mensaje", e.getMessage());		
			}
	}
}
