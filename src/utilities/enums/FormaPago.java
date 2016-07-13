package utilities.enums;

public enum FormaPago {
	
	EFECTIVO("Efectivo",1),
	DEPOSITO("Deposito",2),
	TRANSFERENCIA("Transferencia",3),
	PAYPAL("PayPal",4),
	OTRO("Otro",5);
	
	private String nombre;
	private Integer id;
	
	private FormaPago(String nombre,Integer id) {
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
