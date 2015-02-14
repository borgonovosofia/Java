<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.TipoAnimal"%>
<%@page import="negocio.Raza"%>
<%@page import="javax.websocket.Session"%>
<%
try{
//VERIFICA SI HAY UN MENSAJE DEL SERVLET DE TIPO DE ANIMAL
	try{
	String msj = (String)request.getSession().getAttribute("mensaje");
	if(msj!="" && msj!=null)
	{
		%><script>alert("<%=msj%>");</script><%
		request.getSession().setAttribute("mensaje", null);		
	}
	}
	catch (Exception e3) {}

//VERIFICA SI HAY UN MENSAJE DEL SERVLET DE TIPO DE ANIMAL
	try{
	String msj = (String)request.getSession().getAttribute("error");
	if(msj!="" && msj!=null)
	{
		%><script>alert("<%=msj%>");</script><%
		request.getSession().setAttribute("error", null);		
	}
	}
	catch (Exception e3) {}
List<TipoAnimal> listaTipos = (List<TipoAnimal>) request.getSession().getAttribute("listaTipos");
List<Raza> listaRazas = (List<Raza>) request.getSession().getAttribute("listaRazas");

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
			
			function validarAnimal()
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
						

						<!-- COMIENZO DIV PARA LOS TIPOS DE ANIMALES ---------------------------------------------------------------- -->
						<div style="float:left; width: 80%;"> 
							<form id="frmAnimal" name="frmAnimal" method="post" action="AnimalServlet">
  								<input type="hidden" value="modificar" name="accion"/>
  								<input type="hidden" value="<%=request.getSession().getAttribute("nombreP")%>" name="nombreP"/>  		  		
  								<input type="hidden" value="<%=request.getSession().getAttribute("apellidoP")%>" name="apellidoP"/>  		  		
  								<input type="hidden" value="<%=request.getSession().getAttribute("id_propietario")%>" name="id_propietario"/>  	  								
  								<input type="hidden" value="<%= request.getSession().getAttribute("id_animal") %>" name="id_animal" id="id_animal" />  		  		
  								<h2>Modificar mascota</h2>
  								<table class="tablaMaqueta">
  		  							<tr>
  		    							<td>Propietario</td>
  		    							<% String nombreCompleto = request.getSession().getAttribute("nombreP") + ", " + request.getSession().getAttribute("apellidoP"); %>
  		    							<td><input type="text" class="entrada" value="<%=nombreCompleto%>" disabled="disabled" style="color: black"/></td> 							
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>  
  		    		  				
  		    		  				<tr>
  		    							<td><label for="nombre">Nombre</label></td>
  		    							<td><input type="text" class="entrada" name="nombre" id="nombre" value="<%=request.getSession().getAttribute("nombre") %>" /></td>  		    							
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td></tr>  		    		  				
  		  							
		  							<tr>
  		    							<td><label for="fecha_nac">Fecha nacimiento</label></td>
  		    							<td><input type="text" class="entrada" name="fecha_nac" id="fecha_nac" value="<%=request.getSession().getAttribute("fecha_nac")  %>" /></td>
	      							</tr>
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
 
  		    		  				<tr>
  		    							<td><label for="sexo">Sexo</label></td>
  		    							<td><p>
  		    							  <label>
  		    							  <%String sexo = (String)request.getSession().getAttribute("sexo"); %>
  		    							    <input type="radio" name="sexo" value="M" id="sexo_0" <% if (sexo!=null && sexo.equals("M") ){%><%="checked='checked'"%><%} %>>Macho</label>
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
  		  	   		 									if((Integer)request.getSession().getAttribute("id_tipo") == t.getId_tipo_animal())
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
  		  	   		 									if((Integer)request.getSession().getAttribute("id_raza") == t.getId_raza())
  		  	   		 									{out.print("<option value='"+t.getId_raza()+"' selected='selected'>"+t.getNombre()+"</option>");}
  		  	   		 									else {out.print("<option value='"+t.getId_raza()+"' >"+t.getNombre()+"</option>");} 
  		  											}
 													if(listaRazas.size()==0)
  		  											{	
  		  	   		 									out.print("<option value=''>No hay razas cargados para el animal seleccionado</option>");
	  		  	   		 							}
  		  	   		 							%>   		  									
  		  									</select>
  		  								</td>
  		  							</tr> 		    	
  		  							<tr><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td></tr>  		  							  			
  		    		  				<% String peso = (String) request.getSession().getAttribute("peso"); %>
  		    		  				<tr>
										<td><label for="peso">Peso</label></td>
  		    							<td><input type="text" class="entrada" name="peso" id="peso" value="<%if(peso!=null){ out.print(peso);}%>" /></td>
  		    						</tr>  		  							
  		    		  				
  		    		  				<tr><td>&nbsp;</td><td>&nbsp;</td>
  		  							<tr>
  		    							<td>&nbsp;</td>
  		    							<td><input type="submit" name="button" id="button" value="Modificar" onclick="return validarAnimal()" />
  		    								<input type="button" value="Volver" name="volver" onclick="history.back()" />
  		    							</td>
	      							</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV PARA TIPOS DE ANIMALES ------------------------------------------------------------------------- -->				

					
					
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



