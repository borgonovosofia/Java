<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
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

//trae los valores anteriores
	String nombre;
	String apellido;
	String direccion;
	String email;
	String telefono_fijo;
	String celular;
	String usuario;
	if(request.getSession().getAttribute("nombre")==null)
	{ nombre = "";} 
	else { nombre = (String)request.getSession().getAttribute("nombre");}

	if(request.getSession().getAttribute("apellido")==null)
	{  apellido = "";}
	else{ 	 apellido = (String)request.getSession().getAttribute("apellido");}
	
	if(request.getSession().getAttribute("direccion")==null)
	{  direccion = "";} 
	else{ direccion = (String)request.getSession().getAttribute("direccion");}

	if(request.getSession().getAttribute("email")==null)
	{  email = "";} 
	else{ email = (String)request.getSession().getAttribute("email");}
	
	if(request.getSession().getAttribute("telefono_fijo")==null)
	{  telefono_fijo = "";} 
	else{ telefono_fijo = (String)request.getSession().getAttribute("telefono_fijo");}
	
	if(request.getSession().getAttribute("celular")==null)
	{  celular = "";} 
	else{ celular = (String)request.getSession().getAttribute("celular");}
	
	if(request.getSession().getAttribute("usuario")==null)
	{  usuario = "";} 
	else{ usuario = (String)request.getSession().getAttribute("usuario");}
	
	
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
			function validarNuevo()
			{	var msj = "";
 				var usuario = document.getElementById("usuario").value;
 				var clave = document.getElementById("clave").value;
 				var clave2 = document.getElementById("clave2").value;
				
 				if (usuario=="" || usuario== " ")
 				{msj = msj + "El usuario no puede estar vacio\n";}
 				else if(usuario.length<6)
 				{msj += "El usuario debe contener como minimo 6 caractéres\n";}
 				else if(usuario.length>15)
 				{msj+="El usuario no puede contener mas de 15 caractéres\n";}
 				
 				if (!(clave!="" && clave!= " "))
 				{msj = msj + "La clave de acceso no puede estar vacia \n";}
 				else if(!(clave2!="" && clave2!=" "))
 				{msj =msj + "Debe repetir la clave de acceso\n";}
 				else if(clave!=clave2)
 				{msj =msj + "Las claves no coinciden\n";}
 				else if(clave.length<6)
 				{msj += "La clave debe contener como minimo 6 caractéres\n";}
 				else if(clave.length>15)
 				{msj+="La clave no puede contener mas de 15 caractéres\n";}

 				if(msj=="")
				{ return true;}
				else { 	alert(msj);
						return false;}
				
			}
		</script>

		<body>	
						<br></br>		
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 80%;"> 
							<form id="frmPropietario" name="frmPropietario" method="post" action="PropietarioServlet">
  								<input type="hidden" value="nuevo" name="accion"/>  		  		
  								<h2>Nuevo propietario</h2>
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" value="<%=nombre %>" /></td>
  		    							<td><label for="apellido">Apellido</label></td>
  		    							<td><input type="text" class="entrada" name="apellido" id="apellido" value="<%=apellido%>" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td></tr>  		    		  				
  		  							
		  							<tr>
  		    							<td><label for="direccion">Direccion</label></td>
  		    							<td><input type="text" class="entrada" name="direccion" id="direccion" value="<%= direccion %>" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
 
  		    		  				<tr>
  		    							<td><label for="email">Email</label></td>
  		    							<td><input type="text" class="entrada" name="email" id="email" value="<%=email%>" size="40"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		    		  					
  		    		  				<tr>
  		    							<td><label for="telefono_fijo">Telefono fijo</label></td>
  		    							<td><input type="text" class="entrada" name="telefono_fijo" id="telefono_fijo" value="<%=telefono_fijo%>"/></td>
  		    							<td><label for="celular">Celular</label></td>
  		    							<td><input type="text" class="entrada" name="celular" id="celular" value="<%=celular%>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		    		  				
  		    		  				<tr>
  		    							<td><label for="usuario">Usuario</label></td>
  		    							<td><input type="text" class="entrada" name="usuario" id="usuario" value="<%=usuario%>"/></td>	
  		    						</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>   		    		  				
  		    		  				<tr>
  		    							<td><label for="clave">Clave</label></td>
  		    							<td><input type="password" class="entrada" name="clave" id="clave" value=""/></td>
  		    							<td><label for="clave">Repetir clave</label></td>
  		    							<td><input type="password" class="entrada" name="clave2" id="clave2" value=""/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		    		  				  		    		  				
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Agregar Propietario" onclick="return validarNuevo();" />
											<input type="button" value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
									
				</body>
			</html>				

