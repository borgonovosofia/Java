<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="negocio.Animal"%>
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

<html>
<head>
<meta charset="utf-8">
<title>Veterinaria VR</title>
<link href="estiloPlantilla.css" rel="stylesheet" type="text/css">
<link href="estilo.css" rel="stylesheet" type="text/css">
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
		  				<h1 style="text-align: center;">Detalle de propietario</h1>
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
  		    		  				  		    		  				  		    		  				  		  						
	  							</table>
	  							<br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
						
						
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; width: 100%;">					
							<h3>Listado de Animales</h3>	
							<a href="AnimalServlet?accion=nuevo&id_propietario=<%=id_propietario%>&nombreP=<%=nombre%>&apellidoP=<%=apellido%>" style="padding-left: 12px;">Agregar animal</a>
							<br></br>
							<div class="listado">
							<table >
								<thead  >                                
	                               	<tr>
                                       	<th  colspan="1" width="20%">Nombre</th>
                                       	<th  colspan="1" width="20%">Sexo</th>
                                       	<th  colspan="1" width="20%">Nacimiento</th>
                                       	<th  colspan="1" width="10%">Consultas</th>
                                       	<th  colspan="1" width="10%">Peluqueria</th>
                                       	<th  colspan="1" width="20%"></th>    
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaAnimales.size(); i++) 
  		  								{
  		  	   		 						Animal t = listaAnimales.get(i);
  		  	   		 					%>
  		  	   		 						<tr <%if(i%2!=0){ %>class='alt'<%} %>>
  		  	   		 							<td ><%=t.getNombre()%></td>
  		  	   		 							<td ><%if(t.getSexo()=="M"){out.print("Macho");}else{out.print("Hembra");}%></td>
  		  	   		 							<td ><%=t.getFecha_nac()%></td>
  		  	   		 							<td ><%=t.getCant_consultas()%></td>
  		  	   		 							<td ><%=t.getCant_peluquerias()%></td>
  		  	   		 							<td >
  		  	   		 								<a href="AnimalServlet?accion=ver&id=<%=t.getId_animal()%>">Ver</a>
  		  	   		 								<a href="AnimalServlet?accion=editar&id=<%=t.getId_animal()%>&id_tipo=<%=t.getRaza().getTipo_animal().getId_tipo_animal()%>">Editar</a>
  		  	   		 							</td>  		  	   		 							
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(listaAnimales.size()==0)
  		  								{
  		  									%>
  		  	   		 						<tr><td  colspan="7">&nbsp;&nbsp;No hay animales cargados para el propietario</td></tr> 		  	
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
