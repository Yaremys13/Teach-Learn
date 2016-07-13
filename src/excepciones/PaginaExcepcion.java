package excepciones;

import javax.swing.JOptionPane;

public class PaginaExcepcion extends Exception {

	private static final long serialVersionUID = 1L;

	public PaginaExcepcion() {
		 
	}

	public PaginaExcepcion(String arg0) {
		//super(arg0);
		JOptionPane.showMessageDialog(null, arg0);
	}

	public PaginaExcepcion(Throwable arg0) {
		super(arg0);
		 
	}

	public PaginaExcepcion(String arg0, Throwable arg1) {
		super(arg0, arg1);
		 
	}

	public PaginaExcepcion(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		 
	}

}
