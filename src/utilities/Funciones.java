package utilities;

public class Funciones {
	
	
	/*
	 * Recibe un string de fecha con formato dd/mm/yyyy o dd-mm-yyyy y lo convierte a yyyy-mm-dd
	 */
	public static String formatoFecha(String fecha)
	{	String ret = null;
		if (fecha != null)
		{	ret = fecha.substring(6) + "-" + fecha.substring(3,5) + "-" + fecha.substring(0,2);
			
		}
		return ret;
	}

	/*
	 * Genera un string de verificación aleatorio
	 */
	public static String generarCodigoV()
	{	String ret = "prueba.2016";
	
		return ret;
	}
	
}
