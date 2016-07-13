package utilities.enums;

public enum TipoRespuestaPregunta {
	
	CERRADA ("Cerrada",1),
	ABIERTA_OPCIONES_SIMPLE ("Seleccion Simple",2),
	ABIERTA_OPCIONES_MULTIPLE ("Seleccion Múltiple",3),
	ABIERTA_ANALISIS ("Teórica",4);
	
	private String nombre;
	private Integer id;
	
	private TipoRespuestaPregunta(String nombre,Integer id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
