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
	<title>Veterinaria VR</title>
	
	<!-- FUNCIONES JAVA SCRIPT -->
</head>

<!-- CUERPO -->
<body>
	<center>
		<H1>Veterinaria VR</H1>
		
		<form name="form1" method="post" action="TipoAnimalServlet">
			<input type="hidden" name="accion" id="accion" value="IrRaza" />
			<input type="submit" name="raza" id="raza" value="Razas y tipos de animales"/>
		</form> 
		<form name="form2" method="post" action="VacunaServlet">
			<input type="hidden" name="accion" id="accion" value="IrVacuna" />
			<input type="submit" name="vacuna" id="vacuna" value="Vacunas"/>
		</form> 
		<form name="form3" method="post" action="IntervencionServlet">
			<input type="hidden" name="accion" id="accion" value="IrIntervenciones" />
			<input type="submit" name="intervencion" id="intervencion" value="Intervenciones Quir�rgicas"/>
		</form> 		
		<form name="form4" method="post" action="PropietarioServlet">
			<input type="hidden" name="accion" id="accion" value="IrPropietario" />
			<input type="submit" name="propietario" id="propietario" value="Propietarios"/>
		</form> 			
		<form name="form5" method="post" action="AnimalServlet">
			<input type="hidden" name="accion" id="accion" value="IrAnimales" />
			<input type="submit" name="animal" id="animal" value="Animales"/>
		</form> 	
		<form name="form6" method="post" action="PeluqueriaServlet">
			<input type="hidden" name="accion" id="accion" value="IrPeluquerias" />
			<input type="submit" name="peluqueria" id="peluqueria" value="Peluquerias"/>
		</form> 
		<form name="form7" method="post" action="ConsultaServlet">
			<input type="hidden" name="accion" id="accion" value="IrConsultas" />
			<input type="submit" name="consulta" id="consulta" value="Consultas"/>
		</form> 
		<form name="form8" method="post" action="ConsultaServlet">
			<input type="hidden" name="accion" id="accion" value="GenerarAlertas" />
			<input type="submit" name="consulta" id="consulta" value="Generar Alertas semana"/>
		</form> 
	</center> 	
</body>
</html>