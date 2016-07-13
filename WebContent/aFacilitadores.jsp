<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Facilitadores</title>
<script src="js/jquery-1.12.0.js" type="text/javascript"></script>
<script src="js/varios.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$("#aDP").click(function() {
			if( $('#datosPersonales').is(":visible") ){
			     $('#datosPersonales').css("display","none");
			}else{
			    $('#datosPersonales').css("display","block");
			}
		
		});
		$("#aCV").click(function() {
			if( $('#datosCvs').is(":visible") ){
			     $('#datosCvs').css("display","none");
			}else{
			    $('#datosCvs').css("display","block");
			}
		
		});
	
	});

</script>
<%@include file = "../encabezado.jsp" %>
</head>
<body>
	<h3>Facilitador</h3>
	
		<a id = 'aDP'>Datos Personales</a>
		<div id = "datosPersonales" style = "border:1px solid">
			<% request.setAttribute("opcion", "F"); %>
			<%@include file= "aUsuarios.jsp" %>
			
		</div><br/>
		<a id = "aServicio">Servicios</a>
		<div id = "servicios" style = "border:1px solid">
			<%@include file = "cServicios.jsp" %>
		</div>
	

</body>
</html>