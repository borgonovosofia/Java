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

//trae los valores anteriores
	String nombre;
	String codigo;
	String marca;
	Integer duracion;
	if(request.getSession().getAttribute("nombre")==null)
	{ nombre = "";} 
	else {
	nombre = (String)request.getSession().getAttribute("nombre");}

	if(request.getSession().getAttribute("codigo")==null)
	{  codigo = "";}
	else{
	 codigo = (String)request.getSession().getAttribute("codigo");}
	
	if(request.getSession().getAttribute("marca")==null)
	{  marca = "";} 
	else{
		 marca = (String)request.getSession().getAttribute("marca");
	}
	if(request.getSession().getAttribute("duracion")==null)
	{  duracion = 0;} 
	else{
	 duracion = Integer.parseInt((String)request.getSession().getAttribute("duracion"));
	 
	}

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
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 49%;"> 
							<form id="frmVacuna" name="frmVacuna" method="post" action="VacunaServlet">
  								<input type="hidden" value="nuevo" name="accion"/>  		  		
  								<h2>Nueva vacuna</h2>
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td><label for="codigo">Codigo</label></td>
  		    							<td><input type="text" class="entrada" name="codigo" id="codigo" value="<%=codigo %>" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" value="<%=nombre%>" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  		  							<tr>
  		    							<td><label for="nombre">Marca</label></td>
  		    							<td><input type="text" class="entrada" name="marca" id="marca" value="<%= marca %>" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  		  							<tr>
  		    							<td><label for="nombre">Duración (en dias)</label></td>
  		    							<td><input type="text" class="entrada" name="duracion" id="duracion" 
  		    								value="<%=duracion%>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  		    		  				
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Agregar Vacuna" onclick="return validarNueva();" />
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