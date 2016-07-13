<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Materiales</title>
</head>
<body>
	<h4>Materiales</h4>
	<form>
		<% //TODO: autocompletar %>
		Servicio: <input type = "text" name = "idS" id = "idS" size = "30"/><br/>
		Título: <input type = "text" name = "idS" id = "idS" size = "30"/><br/>
		Descripción: <input type = "text" name = "descripcionM" id = "descripcionM" size = "30" /><br/>
		Archivo: <input type = "file" name = "archivoM" id = "archivoM" size = "15"/><br/>
		Permite descarga <input type = "checkbox" name = "permiteDM" value = "0"/><br/><br/>
		<input type = "button" value = "Actualizar"/>
		<input type = "reset" value = "Cancelar" />
	</form>


</body>
</html>