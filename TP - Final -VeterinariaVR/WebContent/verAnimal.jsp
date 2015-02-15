<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Vacunacion"%>
<%@page import="negocio.Peso"%>
<%@page import="negocio.Peluqueria"%>
<%@page import="negocio.Consulta"%>
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

	List<Peso> listaPesos = (List<Peso>) request.getSession().getAttribute("listaPesos");
	List<Peluqueria> listaPeluquerias = (List<Peluqueria>) request.getSession().getAttribute("listaPeluquerias");
	List<Consulta> listaConsultas = (List<Consulta>) request.getSession().getAttribute("listaConsultas");
	List<Vacunacion> listaVacunaciones = (List<Vacunacion>) request.getSession().getAttribute("listaVacunaciones");
	
		

	//trae los valores anteriores
	String nombreP;
	String apellidoP;
	String sexo;
	String fecha_nac;
	String nombre;
	Integer id_propietario;
	Integer id_raza;
	Integer id_tipo;
	String raza;
	String tipo;
	Integer id_animal;

	id_animal = (Integer)request.getSession().getAttribute("id_animal");
	sexo = (String)request.getSession().getAttribute("sexo");
	nombreP = (String)request.getSession().getAttribute("nombreP");
	apellidoP = (String)request.getSession().getAttribute("apellidoP");
	fecha_nac = (String)request.getSession().getAttribute("fecha_nac");
	nombre = (String)request.getSession().getAttribute("nombre");
	id_propietario = (Integer)request.getSession().getAttribute("id_propietario");
	id_raza = (Integer)request.getSession().getAttribute("id_raza");
	id_tipo = (Integer)request.getSession().getAttribute("id_tipo");	
	raza = (String)request.getSession().getAttribute("raza");
	tipo = (String)request.getSession().getAttribute("tipo");
	
	
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
	function confirmar(msj)
	{
		return confirm(msj);
	}
	
	function validarPeso()
	{
		var msj="";
		var peso = document.getElementById("pesoI").value;
		if(peso=="" || peso==" " )
		{	msj += "Debe completar el peso"	}
		else if(isNaN(peso))
		{	msj += "El peso debe ser un numero. Divida los gramos con un punto. Ej: 10.200";	}
		if(msj!="")
		{alert(msj);return false;}
		else{return true;}
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
		  <% if(login==true)
          		{
          	  %>	
		  				<h1 style="text-align: center;">Detalle de mascota</h1>
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 50%;"> 						  
  								<table class="tablaMaqueta">
  		    		  				<tr>
  		    							<td>Propietario</td>
  		    							<% String nombreCompleto = nombreP + ", " + apellidoP; %>
  		    							<td><input type="text" class="entrada" value="<%=nombreCompleto%>"  contenteditable="false" style="color: black"/></td> 							
	      							</tr>
  		    		  				  								
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre"  contenteditable="false" value="<%=nombre %>" /></td>  		    							
	      							</tr>
  		  							
		  							<tr>
  		    							<td><label for="fecha_nac">Fecha nacimiento</label></td>
  		    							<td><input type="text" class="entrada" name="fecha_nac" id="fecha_nac"  contenteditable="false" value="<%=fecha_nac  %>" /></td>
	      							</tr>
 
  		    		  				<tr>
  		    							<td><label for="sexo">Sexo</label></td>
  		    							<td><input type="text" class="entrada" name="fecha_nac" id="fecha_nac"  contenteditable="false" value="<% if (sexo.equals("M")){%><%="Macho"%><%}else{%><%="Hembra"%><% } %>" /></td>  		    							  		    					
	      							</tr>
  		    		  				                                    
                                    <tr>
  		    							<td><label for="tipo">Animal</label></td>
  		    							<td><input type="text" class="entrada" name="tipo" id="tipo"  contenteditable="false" value="<%=tipo  %>" /></td>
  		  							</tr>
  		  							<tr>
  		  								<td><label for="raza">Raza</label></td>
  		    							<td><input type="text" class="entrada" name="raza" id="raza" contenteditable="false" value="<%=raza%>" /></td>
  		  							</tr>
									<tr> 
	  							</table><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
						
						
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; width: 55%;">					
							<h3>Listado de pesos</h3>
							<%if(tipousuario.equals("V"))
							{ %>
							<table style="text-align: left; padding-left: 10px;">
								<td>
									<form action="AnimalServlet" method="post">
										<input type="hidden" name="accion" id="accion" value="nuevoPeso">
										<input type="hidden" name="id_animal" id="id_animal" value="<%=id_animal%>">									
										<input type="text" name="pesoI" id="pesoI" />
										<input type="submit" onclick="return validarPeso();" value="Nuevo peso"/>
									</form>
								</td>
							</table>
							<%} %>
							<div class="listado" style="clear: both;  margin-top: 10px;">
							<table >
								<thead  >                                
	                               	<tr>
                                       	<th  colspan="1" width="50%">Fecha</th>
                                       	<th  colspan="1" width="50%">Peso</th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaPesos.size(); i++) 
  		  								{
  		  	   		 						Peso t = listaPesos.get(i);
  		  	   		 					%>
  		  	   		 						<tr <%if(i%2!=0){%>class='alt'<%} %>>
  		  	   		 							<td >
  		  	   		 							<%=t.getFecha()%>
  		  	   		 							</td>
  		  	   		 							<td >
  		  	   		 							<%=t.getPeso()+" Kg"%></td>
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(listaPesos.size()==0)
  		  								{
  		  									%>
  		  	   		 						<tr><td  colspan="2">&nbsp;&nbsp;No hay pesos cargados para el animal</td></tr> 		  	
										<%	
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table>
							</div><br></br>
						</div>											
						<!-- FIN DIV ------------------------------------------------------------------------------ -->
						<%if(tipousuario.equals("V"))
						{ %>
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 100%;">					
							<div style="float:left; "><h3>Listado de consultas </h3></div> 
							<%if(tipousuario.equals("V"))
							{ %><div style="float:left; text-align: left;"><h4><a href="">Nueva consulta</a></h4></div><%} %>
							<div class="listado" style="clear: both;">
							<table >
								<thead  >                                
	                               	<tr>
                                       	<th  colspan="1" width="5%">Nro</th>	                               
                                       	<th  colspan="1" width="8%">Fecha</th>
                                       	<th  colspan="1" width="7%">Animal</th>
                                       	<th  colspan="1" width="17%">Propietario</th>
                                       	<th  colspan="1" width="18%">Comentarios</th>
                                       	<th  colspan="1" width="10%">Motivo</th>
                                       	<th  colspan="1" width="13%">Intervencion</th>
                                       	<th  colspan="1" width="7%">Vacunas</th>
                                       	<th  colspan="1" width="15%"></th>

                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaConsultas.size(); i++) 
  		  								{
  		  	   		 					 	Consulta t = listaConsultas.get(i);
  		  	   		 					%>
  		  	   		 						<tr <%if(i%2!=0){%>class='alt'<%} %>>
  		  	   		 							<td ><%=t.getId_consulta()%></td>  		  	   		 						
  		  	   		 							<td ><%=t.getFecha()%></td>
  		  	   		 							<td ><%=t.getAnimal().getNombre()%></td>
  		  	   		 							<td ><%=t.getAnimal().getPropietario().getNombre()+", "+t.getAnimal().getPropietario().getApellido()%></td>
  		  	   		 							<td ><%=t.getComentarios()%></td>
  		  	   		 							<td ><%=t.getMotivo()%></td>
  		  	   		 							<td ><%if(t.getIntervencion()!=null){%><%=t.getIntervencion().getNombre()%><%}else{%><%="No realizada"%><%}%></td>
  		  	   		 							<td ><%=t.getCant_vacunaciones()%></td>
  		  	   		 							<td >
  		  	   		 								<a href="ConsultaServlet?accion=ver&id=<%= t.getId_consulta() %>">Ver</a>
  		  	   		 								<a href="ConsultaServlet?accion=editar&id=<%= t.getId_consulta() %>"">Editar</a>
  		  	   		 								<a href="ConsultaServlet?accion=borrar&id=<%= t.getId_consulta() %>" onclick="return confirmar('¿Está seguro que desea borrar la consulta?');">Borrar</a>  		  	   		 								
  		  	   		 							</td> 
  		  	   		 					  	</tr>			
  		  	   		 					<%	  	
  		  								}
  		  								if(listaConsultas.size()==0)
  		  								{
  		  									%>
  		  	   		 						<tr><td  colspan="9">&nbsp;&nbsp;No hay consultas cargadas para el animal</td></tr> 		  	
										<%	
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table>
							</div><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------------ -->
						<%}else{ %>
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 100%;">					
							<div style="float:left; "><h3>Listado de vacunas </h3></div> 
							<div class="listado" style="clear: both;">
							<table >
								<thead  >                                
	                               	<tr>
                                       	<th  colspan="1" width="5%">Nro</th>	                               
                                       	<th  colspan="1" width="8%">Fecha</th>
                                       	<th  colspan="1" width="7%">Vacuna</th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaVacunaciones.size(); i++) 
  		  								{
  		  	   		 					 	Vacunacion t = listaVacunaciones.get(i);
  		  	   		 					%>
  		  	   		 						<tr <%if(i%2!=0){%>class='alt'<%} %>>
  		  	   		 							<td ><%=t.getId_vacunacion()%></td>  		  	   		 						
  		  	   		 							<td ><%=t.getFecha()%></td>
  		  	   		 							<td ><%=t.getVacuna().getNombre()%></td>
  		  	   		 					  	</tr>			
  		  	   		 					<%	  	
  		  								}
  		  								if(listaConsultas.size()==0)
  		  								{
  		  									%>
  		  	   		 						<tr><td  colspan="3">&nbsp;&nbsp;No hay vacunas cargadas para el animal</td></tr> 		  	
										<%	
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table>
							</div><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------------ -->						
						<%} %>
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 100%;">					
							<div style="float:left; "><h3>Listado de peluquerias </h3></div> 
							<%if(tipousuario.equals("V"))
							{ %>
							<div style="float:left; text-align: left;"><h4><a href="PeluqueriaServlet?accion=nuevo&id_propietario=<%= id_propietario %>&id_animal=<%= id_animal %>">Nueva peluqueria</a></h4></div>
							<%} %>
							<div class="listado" style="clear: both;">
							<table >
								<thead  >                                
	                               	<tr>
	                               		<%if(tipousuario.equals("V"))
										{ %>
                                       	<th  colspan="1" width="10%">Fecha</th>
                                       	<th  colspan="1" width="15%">Proceso</th>
                                       	<th  colspan="1" width="60%">Comentarios</th>
                                       	<th  colspan="1" width="15%"></th>
                                       	<%} else {%>
                                       	<th  colspan="1" width="20%">Fecha</th>
                                       	<th  colspan="1" width="20%">Proceso</th>
                                       	<th  colspan="1" width="60%">Comentarios</th><%} %>
                                       	
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaPeluquerias.size(); i++) 
  		  								{
  		  	   		 						Peluqueria t = listaPeluquerias.get(i);
  		  	   		 					%>
  		  	   		 						<%if(tipousuario.equals("V"))
											{ %>
  		  	   		 						<tr>
  		  	   		 							<td ><%=t.getFecha()%></td>
  		  	   		 							<td ><%=t.getAccion()%></td>
  		  	   		 							<td ><%=t.getComentarios()%></td>
												<td >
  		  	   		 								<a href="PeluqueriaServlet?accion=editar&id=<%=t.getId_peluqueria()%>">Editar</a>
  		  	   		 								<a href="PeluqueriaServlet?accion=borrar&id=<%=t.getId_peluqueria()%>" onclick="return confirmar('¿Está seguro que desea borrar la peluqueria?');">Borrar</a>
  		  	   		 							</td>  		  	   		 									  	   		 								  		  	   		 								  		  	   		 						
  		  	   		 						</tr>
  		  	   		 						<%}
  		  	   		 						else{ %>  		  	   		 						  	
  		  	   		 						<tr>
  		  	   		 							<td ><%=t.getFecha()%></td>
  		  	   		 							<td ><%=t.getAccion()%></td>
  		  	   		 							<td ><%=t.getComentarios()%></td>
													  	   		 									  	   		 								  		  	   		 								  		  	   		 					
  		  	   		 						</tr>
  		  	   		 						<%} %>
  		  	   		 					<%	  	
  		  								}
  		  								if(listaPeluquerias.size()==0)
  		  								{
  		  									%>
  		  	   		 							<%if(tipousuario.equals("V")){ %><tr><td  colspan="4">&nbsp;&nbsp;No hay peluquerias cargadas para el animal</td></tr> 
  		  	   		 							<%}else{ %>	
  		  	   		 							<tr><td  colspan="3">&nbsp;&nbsp;No hay peluquerias cargadas para el animal</td></tr> 	<%} %>  	
										<%	
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table>
							</div><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------------ -->
						<div style="text-align: center; width:100%; clear:both; padding-left: 10px;">
							<input type="button" value="Volver" name="volver" onclick="history.back()" />
						</div>
									
				<%	}
					else
					{
					%>
					<form action="PropietarioServlet" method="get" name="frmActualizar" id="frmActualizar">
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
