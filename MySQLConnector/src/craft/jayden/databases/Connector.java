package craft.jayden.databases;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
	
	
	private String driver;
	private String url;
	private String username;
	private String password;
	
	public Connector(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	
	public Connection getConnection() throws Exception{
		
		try {
			Class.forName(driver);
			System.out.println("Connecting...");
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
			
		}catch (Exception e) {
			System.out.println("Connection Failed");
			System.out.println(e);
		}
		
		
		return null;
	}

}
