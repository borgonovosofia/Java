<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
<% 
try{
//VERIFICA SI HAY UN MENSAJE DEL SERVLET DE TIPO DE ANIMAL
	try{
	String msj = (String)request.getSession().getAttribute("mensajeRaza");
	if(msj!="" && msj!=null)
	{
		%><script>alert("<%=msj%>");</script><%
		request.getSession().setAttribute("mensajeRaza", null);		
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
			function validarRaza()
			{
				var nombre = document.getElementById("nombreNuevo").value;
				if(nombre!="" && nombre!= " ")
				{return true; }
				else { alert("Complete el nuevo nombre de la raza");return false;}
			}
		</script>

		<body>
						<br></br>
						

						<!-- COMIENZO DIV PARA Las RAZAS ---------------------------------------------------------------- -->
						<div style="float:left; width: 49%;"> 
							<form id="frmrAZA" name="frmrAZA" method="post" action="RazaServlet">
  								<input type="hidden" value="modificar" name="accion"/>
  								<input type="hidden" value="<%= request.getSession().getAttribute("idRaza") %>" name="idRaza" id="idRaza" />  		  		
  								<h2>Modificar nombre de la raza</h2>
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td><label for="nombre">Nuevo nombre de la raza</label></td>
  		    							<td><input type="text" class="entrada" name="nombreNuevo" id="nombreNuevo" value="<%= request.getSession().getAttribute("nombreRaza") %>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Modificar" onclick="return validarRaza();" />
  		    								<input type="button" value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV PARA LAS RAZAS ------------------------------------------------------------------------- -->				

					
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