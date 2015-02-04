<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>

<%  
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

	List<TipoAnimal> listaTipos = (List<TipoAnimal>) request.getSession().getAttribute("listaTipos");
	List<Raza> listaRazas = (List<Raza>) request.getSession().getAttribute("listaRazas");

//trae los valores anteriores
	String nombreP;
	String apellidoP;
	String sexo;
	String fecha_nac;
	String nombre;
	Integer id_propietario;
	Integer id_raza;
	Integer id_tipo;
	String peso;

	if(request.getSession().getAttribute("peso")==null)
	{ peso = "";}
	else { peso = (String)request.getSession().getAttribute("peso");}
	
	if(request.getSession().getAttribute("sexo")==null)
	{ sexo = "X";} 
	else {sexo = (String)request.getSession().getAttribute("sexo");}

	
	if(request.getSession().getAttribute("nombreP")==null)
	{ nombreP = "";} 
	else {nombreP = (String)request.getSession().getAttribute("nombreP");}

	if(request.getSession().getAttribute("apellidoP")==null)
	{  apellidoP = "";}
	else{apellidoP = (String)request.getSession().getAttribute("apellidoP");}
	
	if(request.getSession().getAttribute("fecha_nac")==null)
	{ fecha_nac = "";} 
	else{ fecha_nac = (String)request.getSession().getAttribute("fecha_nac");}

	if(request.getSession().getAttribute("nombre")==null)
	{  nombre = "";} 
	else{ nombre = (String)request.getSession().getAttribute("nombre");}
	
	if(request.getSession().getAttribute("id_propietario").getClass().toString().equals("class java.lang.String"))
	{
		id_propietario = Integer.parseInt((String)request.getSession().getAttribute("id_propietario"));
	}
	else
	{
	
	id_propietario = (Integer)request.getSession().getAttribute("id_propietario");}
	
	if(request.getSession().getAttribute("id_raza")==null)
	{  id_raza = 0;} 
	else{ id_raza = (Integer)request.getSession().getAttribute("id_raza");}
	
	if(request.getSession().getAttribute("id_tipo")==null)
	{  id_tipo = 0;} 
	else{ id_tipo = (Integer)request.getSession().getAttribute("id_tipo");}
	
	
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
	<%@page import="java.sql.*" %>
	<!doctype html>
		<html>
		<head>
			<meta charset="utf-8">
			<link href="estilo.css" rel="stylesheet" type="text/css" />
			<title>Veterinaria VR</title>
		</head>
				
		<script>
			function validar(obj) {
			  patron = /^\d{2}\/\d{2}\/\d{4}$/
			  return patron.test(obj.value);
			}

			function validaFechaDDMMAAAA(fecha){
				var dtCh= "/";
				var minYear=1900;
				var maxYear=2100;
				function isInteger(s){
					var i;
					for (i = 0; i < s.length; i++){
						var c = s.charAt(i);
						if (((c < "0") || (c > "9"))) return false;
					}
					return true;
				}
				function stripCharsInBag(s, bag){
					var i;
					var returnString = "";
					for (i = 0; i < s.length; i++){
						var c = s.charAt(i);
						if (bag.indexOf(c) == -1) returnString += c;
					}
					return returnString;
				}
				function daysInFebruary (year){
					return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
				}
				function DaysArray(n) {
					for (var i = 1; i <= n; i++) {
						this[i] = 31
						if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
						if (i==2) {this[i] = 29}
					}
					return this
				}
				function isDate(dtStr){
					var daysInMonth = DaysArray(12)
					var pos1=dtStr.indexOf(dtCh)
					var pos2=dtStr.indexOf(dtCh,pos1+1)
					var strDay=dtStr.substring(0,pos1)
					var strMonth=dtStr.substring(pos1+1,pos2)
					var strYear=dtStr.substring(pos2+1)
					strYr=strYear
					if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
					if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
					for (var i = 1; i <= 3; i++) {
						if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
					}
					month=parseInt(strMonth)
					day=parseInt(strDay)
					year=parseInt(strYr)
					if (pos1==-1 || pos2==-1){
						return false
					}
					if (strMonth.length<1 || month<1 || month>12){
						return false
					}
					if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
						return false
					}
					if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
						return false
					}
					if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
						return false
					}
					return true
				}
				if(isDate(fecha)){
					return true;
				}else{
					return false;
				}
			}			
			
			function validarNuevo()
			{	
				var msj = "";
				var nombre = document.getElementById("nombre").value;
				if(nombre=="" || nombre==" ")
				{msj+="Debe completar el nombre del animal\n"}
				
				var fecha_nac = document.getElementById("fecha_nac").value;
				if(fecha_nac!="" && fecha_nac!=" ")
				{
					if(!validaFechaDDMMAAAA(fecha_nac))
					{
						msj+="La fecha debe tener el formato 'dd/mm/aaaa', respetando los numeros del dia y del mes.\n"
					}
				}
				
				var s = "F";
				var sexo = document.getElementsByName("sexo");
				var i;
				for(i=0;i<sexo.length;i++)
		        {
					if(sexo[i].checked){s="T";}		             
		        }
				if(s=="F")
				{msj+="Debe seleccionar un sexo para el animal\n"}
				
				var raza = document.getElementById("id_raza").value;
				if(raza=="" || raza==" ")
				{ msj += "Debe elegir una raza para el animal\n"}
				
				var peso = document.getElementById("peso").value;
				if(peso!="" && peso!=" " && isNaN(peso))
				{
					msj += "El peso debe ser un numero. Divida los gramos con un punto. Ej: 10.200"
				}
				if(msj!="")
				{alert(msj);return false;}
				else{return true;}
			}
			function actualizar()
			{	
				document.frmAnimal.accion.value="ActualizarCombos"
				document.frmAnimal.submit();
			} 
		</script>

		<body>	
						<br></br>		
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 80%;"> 
							<form id="frmAnimal" name="frmAnimal" method="post" action="AnimalServlet">
  								<input type="hidden" value="nuevo" name="accion"/>  	
  								<input type="hidden" value="<%=nombreP%>" name="nombreP"/>  		  		
  								<input type="hidden" value="<%=apellidoP%>" name="apellidoP"/>  		  		
  								<input type="hidden" value="<%=id_propietario%>" name="id_propietario"/>  		  		
  								<h2>Nuevo Animal</h2>
  								<table class="tablaMaqueta">
  		    		  				<tr>
  		    							<td>Propietario</td>
  		    							<% String nombreCompleto = nombreP + ", " + apellidoP; %>
  		    							<td><input type="text" class="entrada" value="<%=nombreCompleto%>" disabled="disabled" style="color: black"/></td> 							
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		    		  				  								
  		  							<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" value="<%=nombre %>" /></td>  		    							
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td></tr>  		    		  				
  		  							
		  							<tr>
  		    							<td><label for="fecha_nac">Fecha nacimiento</label></td>
  		    							<td><input type="text" class="entrada" name="fecha_nac" id="fecha_nac" value="<%=fecha_nac  %>" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
 
  		    		  				<tr>
  		    							<td><label for="sexo">Sexo</label></td>
  		    							<td><p>
  		    							  <label>
  		    							    <input type="radio" name="sexo" value="M" id="sexo_0" <% if (sexo!=null && sexo.equals("M")){%><%="checked='checked'"%><%} %>>Macho</label>
  		    							  <br>
  		    							  <label>
  		    							    <input type="radio" name="sexo" value="H" id="sexo_1" <% if (sexo!=null && sexo.equals("H")){%><%="checked='checked'"%><%} %>>Hembra</label>
  		    							  <br>
	    							    </p></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		    		  				                                    
                                    <tr>
  		    							<td><label for="id_tipo">Animal</label></td>
  		    							<td>
  		    							  	<select name="id_tipo" id="id_tipo" onchange="actualizar()">
  		    							  		 <%	for (int i = 0; i < listaTipos.size(); i++) 
  		  											{
  		  	   		 									TipoAnimal t = listaTipos.get(i);
  		  	   		 									if(id_tipo == t.getId_tipo_animal())
	  		  	   		 								{out.print("<option value='"+t.getId_tipo_animal()+"' selected='selected' >"+t.getNombre()+"</option>");}  		  	   		 						  	
  		  	   		 									else
	  		  	   		 								{out.print("<option value='"+t.getId_tipo_animal()+"' >"+t.getNombre()+"</option>");}  		  	   		 						  	
  		  											}
  		  											if(listaTipos.size()==0)
  		  											{	
  		  	   		 									out.print("<option value=''>No hay tipos cargados</option>");
	  		  	   		 							}
  		  	   		 							%> 
  		  									</select>
  		  								</td>
  		  							</tr>
  		  							<tr><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td></tr>
  		  							<tr>
  		  								<td><label for="raza">Raza</label></td>
  		  								<td>
  		  									<select name="id_raza" id="id_raza">
 													<%	for (int i = 0; i < listaRazas.size(); i++) 
  		  											{
  		  	   		 									Raza t = listaRazas.get(i);  		  	   		 					
	  		  	   		 								out.print("<option value='"+t.getId_raza()+"' >"+t.getNombre()+"</option>");  		  	   		 						  	
  		  											}
 													if(listaRazas.size()==0)
  		  											{	
  		  	   		 									out.print("<option value=''>No hay razas cargados para el animal seleccionado</option>");
	  		  	   		 							}
  		  	   		 							%>   		  									
  		  									</select>
  		  								</td>
  		  							</tr>
									<tr> 
  		  							<tr><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td></tr>
  		  							<tr>
										<td><label for="peso">Peso</label></td>
  		    							<td><input type="text" class="entrada" name="peso" id="peso" value="<%=peso  %>" /></td>
  		    						</tr>  		  							
  		  							<tr><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td></tr>
  		  							<tr>
  		  								<td>&nbsp;</td>  		  								
  		  								<td><input type="submit" name="button" id="button" value="Agregar Animal" onclick="return validarNuevo();" />
											<input type="button" value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
  	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
						
									
				</body>
			</html>			  								
  		  								
  		  								
  