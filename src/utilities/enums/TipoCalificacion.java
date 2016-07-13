package utilities.enums;

public enum TipoCalificacion {
	
	
	NUMERICO0A20("Del 0 al 20",0,20,1),
	NUMERICO1A20("Del 1 al 20",1,20,2),
	NUMERICO0A10("Del 0 al 10",0,10,3),
	NUMERICO1A10("Del 1 al 10",1,10,4),
	LETRAS("De A a F","A","F",5);
	
	private String nombre;
	private Object minimo;
	private Object maximo;
	private Integer id;
	
	private TipoCalificacion(String nombre,Object minimo,Object maximo,Integer id) {
		this.nombre = nombre;
		this.minimo = minimo;
		this.maximo = maximo;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Object getMinimo() {
		return minimo;
	}

	public void setMinimo(Object minimo) {
		this.minimo = minimo;
	}

	public Object getMaximo() {
		return maximo;
	}

	public void setMaximo(Object maximo) {
		this.maximo = maximo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
