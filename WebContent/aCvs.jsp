<%@page import="utilities.enums.TipoDetalleCvs"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn</title>
</head>
<body>
	<h3>Cvs</h3>
	<% //TODO: autocompletar %>
	<form>
		Facilitador: <input type = "text" name = "idF" id = "idF" size = "30" /><br/>
		Es visible:<input type = "checkbox" name = "esVisibleCvs" id = "esVisibleCvs"/>
		<h4>Datos Personales</h4>
		<div name = "datosPersonales" id = "datosPersonales" style = "border:1px solid">
			<%@include file = "aUsuarios.jsp" %>	
		</div>
		<h4>Detalles</h4>
		<div id = "detallesCvs" style = "border:1px solid">
			Tipo de Detalle: <select name = "tipoDCvs" id = "tipoDCvs">
				<option value = "0">Seleccione...</option>
				<%	for(TipoDetalleCvs opc : TipoDetalleCvs.values())
					{	out.println("<option value = '" + opc.getId() + "'>" + opc.getNombre() + "</option>");
					}
				 %>
			</select><br/>
			Lugar/Institución: <input type = "text" name = "lugarDCvs" id = "lugarDCvs" size = "30" /><br/> 
			Descripción: <input type = "text" name = "contenidoDCvs" id = "contenidoDCvs" size = "30" /><br/> 
			Fecha Inicio: <input type = "text" name = "fechaIDCvs" id = "fechaIDCvs" size = "15" />
			<% //TODO: datepicker %>
			Fecha Fin: <input type = "text" name = "fechaFDCvs" id = "fechaFDCvs" size = "15" /><br/>
			<% //TODO: cálculo de la duración %>
			Breve explicación del trabajo desempeñado:<br/>
			<textarea name = "observacionesDCvs" id = "observacionesDCvs" rows = "5" cols = "30"></textarea><br/>
			
			Sueldo: <input type = "text" name = "sueldoDCvs" id = "sueldoDCvs" size = "20" /><br/>
			Persona de Contacto (Supervisor): <input type = "text" name = "contactoPDCvs" id = "contactoPDCvs" size = "30" /><br/>
			Teléfono de Contacto: <input type = "text" name = "contenidoDCvs" id = "contactoTDCvs" size = "20" /><br/> 
			<br/>
			<input type = "button" value = "Agregar Detalle" />
			<input type = "reset" value = "Cancelar" />
		</div>
		<br/>
		<br/>
		<input type = "button" value = "Actualizar" />
		<input type = "reset" value = "Cancelar" />
	</form>

</body>
</html>