package common;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class DBCon {
	private final static String ID ="root";
	private final static String PWD ="r1r2r3";
	private final static String URL ="jdbc:mariadb://localhost:3307/ezen";
	private final static String DRIVER_NAME ="org.mariadb.jdbc.Driver";
	
	static {
		
		try {
			Class.forName(DRIVER_NAME);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getCon() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, ID, PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		System.out.println(getCon());
	}
}
