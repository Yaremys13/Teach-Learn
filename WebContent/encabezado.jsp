<%@page import="modelos.MUsuario"%>
<img style="float:right;" src = "imagenes/teachlearn.jpg" width = "200" />

<%
	MUsuario u = (MUsuario) session.getAttribute("usuarioLogeado");
	if (u != null)
	{	out.println("<br/><br/><br/>Bienvenid@ " + u.getLoginU());
%>

	<div style = 'float:right'>
		<a href = "cerrarSesion.jsp">Cerrar sesión</a>
	</div>
	
<%
}
 %>
  </div>
<br/>
<br/>
<hr/>