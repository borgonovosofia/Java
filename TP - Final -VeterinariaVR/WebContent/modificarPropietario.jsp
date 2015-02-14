<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
<%
try{
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



//trae los valores anteriores
	String nombre =(String)request.getSession().getAttribute("nombre");
	String apellido = (String)request.getSession().getAttribute("apellido");
	String direccion = (String)request.getSession().getAttribute("direccion");
	String email = (String)request.getSession().getAttribute("email");
	String telefono_fijo = (String)request.getSession().getAttribute("telefono_fijo");
	String celular = (String)request.getSession().getAttribute("celular");
	String usuario = (String)request.getSession().getAttribute("usuario");

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
		function validarPropietario()
		{	
			var msj = "";
				var clave = document.getElementById("clave").value;
				var claveNueva = document.getElementById("claveNueva").value;				
				var claveNueva2 = document.getElementById("claveNueva2").value;
			

			
				if (!(clave!="" && clave!= " "))
				{msj = msj + "Debe ingresar la clave para poder modificar sus datos \n";}
				else if((claveNueva!="" && claveNueva!=" ") && (claveNueva2=="" || claveNueva2==" "))
				{msj =msj + "Si desea cambiar la clave de acceso debe llenar los campos 'Nueva clave' y 'Repetir nueva clave'\n";}
				else if((claveNueva2!="" && claveNueva2!=" ") && (claveNueva=="" || claveNueva==" "))
				{msj =msj + "Si desea cambiar la clave de acceso debe llenar los campos 'Nueva clave' y 'Repetir nueva clave'\n";}
				else if(claveNueva!=claveNueva2)
				{msj =msj + "Las claves no coinciden\n";}
				else if(claveNueva.length<6 && claveNueva.length!=0)
				{msj += "La clave debe contener como minimo 6 caractéres\n";}

				if(msj=="")
			{ return true;}
			else { 	alert(msj);
					return false;}
			
		}
		</script>

		<body>
						<br></br>
						

						<!-- COMIENZO DIV PARA LOS TIPOS DE ANIMALES ---------------------------------------------------------------- -->
						<div style="float:left; width: 90%;"> 
							<form id="frmPropietario" name="frmPropietario" method="post" action="PropietarioServlet">
  								<input type="hidden" value="modificar" name="accion"/>
  								<input type="hidden" value="<%= request.getSession().getAttribute("id") %>" name="id" id="id" />  		  		
  								<h2>Modificar vacuna</h2>
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
  		    							<td><input type="text" class="entrada" name="usr" id="usr" value="<%=usuario%>" disabled="disabled" />
  		    								<input type="hidden" name="usuario" id="usuario" value="<%=usuario%>" />
  		    							</td>	
  		    						</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>   		    		  				
  		    						
  		    						<tr>
  		    							<td><label for="clave">Clave</label></td>
  		    							<td><input type="password" class="entrada" name="clave" id="clave" value=""/></td>
  		    						</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>   		    		  				
  		    		  				<tr>
  		    							<td><label for="claveNueva">Nueva Clave</label></td>
  		    							<td><input type="password" class="entrada" name="claveNueva" id="claveNueva" value=""/></td>
  		    							<td><label for="claveNueva2">Repetir nueva clave</label></td>
  		    							<td><input type="password" class="entrada" name="claveNueva2" id="claveNueva2" value=""/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Modificar" onclick="return validarPropietario()" />
  		    								<input type="button" value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV PARA TIPOS DE ANIMALES ------------------------------------------------------------------------- -->				

					
					
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