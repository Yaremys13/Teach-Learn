<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Participantes</title>
</head>
<body>
	<h3>Participante</h3>
	<form action="">
		<h3>Datos Personales</h3>
		<div id = "datosPersonales" style = "border:1px solid">
			<%@include file = "aUsuarios.jsp" %>
			<br/>
			<input type = "button" value = "Actualizar"/>
			<input type = "reset" value = "Cancelar"/>
		</div><br/>
		<h3>Datos de Cv</h3>
		<div id = "datosCvs" style = "border:1px solid">
			<%@include file = "aCvs.jsp" %>
		</div><br/>	
		
	</form> 

</body>
</html>