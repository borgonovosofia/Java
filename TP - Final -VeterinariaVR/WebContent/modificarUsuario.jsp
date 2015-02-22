<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>

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
	String id = (String)request.getSession().getAttribute("id");
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
function validarPropietario()
{	
	var msj = "";
		var clave = document.getElementById("clave").value;
		var claveNueva = document.getElementById("claveNueva").value;				
		var claveNueva2 = document.getElementById("claveNueva2").value;
		var nombre = document.getElementById("nombre").value;


		if(nombre=="" || nombre==" ")
		{ msj = msj + "El nombre no puede estar vacio\n";}
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



<%	if(login!=false && id!=null && id.equals(idusr))
		{
		%>


						<!-- COMIENZO DIV PARA LOS TIPOS DE ANIMALES ---------------------------------------------------------------- -->
						<div style="float:left; width: 100%; text-align: center;"> 
							<form id="frmPropietario" name="frmPropietario" method="post" action="SesionServlet">
  								<input type="hidden" value="modificar" name="accion"/>
  								<input type="hidden" value="<%= request.getSession().getAttribute("id") %>" name="id" id="id" />  		  		
  								<h2>Modificar propietario</h2>
  								<table class="tablaMaqueta" style="margin:0 auto;">
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" value="<%=nombre %>" /></td>
  		    							<td><label for="apellido">&nbsp;&nbsp;&nbsp;Apellido</label></td>
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
  		    							<td><label for="celular">&nbsp;&nbsp;&nbsp;Celular</label></td>
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
  		    							<td><label for="claveNueva2">&nbsp;&nbsp;&nbsp;Repetir nueva clave</label></td>
  		    							<td><input type="password" class="entrada" name="claveNueva2" id="claveNueva2" value=""/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		  							<tr>
  		    							<td colspan="5" style="text-align: center"><input type="submit"  class="negro redondo boton"  name="button" id="button" value="Modificar" onclick="return validarPropietario()" />
  		    								<input type="button"  class="negro redondo boton"  value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV PARA TIPOS DE ANIMALES ------------------------------------------------------------------------- -->				
<br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>
					<%
		
		}
		else
		{ %> <script>location.href = "index.jsp";</script><%}%>			

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
