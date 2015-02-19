<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Animal"%>
<%@page import="negocio.Vacuna"%>
<%@page import="negocio.IntervencionQuirurgica"%>
<%@page import="negocio.Vacunacion"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>

<!doctype html>
<% 
try{
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
	
		List	<IntervencionQuirurgica> listaIntervenciones = (List<IntervencionQuirurgica>) request.getSession().getAttribute("listaIntervenciones");
		List<Vacuna> listaVacunas = (List<Vacuna>) request.getSession().getAttribute("listaVacunas");
		List<Vacunacion> listaVacunaciones = (List<Vacunacion>) request.getSession().getAttribute("listaVacunaciones");
	
	//trae los valores anteriores
		String id_consulta;
		String id_propietario;
		String id_animal;
		String fecha;
		String comentarios;
		String motivo;
		String id_intervencion;
	
		if(request.getSession().getAttribute("id_consulta")==null)
		{ id_consulta = "";}else { id_consulta = (String)request.getSession().getAttribute("id_consulta");}
		
		
		if(request.getSession().getAttribute("motivo")==null)
		{ motivo = "";}else { motivo = (String)request.getSession().getAttribute("motivo");}
		
		if(request.getSession().getAttribute("comentarios")==null)
		{ comentarios = "";}  else {comentarios = (String)request.getSession().getAttribute("comentarios");}
		
		if(request.getSession().getAttribute("id_animal")==null)
		{ id_animal = "0";} else {id_animal = (String)request.getSession().getAttribute("id_animal");}
	
		if(request.getSession().getAttribute("fecha")==null)
			{  fecha = "";} else{fecha = (String)request.getSession().getAttribute("fecha");}
		
		if(request.getSession().getAttribute("id_propietario")==null)
		{ id_propietario = "0";} else{ id_propietario = (String)request.getSession().getAttribute("id_propietario");}
	
		if(request.getSession().getAttribute("id_intervencion")==null)
		{ id_intervencion = "0";} else{ id_intervencion = (String)request.getSession().getAttribute("id_intervencion");}
		
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
function agregar()
{
	document.frmConsulta.accion.value="AgregarVacunaModificacion";
	document.frmConsulta.submit();
}

function quitarVacunacion(id)
{
	document.frmConsulta.accion.value="quitarVacunaModificar";
	document.frmConsulta.id.value=id;
	document.frmConsulta.submit();
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
	var id_propietario = document.getElementById("id_propietario").value;
	if(id_propietario=="" || id_propietario==" ")
	{msj+="Seleccionar un propietario\n"}
	
	var id_animal = document.getElementById("id_animal").value;
	if(id_animal=="" || id_animal==" ")
	{msj+="Seleccionar un animal\n"}
	
	var motivo = document.getElementById("motivo").value;
	if(motivo=="" || motivo==" ")
	{msj+="Seleccionar un motivo para la consulta\n"}
	
	var fecha = document.getElementById("fecha").value;
	if(fecha!="" && fecha!=" ")
	{
		if(!validaFechaDDMMAAAA(fecha))
		{
			msj+="La fecha debe tener el formato 'dd/mm/aaaa', respetando los numeros del dia y del mes.\n"
		}
	}
	else
	{
		msj += "La fecha no puede estar vacia. \n";	
	}
					
	if(msj!="")
	{alert(msj);return false;}
	else{return true;}
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

<%	if(tipousuario.equals("V"))
		{
		%>

						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 100%;"> 
							<form id="frmConsulta" name="frmConsulta" method="post" action="ConsultaServlet">
  								<input type="hidden" value="modificar" name="accion"/>  	  	
  								<input type="hidden" value="<%=id_consulta %>" name="id_consulta" id="id_consulta"/>
  								<input type="hidden" value="0" name="id" id="id"/>  	  		  		  									  	
  								  	  		  		  									  	
							  <h2>Modificar consulta</h2>
  								<table class="tablaMaqueta" style="width:95%">
  		    		  				<tr><td></td><td></td> <td></td><td></td><td></td></tr>
  		    		  				<tr>
  		    							<td><label for="id_propietario">Propietario</label></td>
  		    							<td><input name="id_propietario" id="id_propietario" value="<%= id_propietario %>" disabled="disabled" /></td>  		    							
  		    							<td> </td>
  		    							<td><label for="id_animal">Animal</label></td>
	    							  	<td><input name="id_animal" id="id_animal" value="<%= id_animal %>" disabled="disabled"/></td> 											
  		  							</tr>
  		    		  				<tr>
  		    		  				  <td>&nbsp;</td>
  		    		  				  <td>&nbsp;</td>
  		    		  				  <td></td>
  		    		  				  <td>&nbsp;</td>
  		    		  				  <td>&nbsp;</td>
	    		  				  </tr>
  		  							<tr><td>  </td><td>  </td> <td> </td> <td> </td><td> </td></tr>
  		  							<tr>
  		  								<td><label for="fecha">Fecha</label></td>
	  								  	<td><input type="text" class="entrada" name="fecha" id="fecha" value="<%=fecha %>" /></td>
	  								  	<td> </td><td> </td><td> </td>
  		  							</tr>
  		  							<tr>
  		  							  <td>&nbsp;</td>
  		  							  <td>&nbsp;</td>
  		  							  <td></td>
  		  							  <td></td>
  		  							  <td></td>
	  							  </tr>  			
  		  							<tr>	<td></td><td></td><td> </td><td> </td><td> </td></tr>
  		  							<tr>
  		    							<td><label for="motivo2">Motivo</label></td>
  		    							<td><input type="text" class="entrada" name="motivo" id="motivo" value="<%=motivo  %>" /></td>
  		    							<td> </td>
  		    							<td><label for="comentarios">Comentarios</label></td>
  		    							<td><textarea class="entrada" name="comentarios" id="comentarios" style="width: 300px;height: 50px;" ><%=comentarios  %></textarea></td>  		    							
	      							</tr>
  		  							<tr>
  		  							  <td>&nbsp;</td>
  		  							  <td>&nbsp;</td>
  		  							  <td></td>
  		  							  <td>&nbsp;</td>
  		  							  <td>&nbsp;</td>
	  							  </tr>
  		    		  				<tr><td> </td><td> </td><td> </td>  <td> </td>  <td> </td></tr>	  						  							  		    		  				
		  							<tr>
  		    							<td><label for="id_intervencion">Intervencion quirúrgica:<%=id_intervencion %></label></td>
  		    							<td>
  		    								<select name="id_intervencion" id="id_intervencion">
  		    							  	<%	if(Integer.parseInt(id_intervencion) == 0)
	  		  	   		 						{out.print("<option value='0' selected='selected' >No se realizó intervención</option>");}  	  	   		 						  						else
	  		  	   		 						{out.print("<option value='0' >No se realizó intervención</option>");}  		  	   		 					  	
                                             	for (int i = 0; i < listaIntervenciones.size(); i++) 
  		  										{
  		    						  				IntervencionQuirurgica t = listaIntervenciones.get(i);
  		  	   		 								if(Integer.parseInt(id_intervencion) == t.getId_intervencion())
	  		  	   		 							{out.print("<option value='"+t.getId_intervencion()+"' selected='selected'>"+t.getNombre()+"</option>");}  	  	   		 						  	
  		  	   		 								else
	  		  	   		 							{out.print("<option value='"+t.getId_intervencion()+"' >"+t.getNombre()+"</option>");}  		  	   		 					  	
  		  										}
  		  										if(listaIntervenciones.size()==0)
  		  										{	
  		  	   		 								out.print("<option value=''>No hay intervenciones cargadas</option>");
	  		  	   		 						}
  		  	   		 						%>
	    							    	</select>
	    							    </td>
  		    							<td> </td>
  		    							<td> </td>
  		    							<td> </td>
	      							</tr>
		  							<tr>
		  							  <td>&nbsp;</td>
		  							  <td>&nbsp;</td>
		  							  <td></td>
		  							  <td></td>
		  							  <td></td>
	  							  </tr>
  		    		  			</table>               
  		    		  			<h3>Vacunaciones agregadas</h3>  
  		    		  			<div class="listado">  		    		  			               
  		    		  			  <table >
                                        <thead>
                                        <tr>
                                            <th  width="60px">Nro.</th>
                                            <th >Codigo</th>
                                            <th >Nombre</th>
                                            <th >Marca</th>
                                            <th >Comentarios</th>
                                            <th >Dias aviso</th>
                                            <th  width="60px"></th>
                                        </tr>
                                        </thead>
                                          <tbody>
                                           <%			  	   		 					  	
                                             	for (int i = 0; i < listaVacunaciones.size(); i++) 
  		  										{
  		    						  				Vacunacion t = listaVacunaciones.get(i);%>
                                          	<tr  <%if(i%2!=0){ %> class='alt'<%} %>>
	                                            <td ><%= t.getVacuna().getId_vacuna() %> </td>
	                                            <td ><%= t.getVacuna().getCodigo() %> </td>
	                                            <td ><%= t.getVacuna().getNombre() %> </td>
	                                            <td ><%= t.getVacuna().getMarca() %> </td>
	                                            <td ><%= t.getComentarios() %> </td>
	                                            <td ><%= t.getDias_aviso() %> </td>
	                                            <td >
														<input type="button"  class="negro redondo boton"  value="Quitar" onclick="quitarVacunacion(<%=t.getVacuna().getId_vacuna()%>);" /> 	                                            
												</td>
	                                        </tr>
	                                        	<%}
												if (listaVacunaciones.size()==0)
												{
													%>
													<tr><td  colspan="7">No hay vacunaciones agregadas a la consulta</td></tr>
													<% 
												}
												%>
                                          </tbody>
                                  	 </table>                               </div>
                                      <br></br>
                                      
                                    
  		    		 				
                                   <fieldset style="border:thin; background-color:#FCF; width: 95%; margin-left: 1em;">                                          
	  				        		<h3 style="padding-left: 0; padding-top: 5px;">Vacunaciones</h3>
                                       <table width="100%" border="0">
                                            <tr>
                                              <td><label for="id_vacuna">Vacuna</label></td>
                                              <td><select name="id_vacuna" id="id_vacuna"">
                                                <%			  	   		 					  	
                                             	for (int i = 0; i < listaVacunas.size(); i++) 
  		  										{	Vacuna t = listaVacunas.get(i);
  		  	   		 								out.print("<option value='"+t.getId_vacuna()+"' >"+t.getNombre()+" - "+t.getMarca()+"</option>");}
  		  										if(listaVacunas.size()==0)
  		  										{	out.print("<option value=''>No hay vacunas cargadas</option>");}
  		  	   		 							%>
                                              		</select>
                                            </td>
                                            <td> </td>
                                            <td><label for="comentarios_vacuna">Comentarios</label></td>
                                            <td><textarea type="text" class="entrada" name="comentarios_vacuna" id="comentarios_vacuna" style="width: 300px;height: 50px;"></textarea></td>
                                           </tr>
                                           <tr> <td> </td><td> </td> <td> </td><td> </td><td> </td> </tr>
                                           <tr>
                                           	<td> <label for="aviso">Aviso de proxima vacunacion</label></td>
  		    								<td>
  		    								 	<p>
  		    							  	  		<label><input type="radio" name="aviso" value="1" id="aviso_0" checked="checked">Si</label>
  		    							  			<br>
  		    							  			<label><input type="radio" name="aviso" value="0" id="aviso_1" >No</label>
  		    							  			<br>
	    							    		</p>
	    							    	</td>
	    							    	<td> </td> <td> </td><td> </td><td> </td>
                                           </tr>
                                           <tr><td> </td><td> </td> <td> </td> <td> </td><td> </td></tr>
                                           <tr><td> </td><td> </td> <td> </td> <td> </td>
                                           	<td>
                                              	<input type="button"  class="negro redondo boton"  value="Agregar vacuna" onclick="agregar()" />
                                              </td>
                                           </tr>
                                          </table>
                                      	</fieldset>
                                    <br></br>
  		  							<input type="submit"  class="negro redondo boton"  name="button" id="button" value="Modificar consulta" onclick="return validarNuevo();" />
  		  							<input type="button"  class="negro redondo boton"  value="Volver" name="volver" onclick="history.back()" />	  								     
							</form>
							<br></br><br></br>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
<%
		
		}
		else
		{ %> <script>location.href = "index.jsp";</script><%}%>


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
