package conn;

import java.sql.*;

public class TestConexionW {
	// Atributos de la clase
//	private String bd = "pruebasBBDDpedro";
	private String bd = "glosario";

	private String login = "root";
	private String pwd = "root";
	private String url = "jdbc:mysql://localhost/" + bd;
	private Connection conexion;
	Connection conn;
	Statement stmt;
	ResultSet resul;
	PreparedStatement pstm;

	// Constructor que crea la conexión
	public TestConexionW() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			conexion = DriverManager.getConnection(url, "root", "");
			// conexion.close();
			// Quitamos esta instrucción: conexion.close();
			System.out.println(" - Conexión con MySQL establecida -");
			// System.out.println("id_poder - Poder ");
		} catch (SQLException e) {
			System.out.println("Error General");
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error al conectarse a la BD");

		} catch (Exception e) {
			// System.out.println (" – Error de Conexión con MySQL -");
			e.printStackTrace();
		}
	}

	public void Consulta(String query, int columna) {
		try {
			stmt = conexion.createStatement();
			resul = stmt.executeQuery(query);
			while (resul.next()) {
				System.out.print(resul.getString(1));
				System.out.println("   " + resul.getString(2));
				// System.out.println(" - " +resul.getString(3));

			}
			resul.close();
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void Consulta2(String query, int cod, int columna) {
		try {
			pstm = conexion.prepareStatement(query);
			pstm.setInt(1, cod);
			ResultSet rset = pstm.executeQuery();
			while (rset.next())
				System.out.println(rset.getString(columna));

			rset.close();
			pstm.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void insertarDatos(String nombre, String apellido, int anios, String genero) throws SQLException {
		// Statement stmt;
		try {
			stmt = conexion.createStatement();
			int r = 0;
			String data = null;
			data = "ISERT INTO pruebasBBDDpedro.datosp1 VALUES(" + nombre + ",'" + apellido + "','" + "'," + anios
					+ "'," + genero + "')";
			r = stmt.executeUpdate(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertadrDatosStatment(String nombre, String apellido, int anios, String genero) {

		try {
			pstm = conexion.prepareStatement("Insert Into pruebasBBDDPedro.datosP1 values(?,?,?,?)");
			pstm.setString(1, nombre);
			pstm.setString(2, apellido);
			pstm.setInt(3, anios);
			pstm.setString(4, genero);
			int r = 0;
			r = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int borrarDatos(String apellido) {

		int r = 0;
		try {
			pstm = conexion.prepareStatement("Delete From pruebasBBDDPedro.datosP1 WHERE apellido  = ?");
			// pstm.setString(1, nombre);
			pstm.setString(1, apellido);
			// pstm.setInt(3, anios);
			// pstm.setString(4, genero);

			r = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public int modificaDatos(String nombre, String apellido) {

		int r = 0;
		try {
			pstm = conexion.prepareStatement("UPDATE pruebasBBDDPedro.datosP1 SET nombre =? WHERE apellido  = ?");
			pstm.setString(1, nombre);
			pstm.setString(2, apellido);
			// pstm.setInt(3, anios);
			// pstm.setString(4, genero);

			r = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public void metaDatos() {
		String sql = "Select * from pruebasBBDDPedro.datosP1";

		try {
			pstm = conexion.prepareStatement(sql);
			resul = pstm.executeQuery();
			ResultSetMetaData rsmd = resul.getMetaData();
			int numColumns = rsmd.getColumnCount();
			System.out.println("El numero de columnas es :" + numColumns);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void pintaTabBBDD() {
		String sqll = "Select * from scrum.alumnos";
		PreparedStatement pstm;
		try {
			pstm = conexion.prepareStatement(sqll);
			resul = pstm.executeQuery();
			ResultSetMetaData rsmd = resul.getMetaData();
			int numColumns = rsmd.getColumnCount();
			// System.out.println(rsmd.getColumnName(i));
			for (int i = 1; i <= numColumns; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();
			while (resul.next()) {
				for (int i = 1; i <= numColumns; i++) {

					System.out.print(resul.getString(i) + "\t");

				}
				System.out.println();
			}

			System.out.println("El numero de columnas es :" + numColumns);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void databaseMetaData() {
	
		
		
		try {
			DatabaseMetaData dbmd = conexion.getMetaData();
			System.out.println("URL de la BD" + dbmd.getURL());
			System.out.println("Usuario de la BD" + dbmd.getUserName());
			System.out.println("Nombre del driver de la BD" + dbmd.getDriverName());
			ResultSet tablas = dbmd.getTables("glosario", null, null, null);
			
			  while (tablas.next()) {
				
				System.out.println("Tablas de la BD:" );
			}
			System.out.println(tablas.getString("TABLE_NAME")+ "\t");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void databaseMetaData() {
//	
//		
//		
//		try {
//			DatabaseMetaData dbmd = conexion.getMetaData();
//			System.out.println("URL de la BD" + dbmd.getURL());
//			System.out.println("Usuario de la BD" + dbmd.getUserName());
//			System.out.println("Nombre del driver de la BD" + dbmd.getDriverName());
//			ResultSet tablas = dbmd.getTables("glosario", null, null, null);
//			
//			  while (tablas.next()) {
//				
//				System.out.println("Tablas de la BD:" );
//			}
//			System.out.println(tablas.getString("TABLE_NAME")+ "\t");
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		TestConexionW prueba = new TestConexionW();
		// System.out.println("--Consulta con Prepared Statement--");
		// prueba.Consulta2("SELECT * FROM glosario.poderes WHERE id_poder=?", 6, 2);
		// prueba.insertadrDatosStatment("Aurora", "Ruelas", 18, "F");
		// prueba.insertadrDatosStatment("Karyna", "Gonzalez", 18, "F");
		// prueba.insertadrDatosStatment("Luna", "Lovegood", 18, "F");
		// prueba.insertadrDatosStatment("Ramona", "Flowers", 23, "F");
		// prueba.modificaDatos("Aurora", "Gonzalez");
		// prueba.borrarDatos("Ruelas");
//		 prueba.borrarDatos("Gonzalez");
		// prueba.borrarDatos("Lovegood");
		// prueba.borrarDatos("Flowers");
//		 prueba.Consulta("SELECT * FROM pruebasBBDDPedro.datosP1 ", 2);
		// prueba.metaDatos();
		prueba.pintaTabBBDD();
//		prueba.databaseMetaData();
	}
}
