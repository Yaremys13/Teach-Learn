<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn</title>
</head>
<body>
	<h2>FeedBack</h2>
	<form>
		<% //TODO: autocompletar %>
		Servicio: <input type = "text" name = "idS" id = "idS" size = "30"/><br/>
		<% //TODO: datepicker %>
		Fecha Inicio: <input type = "text" name = "fechaCFB" id = "fechaCFB" size = "10"/>
		Fecha Cierre: <input type = "text" name = "fechaCierreFB" id = "fechaCierreFB" size = "10"/><br/>
		<h3>Contenido</h3>
		<div id = "feedBackContenido" style = "border:1px solid">
			<%@include file = "aPreguntas.jsp" %>
		</div>
		<br/>
		<input type = "button" value = "Actualizar"/>
		<input type = "reset" value = "Cancelar" />
	</form>
</body>
</html>