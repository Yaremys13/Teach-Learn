<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Calificaciones</title>
</head>
<body>
	<h3>Calificaciones</h3>
	<form action="">
		<% //TODO: autocompletar %>
		Examen: <input type = "text" name = "idE" id = "idE" size = "30" /><br/>
		<table border = 1>
		<tr>
			<th>Usuario Calificado</th>
			<th>Cal. Nro</th>
			<th>Cal. Letra</th>
			<th>Observación</th>
			<th>Soporte</th>
		</tr>
		<tr>
		  	<td><input type = "text" name = "idUCalificadoC" id = "idUCalificadoC" size = "20" /></td>
			<td><input type = "text" name = "calidicacionNC" id = "calificacionNC" size = "5" /></td>
			<td><input type = "text" name = "calificacionLC" id = "calificacionLC" size = "5" /></td>
			<td><textarea name = "observacionC" id = "observacionC" rows = "2" cols = "20"></textarea></td>
			<td><input type = "file"/><br/></td>
		</tr>
		</table><br/>
		<input type = "button" value = "Actualizar"/>
		<input type = "reset" value = "Cancelar"/>
		
	
	</form>
</body>
</html>