package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static final String HOST = "localhost";
	public static final String DB = "java_jsp_database_2";
	public static final String TABLE_NAME = "products";
	public static final String USE_DB_TABLE = DB + "." + TABLE_NAME;
	
	public static final String CLASSNAME = "com.mysql.jdbc.Driver";
	
	public static final String URL = "jdbc:mysql://" + HOST + ":3306/";
	public static final String URL_SSL = URL + "?autoReconnect=true&useSSL=false";
	
	public static final String USERNAME = "root";
	private static final String PASSWORD = "rootAccessBeChjKjen@2002@";
	

	public static Connection getConnection() {
		try {
			Class.forName(CLASSNAME);
			Connection connection = DriverManager.getConnection(URL_SSL, USERNAME, PASSWORD);
			System.out.println("CONNECTED");
			return connection;
		}catch (ClassNotFoundException|SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public static void CloseConnection(Connection connection) {
		try {
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void RollbackConnection(Connection connection) {
		try {
			connection.rollback();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
