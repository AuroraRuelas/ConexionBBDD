package conn;
import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
	//Atributos de la clase
	//private String bd = "scrum";
	private String bd = "pruebasBBDDpedro";
	private String login = "root";
	private String pwd = "root";
	private String url = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private Connection conexion;
	//Constructor que crea la conexión
	public conexion () {
	try {
	Class.forName("com.mysql.jdbc.Driver");
	conexion=DriverManager.getConnection(url,login,pwd);
	System.out.println (" - Conexión con MySQL establecida -");
	conexion.close();
	} catch (Exception e) {
	System.out.println (" – Error de Conexión con MySQL -");
	e.printStackTrace();
	}
	}
	public static void main (String [] args){
	new conexion();
	}
	
}