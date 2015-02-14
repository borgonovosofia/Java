<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Consulta"%>
<%@page import="negocio.Animal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 <%  
 try{
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
	List<Consulta>  listaConsultas;
	String valor;  
	
	
	if(busqueda==true)
	{	
		listaConsultas = (List<Consulta>)request.getSession().getAttribute("listaBusqueda");
		valor = (String)request.getSession().getAttribute("valor");
	}
	else
	{	
		listaConsultas = (List<Consulta>) request.getSession().getAttribute("listaConsultas");	
		valor = "";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="estilo.css" rel="stylesheet" type="text/css" />	
	<title>Veterinaria VR</title>
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
</head>
<body>
<%
	if(s==true)
	{
		%>

						
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 95%;">					
							<h1>Listado de Consultas</h1>
							<h4><a href="ConsultaServlet?accion=nuevo&id_propietario=0&id_animal=0">Nueva consulta</a></h4>						
							<form method="post" action="ConsultaServlet">
								<label>&nbsp;&nbsp;&nbsp;&nbsp;Buscar:</label>
								<input type="hidden" value="buscar" name="accion" id="accion">
								<input type="text" value="<%=valor%>" name="b" id="b">							
								<input type="submit" value="Buscar" onclick="return validarBuscar()"/>
								<a href="ConsultaServlet?accion=buscar">Cancelar busqueda</a>
								<br></br>
							</form>						
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
  		  								if(listaConsultas.size()==0 && busqueda==false)
  		  								{
  		  									%>
  		  	   		 						<tr><td class='listado' colspan="9">&nbsp;&nbsp;No hay consultas cargadas</td></tr> 		  	
										<%	
  		  								}
  		  								if(listaConsultas.size()==0 && busqueda==true)
		  								{
		  									%>
		  	   		 						<tr><td class='listado' colspan="9">&nbsp;&nbsp;No hay consultas para la busqueda realizada</td></tr> 		  	
									<%	
		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table>
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
					<form action="ConsultaServlet" method="get" name="frmActualizar" id="frmActualizar">
						<input type="hidden" value="actualizar" name="accion" id="accion" />
					</form>
					<script>
						document.frmActualizar.submit();
					</script>
		<%		
	}
%>
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



