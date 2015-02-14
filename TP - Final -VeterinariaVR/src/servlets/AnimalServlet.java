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
				response.sendRedirect("index.jsp");
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
			
				try {
					Animal animal = Animal.buscarAnimal(Integer.parseInt((String)request.getParameter("id")));
					request.getSession().setAttribute("nombreP", animal.getPropietario().getNombre());
					request.getSession().setAttribute("apellidoP", animal.getPropietario().getApellido());
					request.getSession().setAttribute("sexo", animal.getSexo());
					request.getSession().setAttribute("fecha_nac", animal.getFecha_nac());
					request.getSession().setAttribute("nombre", animal.getNombre());
					request.getSession().setAttribute("id_propietario", animal.getPropietario().getId_propietario());
					request.getSession().setAttribute("id_raza", animal.getRaza().getId_raza());
					request.getSession().setAttribute("id_tipo", animal.getRaza().getTipo_animal().getId_tipo_animal());
					request.getSession().setAttribute("raza", animal.getRaza().getNombre());
					request.getSession().setAttribute("tipo", animal.getRaza().getTipo_animal().getNombre());
					request.getSession().setAttribute("id_animal", animal.getId_animal());				
					
					
					this.actualizarCombos(request);					
					
					
					request.getSession().setAttribute("busqueda", "false");		
					response.sendRedirect("modificarAnimal.jsp");				
				} catch (NumberFormatException | ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());		
					response.sendRedirect("modificarAnimal.jsp");
				}							
		}
		else if(accion.equals("nuevo"))
		{
			request.getSession().setAttribute("id_animal", null);
			request.getSession().setAttribute("sexo", null);
			request.getSession().setAttribute("nombre", null);
			request.getSession().setAttribute("fecha_nac", null);
			request.getSession().setAttribute("peso", null);

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
		else if(accion.equals("ver"))
		{
			try {
				Animal animal = Animal.buscarAnimal(Integer.parseInt((String)request.getParameter("id")));
				List<Peso> pesos = animal.damePesos();
				List<Peluqueria> peluquerias = animal.damePeluquerias();
				List<Consulta> consultas = animal.dameConsultas();
				
				request.getSession().setAttribute("nombreP", animal.getPropietario().getNombre());
				request.getSession().setAttribute("apellidoP", animal.getPropietario().getApellido());
				request.getSession().setAttribute("sexo", animal.getSexo());
				request.getSession().setAttribute("fecha_nac", animal.getFecha_nac());
				request.getSession().setAttribute("nombre", animal.getNombre());
				request.getSession().setAttribute("id_propietario", animal.getPropietario().getId_propietario());
				request.getSession().setAttribute("id_raza", animal.getRaza().getId_raza());
				request.getSession().setAttribute("id_tipo", animal.getRaza().getTipo_animal().getId_tipo_animal());
				request.getSession().setAttribute("raza", animal.getRaza().getNombre());
				request.getSession().setAttribute("tipo", animal.getRaza().getTipo_animal().getNombre());
				request.getSession().setAttribute("id_animal", animal.getId_animal());				
				
				request.getSession().setAttribute("listaPesos", pesos);
				request.getSession().setAttribute("listaPeluquerias", peluquerias);
				request.getSession().setAttribute("listaConsultas", consultas);
				
				response.sendRedirect("verAnimal.jsp");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("listadoAnimales.jsp");
			} catch (ConException e) {
				e.printStackTrace();
				response.sendRedirect("listadoAnimales.jsp");
			}
			catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("listadoAnimales.jsp");
			}
		}
	}

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
				response.sendRedirect("index.jsp");
			}

		}	
	//PARA AGREGAR 
			else if(accion.equals("nuevo")){
				request.getSession().setAttribute("busqueda", "false");	

				String peso = (String)request.getParameter("peso");				
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
				
				Peso pes =null;
				if(peso!=""){
					pes = new Peso();
					pes.setPeso(Double.parseDouble(peso));
				}
				Animal animal = new Animal(fecha_nac,sexo,nombre,r,p);
				if(animal!=null)
				{
					try{
						int id = Animal.agregarAnimal(animal,id_raza,id_propietario);
						if(peso!=""){Animal.agregarPeso(pes,id);}
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
			else if(accion.equals("nuevoPeso"))
			{
				String peso = (String)request.getParameter("pesoI");											
				int id_animal = Integer.parseInt(request.getParameter("id_animal"));

				Animal animal = new Animal();
				animal.setId_animal(id_animal);
				
				Peso pes = null;
				pes = new Peso();
				pes.setPeso(Double.parseDouble(peso));
				try {
					Animal.agregarPeso(pes,id_animal);
					List<Peso> pesos = animal.damePesos();
					request.getSession().setAttribute("listaPesos", pesos);
					response.sendRedirect("verAnimal.jsp");
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("verAnimal.jsp");
				}
			}
			else if(accion.equals("modificar"))
			{
				try {
					String peso = (String)request.getParameter("peso");					
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
					
					Peso pes = null;
					if(peso!="")
					{
						pes = new Peso();
						pes.setPeso(Double.parseDouble(peso));
					}
					
					boolean rta = Animal.modificarAnimal(animal);
					
					if(rta==true)
					{
						
						if(peso!=""){Animal.agregarPeso(pes,id_animal);}
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
					
					int id_tipo = Integer.parseInt((String)request.getParameter("id_tipo"));								
					String peso = (String)request.getParameter("peso");
					String sexo = (String)request.getParameter("sexo");
					String nombre = (String)request.getParameter("nombre");
					String nombreP = (String)request.getParameter("nombreP");
					String apellidoP = (String)request.getParameter("apellidoP");
					String fecha_nac = (String)request.getParameter("fecha_nac");
					int id_propietario = Integer.parseInt(request.getParameter("id_propietario"));
					
					request.getSession().setAttribute("peso", peso);
					request.getSession().setAttribute("sexo", sexo);
					request.getSession().setAttribute("fecha_nac", fecha_nac);
					request.getSession().setAttribute("nombre", nombre);
					request.getSession().setAttribute("id_tipo", id_tipo);
					request.getSession().setAttribute("id_propietario",id_propietario);
					request.getSession().setAttribute("nombreP", nombreP);
					request.getSession().setAttribute("apellidoP", apellidoP);
					
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


			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("mensaje", e.getMessage());		
			}
	}
}
