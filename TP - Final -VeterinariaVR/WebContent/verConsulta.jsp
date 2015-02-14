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

<%  
try{
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
		<%@page import="java.sql.*" %>
		<!doctype html>
			<html>
			<head>
				<meta charset="utf-8">
				<link href="estilo.css" rel="stylesheet" type="text/css" />
				<title>Veterinaria VR</title>
			</head>
		<body>	
						<br></br>		
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 100%;"> 							  		
							  <h2>Ves consulta</h2>
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
  		    		  				  <td colspan="5"><table class="listado">
                                        <caption><h3 style="padding-left: 0; padding-top: 5px;">Vacunaciones agregadas</h3></caption>
                                        <tr>
                                            <th class="listado" width="60px">Nro.</th>
                                            <th class="listado">Codigo</th>
                                            <th class="listado">Nombre</th>
                                            <th class="listado">Marca</th>
                                            <th class="listado">Comentarios</th>
                                            <th class="listado">Dias aviso</th>
                                        </tr>
                                          <tbody>
                                           <%			  	   		 					  	
                                             	for (int i = 0; i < listaVacunaciones.size(); i++) 
  		  										{
  		    						  				Vacunacion t = listaVacunaciones.get(i);%>
                                          	<tr>
	                                            <td class="listado"><%= t.getVacuna().getId_vacuna() %> </td>
	                                            <td class="listado"><%= t.getVacuna().getCodigo() %> </td>
	                                            <td class="listado"><%= t.getVacuna().getNombre() %> </td>
	                                            <td class="listado"><%= t.getVacuna().getMarca() %> </td>
	                                            <td class="listado"><%= t.getComentarios() %> </td>
	                                            <td class="listado"><%= t.getDias_aviso() %> </td>	                                           
	                                        </tr>
	                                        	<%}
												if (listaVacunaciones.size()==0)
												{
													%>
													<tr><td class="listado" colspan="7">No hay vacunaciones agregadas a la consulta</td></tr>
													<% 
												}
												%>
                                          </tbody>
                                      </table>
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





