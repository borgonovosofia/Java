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
import utilidades.Validaciones;
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
		//PARA REDIRECCIONAR 
		if(accion.equals("IrPeluquerias")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				
				//Guarda lista  veterinario
				if(request.getSession().getAttribute("tipousuario").equals("V"))
				{
					List<Peluqueria> lista = Peluqueria.damePeluquerias();
					request.getSession().setAttribute("listaPeluquerias", lista);
									
				}
				else
				{
					//Guardar lista usuario
					Propietario pr = new Propietario();
					pr.setId_propietario(Integer.parseInt((String)request.getSession().getAttribute("idusr")));
					List<Animal> listaA = pr.dameAnimales();
					List<Peluqueria> listaP = new ArrayList<Peluqueria>();
					for(int i=0;i<listaA.size();i++)
					{
						listaP.addAll(listaA.get(i).damePeluquerias());
					}
						
					request.getSession().setAttribute("listaPeluquerias", listaP);
				}
				//Guarda lista  
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoPeluquerias.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("index.jsp");
			}

		}	
		else if(accion.equals("actualizar"))
		{
			try 
			{						
				if(request.getSession().getAttribute("tipousuario").equals("V"))
				{
					List<Peluqueria> lista = Peluqueria.damePeluquerias();
					request.getSession().setAttribute("listaPeluquerias", lista);
									
				}
				else
				{
					//Guardar lista usuario
					Propietario pr = new Propietario();
					pr.setId_propietario(Integer.parseInt((String)request.getSession().getAttribute("idusr")));
					List<Animal> listaA = pr.dameAnimales();
					List<Peluqueria> listaP = new ArrayList<Peluqueria>();
					for(int i=0;i<listaA.size();i++)
					{
						listaP.addAll(listaA.get(i).damePeluquerias());
					}
						
					request.getSession().setAttribute("listaPeluquerias", listaP);
				}	
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoPeluquerias.jsp");

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
					Peluqueria.borrarPeluqueria(id);
					request.getSession().setAttribute("mensaje", "Borrado correcto");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("listadoPeluquerias.jsp");
				}
		}
		else if(accion.equals("editar"))
		{
			
				try {
					Peluqueria peluqueria = Peluqueria.buscarPeluqueria(Integer.parseInt((String)request.getParameter("id")));
					request.getSession().setAttribute("nombreP", peluqueria.getAnimal().getPropietario().getNombre());
					request.getSession().setAttribute("apellidoP", peluqueria.getAnimal().getPropietario().getApellido());
					request.getSession().setAttribute("nombre", peluqueria.getAnimal().getNombre());
					request.getSession().setAttribute("fecha", peluqueria.getFecha());
					request.getSession().setAttribute("tratamiento", peluqueria.getAccion());
					request.getSession().setAttribute("comentarios", peluqueria.getComentarios());
					request.getSession().setAttribute("id", Integer.toString(peluqueria.getId_peluqueria()));

					this.actualizarCombos(request);					
										
					request.getSession().setAttribute("busqueda", "false");		
					response.sendRedirect("modificarPeluqueria.jsp");				
				} catch (NumberFormatException | ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());		
					response.sendRedirect("modificarPeluqueria.jsp");
				}							
		}
		else if(accion.equals("nuevo"))
		{

			request.getSession().setAttribute("fecha", null);
			request.getSession().setAttribute("tratamiento", null);
			request.getSession().setAttribute("comentarios", null);
			
			try{
			
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
				response.sendRedirect("index.jsp");
				e.printStackTrace();
			}

		}
		else if(accion.equals("buscar"))
		{				
			request.getSession().setAttribute("busqueda", "false");		
			response.sendRedirect("listadoPeluquerias.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//PARA AGREGAR 
		if(accion.equals("nuevo")){
				request.getSession().setAttribute("busqueda", "false");	

				String id_propietario = (String)request.getParameter("id_propietario");				
				String id_animal = (String)request.getParameter("id_animal");				
				String fecha = (String)request.getParameter("fecha");
			   	String tratamiento = (String)request.getParameter("tratamiento");
				String comentarios = Validaciones.validarCodigoDanino((String)request.getParameter("comentarios"));
			
				Propietario p = new Propietario();
				p.setId_propietario(Integer.parseInt(id_propietario));
				
				Animal animal = new Animal();
				animal.setId_animal(Integer.parseInt(id_animal));
				animal.setPropietario(p);
				
				Peluqueria pelu = new Peluqueria(fecha,tratamiento,comentarios,animal);
				
				if(pelu!=null)
				{
					try{
						animal.agregarPeluqueria(pelu);
						request.getSession().setAttribute("mensaje", "Registro correcto");
						response.sendRedirect("listadoPeluquerias.jsp");
					}
					catch (ConException e){
						e.printStackTrace();
						request.getSession().setAttribute("error", e.getMessage());
						response.sendRedirect("nuevaPeluqueria.jsp");
					}					
				}
			}
			else if(accion.equals("modificar"))
			{
				try {
					request.getSession().setAttribute("busqueda", "false");	
									
					String fecha = (String)request.getParameter("fecha");
				   	String tratamiento = (String)request.getParameter("tratamiento");
					String comentarios = Validaciones.validarCodigoDanino((String)request.getParameter("comentarios"));
					int id = Integer.parseInt((String)request.getParameter("id"));		
				
					
					Peluqueria pelu  = new Peluqueria();
					pelu.setFecha(fecha);
					pelu.setAccion(tratamiento);
					pelu.setComentarios(comentarios);
					pelu.setId_peluqueria(id);
					
					
					boolean rta = Peluqueria.modificarPeluqueria(pelu);
					
					if(rta==true)
					{
						request.getSession().setAttribute("mensaje", "Modificacion correcta");
						response.sendRedirect("listadoPeluquerias.jsp");						
					}
					else
					{
						request.getSession().setAttribute("mensaje", "No se pudo modificar la peluqueria, intente mas tarde");
						response.sendRedirect("modificarPeluqueria.jsp");
					}		

				} catch (Exception e) {	
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("modificarPeluqueria.jsp");
				}

				
			}
			else if(accion.equals("buscar"))
			{	
				try 
				{
					String valor = Validaciones.validarCodigoDanino(request.getParameter("b"));
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
					
					String id_propietario = (String)request.getParameter("id_propietario");				
					String fecha = (String)request.getParameter("fecha");
				   	String tratamiento = (String)request.getParameter("tratamiento");
					String comentarios = Validaciones.validarCodigoDanino((String)request.getParameter("comentarios"));
				
					
					request.getSession().setAttribute("id_propietario", id_propietario);
					request.getSession().setAttribute("fecha", fecha);
					request.getSession().setAttribute("tratamiento", tratamiento);
					request.getSession().setAttribute("comentarios", comentarios);
					
					this.actualizarCombos(request);

					
					response.sendRedirect("nuevaPeluqueria.jsp");
			}
			
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
			System.out.println(id_propietario);
			
			Propietario pr = new Propietario();
			pr.setId_propietario(Integer.parseInt(id_propietario));
			List<Animal> listaA = pr.dameAnimales();		
			request.getSession().setAttribute("listaAnimales",listaA);
	
	
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("mensaje", e.getMessage());	
			System.out.println("errrrror");
			
			}

		/*
		try {
			List<Propietario> lista = Propietario.damePropietarios();
			request.getSession().setAttribute("listaPropietarios", lista);
			String id_propietario = null;
			if(request.getParameter("id_propietario")=="0")
			{ id_propietario = Integer.toString(lista.get(0).getId_propietario());}
			else{  id_propietario = (String)request.getParameter("id_propietario");}
			
			Propietario pr = new Propietario();
			pr.setId_propietario(Integer.parseInt(id_propietario));
		
			List<Animal> listaA = pr.dameAnimales();
								
			request.getSession().setAttribute("listaAnimales",listaA);


			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("mensaje", e.getMessage());		
			}*/
	}
}
