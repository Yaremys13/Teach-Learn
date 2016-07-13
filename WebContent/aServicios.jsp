<%@page import="utilities.enums.Moneda"%>
<%@page import="utilities.enums.FormaPago"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Servicios</title>
</head>
<body>
	<h3>Servicios</h3>
	<form>
		Título: <input type = "text" name = "tituloS" id = "tituloS" size = "30" /><br/>
		Duración: <input type = "text" name = "duracionS" id = "duracionS" size = "5" /> <br/>
		<% //TODO: datepicker %>
		Fecha Inicio: <input type = "text" name = "fechaIS" id = "fechaIS" size = "15" />
		<% //TODO: datepicker %>
		Fecha Fin: <input type = "text" name = "fechaFS" id = "fechaFS" size = "15" /><br/>
		Cantidad mínima de participantes: <input type = "text" name = "cantidadMinPS" id = "cantidadMinPS" size = "10" />
		Cantidad máxima de participantes: <input type = "text" name = "cantidadMaxPS" id = "cantidadMaxPS" size = "10" /><br/>
		Costo: <input type = "text" name = "costoS" id = "costoS" size = "15" /><br/>
		Moneda: 
		<select name = "monedaS" id = "monedaS">
			<option value = "0">Seleccione...</option>
			<%
				for (Moneda m : Moneda.values())
				{	out.println("<option value = '" + m.getId() + "'>" + m.getNombre() + "</option>");
				}
			 %>
		</select> <br/>
		Forma de Pago aceptada:
		<select name = "formaPS" id = "formaPS">
			<option value = "0">Seleccione...</option>
			<%
				for (FormaPago fp : FormaPago.values())
				{	out.println("<option value = '" + fp.getId() + "'>" + fp.getNombre() + "</option>");
				}
			 %>
		</select> <br/>
		Número de cuotas aceptadas: <input type = "text" name = "cuotasS" id = "cuotasS" size = "10" /><br/>
		<h4>Contenido</h4>
		<div id = "servicioContenido" style = "border:1px solid">
			<table>
				<tr>
					<th>Descripción de Contenido</th>
					<th>Adjunto</th>
					<th colspan = "2">Contenidos agregados</th>
				</tr>
				<tr>
					<td><textarea name = "descripcionSC" id = "descripcionSC" rows = "5" cols = "30"></textarea></td>
			 		<td><input type = "file" name = "archivo" id = "archivo" /></td>
					<td><input type = "button" value = "&gt;" /><br/>
						<input type = "button" value = "&lt;"/>
					</td>
					<td>
						<select name = "adjuntoSC" id = "adjuntoSC" multiple style = "width:200px">
						</select>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		<input type = "button" value = "Actualizar" />
		<input type = "reset" value = "Cancelar" />
	</form>

</body>
</html>