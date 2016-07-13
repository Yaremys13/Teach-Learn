package utilities.enums;

public enum TiposDocumento {
	
	CI("Cédula de identidad",1),
	ID("Identification card",2),
	LICENCIA("Licencia de conducir",3),
	PASAPORTE("Pasaporte",4);
	
	private String nombre;
	private Integer id;
	
	private TiposDocumento(String nombre,Integer id) {
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
