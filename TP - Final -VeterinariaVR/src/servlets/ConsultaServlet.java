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
		// #region IrConsultas
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
				response.sendRedirect("index.jsp");
			}

		}
		// #endregion 
		// #region GenerarAlertas
		else if(accion.equals("GenerarAlertas")){
			request.getSession().setAttribute("busqueda", "false");				
			try 
			{
				//Guarda lista  
				List<Aviso> lista = Consulta.dameAlertas();
				
				if(request.getSession().getAttribute("tipousuario").equals("V"))
				{
					request.getSession().setAttribute("listaAvisos", lista);
				}
				else
				{
					int idP = Integer.parseInt((String)request.getSession().getAttribute("idusr"));
					List<Aviso> lista2 = new ArrayList<Aviso>();
					for(int i = 0;i<lista.size();i++)
					{
						if(lista.get(i).getAnimal().getPropietario().getId_propietario()== idP)
						{
							lista2.add(lista.get(i));
						}
					}
					request.getSession().setAttribute("listaAvisos", lista2);
				}
				
								
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoAvisos.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("index.jsp");
			}

		}
		// #endregion
		// #region actualizar
		else if(accion.equals("actualizar"))
		{
			try 
			{						
				List<Aviso> lista = Consulta.dameAlertas();
				
				if(request.getSession().getAttribute("tipousuario").equals("V"))
				{
					request.getSession().setAttribute("listaAvisos", lista);
				}
				else
				{
					int idP = Integer.parseInt((String)request.getSession().getAttribute("idusr"));
					List<Aviso> lista2 = new ArrayList<Aviso>();
					for(int i = 0;i<lista.size();i++)
					{
						if(lista.get(i).getAnimal().getPropietario().getId_propietario()== idP)
						{
							lista2.add(lista.get(i));
						}
					}
					request.getSession().setAttribute("listaAvisos", lista2);
				}
						
				
				request.getSession().setAttribute("recarga", true);
				response.sendRedirect("listadoConsultas.jsp");

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("index.jsp");
			}
		}	
		// #endregion
		// #region borrar
		else if(accion.equals("borrar"))
		{
			request.getSession().setAttribute("busqueda", "false");			
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					if(Consulta.borrarConsulta(id))
					{
						request.getSession().setAttribute("mensaje", "Borrado correcto");
					}
					else
					{
						request.getSession().setAttribute("mensaje", "No se pudo borrar, intente mas tarde");
					}	
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
		//#endregion
		// #region ver
		else if(accion.equals("ver"))
		{
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					Consulta c = Consulta.buscarConsulta(id);
					
					request.getSession().setAttribute("propietario", c.getAnimal().getPropietario().getNombre()+", "+c.getAnimal().getPropietario().getApellido());
					request.getSession().setAttribute("animal",c.getAnimal().getNombre() );
					request.getSession().setAttribute("comentarios",c.getComentarios() );
					request.getSession().setAttribute("motivo", c.getMotivo());
					if(c.getIntervencion()!=null)
					{	request.getSession().setAttribute("intervencion",c.getIntervencion().getNombre() );}
					else{request.getSession().setAttribute("intervencion", "No se realizó intervencion");}
					
					request.getSession().setAttribute("fecha",c.getFecha());
					List<Vacunacion> listaV;
					if(c.getVacunaciones()!=null)
					{	listaV = c.getVacunaciones();}
					else {listaV = new ArrayList<Vacunacion>();}
					request.getSession().setAttribute("listaVacunaciones", listaV);
					
				} catch (ConException e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("error", e.getMessage());
				}
				finally{
					response.sendRedirect("verConsulta.jsp");
				}
		}
		// #endregion
		// #region editar
		else if(accion.equals("editar"))
		{
			try {
				String id = (String)request.getParameter("id");
				Consulta c = Consulta.buscarConsulta(Integer.parseInt(id));
				request.getSession().setAttribute("id_consulta", id);
				request.getSession().setAttribute("id_propietario", c.getAnimal().getPropietario().getNombre()+", "+c.getAnimal().getPropietario().getApellido());
				request.getSession().setAttribute("id_animal",c.getAnimal().getNombre() );
				request.getSession().setAttribute("comentarios",c.getComentarios() );
				request.getSession().setAttribute("motivo", c.getMotivo());
				if(c.getIntervencion()!=null)
				{	request.getSession().setAttribute("id_intervencion",Integer.toString(c.getIntervencion().getId_intervencion()) );}
				else{request.getSession().setAttribute("id_intervencion", "0");}
				
				List<Vacuna> listaVacunas = Vacuna.dameVacunas();
				request.getSession().setAttribute("listaVacunas",listaVacunas);	
				
				List<IntervencionQuirurgica> listaI = IntervencionQuirurgica.dameListaIntervenciones();
				request.getSession().setAttribute("listaIntervenciones", listaI);
				
				request.getSession().setAttribute("fecha",c.getFecha());
				List<Vacunacion> listaV;
				if(c.getVacunaciones()!=null)
				{	listaV = c.getVacunaciones();}
				else {listaV = new ArrayList<Vacunacion>();}
				request.getSession().setAttribute("listaVacunaciones", listaV);
				
			} catch (ConException e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
			}
			finally{
				response.sendRedirect("modificarConsulta.jsp");
			}		
		}
		// #endregion
		// #region nuevo
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
				response.sendRedirect("index.jsp");
				e.printStackTrace();
			}

		}
		//#endregion
		// #region buscar
		else if(accion.equals("buscar"))
		{				
			request.getSession().setAttribute("busqueda", "false");		
			response.sendRedirect("listadoConsultas.jsp");
		}
		// #endregion 
}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");

		// #region nuevo
		 if(accion.equals("nuevo")){
				request.getSession().setAttribute("busqueda", "false");	

				String id_propietario = (String)request.getParameter("id_propietario");				
				String id_animal = (String)request.getParameter("id_animal");				
				String fecha = (String)request.getParameter("fecha");
			   	String motivo = (String)request.getParameter("motivo");
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
					catch (Exception e){
						e.printStackTrace();
						request.getSession().setAttribute("error", e.getMessage());
						response.sendRedirect("nuevaConsulta.jsp");
					}					
				}
			}
		// #endregion
		// #region modificar
			else if(accion.equals("modificar"))
			{
					request.getSession().setAttribute("busqueda", "false");	
					
					String fecha = (String)request.getParameter("fecha");
				   	String motivo = (String)request.getParameter("motivo");
					String comentarios = (String)request.getParameter("comentarios");
					String id_intervencion = (String)request.getParameter("id_intervencion");				
					int id_consulta = Integer.parseInt((String)request.getParameter("id_consulta"));

					Consulta consulta = new Consulta();
					consulta.setId_consulta(id_consulta);
					consulta.setFecha(fecha);
					consulta.setComentarios(comentarios);
					consulta.setMotivo(motivo);

					
					IntervencionQuirurgica intervencion = new IntervencionQuirurgica();
					intervencion.setId_intervencion(Integer.parseInt(id_intervencion));
					
					consulta.setIntervencion(intervencion);
					
					List<Vacunacion> listaVacunaciones = (List<Vacunacion>)request.getSession().getAttribute("listaVacunaciones");
					
					consulta.setVacunaciones(listaVacunaciones);
					
					if(consulta!=null)
					{
						try{
							Consulta.modificarConsulta(consulta);
							request.getSession().setAttribute("recarga", false);
							request.getSession().setAttribute("mensaje", "Modificacion correcto");
							response.sendRedirect("listadoConsultas.jsp");
						}
						catch (Exception e){
							e.printStackTrace();
							request.getSession().setAttribute("error", e.getMessage());
							response.sendRedirect("listadoConsultas.jsp");
						}					
					}
				
			}
			// #endregion
		// #region buscar
			else if(accion.equals("buscar"))
			{	
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

			}
			// #endregion buscar
		// #region ActualizarCombos
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
			// #endregion
		// #region AgregarVacuna
		
			else if(accion.equals("AgregarVacuna"))
			{
				try {
					
					
					String id_propietario = (String)request.getParameter("id_propietario");	
					String id_animal = (String)request.getParameter("id_animal");						
					String fecha = (String)request.getParameter("fecha");
					String motivo = (String)request.getParameter("motivo");
					String comentarios = (String)request.getParameter("comentarios");
					String id_intervencion = (String)request.getParameter("id_intervencion");

					List<Propietario> lista = Propietario.damePropietarios();
					request.getSession().setAttribute("listaPropietarios", lista);
					
					
					if(id_propietario.equals("0"))
					{	id_propietario = Integer.toString(lista.get(0).getId_propietario());	}
					
					Propietario pr = new Propietario();
					pr.setId_propietario(Integer.parseInt(id_propietario));
					List<Animal> listaA = pr.dameAnimales();		
					request.getSession().setAttribute("listaAnimales",listaA);
					
					List<Vacuna> listaVac = Vacuna.dameVacunas();
					request.getSession().setAttribute("listaVacunas",listaVac);	
					
					List<IntervencionQuirurgica> listaI = IntervencionQuirurgica.dameListaIntervenciones();
					request.getSession().setAttribute("listaIntervenciones", listaI);
					
					
					request.getSession().setAttribute("id_animal", id_animal);
					request.getSession().setAttribute("id_propietario", id_propietario);
					request.getSession().setAttribute("fecha", fecha);
					request.getSession().setAttribute("motivo", motivo);
					request.getSession().setAttribute("comentarios", comentarios);
					request.getSession().setAttribute("id_intervencion", id_intervencion);
					
					String id_vacuna = (String)request.getParameter("id_vacuna");									
					List<Vacunacion> listaV = (List<Vacunacion>)request.getSession().getAttribute("listaVacunaciones");
					
					boolean rta = false;
					for(int i=0;i<listaV.size();i++)
					{
						if(listaV.get(i).getVacuna().getId_vacuna()==Integer.parseInt(id_vacuna)){rta=true;break;}
					}
					
					if(rta!=true)
					{
						Vacunacion vacunacion = new Vacunacion();
						Vacuna vac = Vacuna.buscarVacuna(Integer.parseInt(id_vacuna));
						int aviso = Integer.parseInt((String)request.getParameter("aviso"));
						
						if(aviso==0)
						{vacunacion.setDias_aviso(0);}
						else{ vacunacion.setDias_aviso(vac.getDuracion());}
						
						String comentarios_vacuna = (String)request.getParameter("comentarios_vacuna");
						vacunacion.setComentarios(comentarios_vacuna);
						vacunacion.setVacuna(vac);
						listaV.add(vacunacion);
					}
					else
					{
						request.getSession().setAttribute("mensaje", "No puede agregar dos veces la misma vacuna");
					}
					request.getSession().setAttribute("listaVacunaciones", listaV);
					
					response.sendRedirect("nuevaConsulta.jsp");
										
					} catch (Exception e) {
						e.printStackTrace();
						request.getSession().setAttribute("mensaje", "No se pudo agregar la vacuna, intente mas tarde");
						response.sendRedirect("nuevaConsulta.jsp");
					}

				
			}
			// #endregion
		// #region AgregarVacunaModificacion
			else if(accion.equals("AgregarVacunaModificacion"))
			{
				try {					
					
					String id_propietario = (String)request.getParameter("id_propietario");	
					String id_animal = (String)request.getParameter("id_animal");						
					String fecha = (String)request.getParameter("fecha");
					String motivo = (String)request.getParameter("motivo");
					String comentarios = (String)request.getParameter("comentarios");
					String id_intervencion = (String)request.getParameter("id_intervencion");									
					String id_consulta = (String)request.getParameter("id_consulta");
					request.getSession().setAttribute("id_consulta", id_consulta);
					request.getSession().setAttribute("id_animal", id_animal);
					request.getSession().setAttribute("id_propietario", id_propietario);
					request.getSession().setAttribute("fecha", fecha);
					request.getSession().setAttribute("motivo", motivo);
					request.getSession().setAttribute("comentarios", comentarios);
					request.getSession().setAttribute("id_intervencion", id_intervencion);
					
					String id_vacuna = (String)request.getParameter("id_vacuna");									
					List<Vacunacion> listaV = (List<Vacunacion>)request.getSession().getAttribute("listaVacunaciones");
					
					boolean rta = false;
					for(int i=0;i<listaV.size();i++)
					{
						if(listaV.get(i).getVacuna().getId_vacuna()==Integer.parseInt(id_vacuna)){rta=true;break;}
					}
					 
					if(rta!=true)
					{
						Vacunacion vacunacion = new Vacunacion();
						Vacuna vac = Vacuna.buscarVacuna(Integer.parseInt(id_vacuna));
						int aviso = Integer.parseInt((String)request.getParameter("aviso"));
						
						if(aviso==0)
						{vacunacion.setDias_aviso(0);}
						else{ vacunacion.setDias_aviso(vac.getDuracion());}
						
						String comentarios_vacuna = (String)request.getParameter("comentarios_vacuna");
						vacunacion.setComentarios(comentarios_vacuna);
						vacunacion.setVacuna(vac);
						listaV.add(vacunacion);
					}
					else
					{
						request.getSession().setAttribute("mensaje", "No puede agregar dos veces la misma vacuna");
					}
					request.getSession().setAttribute("listaVacunaciones", listaV);
					
					response.sendRedirect("modificarConsulta.jsp");
										
					} catch (Exception e) {
						e.printStackTrace();
						request.getSession().setAttribute("mensaje", "No se pudo agregar la vacuna, intente mas tarde");
						response.sendRedirect("modificarConsulta.jsp");
					}

				
			}
			//#endregion
		// #region quitarVacuna
		else if(accion.equals("quitarVacuna"))
		{
			this.quitarVacuna(request);			
			response.sendRedirect("nuevaConsulta.jsp");
					
		}
		//#endregion
		// #region quitarVacunaModificar
		else if(accion.equals("quitarVacunaModificar"))
		{
			String id_consulta = (String)request.getParameter("id_consulta");
			request.getSession().setAttribute("id_consulta", id_consulta);
			this.quitarVacuna(request);		
			response.sendRedirect("modificarConsulta.jsp");

					
		}
		// #endregion 
	}
	
	
	// #region ACTUALIZAR COMBOS
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
			
			List<Vacuna> listaV = Vacuna.dameVacunas();
			request.getSession().setAttribute("listaVacunas",listaV);	
			
			List<IntervencionQuirurgica> listaI = IntervencionQuirurgica.dameListaIntervenciones();
			request.getSession().setAttribute("listaIntervenciones", listaI);
				
	
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("mensaje", e.getMessage());	
			
			}

	}
	
	// #endregion
	
	
	// #region QUITAR VACUNA
	private void quitarVacuna(HttpServletRequest request)
	{
		int id = Integer.parseInt((String)request.getParameter("id"));
		List<Vacunacion> listaV = (List<Vacunacion>)request.getSession().getAttribute("listaVacunaciones");
		
		for(int i=0;i<listaV.size();i++)
		{
			if(listaV.get(i).getVacuna().getId_vacuna()==(id))
				{listaV.remove(i);
				break;}
		}
		
		request.getSession().setAttribute("listaVacunaciones", listaV);
		
						
		String id_propietario = (String)request.getParameter("id_propietario");	
		String id_animal = (String)request.getParameter("id_animal");						
		String fecha = (String)request.getParameter("fecha");
		String motivo = (String)request.getParameter("motivo");
		String comentarios = (String)request.getParameter("comentarios");
		String id_intervencion = (String)request.getParameter("id_intervencion");							
			
		request.getSession().setAttribute("id_animal", id_animal);
		request.getSession().setAttribute("id_propietario", id_propietario);
		request.getSession().setAttribute("fecha", fecha);
		request.getSession().setAttribute("motivo", motivo);
		request.getSession().setAttribute("comentarios", comentarios);
		request.getSession().setAttribute("id_intervencion", id_intervencion);
	}
	//#endregion
	
}
