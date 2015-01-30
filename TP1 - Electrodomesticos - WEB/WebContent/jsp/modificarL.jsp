<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar lavarropas</title>
<script>
function volver()
{ 
	location.href = "listados.jsp";
}

function validar()
{
	var txtCarga = document.getElementById("carga");
	var txtPrecioBase = document.getElementById("precio");
	var txtPeso = document.getElementById("peso");
	var cboColor = document.getElementById("color");
	var cboConsumo = document.getElementById("consumo");
	var mensaje = "";
	
	if(!(!txtCarga.value=="" && !txtPrecioBase.value=="" &&  !txtPeso.value=="" && !cboColor.value =="Defecto" && 
			 !cboConsumo.value =="Defecto"))
	{
		if(!(txtCarga.value=="" && txtPrecioBase.value=="" && txtPeso.value=="" && cboColor.value =="Defecto" && 
				 cboConsumo.value =="Defecto" ))
		{
			if((!(!txtPrecioBase.value=="" && !txtPeso.value==""))|| cboConsumo.value =="Defecto"&& !cboColor.value =="Defecto" ||
			!cboConsumo.value =="Defecto" && cboColor.value =="Defecto")
			{
				mensaje = mensaje + "La combinacion de datos ingresados no es correcta\n" + "debe completar todos los campos, ninguno de ellos\n"
						  + "o solo el precio y el peso\n\n";				}
		}
	}
	if(isNaN(txtPrecioBase.value))
	{mensaje = mensaje + "El formato del precio no es correcto\n";}
	if(isNaN(txtPeso.value))
	{mensaje = mensaje + "El formato del peso no es correcto\n";}
	if(isNaN(txtCarga.value))
	{	mensaje = mensaje + "El formato de la carga no es correcto\n";	}

	if(mensaje!="")
	{alert(mensaje);return false;}
	else{return true;}
}
</script>
</head>
<body>
<div style="background-color:#FFF; width:80%; alignment-adjust:central; text-align:center">
	<h2>Modificar lavarropas <%= request.getSession().getAttribute("id") %></h2>
	<form id="frm" name="frm" action="LavarropasServlet" method="post">
   	  <p>
   	  	<input type="hidden" value="modificar" name="accion" id="accion" />
   	    <input type="hidden" value="<%= request.getSession().getAttribute("id") %>" id="id" name="id" />
   	  	    	   
   	    <label for="color">Color</label>
   	    <select name="color" id="color">
   	    	<option value="Defecto" <%if(request.getSession().getAttribute("color").equals("Defecto")){%><%="selected='selected'"%><%} %>>Defecto</option>
   	    	<option value="GRIS"    <%if(request.getSession().getAttribute("color").equals("GRIS")){%><%="selected='selected'"%><%} %>>GRIS</option>
   	    	<option value="NEGRO"   <%if(request.getSession().getAttribute("color").equals("NEGRO")){%><%="selected='selected'"%><%} %>>NEGRO</option>
   	    	<option value="BLANCO"  <%if(request.getSession().getAttribute("color").equals("BLANCO")){%><%="selected='selected'"%><%} %>>BLANCO</option>
   	    	<option value="ROJO"    <%if(request.getSession().getAttribute("color").equals("ROJO")){%><%="selected='selected'"%><%} %>>ROJO</option>
   	    	<option value="AZUL"    <%if(request.getSession().getAttribute("color").equals("AZUL")){%><%="selected='selected'"%><%} %>>AZUL</option>	    	
        </select>
      </p>
      
      <p>
   	    <label for="consumo">Consumo</label>
   	    <select name="consumo" id="consumo">
   	       	<option value="Defecto">Defecto</option>
   	    	<option value="A" <%if(request.getSession().getAttribute("consumo").equals("A")){%><%="selected='selected'"%><%} %> >A</option>
   	    	<option value="B" <%if(request.getSession().getAttribute("consumo").equals("B")){%><%="selected='selected'"%><%} %> >B</option>
   	    	<option value="C" <%if(request.getSession().getAttribute("consumo").equals("C")){%><%="selected='selected'"%><%} %> >C</option>
   	    	<option value="D" <%if(request.getSession().getAttribute("consumo").equals("D")){%><%="selected='selected'"%><%} %> >D</option>
   	    	<option value="E" <%if(request.getSession().getAttribute("consumo").equals("E")){%><%="selected='selected'"%><%} %> >E</option>	
   	    	<option value="F" <%if(request.getSession().getAttribute("consumo").equals("F")){%><%="selected='selected'"%><%} %> >F</option>	    	    	    	    
        </select>
   	  </p>
   	  <p>
   	    <label for="precio">Precio Base</label>
   	    <input type="text" name="precio" id="precio" value="<%= request.getSession().getAttribute("precio") %>" />
   	  </p>
   	  <p>
   	    <label for="peso">Peso</label>
   	    <input type="text" name="peso" id="peso" value="<%= request.getSession().getAttribute("peso") %>"/>
   	  </p>
   	  <p>
   	    <label for="resolucion">Carga</label>
   	    <input type="text" name="carga" id="carga" value="<%= request.getSession().getAttribute("carga") %>"/>
   	  </p>
      <input name="btnGuardar" value="Modificar" type="submit" onclick="return validar()" />
      <input name="btnVolver" value="Volver" type="button" onclick="volver()"/>
      
	</form>
</div>
</body>
</html>