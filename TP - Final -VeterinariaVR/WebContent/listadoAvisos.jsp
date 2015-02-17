<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Consulta"%>
<%@page import="negocio.Animal"%>
<%@page import="negocio.Vacuna"%>
<%@page import="negocio.Aviso"%>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
	
	//VERIFICA SI HAY alguna busqueda realizada
		boolean busqueda = false;
		try{
			busqueda = Boolean.parseBoolean((String)request.getSession().getAttribute("busqueda"));
			}
		catch (Exception e3) {}
		
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

		List<Aviso> listaAvisos = (List<Aviso>) request.getSession().getAttribute("listaAvisos");	

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
	function redireccionar() 
	{
		location.href="index.jsp";
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
		  <% if(login==true)
    	  	{%>

						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 100%;">					
							<h1>Listado de avisos de vacunación de esta semana</h1>
							<div class="listado">
							<table  width="100%">
								<thead  >                                
	                               	<tr>
                                       	<th  colspan="1" width="11%">Animal</th>
                                       	<th  colspan="1" width="15%">Propietario</th>
                                       	<th  colspan="1" width="10%">Codigo</th>
                                       	<th  colspan="1" width="10%">Vacuna</th>
                                       	<th  colspan="1" width="10%">Marca</th>                                       	
                                       	<th  colspan="1" width="12%">Fec. vacunación</th>
                                       	<th  colspan="1" width="12%">Proxima fecha</th>
                                       	<th  colspan="1" width="25%">Contacto</th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaAvisos.size(); i++) 
  		  								{
  		  	   		 					 	Aviso t = listaAvisos.get(i);
  		  	   		 					 	String fecha1 = t.getFechaVacunacion().getDate() + "/" + (1+t.getFechaVacunacion().getMonth())+"/"+(1900+t.getFechaVacunacion().getYear());
  		  	   		 					 	String fecha2 = t.getFechaAviso().getDate() + "/" + (1+t.getFechaAviso().getMonth())+"/"+(1900+t.getFechaAviso().getYear());
  		  	   		 					%>
  		  	   		 						<tr <%if(i%2!=0){%>class="alt"<%} %>>
  		  	   		 							<td ><%=t.getAnimal().getNombre()%></td>  
  		  	   		 							<td ><%=t.getAnimal().getPropietario().getNombre()+", "+t.getAnimal().getPropietario().getApellido()%></td>
  		  	   		 							<td ><%=t.getVacuna().getCodigo()%></td>
  		  	   		 							<td ><%=t.getVacuna().getNombre()%></td>
  		  	   		 							<td ><%=t.getVacuna().getMarca()%></td>
  		  	   		 							<td ><%=fecha1%></td>
  		  	   		 							<td ><%=fecha2%></td>
  		  	   		 							<td >
  		  	   		 								<%
  		  	   		 								  if(t.hayContacto()[0].equals("") && t.hayContacto()[1].equals("") && t.hayContacto()[2].equals(""))
  		  	   		 								  {%>
  		  	   		 								  	<%="No hay email o telefonos registrados"%>
  		  	   		 									
  		  	   		 								<%}
  		  	   		 								  else{%><table width="100%"><td><%
  	  		  	   		 								  	if(!t.hayContacto()[1].equals("") )
	  	  		  	   		 								  	{%><%="<tr>Email: "+t.hayContacto()[1]+"</tr>"%><%}
  	  		  	   		 								  	if(!t.hayContacto()[2].equals("") )
			  	   		 								  		{%><%="<tr>Fijo: "+t.hayContacto()[2]+"</tr>"%><%}
  	  		  	   		 								  	if(!t.hayContacto()[0].equals("") )
	   		 								  					{%><%="<tr>Celular: "+t.hayContacto()[0]+"</tr>"%><%}  		  	   		 								    		  	   		 								 
  		  	   		 									  %></td></table>
  		  	   		 									  <%}%>
  		  	   		 							</td>
  		  	   		 								
  		  	   		 					<%	  	
  		  							}
  		  								if(listaAvisos.size()==0 )
  		  								{
  		  									%><tr><td  colspan="8">&nbsp;&nbsp;No hay avisos para esta semana</td></tr> <%	
  		  								}  		  								
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table>
							</div>
							
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------------ -->
					<div style="text-align: left; clear: both; margin-left: 10px;">
						<br></br><input type="button"  class="negro redondo boton"  value="Volver" name="volver" onclick="redireccionar()" />
					</div>
					<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
					
		<%
		}
		else
		{
		%>
					<form action="AnimalServlet" method="get" name="frmActualizar" id="frmActualizar">
						<input type="hidden" value="actualizar" name="accion" id="accion" />
					</form>
					<script>
						document.frmActualizar.submit();
					</script>
		<%		
		}
		%>
		

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
