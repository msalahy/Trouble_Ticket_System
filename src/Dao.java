/*
 *This class is mainly working to connect us with the Database. 
 *
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

	// Code database URL
	static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/tickets?autoReconnect=true&useSSL=false";
	// Database credentials
	static final String USER = "fp411", PASS = "411";

	public Connection connect() throws SQLException {

		return DriverManager.getConnection(DB_URL, USER, PASS);

	}
}
