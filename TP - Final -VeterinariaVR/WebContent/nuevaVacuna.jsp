<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
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
<html>
<head>
<meta charset="utf-8">
<title>Veterinaria VR</title>
<link href="estiloPlantilla.css" rel="stylesheet" type="text/css">
<link href="estilo.css" rel="stylesheet" type="text/css">
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
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="UsuarioServlet?accion=ModificarUsuario" >&nbsp;Mis datos&nbsp;</a>	                                 
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
		
						<br></br>		
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="width: 49%; margin: 0 auto;"> 
							<form id="frmVacuna" name="frmVacuna" method="post" action="VacunaServlet">
  								<input type="hidden" value="nuevo" name="accion"/>  		  		
  								
  								<table class="tablaMaqueta" style="margin: 0 auto;">
  									<caption><h2>Nueva vacuna</h2></caption>
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
  		    							<td><label for="nombre">Duración (en dias)&nbsp;&nbsp;</label></td>
  		    							<td><input type="text" class="entrada" name="duracion" id="duracion" 
  		    								value="<%=duracion%>"/></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  		    		  				
  		  							<tr>
  		    							<td colspan="2" style="text-align: center;"><input type="submit" name="button" id="button" value="Agregar Vacuna" onclick="return validarNueva();" />
										&nbsp;&nbsp;<input type="button" value="Volver" name="volver" onclick="history.back()" />
										
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>
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
