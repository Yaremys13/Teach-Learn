<script>
	$(document).ready ( function ()
	{
		$('#bEntrar').click(function()
		{	if($('login').val() == "")
			{	cambiarEstilo(elemento);
				ok = 1;
			}	
		});
		
	});

</script>
<form id = "fInicio" action = "SIniciarSesion" method = "post">
	Login/Email: <input type = "text" name = "login" id = "login" size = "15" /><br/>
	Password: <input type = "password" name = "password" id = "password" size = "15" /><br/><br/> 
	<input type = "submit" id = "bEntrar" value = "Entrar" />
	<input type = "reset" id = "bReset" value = "Cancelar" />
</form>
