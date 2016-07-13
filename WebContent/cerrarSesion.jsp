<%
	session.removeAttribute("usuarioLogeado");
	session.invalidate();
%>
<script>
	location.href = "index.jsp";
</script>