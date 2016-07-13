<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Contestar FeedBack</title>
</head>
<body>
	<form>
		<% //TODO: autocompletar %>
		Identificarse <input type = "checkbox" name = "identificarse" id = "identificarse" /><br/> 
		Examen: <input type = "text" name = "idE" id = "idE" size = "30" /><br/>
		<table border = 1>
		<tr>
			<th>Pregunta</th>
			<th>Respuesta</th>
			<th>Observación</th>
			
		</tr>
		<tr>
		  	<td><input type = "text" name = "idPr" id = "idPr" size = "30" /></td>
			<td><input type = "text" name = "idRPr" id = "idRPr" size = "" /></td>
			<td><textarea name = "observacionC" id = "observacionC" rows = "2" cols = "20"></textarea></td>
			
		</tr>
		</table><br/>
		<input type = "button" value = "Actualizar"/>
		<input type = "reset" value = "Cancelar"/>
		
	
	</form>


</body>
</html>