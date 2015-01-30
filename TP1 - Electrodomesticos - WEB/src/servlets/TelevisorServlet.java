package servlets;

import java.io.IOException;

import negocio.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import data.TelevisorAdapter;
import utilidades.conException;

/**
 * Servlet implementation class TelevisorServlet
 */
@WebServlet("/jsp/TelevisorServlet")

public class TelevisorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TelevisorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	public boolean securityRedirect(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return true;
	}
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("baja"))
		{
			String id = request.getParameter("id");
			try {
				TelevisorAdapter adapter = new TelevisorAdapter();
				adapter.borrarTelevision(Integer.parseInt(id));
				} catch (conException e) {
					e.printStackTrace();
				}
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB");	
		}
		
		if(accion.equals("modificarT"))
		{
			request.getSession().setAttribute("id", request.getParameter("id"));
			request.getSession().setAttribute("color", request.getParameter("color"));
			request.getSession().setAttribute("consumo", request.getParameter("consumo"));
			request.getSession().setAttribute("resolucion", request.getParameter("resolucion"));
			request.getSession().setAttribute("precio", request.getParameter("precio"));
			request.getSession().setAttribute("peso", request.getParameter("peso"));
			request.getSession().setAttribute("sintonizador", request.getParameter("sintonizador"));	
			
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB/jsp/modificarT.jsp");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("alta"))
		{
			Television tv = crearTelevision(request);
			if(tv!=null)
			{
				try {
					Television.agregarTelevision(tv);
				} catch (conException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB");	
		}
		if(accion.equals("modificar"))
		{
			Television tv = crearTelevision(request);
			if(tv!=null)
			{
				try {
					String i = request.getParameter("id");
					int id = Integer.parseInt(i);
					Television.modificarTelevision(tv,id);
				} catch (conException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB");	
		}
	}
	
	public Television crearTelevision(HttpServletRequest request)
	{
		Television tev = null;
		String resolucion = request.getParameter("resolucion");
		String precio = request.getParameter("precio");
		String color = request.getParameter("color");
		String consumo = request.getParameter("consumo");
		String s = request.getParameter("sintonizador");
		String peso = request.getParameter("peso");

		Boolean sintonizador = null;
		if(s.equals("si"))
		{ sintonizador = true;}
		else 
		{ sintonizador = false;} 
		
		try {
			if(!resolucion.trim().equals("") && !precio.trim().equals("") && 
			 !peso.trim().equals("") && !color.equals("Defecto") && 
			 !consumo.equals("Defecto"))
			{
				tev= new Television(	Integer.parseInt(resolucion),  sintonizador,
										Double.parseDouble(precio), color,
										consumo,Double.parseDouble(peso));
			}
			else
			{
				if(resolucion.trim().equals("") && precio.trim().equals("") && 
						peso.trim().equals("") && color.equals("Defecto") && 
						 consumo.equals("Defecto") )
				{
					tev = new Television();
					tev.setSintonizador(sintonizador);

				}
				else
				{
					if(!precio.trim().equals("") && !peso.trim().equals(""))
					{
						tev= new Television(Double.parseDouble(precio),Double.parseDouble(peso));
						tev.setSintonizador(sintonizador);

					}
					else
					{
						tev = new Television();	
						tev.setSintonizador(sintonizador);
					}
				}
			}
		} catch (conException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (NumberFormatException w)
		{
			System.out.println("Error al insertar nuevo televisor");

		}
		return tev;
	}
	

}
