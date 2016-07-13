<%@page import="utilities.enums.TipoRespuestaPregunta"%>
<table>
	<tr>
		<th>Título</th>
		<th>Enunciado</th>
		<th>Tipo de Respuesta</th>
		<th>Respuestas</th>
		<th>Opciones</th>
	</tr>
	<tr>
		<td><input type = "text" name = "tituloPr" id = "tituloPr" size = "20" /></td>
		<td><textarea name = "enunciadoPr" id = "enunciadoPr" rows = "4" cols = "20"></textarea></td>
		<td><select name = "tipoRPr" id = "tipoRPr">
				<option value = "0">Seleccione...</option>
				<%
					for (TipoRespuestaPregunta trp : TipoRespuestaPregunta.values())
					{	out.println("<option value = '" + trp.getId() + "'>" + trp.getNombre() + "</option>");
						
					}
				%>
			</select>
		</td> 
		<td>
			<table>
				<tr>
					<td><input type = "text" name = "opcionRespuestaPr" id = "opcionRespuestaPr" size = "15"/>
						Es correcta <input type = "checkbox" name = "esCorrectaR" id = "esCorrectaR" />
					</td>
					<td><input type = "button" value = "&gt;" /><br/>
						<input type = "button" value = "&lt;" />
					</td>
					<td><select name = "respuestas" id = "respuestas">
						</select>
					</td>
				</tr>	
			</table> 
		</td>
		<td><input type = "button" value = "A" />
			<input type = "button" value = "E" />
		</td>
	</tr>

</table>
