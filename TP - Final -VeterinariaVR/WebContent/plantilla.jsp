<!doctype html>
<% 
	String tipousuario="";
	String usr="";
	String idusr="";
	boolean login = false;
	try{
		boolean login2 = (Boolean)request.getSession().getAttribute("login");
		login=login2;
		}
	catch (Exception e3) {login=false;}
	finally{
		request.getSession().setAttribute("login", false);
	}
	if(login==true)
	{
		tipousuario = (String)request.getSession().getAttribute("tipousuario");
		usr = (String)request.getSession().getAttribute("usr");
		idusr = (String)request.getSession().getAttribute("idusr");
	}	
%>	
<html>
<head>
<meta charset="utf-8">
<title>Veterinaria VR</title>
<link href="estiloPlantilla.css" rel="stylesheet" type="text/css">
<link href="estilo.css" rel="stylesheet" type="text/css">
</head>
<script>
	function cambiarAccion(accion,action)
	{
		document.getElementById("accion").setAttribute("value",accion);
		document.form1.setAttribute("action",action);
		document.form1.submit();	
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
          
          <!-- INICIO CONTENT !-->
			  <div class="content">
	          <% if(login==true)
          		{
          	  %>			  
					<div style="text-align: center;">
						<form name="form1" method="post" action="">
							<input type="hidden" name="accion" id="accion" value="" />
							<input type="button" class="boton negro redondo"  style="font-size: 1.2em;"
                        					value="Razas y tipos de animales" onclick="cambiarAccion('IrRaza','TipoAnimalServlet');"/>
	                                        
							<input type="button" name="vacuna" id="vacuna"   class="boton negro redondo" style="font-size: 1.2em;"
                        				value="Vacunas" onclick="cambiarAccion('IrVacuna','VacunaServlet');"/>
                                        	
							<input type="button" name="intervencion" id="intervencion"   class="boton negro redondo" style="font-size: 1.2em;"
	                        			value="Intervenciones Quirúrgicas" onclick="cambiarAccion('IrIntervenciones','IntervencionServlet');"/>
	                                    
							<input type="button" name="propietario" id="propietario"   class="boton negro redondo" style="font-size: 1.2em;"
                        				value="Propietarios" onclick="cambiarAccion('IrPropietario','PropietarioServlet');"/>
	                                    
							<input type="button" name="animal" id="animal"  class="boton negro redondo" style="font-size: 1.2em;"
                        	value="Animales" onclick="cambiarAccion('IrAnimales','AnimalServlet');"/>
	                        
							<input type="button" name="peluqueria" id="peluqueria"  class="boton negro redondo" style="font-size: 1.2em;"
                        				value="Peluquerias" onclick="cambiarAccion('IrPeluquerias','PeluqueriaServlet');"/>
	                                    
							<input type="button" name="consulta" id="consulta"  class="boton negro redondo"  style="font-size: 1.2em;"
                        				value="Consultas" onclick="cambiarAccion('IrConsultas','ConsultaServlet');"/>
	                                    
							<input type="button" name="consulta" id="consulta"  class="boton negro redondo" style="font-size: 1.2em;"
                        				value="Generar Alertas semana" onclick="cambiarAccion('GenerarAlertas','ConsultaServlet');"/>
						</form> 
					</div> 	
				<%}%>
				
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
		  <!-- TemplateBeginEditable name="cuerpo"--------------------------------------------------------------------------------------------------------------------- -->





		  <!-- TemplateEndEditable -------------------------------------------------------------------------------------------------------------------------------------- --> 				
   		  <!-- ------------------------------------------------------------------FINAL EDITABLE---------------------------------------------------------------------------------------- -->
   		  <!-- ------------------------------------------------------------------FINAL EDITABLE---------------------------------------------------------------------------------------- -->
   		  <!-- ------------------------------------------------------------------FINAL EDITABLE---------------------------------------------------------------------------------------- -->
   		</div>
          <!-- FINAL CONTENT !-->
          
          <!-- INICIO PIE !-->
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
