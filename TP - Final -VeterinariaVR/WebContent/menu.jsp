<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="javax.websocket.Session"%>
<%
 //VERIFICA SI HAY UN MENSAJE DE ERROR PARA MOSTRAR
	try{
		String msj3 = (String)request.getSession().getAttribute("error");
		if(msj3!="" && msj3!=null)
		{
			%><script>alert("<%=msj3%>");</script><%
			request.getSession().setAttribute("error", null);			
		}
	}
	catch (Exception e3) {}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- ENCABEZADO -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="estilo.css" rel="stylesheet" type="text/css" />
	<title>Insert title here</title>
	
	<!-- FUNCIONES JAVA SCRIPT -->
	<script>
		function razas()
		{
			var accion = document.getElementById("accion");
			accion.setAttribute("value", "IrRaza");
			document.form1.submit();
		}
	</script>

</head>

<!-- CUERPO -->
<body>
	<center>
		<H1>Veterinaria VR</H1>
		<form name="form1" method="post" action="TipoAnimalServlet">
			<input type="hidden" name="accion" id="accion" value="1" />
			<input type="button" name="raza" id="raza" value="Razas y tipos de animales" onclick="razas();"/>
		</form> 
	</center> 	
</body>
</html>