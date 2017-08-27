package farto.cleva.guilherme.framework.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class DatabaseConnectionFactory {

	// Singleton
	private static DatabaseConnectionFactory INSTANCE = null;

	// JDBC URL
	private static final String URL_JDBC = "jdbc:postgresql://localhost/academico";

	// JDBC Driver
	private static final String DRIVER = "org.postgresql.Driver";

	// Database User
	private static final String USER = "postgres";

	// Database Password
	private static final String PWD = "post@123";

	private DatabaseConnectionFactory() throws Exception {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			throw e;
		}
	}

	public static DatabaseConnectionFactory getInstance() throws Exception {
		if (INSTANCE == null) {
			INSTANCE = new DatabaseConnectionFactory();
		}

		return INSTANCE;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL_JDBC, USER, PWD);
		} catch (Exception e) {
			throw e;
		}

		return conn;
	}

	public static void testConnection() {
		try {
			Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

			StringBuilder sql = new StringBuilder(" SELECT NOW() AGORA ");

			PreparedStatement stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Date agora = new Date(rs.getTimestamp("AGORA").getTime());

				System.out.println(agora);
			}

			rs.close();

			stmt.close();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testConnection();
	}

}
