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

@WebServlet("/SesionServlet")
public class SesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SesionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//#region IrLogin
		if(accion.equals("IrLogin"))
		{
			request.getSession().setAttribute("login", false);
			request.getSession().setAttribute("usr", "");
			request.getSession().setAttribute("idusr", "");
			request.getSession().setAttribute("tipousuario", "");
			response.sendRedirect("login.jsp");
		}
		//#endregion
		//#region CerrarSesión
		if(accion.equals("CerrarSesion"))
		{
			request.getSession().setAttribute("login", false);
			request.getSession().setAttribute("usr", "");
			request.getSession().setAttribute("idusr", "");
			request.getSession().setAttribute("tipousuario", "");
			response.sendRedirect("index.jsp");
		}
		//#endregion
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//#region IniciarSesion
		if(accion.equals("IniciarSesion"))
		{
			try {
				Propietario pr = Propietario.login(request.getParameter("usuario"),request.getParameter("clave"));
				if(pr.getId_propietario()==0)
				{
					request.getSession().setAttribute("mensaje", "El usuario y/o la clave son incorrectos");
					request.getSession().setAttribute("usuario",request.getParameter("usuario"));
					request.getSession().setAttribute("clave", request.getParameter("clave"));
					response.sendRedirect("login.jsp");

				}
				else
				{
					request.getSession().setAttribute("login", true);
					request.getSession().setAttribute("usr", pr.getUsuario());
					request.getSession().setAttribute("idusr", Integer.toString(pr.getId_propietario()));
					request.getSession().setAttribute("tipousuario", pr.getTipo());
					response.sendRedirect("index.jsp");
				}	
				
			} catch (ConException e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", e.getMessage());
				response.sendRedirect("index.jsp");
			}
			
		}
		//#endregion	
	}
}
