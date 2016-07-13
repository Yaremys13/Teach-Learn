<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teach&Learn - Home</title>
</head>
<body>
<%@include file = "encabezado.jsp" %>
<%
	if (u != null)
	{	if (u.esFacilitador(u) && !u.esParticipante(u))
		{
	
%>
<div id = "homeFacilitador">
	<a href = "aFacilitadores.jsp">Mi Perfil</a><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href = "aCvs.jsp">Cv</a><br/>
	<a href = "aServicios.jsp">Mis Cursos</a><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href = "aExamenes.jsp">Exámenes</a><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href = "aCalificaciones.jsp">Calificaciones</a><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href = "aMateriales.jsp">Materiales</a><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href = "aFeedBack.jsp">Feedbacks</a><br/>
	<br/> 
	<a href = "aParticipantes.jsp">Registrarme como Participante</a>
</div>
		
<%
		}
		else if (u.esParticipante(u) && !u.esFacilitador(u))
		{
%>
<div id = "homeParticipante">
	<a href = "aParticipantes.jsp">Mi Perfil</a><br/>
	<%//TODO: cServicios %>
	<a href = "cServicios.jsp">Cursos</a><br/>
	<%//TODO: aplicarExamenes %>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href = "aplicarExamenes.jsp">Exámenes</a><br/>
	<%//TODO: cCalificaciones %>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href = "cCalificaciones.jsp">Calificaciones</a><br/>
	<%//TODO: cMateriales %>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href = "cMateriales.jsp">Materiales</a><br/>
	<%//TODO: aplicarFeedBack %>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href = "aplicarFeddBack.jsp">Feedbacks</a><br/>
	<a href = "aPagos.jsp">Pagos</a><br/>
	<br/>
	<a href = "aFacilitadores.jsp">Registrarme como Facilitador</a><br/>
</div>
<%
		}
		else if (u.esParticipante(u) && u.esFacilitador(u))
		{	
		%>	<a href = "aFacilitadores.jsp">Perfil de Facilitador</a><br/>
			<a href = "aParticipantes.jsp">Perfil de Participante</a>
		<%	
		}
		else
		{
		%>
			<br/>
			<a href = "aFacilitadores.jsp">Registrarme como Facilitador</a><br/>
			<a href = "aParticipantes.jsp">Registrarme como Participante</a>
		<%
		}
	}

%>
</body>
</html>