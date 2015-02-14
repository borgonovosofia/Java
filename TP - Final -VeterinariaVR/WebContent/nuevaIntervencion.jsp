<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>

<% 
try{
	//VERIFICA SI HAY UN MENSAJE DE ERROR PARA MOSTRAR
	try{
		String msj3 = (String)request.getSession().getAttribute("error");
		if(msj3!="" && msj3!=null)
		{
			request.getSession().setAttribute("error", null);
			request.getSession().setAttribute("recarga", true);
			%><script>alert("<%=msj3%>");</script><%
		}
	}
	catch (Exception e3) {}
//VERIFICA SI HAY UN MENSAJE DEL SERVLET 
	try{
	String msj = (String)request.getSession().getAttribute("mensaje");
	if(msj!="" && msj!=null)
	{
		%><script>alert("<%=msj%>");</script><%
		request.getSession().setAttribute("mensaje", null);		
	}
	}
	catch (Exception e3) {}
%>
	<%@page import="java.sql.*" %>
	<!doctype html>
		<html>
		<head>
			<meta charset="utf-8">
			<link href="estilo.css" rel="stylesheet" type="text/css" />
			<title>Veterinaria VR</title>
		</head>
				
		<script>
			function validarNueva()
			{
				var msj = "";
				
				var nombre = document.getElementById("nombre").value;
				if (!(nombre!="" && nombre!= " "))
				{msj+="El nombre de la intervencion no puede estar vacio \n";	}
						
				if(msj=="")
				{ return true;}
				else { alert(msj);return false;}
			}
		</script>

		<body>	
						<br></br>		
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 49%;"> 
							<form id="frmIntervencion" name="frmIntervencion" method="post" action="IntervencionServlet">
  								<input type="hidden" value="nuevo" name="accion"/>  		  		
  								<h2>Nueva intervención quirúrgica</h2>
  								<table class="tablaMaqueta">  		  							
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" size="45" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>		    		  				
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Agregar Intervencion" onclick="return validarNueva();" />
  		    								<input type="button" value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->	
				
				</body>
			</html>				
		<%
}
catch (Exception e3) {
	e3.printStackTrace();%>
	<script>
		alert("Sucedio un imprevisto al cargar la página. Por favor intente mas tarde");
		location.href="listadoConsultas.jsp";
	</script>
<% }%>
