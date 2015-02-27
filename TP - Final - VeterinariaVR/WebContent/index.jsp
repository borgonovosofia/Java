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
<link href="estilo.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	if ((navigator.appName).indexOf("Microsoft")!=-1)
	{	document.write('<link href="estiloPlantilla2.css" rel="stylesheet" type="text/css">'); }
	else 
	{	document.write('<link href="estiloPlantilla.css" rel="stylesheet" type="text/css">'); }
</script>
</head>
<script>

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
          
			  <div class="content" style="width: 95%; text-align: center; margin: 0 auto;;">
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
 			  	   		<div style="width: 100%; clear: both; text-align: center; height: 20ex;">
  			  	<img src="imagenes/perros.jpg" style="height: 100%;" />&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/alimento2.png" style="height: 100%;" />&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/alimento3.png" style="height: 100%;" />&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/alimento4.png" style="height: 100%;" />&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/alimento1.jpg" style="height: 100%;" /> 	&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/gato1.png" style="height: 100%;" /> 	
  		   </div>
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
 
    	   <div style="width: 100%; clear: both; text-align: center;height: 20ex; ">
    		    <img src="imagenes/Pipeta1.png" style="height: 80%" />&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/Pipeta2.jpg" style="height: 80%" />&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/Pipeta3.jpg" style="height: 80%" />&nbsp;&nbsp;&nbsp;&nbsp;
  			  	<img src="imagenes/Pipeta4.jpg" style="height: 80%" />		&nbsp;&nbsp;&nbsp;&nbsp;
    			<img src="imagenes/Pipeta5.jpg" style="height: 80%" />&nbsp;&nbsp;&nbsp;&nbsp;
		  </div>
		  <br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>
		  
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
