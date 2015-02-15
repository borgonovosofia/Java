<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Peluqueria"%>
<%@page import="negocio.Animal"%>
<%@page import="negocio.Raza"%>
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
		
		//VERIFICA SI ES NECESARIO RECARGAR LA PAGINA 
		boolean s = true;
		try{
			boolean recarga = (Boolean)request.getSession().getAttribute("recarga");
			s=recarga;
	  	}
		catch (Exception e3) {s=false;}
		finally{
			request.getSession().setAttribute("recarga", false);
		}
		List<Peluqueria> lista;
		String valor;  
		
		if(busqueda==true)
		{	
			lista = (List<Peluqueria>)request.getSession().getAttribute("listaBusqueda");
			valor = (String)request.getSession().getAttribute("valor");
		}
		else
		{	
			lista = (List<Peluqueria>) request.getSession().getAttribute("listaPeluquerias");
			valor = "";
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
		function confirmar(msj)
		{
			return confirm(msj);
		}
		function validarBuscar()
		{
			var busqueda = document.getElementById("busqueda").value;
			if(busqueda!="" && busqueda!=null){return true;}
			else{return false;}
		}
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
	if(s==true)
	{
		%>

						
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 95%;">					
							<h1>Listado de Peluquerias</h1>
							<%if(tipousuario.equals("V")){ %>							
							<h4><a href="PeluqueriaServlet?accion=nuevo&id_propietario=0&id_animal=0">Nueva peluqueria</a></h4>						
							<form method="post" action="PeluqueriaServlet">
								<label>&nbsp;&nbsp;&nbsp;&nbsp;Buscar:</label>
								<input type="hidden" value="buscar" name="accion" id="accion">
								<input type="text" value="<%=valor%>" name="b" id="b">							
								<input type="submit" value="Buscar" onclick="return validarBuscar()"/>
								<a href="PeluqueriaServlet?accion=buscar">Cancelar busqueda</a>
								<br></br>
							</form>	
							<%} %>
							<div class="listado">					
							<table >
								<thead  >                                
	                               	<tr>
	                               	  	<%if(tipousuario.equals("V")){ %>
	                               	
                                       	<th  colspan="1" width="5%" >Nro</th>
                                       	<th  colspan="1" width="7%" >Fecha</th>
                                       	<th  colspan="1" width="10%">Tratamiento</th>
                                       	<th  colspan="1" width="20%">Comentarios</th>       
                                       	<th  colspan="1" width="10%">Nombre</th>  
                                       	<th  colspan="1" width="10%">Animal</th>  
                                       	<th  colspan="1" width="10%">Raza</th>  
                                       	<th  colspan="1" width="15%">Propietario</th>  
                                       	<th  width="13%"></th>
                                       	<%}else{ %>
                                       	<th  colspan="1" width="5%" >Nro</th>
                                       	<th  colspan="1" width="7%" >Fecha</th>
                                       	<th  colspan="1" width="12%">Tratamiento</th>
                                       	<th  colspan="1" width="22%">Comentarios</th>       
                                       	<th  colspan="1" width="12%">Nombre</th>  
                                       	<th  colspan="1" width="12%">Animal</th>  
                                       	<th  colspan="1" width="12%">Raza</th>  
                                       	<th  colspan="1" width="18%">Propietario</th>                                         	
                                       	<%} %>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < lista.size(); i++) 
  		  								{
  		  	   		 						Peluqueria t = lista.get(i);
  		  	   		 					%>
  		  	   		 						<tr <%if(i%2!=0){%>class='alt'<%} %>>
  		  	   		 							<td >
  		  	   		 							<%=t.getId_peluqueria()%>
  		  	   		 							</td>
  		  	   		 							<td >
  		  	   		 							<%=t.getFecha()%></td>
  		  	   		 							<td >
  		  	   		 							<%=t.getAccion()%></td>
  		  	   		 							<td >
  		  	   		 							<%= t.getComentarios() %></td>
  		  	   		 							<td >
  		  	   		 							<%=t.getAnimal().getNombre()%>
  		  	   		 							</td>
												<td >
  		  	   		 							<%=t.getAnimal().getRaza().getTipo_animal().getNombre()%></td>
  		  	   		 							<td >
  		  	   		 							<%=t.getAnimal().getRaza().getNombre()%></td>
  		  	   		 							<td >
  		  	   		 							<%=t.getAnimal().getPropietario().getNombre()+", "+t.getAnimal().getPropietario().getApellido()%></td>  		  	   		 							
  		  	   		 							
  		  	   		 							<%if(tipousuario.equals("V")){ %>
  		  	   		 							<td >
  		  	   		 							<a href="PeluqueriaServlet?accion=editar&id=<%=t.getId_peluqueria()%>">Editar</a>
  		  	   		 							<a href="PeluqueriaServlet?accion=borrar&id=<%=t.getId_peluqueria()%>" onclick="return confirmar('¿Está seguro que desea borrar la peluqueria?')">Borrar</a>
  		  	   		 							</td>			  	   		 								  		  	   		 								  		  	   		 						
  		  	   		 							<%} %>	 								  		  	   		 						  		  	   		 						
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(lista.size()==0)
  		  								{
  		  									if(busqueda==true)
  		  									{
  		  									%>
  		  	   		 						<tr><td  <%if(tipousuario.equals("V")){ %>colspan="9"<%}else{%> colspan="8"<%}%>>&nbsp;&nbsp; No hay peluquerias para la busqueda realizada</td></tr>
  		  	   		 						<%
  		  									}
  		  									else
  		  									{%>
  		  	   		 						<tr><td  colspan="9">&nbsp;&nbsp;No hay peluquerias cargadas</td></tr> 		  	
										<%	}
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
						<br></br><input type="button" value="Volver" name="volver" onclick="redireccionar()" />
					</div>
	<%
	}
	else
	{
		%>
					<form action="PeluqueriaServlet" method="get" name="frmActualizar" id="frmActualizar">
						<input type="hidden" value="actualizar" name="accion" id="accion" />
					</form>
					<script>
						document.frmActualizar.submit();
					</script>
		<%		
	}
%>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

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
