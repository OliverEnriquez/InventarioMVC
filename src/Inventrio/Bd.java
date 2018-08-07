package Inventrio;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Bd {
	private String maquina = "localhost";
	private String usuario = "root";
	private String clave = "root";
	private int puerto = 3306;
	private String servidor = "";
	private static Connection conexion = null;
	
	//Constructor
	public Bd(String baseDatos) {
		this.servidor = "jdbc:mysql://" + this.maquina + ":" +
						this.puerto + "/" + baseDatos;
		//Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.err.println("Error al registrar diver");
			System.exit(0);
		}
		
		//Conexion
		try {
			conexion = DriverManager.getConnection(this.servidor, this.usuario, this.clave);
		} catch (SQLException ex) {
			System.err.println("Error al conectar con el servidor");
			System.out.println(ex.getMessage());
			System.out.println(this.servidor);
			System.exit(0);
		}
		System.out.println("Conectado a " + baseDatos);
	}
	
	public static Connection getConexion() {
		return conexion;
	}
}

