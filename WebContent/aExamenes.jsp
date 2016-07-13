<%@page import="utilities.enums.TipoCalificacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Exámenes </title>
</head>
<body>
	<form>
		<% //TODO: autocompletar %>
		Servicio: <input type = "text" name = "idS" id = "idS" size = "20"/><br/>
		Tipo de Calificación: 
		<select name = "tipoCE" id = "tipoCE">
			<option value = "0">Seleccione...</option>
			<%
				for (TipoCalificacion tc : TipoCalificacion.values())
				{	out.println("<option value = '" + tc.getId() + "'>"+ tc.getNombre() + "</option>");
				}
			
			%>
		</select><br/>
		Es certificación <input type = "checkbox" name = "esCertificacionE" id = "esCertificacionE"/>
		<h3>Contenido del Examen</h3>
		<div id = "contenidoExamen" style = "border:1px solid">
			<%@include file = "aPreguntas.jsp" %>
		</div>
		
	</form>

</body>
</html>