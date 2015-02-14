
<%@page import="javax.xml.ws.Response"%>
<%@page import="java.util.List"%>
<%@page import="negocio.Propietario"%>
<%@page import="negocio.Animal"%>
<%@page import="negocio.Vacuna"%>
<%@page import="negocio.IntervencionQuirurgica"%>
<%@page import="negocio.Vacunacion"%>
<%@page import="javax.websocket.Session"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>

<%  try{
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
	
		List<Animal> listaAnimales = (List<Animal>) request.getSession().getAttribute("listaAnimales");
		List<Propietario> listaPropietarios = (List<Propietario>) request.getSession().getAttribute("listaPropietarios");
		List	<IntervencionQuirurgica> listaIntervenciones = (List<IntervencionQuirurgica>) request.getSession().getAttribute("listaIntervenciones");
		List<Vacuna> listaVacunas = (List<Vacuna>) request.getSession().getAttribute("listaVacunas");
		List<Vacunacion> listaVacunaciones = (List<Vacunacion>) request.getSession().getAttribute("listaVacunaciones");
	
	//trae los valores anteriores
		String id_propietario;
		String id_animal;
		String fecha;
		String comentarios;
		String motivo;
		String id_intervencion;
	
		
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
		<%@page import="java.sql.*" %>
		<!doctype html>
			<html>
			<head>
				<meta charset="utf-8">
				<link href="estilo.css" rel="stylesheet" type="text/css" />
				<title>Veterinaria VR</title>
			</head>
			<script>
			function actualizar()
			{	
				document.frmConsulta.accion.value="ActualizarCombos";
				document.frmConsulta.submit();
			} 
			
			function agregar()
			{
				document.frmConsulta.accion.value="AgregarVacuna";
				document.frmConsulta.submit();
			}
			
			function quitarVacunacion(id)
			{
				document.frmConsulta.accion.value="quitarVacuna";
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
						<br></br>		
						<!-- COMIENZO DIV ---------------------------------------------------------------- -->
						<div style="float:left; width: 100%;"> 
							<form id="frmConsulta" name="frmConsulta" method="post" action="ConsultaServlet">
  								<input type="hidden" value="nuevo" name="accion"/>  	  		  	
  								<input type="hidden" value="0" name="id" id="id"/>  	  		  		  									  	
  									
							  <h2>Nueva consulta</h2>
  								<table class="tablaMaqueta" style="width:95%">
  		    		  				<tr><td></td><td></td> <td></td><td></td><td></td></tr>
  		    		  				<tr>
  		    							<td><label for="id_propietario">Propietario</label></td>
  		    							<td>
  		    							  	<select name="id_propietario" id="id_propietario" onchange="actualizar()">
  		    							  		 <%	for (int i = 0; i < listaPropietarios.size(); i++) 
  		  											{
  		    							  			Propietario t = listaPropietarios.get(i);
  		  	   		 									if(Integer.parseInt(id_propietario) == t.getId_propietario())
	  		  	   		 								{out.print("<option value='"+t.getId_propietario()+"' selected='selected' >"+t.getNombre()+", "+t.getApellido()+"</option>");}  		  	   		 						  	
  		  	   		 									else
	  		  	   		 								{out.print("<option value='"+t.getId_propietario()+"' >"+t.getNombre()+", "+t.getApellido()+"</option>");}  		  	   		 						  	
  		  											}
  		  											if(listaPropietarios.size()==0)
  		  											{	
  		  	   		 									out.print("<option value=''>No hay propietarios cargados</option>");
	  		  	   		 							}
  		  	   		 							%> 
  		  									</select>
  		  								</td>
  		    							<td> </td>
  		    							<td><label for="id_animal">Animal</label></td>
	    							  	<td><select name="id_animal" id="id_animal">
 													<%	for (int i = 0; i < listaAnimales.size(); i++) 
  		  											{
  		  	   		 									Animal t = listaAnimales.get(i);  
  		  	   		 									
  		  	   		 									if(Integer.parseInt(id_animal) == t.getId_animal())
		  	   		 									{out.print("<option value='"+t.getId_animal()+"' selected='selected' >"+t.getNombre()+"</option>");}  		  	   		 						  	
	  	   		 										else
		  	   		 									{out.print("<option value='"+t.getId_animal()+"' >"+t.getNombre()+"</option>");}   		  	   		 									
  		  											}
 													if(listaAnimales.size()==0)
  		  											{	
  		  	   		 									out.print("<option value=''>No hay mascotas cargadas para el propietario seleccionado</option>");
	  		  	   		 							}
  		  	   		 							%>   		  									
  		  									</select>
  		  								</td>
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
  		    							<td><label for="id_intervencion">Intervencion quirúrgica</label></td>
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
  		    		  				<tr><td> </td><td> </td><td> </td><td> </td> <td> </td></tr>						  		    		  			
  		    		  				<tr>
  		    		  				  <td colspan="5"><table class="listado">
                                        <caption><h3 style="padding-left: 0; padding-top: 5px;">Vacunaciones agregadas</h3></caption>
                                        <tr>
                                            <th class="listado" width="60px">Nro.</th>
                                            <th class="listado">Codigo</th>
                                            <th class="listado">Nombre</th>
                                            <th class="listado">Marca</th>
                                            <th class="listado">Comentarios</th>
                                            <th class="listado">Dias aviso</th>
                                            <th class="listado" width="60px"></th>
                                        </tr>
                                          <tbody>
                                           <%			  	   		 					  	
                                             	for (int i = 0; i < listaVacunaciones.size(); i++) 
  		  										{
  		    						  				Vacunacion t = listaVacunaciones.get(i);%>
                                          	<tr>
	                                            <td class="listado"><%= t.getVacuna().getId_vacuna() %> </td>
	                                            <td class="listado"><%= t.getVacuna().getCodigo() %> </td>
	                                            <td class="listado"><%= t.getVacuna().getNombre() %> </td>
	                                            <td class="listado"><%= t.getVacuna().getMarca() %> </td>
	                                            <td class="listado"><%= t.getComentarios() %> </td>
	                                            <td class="listado"><%= t.getDias_aviso() %> </td>
	                                            <td class="listado">
														<input type="button" value="Quitar" onclick="quitarVacunacion(<%=t.getVacuna().getId_vacuna()%>);" /> 	                                            
												</td>
	                                        </tr>
	                                        	<%}
												if (listaVacunaciones.size()==0)
												{
													%>
													<tr><td class="listado" colspan="7">No hay vacunaciones agregadas a la consulta</td></tr>
													<% 
												}
												%>
                                          </tbody>
                                      </table>
                                      <br></br>
                                      </td>
	    		  				    </tr>
                                    
                                    
  		    		 				<tr>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
	    		 				  </tr>
  		    		 				<tr>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
  		    		 				  <td> </td>
	    		 				  </tr>
                                  <tr>
  		    		  				  <td colspan="5">
                                      	<fieldset style="border:thin; background-color:#FCF; width: 95%;">                                          
	  				        			  <legend><h3 style="padding-left: 0; padding-top: 5px;">Vacunaciones</h3></legend>
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
                                              	<input type="button" value="Agregar vacuna" onclick="agregar()" />
                                              </td>
                                           </tr>
                                          </table>
                                      	</fieldset>
                                      </td>
                                   </tr>
  		    		 				<tr>
  		  								<td colspan="5" style="text-align:center;"> <br></br>
  		  								  <input type="submit" name="button" id="button" value="Agregar consulta" onclick="return validarNuevo();" />
  		  								  <input type="button" value="Volver" name="volver" onclick="history.back()" />	  								       </td>  		  								
	  								</tr>
	  							</table>	
							</form>
						</div>
						<!-- FIN DIV ------------------------------------------------------------------------- -->		
						
									
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





