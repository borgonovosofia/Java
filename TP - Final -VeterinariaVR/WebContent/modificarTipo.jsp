<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>

	<%@page import="java.sql.*" %>
	<!doctype html>
		<html>
		<head>
			<meta charset="utf-8">
			<link href="estilo.css" rel="stylesheet" type="text/css" />
			<title>Documento sin título</title>
		</head>
				
		<script>
			function validarTipo()
			{
				var nombre = document.getElementById("nombreTipo").value;
				if(nombre!="" && nombre!= " ")
				{return true; }
				else { alert("Complete el nuevo nombre del Animal");return false;}
			}
		</script>

		<body>
						<br></br>
						

						<!-- COMIENZO DIV PARA LOS TIPOS DE ANIMALES ---------------------------------------------------------------- -->
						<div style="float:left; width: 49%;"> 
							<form id="frmTipoAnimal" name="frmTipoAnimal" method="post" action="TipoAnimalServlet">
  								<input type="hidden" value="modificar" name="accion"/>
  								<input type="hidden" value="<%= request.getSession().getAttribute("idTipo") %>" name="idTipo" id="idTipo" />  		  		
  								<h2>Modificar nombre de animal</h2>
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td><label for="nombre">Nuevo nombre del tipo de animal</label></td>
  		    							<td><input type="text" class="entrada" name="nombreTipo" id="nombreTipo" value="<%= request.getSession().getAttribute("nombreTipo") %>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Modificar" onclick="return validarTipo()" /></td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV PARA TIPOS DE ANIMALES ------------------------------------------------------------------------- -->				

					
				</body>
			</html>				

