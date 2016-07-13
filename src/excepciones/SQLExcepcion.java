package excepciones;

import javax.swing.JOptionPane;

public class SQLExcepcion extends Exception {


	private static final long serialVersionUID = 1L;


	public SQLExcepcion(String arg0) {
		super(arg0);
		JOptionPane.showMessageDialog(null, arg0);
	}

	public SQLExcepcion(Throwable arg0) {
		super(arg0);
		
	}

	public SQLExcepcion(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public SQLExcepcion(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

}
