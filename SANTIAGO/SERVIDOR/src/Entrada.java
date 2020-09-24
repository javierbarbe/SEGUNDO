import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Entrada {

	public Entrada() {
		// TODO Auto-generated constructor stub
	}
	
	private  String bd ="base1";
	private  String user="root";
	private  String password = "despliegue";
	private  String host= "localhost";
	private  String server= "jdbc:mysql://"+ host+"/"+bd;
	private  Connection myConexion ;
	
	
	
	public  Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConexion = DriverManager.getConnection(server, user, password);
			System.out.println("conexion correcta");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			System.out.println("ERROR EN LA CONEXION EN LA BBDD");
			//System.out.println(e.getMessage());
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error cargando el driver MYSQL");
		}
		
	
		return myConexion;
	}

}
