function cambiarEstilo(elemento)
{	elemento.css("border", "1px solid");
	elemento.css("border-color", "red");
	elemento.css("background", "#F6E3CE");				
}	

function validarElementoVacio(nombre,divError)
{	ret = 0;
	if($("#"+nombre).val() == "")
	{	cambiarEstilo($("#"+nombre));
		$("#" + divError).css("display","block");
		ret = 1;
	}
	return ret;
}

function validarElementoSeleccionado(nombre,divError)
{	ret = 0;
	if($("#"+nombre).val() == 0)
	{	cambiarEstilo($("#"+nombre));
		$("#" + divError).css("display","block");
		ret = 1;
	}
	return ret;
}