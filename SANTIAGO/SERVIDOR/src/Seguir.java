import java.sql.Connection;
import java.sql.SQLException;

public class Seguir {

	public Seguir() {
		// TODO Auto-generated constructor stub
		Conectar miconexion = new Conectar();
		con = miconexion.getConnection();
		if(con!= null){
			System.out.println("Conexion creada en seguir");
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("error al cerrar la conexion");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("fallo en la conexion en seguir");
			System.out.println("sin conexion");
		}
		
	}
 // atributos
	private Connection con;
}
