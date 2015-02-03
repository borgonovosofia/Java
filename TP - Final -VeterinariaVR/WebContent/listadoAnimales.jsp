<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="negocio.Animal"%>
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
	List<Animal> lista;
	String valor;  
	
	if(busqueda==true)
	{	
		lista = (List<Animal>)request.getSession().getAttribute("listaBusqueda");
		valor = (String)request.getSession().getAttribute("valor");
	}
	else
	{	
		lista = (List<Animal>) request.getSession().getAttribute("listaAnimales");
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
		location.href="menu.jsp";
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
							<h1>Listado de animales</h1>
							<h4><a href="listadoPropietarios.jsp">Nuevo animal</a></h4>						
							<form method="post" action="AnimalServlet">
								<label>&nbsp;&nbsp;&nbsp;&nbsp;Buscar:</label>
								<input type="hidden" value="buscar" name="accion" id="accion">
								<input type="text" value="<%=valor%>" name="b" id="b">							
								<input type="submit" value="Buscar" onclick="return validarBuscar()"/>
								<a href="AnimalServlet?accion=buscar">Cancelar busqueda</a>
								<br></br>
							</form>						
							<table class="listado">
								<thead class="listado" >                                
	                               	<tr>
                                       	<th class="listado" colspan="1" width="5%">Nro</th>
                                       	<th class="listado" colspan="1" width="10%">Nombre</th>
                                       	<th class="listado" colspan="1" width="15%">Nacimiento</th>
                                       	<th class="listado" colspan="1" width="20%">Sexo</th>       
                                       	<th class="listado" colspan="1" width="15%">Propietario</th>                                       	
                                       	<th class="listado" colspan="1" width="10%">Animal</th>  
                                       	<th class="listado" colspan="1" width="10%">Raza</th>                                       	                                   	                                     	                               	
                                       	<th class='listado' width="20%"></th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < lista.size(); i++) 
  		  								{
  		  	   		 						Animal t = lista.get(i);
  		  	   		 					%>
  		  	   		 						<tr>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getId_animal()%>
  		  	   		 							</td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getNombre()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getFecha_nac()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%if(t.getSexo().equals("M")){%><%="Macho"%><%}else{%><%="Hembra"%><%}%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getPropietario().getNombre()+", "+t.getPropietario().getApellido()%>
  		  	   		 							</td>
												<td class='listado'>
  		  	   		 							<%=t.getRaza().getTipo_animal().getNombre()%></td>
  		  	   		 							<td class='listado'>
  		  	   		 							<%=t.getRaza().getNombre()%></td>  		  	   		 							
  		  	   		 							<td class='listado'>
  		  	   		 							<a href="AnimalServlet?accion=editar&id=<%=t.getId_animal()%>&nombre=<%=t.getNombre()%>&fecha_nac=<%=t.getFecha_nac()%>&sexo=<%=t.getSexo()%>&id_propietario=<%=t.getPropietario().getId_propietario()%>&id_raza=<%=t.getRaza().getId_raza()%>&id_tipo=<%=t.getRaza().getTipo_animal().getId_tipo_animal()%>&nombreP=<%=t.getPropietario().getNombre()%>&apellidoP=<%=t.getPropietario().getApellido()%>">Editar</a>
  		  	   		 							<a href="AnimalServlet?accion=borrar&id=<%=t.getId_animal()%>" onclick="return confirmar('¿Está seguro que desea borrar el propietario?')">Borrar</a>		  	   		 								  		  	   		 								  		  	   		 						
  		  	   		 							<a href="">Ver</a>		  	   		 								  		  	   		 								  		  	   		 						  		  	   		 						
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(lista.size()==0)
  		  								{
  		  									if(busqueda==true)
  		  									{
  		  									%>
  		  	   		 						<tr><td class='listado' colspan="8">&nbsp;&nbsp; No hay animales para la busqueda realizada</td></tr>
  		  	   		 						<%
  		  									}
  		  									else
  		  									{%>
  		  	   		 						<tr><td class='listado' colspan="8">&nbsp;&nbsp;No hay animales cargados</td></tr> 		  	
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
					<form action="AnimalServlet" method="get" name="frmActualizar" id="frmActualizar">
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