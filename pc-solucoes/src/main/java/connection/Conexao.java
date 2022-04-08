package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static String url = "jdbc:mysql://localhost:3306/cursojsp";
	private static String user = "root";
	private static String password = "admin123";
	private static Connection connection = null;

	public Conexao() {
		conectarBd();
	}

	static {
		conectarBd();
	}

	private static void conectarBd() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		return connection;
	}

}
