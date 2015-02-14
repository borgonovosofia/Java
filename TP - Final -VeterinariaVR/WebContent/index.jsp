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
			//request.getSession().setAttribute("recarga", true); -----------------------------------------------------------
			%><script>alert("<%=msj3%>");</script><%
		}
	}
	catch (Exception e3) {}

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
    <script type="text/javascript" language="javascript" src="sliderengine/jquery.js"></script>
    <script type="text/javascript" language="javascript" src="sliderengine/amazingslider.js"></script>
    <script type="text/javascript" language="javascript" src="sliderengine/initslider-1.js"></script>
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
							
							<%if(tipousuario.equals("V"))
							{ %>
							<input type="button" class="boton negro redondo"  style="font-size: 1em;"
                        					value="Razas y tipos de animales" onclick="cambiarAccion('IrRaza','TipoAnimalServlet');"/>	                                       
							<input type="button" name="vacuna" id="vacuna"   class="boton negro redondo" style="font-size: 1em;"
                        				value="Vacunas" onclick="cambiarAccion('IrVacuna','VacunaServlet');"/>                                    
							<input type="button" name="intervencion" id="intervencion"   class="boton negro redondo" style="font-size: 1em;"
	                        			value="Intervenciones Quirúrgicas" onclick="cambiarAccion('IrIntervenciones','IntervencionServlet');"/>	                                    
							<input type="button" name="propietario" id="propietario"   class="boton negro redondo" style="font-size: 1em;"
                        				value="Propietarios" onclick="cambiarAccion('IrPropietario','PropietarioServlet');"/>
							<%} %>
	                                    
							<input type="button" name="animal" id="animal"  class="boton negro redondo" style="font-size: 1em;"
                        	value="Animales" onclick="cambiarAccion('IrAnimales','AnimalServlet');"/>
	                        
							<input type="button" name="peluqueria" id="peluqueria"  class="boton negro redondo" style="font-size: 1em;"
                        				value="Peluquerias" onclick="cambiarAccion('IrPeluquerias','PeluqueriaServlet');"/>
	                                    
							<input type="button" name="consulta" id="consulta"  class="boton negro redondo"  style="font-size: 1em;"
                        				value="Consultas" onclick="cambiarAccion('IrConsultas','ConsultaServlet');"/>
	                                    
							<input type="button" name="consulta" id="consulta"  class="boton negro redondo" style="font-size: 1em;"
                        				value="Generar Alertas semana" onclick="cambiarAccion('GenerarAlertas','ConsultaServlet');"/>
                        				
							<input type="button" name="datos" id="datos"  class="boton negro redondo" style="font-size: 1em;"
                        				value="Modificar mis datos" onclick="cambiarAccion('ModificarUsuario','UsuarioServlet');"/>                        				
						</form> 
					</div> 	
				<%} %>
				
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
   		  <!-- -----------------------------------------------------------------PARTE EDITABLE----------------------------------------------------------------------------------------- -->
		  <!-- TemplateBeginEditable name="cuerpo"--------------------------------------------------------------------------------------------------------------------- -->
  			  <div id="amazingslider-1" style="display:block;position:relative;margin:16px auto 56px; ">
        			<ul class="amazingslider-slides" style="display:none;">
            			<li><img src="images/10537430_296061223898828_8035619743284949628_n.jpg" /></li>
            			<li><img src="images/10685673_341873065984310_1620343750935876290_n.jpg" /></li>
            			<li><img src="images/10552496_298129460358671_2521904490797215899_n.jpg" /></li>
            			<li><img src="images/10171627_301381976700086_284064906463145075_n.jpg" /></li>
            			<li><img src="images/10628828_327444510760499_7230595569878855867_o.jpg" /></li>
            			<li><img src="images/10865974_370546219783661_5647232034562019812_o.jpg" /></li>
            			<li><img src="images/1425279_324249531079997_3384110346838246750_o.jpg" /></li>
        			</ul>
        			<ul class="amazingslider-thumbnails" style="display:none;">
            			<li><img src="images/10537430_296061223898828_8035619743284949628_n-tn.jpg" /></li>
            			<li><img src="images/10685673_341873065984310_1620343750935876290_n-tn.jpg" /></li>
            			<li><img src="images/10552496_298129460358671_2521904490797215899_n-tn.jpg" /></li>
            			<li><img src="images/10171627_301381976700086_284064906463145075_n-tn.jpg" /></li>
            			<li><img src="images/10628828_327444510760499_7230595569878855867_o-tn.jpg" /></li>
            			<li><img src="images/10865974_370546219783661_5647232034562019812_o-tn.jpg" /></li>
            			<li><img src="images/1425279_324249531079997_3384110346838246750_o-tn.jpg" /></li>
        			</ul>
        			<div class="amazingslider-engine" style="display:none;"><a href="http://amazingslider.com" title="Responsive jQuery Slider">Responsive jQuery Slider</a></div>
    			</div>
			
		  
		  
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
</body>
</html>
