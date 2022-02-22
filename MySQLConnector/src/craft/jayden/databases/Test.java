package craft.jayden.databases;

public class Test {
	
	public static void main(String[] args) throws Exception {
		Connector con = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306", "ysoserious", "jayden0522");
		con.getConnection();
	}

}
