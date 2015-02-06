package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ConException;
import negocio.*;

@WebServlet("/PeluqueriaServlet")
public class PeluqueriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeluqueriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");				
		if(accion.equals("actualizar"))
		{	System.out.println("entro a actualizar");
			try 
			{
				List<Peluqueria> lista = Peluqueria.damePeluquerias();
				request.getSession().setAttribute("listaPeluqueria", lista);					
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoPeluquerias.jsp");

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
					Peluqueria.borrarPeluqueria(id);
					request.getSession().setAttribute("mensaje", "Borrado correcto");
					response.sendRedirect("listadoAnimales.jsp");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("listadoAnimales.jsp");
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("listadoAnimales.jsp");
				}
				
		}
		else if(accion.equals("editar"))
		{/*
			
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
				}	*/						
		}
		else if(accion.equals("nueva"))
		{
			try {
				request.getSession().setAttribute("nombreP", null);
				request.getSession().setAttribute("apellidoP", null);
				request.getSession().setAttribute("nombre", null);
				request.getSession().setAttribute("tratamiento", null);
				request.getSession().setAttribute("comentarios", null);
				request.getSession().setAttribute("fecha", null);

				/*Busco el id_propietario */			
				String id_propietario = (String)request.getParameter("id_propietario");
				
				/*Creo el propietario*/
				Propietario pr = new Propietario();
				pr.setId_propietario(Integer.parseInt(id_propietario));
				
				/*Cargo la lista de propietarios*/
				List<Propietario> lista = Propietario.damePropietarios();
				request.getSession().setAttribute("listaPropietarios", lista);	
				
				/*Busco el propietario correcto*/		
				if(id_propietario.equals("0"))
				{	id_propietario = Integer.toString(lista.get(0).getId_propietario());	}			
				
				//SETEO DE ID PROPIETARIO 
				request.getSession().setAttribute("id_propietario", id_propietario);
				
				
				/*Busco el id_animal */			
				String id_animal = (String)request.getParameter("id_animal");
				if(id_animal.equals("0"))
				{
					request.getSession().setAttribute("id_animal", null);
				}else
				{
					request.getSession().setAttribute("id_animal", id_animal);	
				}
					
				pr.setId_propietario(Integer.parseInt(id_propietario));
				List<Animal> listaA = pr.dameAnimales();		
				request.getSession().setAttribute("listaAnimales",listaA);			
				response.sendRedirect("nuevaPeluqueria.jsp");
			} catch (Exception e) {
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("menu.jsp");
				e.printStackTrace();
			}
		}
		else if(accion.equals("buscar"))
		{				
			request.getSession().setAttribute("busqueda", "false");		
			response.sendRedirect("listadoPeluquerias.jsp");

		}
		else{System.out.println("Entro por get y no hubo coincidencia");}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//PARA REDIRECCIONAR 
		if(accion.equals("IrPeluquerias")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				List<Peluqueria> lista = Peluqueria.damePeluquerias();
				request.getSession().setAttribute("listaPeluquerias", lista);
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoPeluquerias.jsp");

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

				String tratamiento =(String)request.getParameter("tratamiento");
				String comentarios = (String)request.getParameter("comentarios");
				String id_animal =(String)request.getParameter("id_animal");
				String fecha =(String)request.getParameter("fecha");

				Peluqueria pelu = new Peluqueria();
				pelu.setAccion(tratamiento);
				pelu.setComentarios(comentarios);
				pelu.setFecha(fecha);
				
				Animal animal = new Animal();
				animal.setId_animal(Integer.parseInt(id_animal));
				
				try{
						animal.agregarPeluqueria(pelu);
						request.getSession().setAttribute("mensaje", "Registro correcto");
						response.sendRedirect("listadoAnimales.jsp");
				}
				catch (Exception e){
						e.printStackTrace();
						request.getSession().setAttribute("error", e.getMessage());
						request.getSession().setAttribute("tratamiento",(String)request.getParameter("tratamiento"));
						request.getSession().setAttribute("comentarios",(String)request.getParameter("comentarios"));
						request.getSession().setAttribute("id_animal",(String)request.getParameter("id_animal"));
						request.getSession().setAttribute("fecha",(String)request.getParameter("fecha"));
						request.getSession().setAttribute("id_propietario",(String)request.getParameter("id_propietario"));

						
						response.sendRedirect("nuevaPeluqueria.jsp");					
				}
			}
			else if(accion.equals("modificar"))
			{/*
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
				}*/

				
			}
			else if(accion.equals("buscar"))
			{	
				try 
				{
					String valor = request.getParameter("b");
					request.getSession().setAttribute("valor", valor);
					
					request.getSession().setAttribute("busqueda", "true");
					
					List<Peluqueria> lista = Peluqueria.damePeluquerias(valor); 
					request.getSession().setAttribute("listaBusqueda", lista);
					
					response.sendRedirect("listadoPeluquerias.jsp");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					request.getSession().setAttribute("mensaje", "No se pudo realizar la busqueda");
					response.sendRedirect("listadoPeluquerias.jsp");
				}

			}
			else if(accion.equals("ActualizarCombos"))
			{
					this.actualizarCombos(request);
					
					String tratamiento =(String)request.getParameter("tratamiento");
					String comentarios = (String)request.getParameter("comentarios");
					String id_propietario =(String)request.getParameter("id_propietario");
										
					request.getSession().setAttribute("tratamiento", tratamiento);
					request.getSession().setAttribute("comentarios", comentarios);
					request.getSession().setAttribute("id_propietario",id_propietario);
					
					response.sendRedirect("nuevaPeluqueria.jsp");
			}
			else{System.out.println("Entro por post y no hubo coincidencia");}
			
	}
	
	
	
	private void actualizarCombos(HttpServletRequest request)
	{
		try {
			/*Cargo la lista de propietarios*/
			List<Propietario> lista = Propietario.damePropietarios();
			request.getSession().setAttribute("listaPropietarios", lista);
			
			/*Busco el propietario correcto*/
			String id_propietario = (String)request.getParameter("id_propietario");
			if(id_propietario.equals("0"))
			{	
				id_propietario = Integer.toString(lista.get(0).getId_propietario());
			}
			
			Propietario pr = new Propietario();
			pr.setId_propietario(Integer.parseInt(id_propietario));
			List<Animal> listaA = pr.dameAnimales();		
			request.getSession().setAttribute("listaAnimales",listaA);
	
	
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("mensaje", e.getMessage());		
			}
	}
}
