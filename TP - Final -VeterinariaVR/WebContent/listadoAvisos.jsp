<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Consulta"%>
<%@page import="negocio.Animal"%>
<%@page import="negocio.Vacuna"%>
<%@page import="negocio.Aviso"%>
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

	List<Aviso> listaAvisos = (List<Aviso>) request.getSession().getAttribute("listaAvisos");	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="estilo.css" rel="stylesheet" type="text/css" />	
	<title>Veterinaria VR</title>
</head>
<script>
function redireccionar() 
		{
		location.href="index.jsp";
		} 
</script>
<body>

						
						<!-- COMIENZO DIV --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 100%;">					
							<h1>Listado de avisos de vacunación de esta semana</h1>
							<table class="listado" width="100%">
								<thead class="listado" >                                
	                               	<tr>
                                       	<th class="listado" colspan="1" width="11%">Animal</th>
                                       	<th class="listado" colspan="1" width="15%">Propietario</th>
                                       	<th class="listado" colspan="1" width="10%">Codigo</th>
                                       	<th class="listado" colspan="1" width="10%">Vacuna</th>
                                       	<th class="listado" colspan="1" width="10%">Marca</th>                                       	
                                       	<th class="listado" colspan="1" width="12%">Fec. vacunación</th>
                                       	<th class="listado" colspan="1" width="12%">Proxima fecha</th>
                                       	<th class="listado" colspan="1" width="25%">Contacto</th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < listaAvisos.size(); i++) 
  		  								{
  		  	   		 					 	Aviso t = listaAvisos.get(i);
  		  	   		 					 	String fecha1 = t.getFechaVacunacion().getDate() + "/" + (1+t.getFechaVacunacion().getMonth())+"/"+(1900+t.getFechaVacunacion().getYear());
  		  	   		 					 	String fecha2 = t.getFechaAviso().getDate() + "/" + (1+t.getFechaAviso().getMonth())+"/"+(1900+t.getFechaAviso().getYear());
  		  	   		 					%>
  		  	   		 						<tr>
  		  	   		 							<td class='listado'><%=t.getAnimal().getNombre()%></td>  
  		  	   		 							<td class='listado'><%=t.getAnimal().getPropietario().getNombre()+", "+t.getAnimal().getPropietario().getApellido()%></td>
  		  	   		 							<td class='listado'><%=t.getVacuna().getCodigo()%></td>
  		  	   		 							<td class='listado'><%=t.getVacuna().getNombre()%></td>
  		  	   		 							<td class='listado'><%=t.getVacuna().getMarca()%></td>
  		  	   		 							<td class='listado'><%=fecha1%></td>
  		  	   		 							<td class='listado'><%=fecha2%></td>
  		  	   		 							<td class='listado'>
  		  	   		 								<%
  		  	   		 								  if(t.hayContacto()[0].equals("") && t.hayContacto()[1].equals("") && t.hayContacto()[2].equals(""))
  		  	   		 								  {%>
  		  	   		 								  	<%="No hay email o telefonos registrados"%>
  		  	   		 									
  		  	   		 								<%}
  		  	   		 								  else{%><table width="100%"><td><%
  	  		  	   		 								  	if(!t.hayContacto()[1].equals("") )
	  	  		  	   		 								  	{%><%="<tr>Email: "+t.hayContacto()[1]+"</tr>"%><%}
  	  		  	   		 								  	if(!t.hayContacto()[2].equals("") )
			  	   		 								  		{%><%="<tr>Fijo: "+t.hayContacto()[2]+"</tr>"%><%}
  	  		  	   		 								  	if(!t.hayContacto()[0].equals("") )
	   		 								  					{%><%="<tr>Celular: "+t.hayContacto()[0]+"</tr>"%><%}  		  	   		 								    		  	   		 								 
  		  	   		 									  %></td></table>
  		  	   		 									  <%}%>
  		  	   		 							</td>
  		  	   		 								
  		  	   		 					<%	  	
  		  							}
  		  								if(listaAvisos.size()==0 )
  		  								{
  		  									%><tr><td class='listado' colspan="5">&nbsp;&nbsp;No hay avisos para esta semana</td></tr> <%	
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



