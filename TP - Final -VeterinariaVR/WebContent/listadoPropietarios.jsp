<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 <%  //VERIFICA SI HAY alguna busqueda realizada
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
	List<Propietario> lista;
	String valor;  
	
	if(busqueda==true)
	{	
		lista = (List<Propietario>) request.getSession().getAttribute("listaBusqueda");
		valor = (String)request.getSession().getAttribute("valor");
	}
	else
	{	
		lista = (List<Propietario>) request.getSession().getAttribute("listaPropietarios");
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
						<div style="float: left; clear:left; width: 100%;">					
							<h1>Listado de propietarios</h1>
							<h4><a href="PropietarioServlet?accion=nueva">Nuevo propietario</a></h4>						
							<form method="post" action="PropietarioServlet">
								<label>&nbsp;&nbsp;&nbsp;&nbsp;Buscar:</label>
								<input type="hidden" value="buscar" name="accion" id="accion">
								<input type="text" value="<%=valor%>" name="b" id="b">							
								<input type="submit" value="Buscar" onclick="return validarBuscar()"/>
								<a href="PropietarioServlet?accion=buscar">Cancelar busqueda</a>
								<br></br>
							</form>						
							<table class="listado">
								<thead class="listado" >                                
	                               	<tr>
                                       	<th class="listado" colspan="1" width="3%">Nro</th>
                                       	<th class="listado" colspan="1" width="10%">Nombre</th>
                                       	<th class="listado" colspan="1" width="15%">Apellido</th>
                                       	<th class="listado" colspan="1" width="20%">Email</th>       
                                       	<th class="listado" colspan="1" width="9%">Tel. Fijo</th>                                       	
                                       	<th class="listado" colspan="1" width="9%">Tel. Celular</th>  
                                       	<th class="listado" colspan="1" width="10%">Usuario</th>                                       	                                   	                                     	                               	
                                       	<th class='listado' width="24%"></th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < lista.size(); i++) 
  		  								{
  		  	   		 						Propietario t = lista.get(i);
  		  	   		 					%>
  		  	   		 						<tr>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getId_propietario()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getNombre()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getApellido()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getEmail()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getTelefono_fijo()%></td>
												<td class='listado'>
  		  	   		 							<%=t.getCelular()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getUsuario()%></td>  		  	   		 							
  		  	   		 							<td class='listado'>
  		  	   		 							<a href="PropietarioServlet?accion=editar&id=<%=t.getId_propietario()%>&nombre=<%=t.getNombre()%>&apellido=<%=t.getApellido()%>&direccion=<%=t.getDireccion()%>&email=<%=t.getEmail()%>&telefono_fijo=<%=t.getTelefono_fijo()%>&celular=<%=t.getCelular()%>&usuario=<%=t.getUsuario()%>">Editar</a>
  		  	   		 							<a href="PropietarioServlet?accion=borrar&id=<%=t.getId_propietario()%>" onclick="return confirmar('¿Está seguro que desea borrar el propietario?')">Borrar</a>
  		  	   		 							<a href="AnimalServlet?accion=nuevo&id_propietario=<%=t.getId_propietario()%>&nombreP=<%=t.getNombre()%>&apellidoP=<%=t.getApellido()%>">Agregar animal</a>
  		  	   		 							<a href="PropietarioServlet?accion=ver&id=<%= t.getId_propietario() %>">Ver</a>
  		  	   		 									  	   		 								  		  	   		 								  		  	   		 						
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(lista.size()==0)
  		  								{
  		  									if(busqueda==true)
  		  									{
  		  									%>
  		  	   		 						<tr><td class='listado' colspan="8">&nbsp;&nbsp; No hay propietarios para la busqueda realizada</td></tr>
  		  	   		 						<%
  		  									}
  		  									else
  		  									{%>
  		  	   		 						<tr><td class='listado' colspan="8">&nbsp;&nbsp;No hay propietarios cargados</td></tr> 		  	
										<%	}
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
					<form action="PropietarioServlet" method="get" name="frmActualizar" id="frmActualizar">
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