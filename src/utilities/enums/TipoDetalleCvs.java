package utilities.enums;

public enum TipoDetalleCvs {
	

	ESTUDIOS("Estudios Realizados",1), 
	EXPERIENCIA("Experiencia Laboral", 2), 
	CURSOS("Cursos Realizados", 3), 
	ACTITUDES("Actitudes adicionales",4), 
	OTROS("Otros", 5);
	
	private String nombre;
	private Integer id;
	
	TipoDetalleCvs(String nombre, Integer id)
	{	this.nombre = nombre;
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
