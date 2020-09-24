import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Seguir2 {

	public Seguir2() {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated constructor stub
		System.out.println("Introduzca usuario");
		String usu = sc.next();
		System.out.println("Introduzca contraseña");
		String pas = sc.next();
		System.out.println("Introduzca bbdd");
		String base = sc.next();
		Conectar proceso = new Conectar(usu, pas, base);
		con = proceso.getConnection();
		if (con == null) {
			System.out.println("CONEXION IMPOSIBLE");
		} else {
			System.out.println("CONEXION CORRECTA");
			proceso.cerrar(con);

		}
	}

	// atributos
	private Connection con;
}
