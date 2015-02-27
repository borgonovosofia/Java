<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.IntervencionQuirurgica"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<% 

	try{
		 //VERIFICA SI HAY alguna busqueda realizada
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
		
		//VERIFICA SI ES NECESARIO RECARGAR LA PAGINA PARA CARGAR EL COMBO BOX DE TIPOS DE ANIMALES Y LISTADO DE TIPOS
		boolean s = true;
		try{
			boolean recarga = (Boolean)request.getSession().getAttribute("recarga");
			s=recarga;
	  	}
		catch (Exception e3) {s=false;}
		finally{
			request.getSession().setAttribute("recarga", false);
		}
		List<IntervencionQuirurgica> lista;
		String valor;  
		
		if(busqueda==true)
		{	
			lista = (List<IntervencionQuirurgica>) request.getSession().getAttribute("listaBusqueda");
			valor = (String)request.getSession().getAttribute("valor");
		}
		else
		{	
			lista = (List<IntervencionQuirurgica>) request.getSession().getAttribute("listaIntervenciones");
			valor = "";
		}
	
%>	
<html>
<head>
<meta charset="utf-8">
<title>Veterinaria VR</title>
<link href="estilo.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	if ((navigator.appName).indexOf("Microsoft")!=-1)
	{	document.write('<link href="estiloPlantilla2.css" rel="stylesheet" type="text/css">'); }
	else 
	{	document.write('<link href="estiloPlantilla.css" rel="stylesheet" type="text/css">'); }
</script>
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
							<a class="boton negro redondo"  style="font-size: 1em; text-decoration: none;" href="SesionServlet?accion=ModificarUsuario" >&nbsp;Mis datos&nbsp;</a>	                                 
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
	if(s==true) //SOLO MUESTRA LA PAGINA SI YA ESTA CARGADO EL COMBO. SINO REDIRECCIONA PARA RECARGAR.
	{
		if(tipousuario.equals("V"))
		{
		%>

						
						<!-- COMIENZO DIV PARA LISTADO DE RAZAS --------------------------------------------------------------------- -->
						<div style="float: left; clear:left; width: 90%;">					
							<h1>Listado de Intervenciones Quirúrgicas</h1>
							<h4><a href="nuevaIntervencion.jsp">Nueva intervención</a></h4>						
							<form method="post" action="IntervencionServlet">
								<label>&nbsp;&nbsp;&nbsp;&nbsp;Buscar:</label>
								<input type="hidden" value="buscar" name="accion" id="accion">
								<input type="text" value="<%=valor%>" name="b" id="b">							
								<input type="submit" class="boton negro redondo"  value="Buscar" onclick="return validarBuscar()"/>
								<a href="IntervencionServlet?accion=buscar">Cancelar busqueda</a>
								<br></br>
							</form>			
							<div class='listado'>			
							<table>
								<thead >                                
	                               	<tr>
                                       	<th  colspan="1" width="20%">Nombre</th>                                       	
                                       	<th   width="20%"></th>
                                    </tr>
	                            </thead>				
		                        <tbody>
		                          	<%  		  								
  		  								for (int i = 0; i < lista.size(); i++) 
  		  								{
  		  	   		 						IntervencionQuirurgica t = lista.get(i);
  		  	   		 					%>
  		  	   		 						<tr <%if(i%2==0){%>class='alt'<%} %>>
  		  	   		 							<td  >
  		  	   		 							<%=t.getNombre()%></td>  		  	   		 					
  		  	   		 							<td  >
  		  	   		 								<a href="IntervencionServlet?accion=editar&id=<%=t.getId_intervencion()%>&nombre=<%=t.getNombre()%>">Editar</a>
  		  	   		 								<a href="IntervencionServlet?accion=borrar&id=<%=t.getId_intervencion()%>" onclick="return confirmar('¿Está seguro que desea borrar la intervención?')">Borrar</a>		  	
  		  	   		 							</td>   		 								  		  	   		 								  		  	   		 						
  		  	   		 						</tr>  	
  		  	   		 						
  		  	   		 					<%	  	
  		  								}
  		  								if(lista.size()==0)
  		  								{
  		  									if(busqueda==true)
  		  									{
  		  									%>
  		  	   		 						<tr><td   colspan="2">&nbsp;&nbsp; No hay intervenciones para la busqueda realizada</td></tr>
  		  	   		 						<%
  		  									}
  		  									else
  		  									{%>
  		  	   		 						<tr><td   colspan="2">&nbsp;&nbsp;No hay intervenciones cargadas</td></tr> 		  	
										<%	}
  		  								}
  		  							%>		
		                        	<tr>
		                        	</tr>
		                        </tbody>
							</table>
							</div>
						</div>
						<!-- FIN DIV PARA LISTADO DE RAZAS ------------------------------------------------------------------------------ -->
						<div style="text-align: left; clear: both; margin-left: 10px;">
						<br></br><input type="button" class="boton negro redondo"  value="Volver" name="volver" onclick="redireccionar()" />
					</div><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>
	<%
		}
		else
		{ %> <script>location.href = "index.jsp";</script><%}
	}
	else
	{
		%>
					<form action="IntervencionServlet" method="get" name="frmActualizar" id="frmActualizar">
						<input type="hidden" value="actualizar" name="accion" id="accion" />
					</form>
					<script>
						document.frmActualizar.submit();
					</script>
		<%		
	}
%>



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