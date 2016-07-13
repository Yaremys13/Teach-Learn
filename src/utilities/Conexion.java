package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	static String url = "jdbc:mysql://localhost:3306/teach_learn";
	static String usuario = "root";
	static String password = "1234";
	

	public static Connection conectar()
	{	Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,usuario,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
		}
		return con;
	}

}
