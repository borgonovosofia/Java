package servlets;

import java.io.IOException;

import negocio.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import data.LavarropasAdapter;
import utilidades.conException;

/**
 * Servlet implementation class TelevisorServlet
 */
@WebServlet("/jsp/LavarropasServlet")

public class LavarropasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LavarropasServlet() {
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
				LavarropasAdapter adapter = new LavarropasAdapter();
				adapter.borrarLavarropas(Integer.parseInt(id));
				} catch (conException e) {
					e.printStackTrace();
				}
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB");	
		}
		
		if(accion.equals("modificarL"))
		{
			request.getSession().setAttribute("id", request.getParameter("id"));
			request.getSession().setAttribute("color", request.getParameter("color"));
			request.getSession().setAttribute("consumo", request.getParameter("consumo"));
			request.getSession().setAttribute("carga", request.getParameter("carga"));
			request.getSession().setAttribute("precio", request.getParameter("precio"));
			request.getSession().setAttribute("peso", request.getParameter("peso"));
			
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB/jsp/modificarL.jsp");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.equals("alta"))
		{
			Lavarropas l = crearLavarropas(request);
			if(l!=null)
			{
				try {
					Lavarropas.agregarLavarropas(l);
				} catch (conException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB");	
		}
		if(accion.equals("modificar"))
		{
			Lavarropas l = crearLavarropas(request);
			if(l!=null)
			{
				try {
					String i = request.getParameter("id");
					int id = Integer.parseInt(i);
					Lavarropas.modificarLavarropas(l,id);
				} catch (conException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("/TP1_-_Electrodomesticos_-_WEB");	
		}
	}
	
	public Lavarropas crearLavarropas(HttpServletRequest request)
	{
		Lavarropas lav = null;
		String carga = request.getParameter("carga");
		String precio = request.getParameter("precio");
		String color = request.getParameter("color");
		String consumo = request.getParameter("consumo");
		String peso = request.getParameter("peso");

		try {
			if(!carga.trim().equals("") && !precio.trim().equals("") && 
			 !peso.trim().equals("") && !color.equals("Defecto") && 
			 !consumo.equals("Defecto"))
			{
				lav = new Lavarropas(Double.parseDouble(carga),Double.parseDouble(precio),color,consumo,Double.parseDouble(peso));
			}
			else
			{
				if(carga.trim().equals("") && precio.trim().equals("") && 
						peso.trim().equals("") && color.equals("Defecto") && 
						 consumo.equals("Defecto") )
				{
					lav = new Lavarropas();
				}
				else
				{
					if(!precio.trim().equals("") && !peso.trim().equals("") && carga.trim().equals("") && color.equals("Defecto") && consumo.equals("Defecto") )
					{
						lav = new Lavarropas(Double.parseDouble(precio),Double.parseDouble(peso));
					}
					else
					{
						lav = new Lavarropas();	
					}
				}
			}
		} catch (conException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (NumberFormatException w)
		{
			System.out.println("Error al insertar nuevo lavarropas");

		}
		return lav;
	}
	

}
