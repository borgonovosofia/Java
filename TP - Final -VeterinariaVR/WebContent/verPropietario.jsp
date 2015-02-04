<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="negocio.Animal"%>
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

	List<Animal> listaAnimales = (List<Animal>) request.getSession().getAttribute("listaAnimales");

//trae los valores anteriores
	String nombre;
	String apellido;
	String direccion;
	String email;
	String telefono_fijo;
	String celular;
	String usuario;
	Integer id_propietario;


	id_propietario = (Integer)request.getSession().getAttribute("id_propietario");
	nombre = (String)request.getSession().getAttribute("nombre");
	apellido = (String)request.getSession().getAttribute("apellido");
	direccion = (String)request.getSession().getAttribute("direccion");
	email = (String)request.getSession().getAttribute("email");
	telefono_fijo = (String)request.getSession().getAttribute("telefono_fijo");
	celular = (String)request.getSession().getAttribute("celular");
	usuario = (String)request.getSession().getAttribute("usuario");
	
	
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
						<div style="float:left; width: 80%;"> 						  
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" contenteditable="false" value="<%=nombre %>" /></td>
  		    							<td><label for="apellido">Apellido</label></td>
  		    							<td><input type="text" class="entrada" name="apellido" id="apellido" contenteditable="false" value="<%=apellido%>" /></td>
	      							</tr>
  		  							
		  							<tr>
  		    							<td><label for="direccion">Direccion</label></td>
  		    							<td><input type="text" class="entrada" name="direccion" id="direccion" contenteditable="false" value="<%= direccion %>" /></td>
	      							</tr>
 
  		    		  				<tr>
  		    							<td><label for="email">Email</label></td>
  		    							<td><input type="text" class="entrada" name="email" id="email" contenteditable="false" value="<%=email%>" size="40"/></td>
	      							</tr>
  		    		  					
  		    		  				<tr>
  		    							<td><label for="telefono_fijo">Telefono fijo</label></td>
  		    							<td><input type="text" class="entrada" name="telefono_fijo" id="telefono_fijo" contenteditable="false" value="<%=telefono_fijo%>"/></td>
  		    							<td><label for="celular">Celular</label></td>
  		    							<td><input type="text" class="entrada" name="celular" id="celular" contenteditable="false" value="<%=celular%>"/></td>
	      							</tr>
  		    		  				
  		    		  				<tr>
  		    							<td><label for="usuario">Usuario</label></td>
  		    							<td><input type="text" class="entrada" name="usuario" id="usuario" contenteditable="false" value="<%=usuario%>"/></td>	
  		    						</tr>
  		    		  				  		    		  				  		    		  				  		  						
	  							</table><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
						
						
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; width: 100%;">					
							<h3>Listado de Animales</h3>	
							<a href="AnimalServlet?accion=nuevo&id_propietario=<%=id_propietario%>&nombreP=<%=nombre%>&apellidoP=<%=apellido%>" style="padding-left: 12px;">Agregar animal</a>
							<br></br>
							<table class="listado">
								<thead class="listado" >                                
	                               	<tr>
                                       	<th class="listado" colspan="1" width="20%">Nombre</th>
                                       	<th class="listado" colspan="1" width="20%">Sexo</th>
                                       	<th class="listado" colspan="1" width="20%">Nacimiento</th>
                                       	<th class="listado" colspan="1" width="10%">Consultas</th>
                                       	<th class="listado" colspan="1" width="10%">Peluqueria</th>
                                       	<th class="listado" colspan="1" width="20%"></th>    
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaAnimales.size(); i++) 
  		  								{
  		  	   		 						Animal t = listaAnimales.get(i);
  		  	   		 					%>
  		  	   		 						<tr>
  		  	   		 							<td class='listado'><%=t.getNombre()%></td>
  		  	   		 							<td class='listado'><%if(t.getSexo()=="M"){out.print("Macho");}else{out.print("Hembra");}%></td>
  		  	   		 							<td class='listado'><%=t.getFecha_nac()%></td>
  		  	   		 							<td class='listado'><%=t.getCant_consultas()%></td>
  		  	   		 							<td class='listado'><%=t.getCant_peluquerias()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 								<a href="AnimalServlet?accion=ver&id=<%=t.getId_animal()%>">Ver</a>
  		  	   		 								<a href="AnimalServlet?accion=editar&id=<%=t.getId_animal()%>&id_tipo=<%=t.getRaza().getTipo_animal().getId_tipo_animal()%>">Editar</a>
  		  	   		 							</td>  		  	   		 							
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(listaAnimales.size()==0)
  		  								{
  		  									%>
  		  	   		 						<tr><td class='listado' colspan="7">&nbsp;&nbsp;No hay animales cargados para el propietario</td></tr> 		  	
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
  		  								
  		  								
  