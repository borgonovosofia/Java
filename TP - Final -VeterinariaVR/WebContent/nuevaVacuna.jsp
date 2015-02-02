<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>

<%  
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
//VERIFICA SI HAY UN MENSAJE DEL SERVLET DE VACUNA
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
  								<input type="hidden" value="nuevo" name="accion"/>  		  		
  								<h2>Nueva vacuna</h2>
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td><label for="codigo">Codigo</label></td>
  		    							<td><input type="text" class="entrada" name="codigo" id="codigo" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  		  							<tr>
  		    							<td><label for="nombre">Marca</label></td>
  		    							<td><input type="text" class="entrada" name="marca" id="marca" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  		  							<tr>
  		    							<td><label for="nombre">Duración (en dias)</label></td>
  		    							<td><input type="text" class="entrada" name="duracion" id="duracion" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  		    		  				
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Agregar Vacuna" onclick="return validarNueva();" /></td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV PARA TIPOS DE ANIMALES ------------------------------------------------------------------------- -->						
				</body>
			</html>				

