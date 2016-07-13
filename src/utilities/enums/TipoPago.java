package utilities.enums;

public enum TipoPago {
	
	GENERAL("General",1),
	CUOTA("Cuota",2);
	
	private String nombre;
	private Integer id;
	
	private TipoPago(String nombre, Integer id) {
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
