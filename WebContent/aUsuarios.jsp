<%@page import="modelos.MUsuario"%>
<%@page import="utilities.enums.Nacionalidades"%>
<%@page import="utilities.enums.TiposDocumento"%>
<script>
	$(document).ready(function () {
		
		$("#actualizarUsuario").click( function (){
			ok = validarElementoVacio("nombreU","eNombre");
			ok = validarElementoVacio("apellidoU","eApellido");
			ok = validarElementoSeleccionado("tipoDocumentoU","eDocId");
			ok = validarElementoVacio("ciU","eDocId");
			ok = validarElementoVacio("fechaNU","eFechaNac");
			ok = validarElementoVacio("lugarNU","eLugarNac");
			ok = validarElementoSeleccionado("nacionalidadU","eNacionalidad");
			ok = validarElementoVacio("telefonoU","eTelefono");
			ok = validarElementoVacio("telefonoCU","eTelCel");
			ok = validarElementoVacio("emailU","eEmail");
			ok = validarElementoVacio("loginU","eLogin");
			ok = validarElementoVacio("passwordU","ePassword");
			
			if (ok != 1)
			{	$("#formAct").submit();
				
			}
		});	
		$("[id$='U']").focus(function(){
			$(this).removeAttr("style");
			eName = ($(this).attr("data-eName"));
			$("#" + eName).css("display","none");
		}); 
	
	});

</script>
<%
	MUsuario usuario = ((MUsuario) session.getAttribute("usuarioLogeado")) == null?new MUsuario():(MUsuario) session.getAttribute("usuarioLogeado") ;
%>
<form action= "SActualizarUsuario" id = "formAct" method = "post">
	
	<input type = "hidden" name = "opcion" value = "<%=request.getAttribute("opcion")%>" />
	Nombre: <input type = "text" name = "nombreU" id = "nombreU" size = "30" data-eName = "eNombre" value = "<%= usuario.getNombreU() %>"/>
	<div id = "eNombre" style = "display:none"><p>Nombre no puede estar vac�o</p></div><br/>
	Apellido: <input type = "text" name = "apellidoU" id = "apellidoU" size = "30" data-eName = "eApellido" value = "<%= usuario.getApellidoU() %>" />
	<div id = "eApellido" style = "display:none"><p>Apellido no puede estar vac�o</p></div><br/>
	Documento de Identificaci�n: 
	<select name = "tipoDocumentoU" id = "tipoDocumentoU" data-eName = "eDocId">
		<option value = "0">Seleccione...</option>
		<%	for (TiposDocumento td : TiposDocumento.values())
			{	String selected = usuario.getTipoDocumentoU().intValue()==td.getId()?"selected":"";
				out.println("<option value = '" + td.getId() + "' "+ selected +" >" + td.getNombre() + "</option>");
			}
		
		 %> 
	</select><input type = "text" name = "ciU" id = "ciU" size = "20" data-eName = "eDocId" value = "<%= usuario.getCiU() %>"/>
	<div id = "eDocId" style = "display:none"><p>Debe seleccionar un tipo de documento y seguidamente introducir el n�mero</p></div><br/>
	<% //TODO: datepicker %>
	Fecha de Nacimiento: <input type = "text" name = "fechaNU" id = "fechaNU" size = "15" data-eName = "eFechaNac"/>
	<div id = "eFechaNac" style = "display:none"><p>Debe seleccionar la fecha de nacimiento</p></div><br/>
	Lugar de Nacimiento: <input type = "text" name = "lugarNU" id = "lugarNU" size = "20" data-eName = "eLugarNac"/>
	<div id = "eLugarNac" style = "display:none"><p>Lugar de nacimiento no puede estar vac�o</p></div><br/>
	Nacionalidad: 
	<select name = "nacionalidadU" id = "nacionalidadU" data-eName = "eNacionalidad">
		<option value = "0">Seleccione...</option>
		<%
			for(Nacionalidades n : Nacionalidades.values())
			{
				out.println("<option value = '" + n.getId() + "'>" + n.getNombre() + "</option>");
			}
		%>	
	</select>
	<div id = "eNacionalidad" style = "display:none"><p>Debe seleccionar la Nacionalidad</p></div><br/>
	Tel�fono:<input type = "text" name = "telefonoU" id = "telefonoU" size = "20" data-eName = "eTelefono"/>
	<div id = "eTelefono" style = "display:none"><p>Tel�fono no puede estar vac�o</p></div><br/>
	Tel�fono celular: <input type = "text" name = "telefonoCU" id = "telefonoCU" size = "20" data-eName = "eTelCel"/>
	<div id = "eTelCel" style = "display:none"><p>Tel�fono celular no puede estar vac�o</p></div><br/>
	<h3>Direcci�n</h3>
	<%@include file = "aDirecciones.jsp" %>
	<h3>Datos de Conexi�n</h3>
	Email: <input type = "text" name = "emailU" id = "emailU" size = "40" data-eName = "eEmail"/>
	<div id = "eEmail" style = "display:none"><p>Email no puede estar vac�o</p></div><br/>
	Login: <input type = "text" name = "loginU" id = "loginU" size = "20" data-eName = "eLogin"/>
	<div id = "eLogin" style = "display:none"><p>Login no puede estar vac�o</p></div><br/>
	Password: <input type = "password" name = "passwordU" id = "passwordU" size = "20" data-eName = "ePassword"/>
	<div id = "ePassword" style = "display:none"><p>Password no puede estar vac�o</p></div><br/>
	<br/>
	<table>
		<tr>
			<td><img src = "" width = "100px" height = "90px"/></td>
			<td>Foto: <input type = "file" name = "fotoPU" id = "fotoPU"/>
				<input type = "hidden" name = "hFotoPU" id = "hFotoPU"/>
			</td>
		</tr>
	</table><br/>
	Recibe las notificaciones <input type = "checkbox" name = "recibeNU" id = "recibeNU"/>
	<br/><br/>
	<input type = "button" id = "actualizarUsuario" value = "Actualizar"/>
	<input type = "reset" value = "Cancelar"/>
</form>