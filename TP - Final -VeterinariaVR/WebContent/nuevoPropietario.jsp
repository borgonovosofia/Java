<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<!doctype html>
<% 
try{
	String tipousuario="";
	String usr="";
	String idusr="";
	boolean login = false;
	try{
		boolean login2 = (Boolean)request.getSession().getAttribute("login");
		login=login2;
		}
	catch (Exception e3) {login=false;}
	if(login==true)
	{
		tipousuario = (String)request.getSession().getAttribute("tipousuario");
		usr = (String)request.getSession().getAttribute("usr");
		idusr = (String)request.getSession().getAttribute("idusr");
	}
	
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
<html>
<head>
<meta charset="utf-8">
<title>Veterinaria VR</title>
<link href="estilo.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	if ((navigator.appName).indexOf("Microsoft")!=-1)
	{	document.write('<link href="estiloPlantilla2.css" rel="stylesheet" type="text/css">'); }
	else 
	{	document.write('<link href="estiloPlantilla.css" rel="stylesheet" type="text/css">'); }
</script>
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
	<div class="container">
    
    	  <!-- INICIO ENCABEZADO !-->
    	  <% if(login==true)
    	  	{%>
    	  		<div style="width: 100; text-align: right; margin-top:0.5em; margin-right: 2em;">
    	  			<p>Usuario: <%=usr %>
    	  			<a href="SesionServlet?accion=CerrarSesion">(Cerrar Sesión)</a>
    	  			</p>
    	  		</div>				    	  	
    	  	<%}
    	  	else
			{%>
				<div style="width: 100; text-align: right; margin-top:0.5em; margin-right: 2em;"><a href="SesionServlet?accion=IrLogin">Iniciar Sesión</a></div>				
			<%
			}
			 %>    	  
		  <div class="header">
          	<a href="index.jsp">
           	<img src="imagenes/logo.png" alt="Veterinaria VR" name="logo" height="100%" id="Insert_logo" />
            </a> 
		  </div>
          <!-- FINAL ENCABEZADO!-->
          <!-- INICIO BARRA IZQUIERDA !-->          
           <div class="sidebar1">
           <!-- end .sidebar1 -->
  			</div>
          <!-- FINAL BARRA IZQUIERDA!-->
          <!-- INICIO CONTENT !-->
          
			  <div class="content">
	          <% if(login==true)
          		{
          	  %>			  
					<div style="text-align: center;">
							
	                        <a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="ConsultaServlet?accion=GenerarAlertas" >&nbsp;Alertas semana&nbsp;</a>
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="SesionServlet?accion=ModificarUsuario" >&nbsp;Mis datos&nbsp;</a>	                                 
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="AnimalServlet?accion=IrAnimales" >&nbsp;Animales&nbsp;</a>
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="PeluqueriaServlet?accion=IrPeluquerias" >&nbsp;Peluquerias&nbsp;</a>
							
							<%if(tipousuario.equals("V"))
							{ %>
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="TipoAnimalServlet?accion=IrRaza" >&nbsp;Razas y animales&nbsp;</a>							
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="VacunaServlet?accion=IrVacuna" >&nbsp;Vacunas&nbsp;</a>							
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="IntervencionServlet?accion=IrIntervenciones" >&nbsp;Intervenciones Quirurgicas&nbsp;</a>							
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="PropietarioServlet?accion=IrPropietario" >&nbsp;Propietarios&nbsp;</a>
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="ConsultaServlet?accion=IrConsultas" >&nbsp;Consultas&nbsp;</a>
							
							                                                                                                         
							<%} %>           	                                    	                        	                                    
						<br></br>
						
					</div> 	
				<%} %>
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
		  <!-- TemplateBeginEditable name="cuerpo"--------------------------------------------------------------------------------------------------------------------- -->

		  <% if(login==true && tipousuario.equals("V")){ %>
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div> 
							<form id="frmPropietario" name="frmPropietario" method="post" action="PropietarioServlet">
  								<input type="hidden" value="nuevo" name="accion"/>  		  		
  								<div style="text-align: center;"><h2>Nuevo propietario</h2></div>
  								<table class="tablaMaqueta" style="margin: 0 auto; ">
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" value="<%=nombre %>" /></td>
  		    							<td><label for="apellido">&nbsp;&nbsp;Apellido</label></td>
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
  		    							<td><label for="telefono_fijo">Telefono fijo&nbsp;&nbsp;</label></td>
  		    							<td><input type="text" class="entrada" name="telefono_fijo" id="telefono_fijo" value="<%=telefono_fijo%>"/></td>
  		    							<td><label for="celular">&nbsp;&nbsp;Celular</label></td>
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
  		    							<td><label for="clave">&nbsp;&nbsp;Repetir clave</label></td>
  		    							<td><input type="password" class="entrada" name="clave2" id="clave2" value=""/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		    		  				  		    		  				
  		  							<tr style="text-align: center;">
  		    							<td colspan="4"><input type="submit"  class="negro redondo boton"  name="button" id="button" value="Agregar Propietario" onclick="return validarNuevo();" />
											<input type="button"  class="negro redondo boton"  value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<br></br><br></br><br></br>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
		<%}	else{ %> <script>location.href = "index.jsp";</script><%} %>
									



		  <!-- TemplateEndEditable -------------------------------------------------------------------------------------------------------------------------------------- --> 				
   		  <!-- ------------------------------------------------------------------FINAL EDITABLE---------------------------------------------------------------------------------------- -->
   		  <!-- ------------------------------------------------------------------FINAL EDITABLE---------------------------------------------------------------------------------------- -->
   		  <!-- ------------------------------------------------------------------FINAL EDITABLE---------------------------------------------------------------------------------------- -->
   		</div>
          <!-- FINAL CONTENT !-->
          <!-- INICIO BARRA DERECHA!-->
 		<div class="sidebar2">
    	</div>          
          <!-- FINAL BARRA DERECHA !-->          <!-- INICIO PIE !-->
		<div class="footer">
          	<div style="padding-left:3em; text-align:center; width:100%;">
                <p><b>Direccion:</b> Rivadavia 773</p>
			    <p><b>Tel. Fijo:</b> (336)4423408</p>	
	            <p><b>Celular:</b> (336)154185286 </p>	
   		  	</div>
            <div style="float:right; text-align=right;  width:100%; ">
		   		<p style="font-size:0.8em; text-align:right;margin-right:1em;">Desarrollo: Borgonovo Sofia </p>	
   		  	</div>
		</div>
	</div>
</body>
</html>
<%
}
catch (Exception e3) {
	e3.printStackTrace();%>
	<script>
		alert("Sucedio un imprevisto al cargar la página. Por favor intente mas tarde");
		location.href="index.jsp";
	</script>
<% }%>
