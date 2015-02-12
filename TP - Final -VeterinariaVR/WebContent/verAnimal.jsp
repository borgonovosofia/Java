
<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>

<%@page import="negocio.Peso"%>
<%@page import="negocio.Peluqueria"%>
<%@page import="negocio.Consulta"%>
<%@page import="javax.websocket.Session"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%  
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
	<%@page import="java.sql.*" %>
	<!doctype html>
		<html>
		<head>
			<meta charset="utf-8">
			<link href="estilo.css" rel="stylesheet" type="text/css" />
			<title>Veterinaria VR</title>
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
							<table class="listado">
								<thead class="listado" >                                
	                               	<tr>
                                       	<th class="listado" colspan="1" width="50%">Fecha</th>
                                       	<th class="listado" colspan="1" width="50%">Peso</th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaPesos.size(); i++) 
  		  								{
  		  	   		 						Peso t = listaPesos.get(i);
  		  	   		 					%>
  		  	   		 						<tr>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getFecha()%>
  		  	   		 							</td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getPeso()+" Kg"%></td>
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(listaPesos.size()==0)
  		  								{
  		  									%>
  		  	   		 						<tr><td class='listado' colspan="2">&nbsp;&nbsp;No hay pesos cargados para el animal</td></tr> 		  	
										<%	
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table><br></br>
						</div>											
						<!-- FIN DIV ------------------------------------------------------------------------------ -->
						
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 100%;">					
							<div style="float:left; "><h3>Listado de consultas </h3></div> 
							<div style="float:left; text-align: left;"><h4><a href="">Nueva consulta</a></h4></div>
							<table class="listado">
								<thead class="listado" >                                
	                               	<tr>
                                       	 <th class="listado" colspan="1" width="5%">Nro</th>	                               
                                       	<th class="listado" colspan="1" width="8%">Fecha</th>
                                       	<th class="listado" colspan="1" width="7%">Animal</th>
                                       	<th class="listado" colspan="1" width="17%">Propietario</th>
                                       	<th class="listado" colspan="1" width="18%">Comentarios</th>
                                       	<th class="listado" colspan="1" width="10%">Motivo</th>
                                       	<th class="listado" colspan="1" width="13%">Intervencion</th>
                                       	<th class="listado" colspan="1" width="7%">Vacunas</th>
                                       	<th class="listado" colspan="1" width="15%"></th>

                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaConsultas.size(); i++) 
  		  								{
  		  	   		 					 	Consulta t = listaConsultas.get(i);
  		  	   		 					%>
  		  	   		 						<tr>
  		  	   		 							<td class='listado'><%=t.getId_consulta()%></td>  		  	   		 						
  		  	   		 							<td class='listado'><%=t.getFecha()%></td>
  		  	   		 							<td class='listado'><%=t.getAnimal().getNombre()%></td>
  		  	   		 							<td class='listado'><%=t.getAnimal().getPropietario().getNombre()+", "+t.getAnimal().getPropietario().getApellido()%></td>
  		  	   		 							<td class='listado'><%=t.getComentarios()%></td>
  		  	   		 							<td class='listado'><%=t.getMotivo()%></td>
  		  	   		 							<td class='listado'><%if(t.getIntervencion()!=null){%><%=t.getIntervencion().getNombre()%><%}else{%><%="No realizada"%><%}%></td>
  		  	   		 							<td class='listado'><%=t.getCant_vacunaciones()%></td>
  		  	   		 							<td class='listado'>
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
  		  	   		 						<tr><td class='listado' colspan="9">&nbsp;&nbsp;No hay consultas cargadas para el animal</td></tr> 		  	
										<%	
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------------ -->
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 100%;">					
							<div style="float:left; "><h3>Listado de peluquerias </h3></div> 
							<div style="float:left; text-align: left;"><h4><a href="PeluqueriaServlet?accion=nuevo&id_propietario=<%= id_propietario %>&id_animal=<%= id_animal %>">Nueva peluqueria</a></h4></div>
								
							<table class="listado">
								<thead class="listado" >                                
	                               	<tr>
                                       	<th class="listado" colspan="1" width="10%">Fecha</th>
                                       	<th class="listado" colspan="1" width="15%">Proceso</th>
                                       	<th class="listado" colspan="1" width="60%">Comentarios</th>
                                       	<th class="listado" colspan="1" width="15%"></th>
                                       	
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaPeluquerias.size(); i++) 
  		  								{
  		  	   		 						Peluqueria t = listaPeluquerias.get(i);
  		  	   		 					%>
  		  	   		 						<tr>
  		  	   		 							<td class='listado'><%=t.getFecha()%></td>
  		  	   		 							<td class='listado'><%=t.getAccion()%></td>
  		  	   		 							<td class='listado'><%=t.getComentarios()%></td>
												<td class='listado'>
  		  	   		 								<a href="PeluqueriaServlet?accion=editar&id=<%=t.getId_peluqueria()%>">Editar</a>
  		  	   		 								<a href="PeluqueriaServlet?accion=borrar&id=<%=t.getId_peluqueria()%>" onclick="return confirmar('¿Está seguro que desea borrar la peluqueria?');">Borrar</a>
  		  	   		 							</td>  		  	   		 									  	   		 								  		  	   		 								  		  	   		 						
  		  	   		 						</tr>  		  	   		 						  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(listaPeluquerias.size()==0)
  		  								{
  		  									%>
  		  	   		 						<tr><td class='listado' colspan="4">&nbsp;&nbsp;No hay peluquerias cargadas para el animal</td></tr> 		  	
										<%	
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------------ -->
						<div style="text-align: center; width:100%; clear:both; padding-left: 10px;">
							<input type="button" value="Volver" name="volver" onclick="history.back()" />
						</div>
									
				</body>
			</html>			  								
  		  								
  		  								
