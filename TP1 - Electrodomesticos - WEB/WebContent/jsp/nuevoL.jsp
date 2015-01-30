<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<h2>Nuevo lavarropas</h2>
	<form id="frm" name="frm" action="LavarropasServlet" method="post">
   	  <p>
   	  	<input type="hidden" value="alta" name="accion" id="accion" /> 
   	    <label for="Precio Base"></label>
   	   
   	    <label for="color">Color</label>
   	    <select name="color" id="color">
   	    	<option value="Defecto">Defecto</option>
   	    	<option value="GRIS">GRIS</option>
   	    	<option value="NEGRO">NEGRO</option>
   	    	<option value="BLANCO">BLANCO</option>
   	    	<option value="ROJO">ROJO</option>
   	    	<option value="AZUL">AZUL</option>	    	
        </select>
      </p>
      
      <p>
   	    <label for="consumo">Consumo</label>
   	    <select name="consumo" id="consumo">
   	       	<option value="Defecto">Defecto</option>
   	    	<option value="A">A</option>
   	    	<option value="B">B</option>
   	    	<option value="C">C</option>
   	    	<option value="D">D</option>
   	    	<option value="E">E</option>	
   	    	<option value="F">F</option>	    	    	    	    
        </select>
   	  </p>
   	  <p>
   	    <label for="precio">Precio Base</label>
   	    <input type="text" name="precio" id="precio" />
   	  </p>
   	  <p>
   	    <label for="peso">Peso</label>
   	    <input type="text" name="peso" id="peso" />
   	  </p>
   	  <p>
   	    <label for="resolucion">Carga</label>
   	    <input type="text" name="carga" id="carga" />
   	  </p>
      <input name="btnGuardar" value="Guardar" type="submit" onclick="return validar()" />
      <input name="btnVolver" value="Volver" type="button" onclick="volver()"/>
      
	</form>
</div>
</body>
</html>