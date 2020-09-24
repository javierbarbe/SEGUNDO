import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

	private  String bd ="base1";
	private  String user="root";
	private  String password = "despliegue";
	private  String host= "localhost";
	private  String server= "jdbc:mysql://"+ host+"/"+ bd;
	private  Connection myConexion ;
	
	
	public Conectar() {
		
		
		}
	public Conectar (String user, String password , String bbdd){
		this.user=user;
		this.password=password;
		this.bd= bbdd;
		this.server= "jdbc:mysql://"+ host+"/"+ bd;
	}
	public void cerrar (Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error al salir en el metodo cerrar");
		}
	}
	public  Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println(server);
			myConexion = DriverManager.getConnection(server, user, password);
			System.out.println("conexion correcta");
			System.out.println("el user es: "+user);
			System.out.println("el password es: "+password);
			System.out.println("la bdd es : "+bd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			System.out.println("ERROR EN LA CONEXION EN LA BBDD");
		System.out.println(e.getMessage());
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error cargando el driver MYSQL");
		}
		
	
		return myConexion;
	}
	
	

}
