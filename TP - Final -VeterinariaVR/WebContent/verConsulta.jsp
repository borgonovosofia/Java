<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Animal"%>
<%@page import="negocio.Vacuna"%>
<%@page import="negocio.IntervencionQuirurgica"%>
<%@page import="negocio.Vacunacion"%>
<%@page import="javax.websocket.Session"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>

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
	
		List<Vacunacion> listaVacunaciones = (List<Vacunacion>) request.getSession().getAttribute("listaVacunaciones");
	
		//trae los valores anteriores
		String propietario;
		String animal;
		String fecha;
		String comentarios;
		String motivo;
		String intervencion;
	
		
		if(request.getSession().getAttribute("motivo")==null)
		{ motivo = "";}else { motivo = (String)request.getSession().getAttribute("motivo");}
		
		if(request.getSession().getAttribute("comentarios")==null)
		{ comentarios = "";}  else {comentarios = (String)request.getSession().getAttribute("comentarios");}
		
		if(request.getSession().getAttribute("animal")==null)
		{ animal = "0";} else {animal = (String)request.getSession().getAttribute("animal");}
	
		if(request.getSession().getAttribute("fecha")==null)
			{  fecha = "";} else{fecha = (String)request.getSession().getAttribute("fecha");}
		
		if(request.getSession().getAttribute("propietario")==null)
		{ propietario = "0";} else{ propietario = (String)request.getSession().getAttribute("propietario");}
	
		if(request.getSession().getAttribute("intervencion")==null)
		{ intervencion = "0";} else{ intervencion = (String)request.getSession().getAttribute("intervencion");}
		
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
<%

		if(tipousuario.equals("V"))
		{
		%>

						<br></br>		
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 100%;"> 							  		
							  <h2>Ver consulta</h2>
  								<table class="tablaMaqueta" style="width:95%">
  		    		  				<tr><td></td><td></td> <td></td><td></td><td></td></tr>
  		    		  				<tr>
  		    							<td><label for="propietario">Propietario</label></td>
  		    							<td><input name="propietario" id="propietario" value="<%= propietario %>" contenteditable="false" /></td>
  		    							<td> </td>
  		    							<td><label for="animal">Animal</label></td>
	    							  	<td><input name="animal" id="animal" value="<%= animal %>" contenteditable="false"/></td>
  		  							</tr>
  		    		  				<tr>
  		    		  				  <td>&nbsp;</td>
  		    		  				  <td>&nbsp;</td>
  		    		  				  <td></td>
  		    		  				  <td>&nbsp;</td>
  		    		  				  <td>&nbsp;</td>
	    		  				  </tr>
  		  							<tr><td>  </td><td>  </td> <td> </td> <td> </td><td> </td></tr>
  		  							<tr>
  		  								<td><label for="fecha">Fecha</label></td>
	  								  	<td><input type="text" class="entrada" name="fecha" id="fecha" value="<%=fecha %>" contenteditable="false"/></td>
	  								  	<td> </td><td> </td><td> </td>
  		  							</tr>
  		  							<tr>
  		  							  <td>&nbsp;</td>
  		  							  <td>&nbsp;</td>
  		  							  <td></td>
  		  							  <td></td>
  		  							  <td></td>
	  							  </tr>  			
  		  							<tr>	<td></td><td></td><td> </td><td> </td><td> </td></tr>
  		  							<tr>
  		    							<td><label for="motivo2">Motivo</label></td>
  		    							<td><input type="text" class="entrada" name="motivo" id="motivo" value="<%=motivo  %>" contenteditable="false"/></td>
  		    							<td> </td>
  		    							<td><label for="comentarios">Comentarios</label></td>
  		    							<td><textarea class="entrada" name="comentarios" id="comentarios" style="width: 300px;height: 50px;" contenteditable="false"><%=comentarios  %></textarea></td>  		    							
	      							</tr>
  		  							<tr>
  		  							  <td>&nbsp;</td>
  		  							  <td>&nbsp;</td>
  		  							  <td></td>
  		  							  <td>&nbsp;</td>
  		  							  <td>&nbsp;</td>
	  							  </tr>
  		    		  				<tr><td> </td><td> </td><td> </td>  <td> </td>  <td> </td></tr>	  						  							  		    		  				
		  							<tr>
  		    							<td><label for="intervencion">Intervencion quirúrgica</label></td>
  		    							<td><input type="text" class="entrada" name="intervencion" id="intervencion" value="<%=intervencion  %>" contenteditable="false"/></td>
  		    							<td> </td>
  		    							<td> </td>
  		    							<td> </td>
	      							</tr>
		  							<tr>
		  							  <td>&nbsp;</td>
		  							  <td>&nbsp;</td>
		  							  <td></td>
		  							  <td></td>
		  							  <td></td>
	  							  </tr>
  		    		  				<tr><td> </td><td> </td><td> </td><td> </td> <td> </td></tr>						  		    		  			
  		    		  				<tr>
  		    		  				  <td colspan="5">
										<h3 style="padding-left: 0; padding-top: 5px;">Vacunaciones</h3>  		    		  				  
  		    		  				  
  		    		  				  <div class="listado">
  		    		  				  <table >
  		    		  				  	<thead>
                                        <tr>
                                            <th  width="60px">Nro.</th>
                                            <th >Codigo</th>
                                            <th >Nombre</th>
                                            <th >Marca</th>
                                            <th >Comentarios</th>
                                            <th >Dias aviso</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                           <%			  	   		 					  	
                                             	for (int i = 0; i < listaVacunaciones.size(); i++) 
  		  										{
  		    						  				Vacunacion t = listaVacunaciones.get(i);%>
                                          	<tr <%if(i%2!=0){%>class='alt'<%} %>>
	                                            <td ><%= t.getVacuna().getId_vacuna() %> </td>
	                                            <td ><%= t.getVacuna().getCodigo() %> </td>
	                                            <td ><%= t.getVacuna().getNombre() %> </td>
	                                            <td ><%= t.getVacuna().getMarca() %> </td>
	                                            <td ><%= t.getComentarios() %> </td>
	                                            <td ><%= t.getDias_aviso() %> </td>	                                           
	                                        </tr>
	                                        	<%}
												if (listaVacunaciones.size()==0)
												{
													%>
													<tr><td  colspan="7">No hay vacunaciones agregadas a la consulta</td></tr>
													<% 
												}
												%>
                                          </tbody>
                                      </table>
                                      </div>
                                      <br></br>
                                      </td>
	    		  				    </tr>
                                    
                                    
  		    		 				<tr>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
	    		 				  </tr>
  		    		 				<tr>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
	    		 				  </tr>
                                  
  		    		 				<tr>
  		  								<td colspan="5" style="text-align:center;"> <br></br>
  		  								  <input type="button" value="Volver" name="volver" onclick="history.back()" />	  								       </td>  		  								
	  								</tr>
	  							</table>	
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
						
	<%
		
		}
		else
		{ %> <script>location.href = "index.jsp";</script><%}

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
