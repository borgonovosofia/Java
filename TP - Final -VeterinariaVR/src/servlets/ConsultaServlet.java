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

@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("actualizar"))
		{
			try 
			{						
				List<Consulta> lista = Consulta.dameConsultas();
				request.getSession().setAttribute("listaConsultas", lista);		
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoConsultas.jsp");

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
					Consulta.borrarConsulta(id);
					request.getSession().setAttribute("mensaje", "Borrado correcto");
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("listadoConsultas.jsp");
				}
		}
		else if(accion.equals("editar"))
		{
			/*
				try {
					Consulta consulta = Consulta.buscarConsulta(Integer.parseInt((String)request.getParameter("id")));
					request.getSession().setAttribute("nombreP", consulta.getAnimal().getPropietario().getNombre());
					request.getSession().setAttribute("apellidoP", consulta.getAnimal().getPropietario().getApellido());
					request.getSession().setAttribute("nombre", consulta.getAnimal().getNombre());
					request.getSession().setAttribute("fecha", consulta.getFecha());
					request.getSession().setAttribute("motivo", consulta.getMotivo());
					request.getSession().setAttribute("comentarios", consulta.getComentarios());
					request.getSession().setAttribute("id", Integer.toString(consulta.getId_consulta()));

					this.actualizarCombos(request);					
										
					request.getSession().setAttribute("busqueda", "false");		
					response.sendRedirect("modificarConsulta.jsp");				
				} catch (NumberFormatException | ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());		
					response.sendRedirect("modificarConsulta.jsp");
				}			*/			
		}
		else if(accion.equals("nuevo"))
		{

			request.getSession().setAttribute("fecha", null);
			request.getSession().setAttribute("motivo", null);
			request.getSession().setAttribute("comentarios", null);
			request.getSession().setAttribute("id_intervencion", "0");
			
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
				
				List<Vacuna> listaV = Vacuna.dameVacunas();
				request.getSession().setAttribute("listaVacunas",listaV);	
				
				List<IntervencionQuirurgica> listaI = IntervencionQuirurgica.dameListaIntervenciones();
				request.getSession().setAttribute("listaIntervenciones", listaI);
				
				List<Vacunacion> listaVac = new ArrayList<Vacunacion>();
				request.getSession().setAttribute("listaVacunaciones", listaVac);

				
				response.sendRedirect("nuevaConsulta.jsp");
			
			} catch (Exception e) {
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("menu.jsp");
				e.printStackTrace();
			}

		}
		else if(accion.equals("buscar"))
		{				
			request.getSession().setAttribute("busqueda", "false");		
			response.sendRedirect("listadoConsultas.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//PARA REDIRECCIONAR 
		if(accion.equals("IrConsultas")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				//Guarda lista  
				List<Consulta> lista = Consulta.dameConsultas();
				request.getSession().setAttribute("listaConsultas", lista);
								
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoConsultas.jsp");

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

				String id_propietario = (String)request.getParameter("id_propietario");				
				String id_animal = (String)request.getParameter("id_animal");				
				String fecha = (String)request.getParameter("fecha");
			   	String motivo = (String)request.getParameter("movivo");
				String comentarios = (String)request.getParameter("comentarios");
				String id_intervencion = (String)request.getParameter("id_intervencion");				


				Propietario p = new Propietario();
				p.setId_propietario(Integer.parseInt(id_propietario));
				
				Animal animal = new Animal();
				animal.setId_animal(Integer.parseInt(id_animal));
				animal.setPropietario(p);
				
				Consulta consulta = new Consulta(fecha,comentarios,motivo,animal);
				
				IntervencionQuirurgica intervencion = new IntervencionQuirurgica();
				intervencion.setId_intervencion(Integer.parseInt(id_intervencion));
				
				consulta.setIntervencion(intervencion);
				
				List<Vacunacion> listaVacunaciones = (List<Vacunacion>)request.getSession().getAttribute("listaVacunaciones");
				
				consulta.setVacunaciones(listaVacunaciones);
				
				if(consulta!=null)
				{
					try{
						Consulta.agregarConsulta(consulta);
						request.getSession().setAttribute("mensaje", "Registro correcto");
						response.sendRedirect("listadoConsultas.jsp");
					}
					catch (ConException e){
						e.printStackTrace();
						request.getSession().setAttribute("error", e.getMessage());
						response.sendRedirect("nuevaConsulta.jsp");
					}					
				}
			}
			else if(accion.equals("modificar"))
			{/*
				try {
					request.getSession().setAttribute("busqueda", "false");	
									
					String fecha = (String)request.getParameter("fecha");
				   	String motivo = (String)request.getParameter("motivo");
					String comentarios = (String)request.getParameter("comentarios");
					int id = Integer.parseInt((String)request.getParameter("id"));
				
				
					
					Consulta pelu  = new Consulta();
					pelu.setFecha(fecha);
					pelu.setMotivo(motivo);
					pelu.setComentarios(comentarios);
					pelu.setId_consulta(id);
					
					
					boolean rta = Consulta.modificarConsulta(pelu);
					
					if(rta==true)
					{
						request.getSession().setAttribute("mensaje", "Modificacion correcta");
						response.sendRedirect("listadoConsultas.jsp");						
					}
					else
					{
						request.getSession().setAttribute("mensaje", "No se pudo modificar la consulta, intente mas tarde");
						response.sendRedirect("modificarConsulta.jsp");
					}		

				} catch (Exception e) {	
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect("modificarConsulta.jsp");
				}*/

				
			}
			else if(accion.equals("buscar"))
			{/*	
				try 
				{
					String valor = request.getParameter("b");
					request.getSession().setAttribute("valor", valor);
					
					request.getSession().setAttribute("busqueda", "true");
					
					List<Consulta> lista = Consulta.dameConsultas(valor); 
					request.getSession().setAttribute("listaBusqueda", lista);
					
					response.sendRedirect("listadoConsultas.jsp");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					request.getSession().setAttribute("mensaje", "No se pudo realizar la busqueda");
					response.sendRedirect("listadoConsultas.jsp");
				}
*/
			}
			else if(accion.equals("ActualizarCombos"))
			{
					
					String id_propietario = (String)request.getParameter("id_propietario");				
					String fecha = (String)request.getParameter("fecha");
				   	String motivo = (String)request.getParameter("motivo");
					String comentarios = (String)request.getParameter("comentarios");
					String id_intervencion = (String)request.getParameter("id_intervencion");

					
					request.getSession().setAttribute("id_propietario", id_propietario);
					request.getSession().setAttribute("fecha", fecha);
					request.getSession().setAttribute("motivo", motivo);
					request.getSession().setAttribute("comentarios", comentarios);
					request.getSession().setAttribute("id_intervencion", id_intervencion);
					
					this.actualizarCombos(request);

					
					response.sendRedirect("nuevaConsulta.jsp");
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
			
			List<Vacuna> listaV = Vacuna.dameVacunas();
			request.getSession().setAttribute("listaVacunas",listaV);	
			
			List<IntervencionQuirurgica> listaI = IntervencionQuirurgica.dameListaIntervenciones();
			request.getSession().setAttribute("listaIntervenciones", listaI);
			
			List<Vacunacion> listaVac = new ArrayList<Vacunacion>();
			request.getSession().setAttribute("listaVacunaciones", listaVac);

	
	
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
