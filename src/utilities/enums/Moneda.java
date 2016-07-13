package utilities.enums;

public enum Moneda {
	
	BOLIVAR("Bol�var",1),
	DOLAR("D�lar",2);
	
	private String nombre;
	private Integer id;
	
	private Moneda(String nombre,Integer id) {
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
