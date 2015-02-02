<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
<%
//VERIFICA SI HAY UN MENSAJE DEL SERVLET DE TIPO DE ANIMAL
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
		function validarVacuna()
		{
			var codigo = document.getElementById("codigo").value;
			var msj = "";
			if (!(codigo!="" && codigo!= " "))
			{msj+="El codigo de la vacuna no puede estar vacio \n";	}
			
			var nombre = document.getElementById("nombre").value;
			if (!(nombre!="" && nombre!= " "))
			{msj+="El nombre de la vacuna no puede estar vacio \n";	}
			
			var dias = document.getElementById("duracion").value;
			if (dias!="" && dias!= " " )
			{
				if(isNaN(dias))
				{msj+="La duracion de la vacuna debe ser un numero \n";	}
			}
			if(msj=="")
			{ return true;}
			else { alert(msj);return false;}
		}
		</script>

		<body>
						<br></br>
						

						<!-- COMIENZO DIV PARA LOS TIPOS DE ANIMALES ---------------------------------------------------------------- -->
						<div style="float:left; width: 49%;"> 
							<form id="frmVacuna" name="frmVacuna" method="post" action="VacunaServlet">
  								<input type="hidden" value="modificar" name="accion"/>
  								<input type="hidden" value="<%= request.getSession().getAttribute("id") %>" name="id" id="id" />  		  		
  								<h2>Modificar vacuna</h2>
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td><label for="nombre">Codigo de la vacuna</label></td>
  		    							<td><input type="text" class="entrada" name="codigo" id="codigo" value="<%= request.getSession().getAttribute("codigo") %>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		    		  				<tr>
  		    							<td><label for="nombre">Nombre de la vacuna</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" value="<%= request.getSession().getAttribute("nombre") %>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		    		  				<tr>
  		    							<td><label for="nombre">Marca de la vacuna</label></td>
  		    							<td><input type="text" class="entrada" name="marca" id="marca" value="<%= request.getSession().getAttribute("marca") %>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		    		  				<tr>
  		    							<td><label for="nombre">Duracion de la vacuna</label></td>
  		    							<td><input type="text" class="entrada" name="duracion" id="duracion" value="<%=request.getSession().getAttribute("duracion")%>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Modificar" onclick="return validarVacuna()" />
  		    								<input type="button" value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV PARA TIPOS DE ANIMALES ------------------------------------------------------------------------- -->				

					
					
				</body>
			</html>				

