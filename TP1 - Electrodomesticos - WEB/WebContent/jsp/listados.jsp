<%@ page import="java.util.*" %>
<%@ page import="javax.*" %>
<%@ page import="negocio.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
		ArrayList<Television> listaT = null;
		ArrayList<Lavarropas> listaL = null;
		listaT = (ArrayList<Television>)session.getAttribute("listaT");	
		listaL = (ArrayList<Lavarropas>)session.getAttribute("listaL");
		int i = 0;
		 try
		 {
			 if(listaT.size()==0 ||listaL.size()==0)
			 { i =1; }
			 else
			 { i = 2;}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function nuevoT()
{
	location.href = "nuevoT.jsp";
}
function nuevoL()
{
	location.href = "nuevoL.jsp";		
}
</script>
</head>
<body>
<div id="televisores" style="width:80; alignment-baseline:central; text-align:center;">
	<form name="frmTelevisores" id="frmTelevisores" method="post" action="">
   		<h1>Televisores</h1> 
   		<input type="hidden" name="accionT" id="accionT"> 
				<%		
					listaT = (ArrayList<Television>)session.getAttribute("listaT");
					if(listaT.size()!=0)
					{
					%>
					<table style="width:100%; border:hidden;">
    					<tr>
      						<th style="width:5%;">Id</th>
      						<th style="width:15%;">Color</th>
      						<th style="width:15%;">Consumo</th>
      						<th style="width:15%;">Precio Base</th>
      						<th style="width:15%;">Precio Total</th>
      						<th style="width:15%;">Peso</th>
      						<th style="width:15%;">Resolución</th>
      						<th style="width:5%;">Sintonizador</th>
      						<th></th><th></th>
    					</tr>
    					<tbody>
					<%
					
						for(Television obj: listaT)
						{
					%>
							<tr>
								
								<td><%=obj.getId() %></td>
								<td><%=obj.getColor().getColor() %></td>
								<td><%=obj.getConsumo().getConsumo()%></td>
								<td><%=obj.getPrecio_base() %></td>
								<td><%=obj.precioFinal()%></td>								
								<td><%=obj.getPeso() %></td>
								<td><%=obj.getResolucion() %></td>
								<td><%if(obj.getSintonizador()){%><%="SI"%><%}else{%><%="NO"%><%} %></td>
								<td>
  									<a href="TelevisorServlet?accion=modificarT&id=<%=obj.getId()%>&color=<%=obj.getColor().getColor()%>&consumo=<%=obj.getConsumo().getConsumo()%>&precio=<%=obj.getPrecio_base()%>&peso=<%=obj.getPeso()%>&resolucion=<%=obj.getResolucion()%>&sintonizador=<%=obj.getSintonizador() %>"
  										 name="modificarTelevisor" id="modificarTelevisor">Modificar</a>
    							</td>
    							<td>
  									<a href="TelevisorServlet?id=<%=obj.getId()%>&accion=baja" name="borrarTelevisor" id="borrarTelevisor"
  										 onclick="return confirm('Está seguro que desea eliminar el televisor?')">Eliminar</a>
  								</td>
							</tr>
					<%
						}
					%>
					
    					</tbody>
	  				</table>
  	  				<%	
					}
					else
					{
						%>
						<p style="color: red; font-size:15px">No hay televisores registrados</p>
						<%
					}
					%>
	  <p>
	    <input type="button" name="nuevoTelevisor" id="nuevoTelevisor" value="Nuevo Televisor" onclick="nuevoT();">
  	  </p>
	</form>
</div> 

<br>
<br>

<div id="lavarropas" style="width:80; alignment-baseline:central; text-align:center;">
	<form name="frmLavarropas" id="frmLavarropas" method="post" action="">
   		<h1>Lavarropas</h1> 
  	 	<input type="hidden" name="accionL" id="accionL">    
  		
				<%
					listaL = (ArrayList<Lavarropas>)session.getAttribute("listaL");
					if(listaL.size()!=0)
					{
					%>
					<table style="width:100%; border:hidden;">
    					<tr>
      						<th style="width:10%;">Id</th>
      						<th style="width:15%;">Color</th>
      						<th style="width:15%;">Consumo</th>
      						<th style="width:15%;">Precio</th>
      						<th style="width:15%;">Peso</th>
      						<th style="width:15%;">Carga</th>
      						<th></th><th></th>
    					</tr>
    					<tbody>
					<%
						for(Lavarropas obj: listaL){
					%>
							<tr>
								<td><%=obj.getId() %></td>
								<td><%=obj.getColor().getColor() %></td>
								<td><%=obj.getConsumo().getConsumo()%></td>
								<td><%=obj.getPrecio_base() %></td>
								<td><%=obj.getPeso() %></td>
								<td><%=obj.getCarga() %></td>
								<td>
  									<a href="LavarropasServlet?accion=modificarL&id=<%=obj.getId()%>&color=<%=obj.getColor().getColor()%>&consumo=<%=obj.getConsumo().getConsumo()%>&precio=<%=obj.getPrecio_base()%>&peso=<%=obj.getPeso()%>&carga=<%=obj.getCarga()%>" 
  									name="modificarLavarropas" id="modificarLavarropas" >Modificar</a>
    							</td>
    							<td>
  									<a href="LavarropasServlet?id=<%=obj.getId()%>&accion=baja" name="borrarLavarropas" id="borrarLavarropas"  onclick="return confirm('Está seguro que desea eliminar el lavarropas?')">Eliminar</a>
  								</td>
							</tr>
					<%
						}
					%>	
    					</tbody>
  					</table>
  		  	  		<%	
					}
					else
					{
						%>
						<p style="color: red; font-size:15px">No hay lavarropas registrados</p>
						<%
					}
					%>
  		
  		<p>
    		<input type="button" name="nuevoLavarropas" id="nuevoLavarropas" value="Nuevo Lavarropas" onclick="nuevoL();">
    </p>
	</form>
</div> 

</body>
</html>

<%
		 }
		 catch (java.lang.NullPointerException e)
		 {   		 
			 response.sendRedirect("");
		 }%>