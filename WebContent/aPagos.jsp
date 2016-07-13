<%@page import="utilities.enums.TipoPago"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Pagos</title>
</head>
<body>

	<form>
		<% //TODO: autocompletar %>
		Servicio: <input type = "text" name = "idS" id = "idS" size = "30" /><br/>
		<% //TODO: datepicker %>
		Fecha Registro: <input type = "text" name = "fechaRPa" id = "fechaRPa" size = "10"/><br/>
		Monto: <input type = "text" name = "montoPa" id = "montoPa" size = "15"/><br/>
		Comprobante: <input type = "file" name = "comprobantePa" id = "comprobantePa" size = "20"/><br/>
		Tipo de Pago: 
		<select name = "tipoPa" id = "tipoPa">
			<option value = "0">Seleccione...</option>
			<%
				for (TipoPago tp : TipoPago.values())
				{	out.println("<option value = '" + tp.getId() + "'>" + tp.getNombre() + "</option>");
					
				}
			%>
		</select><br/>
		Cuota Nro.:<input type = "text" name = "cuotaNPa" id = "cuotaNPa" size = "5"/><br/>
		<br/>
		<input type = "button" value = "Actualizar"/>
		<input type = "reset" value = "Cancelar" />
	</form>

</body>
</html>