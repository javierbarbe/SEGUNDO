package graficas;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Seguir2 {
 private String usu="root", pas="despliegue", base="base1";
	public Seguir2() {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated constructor stub
//		System.out.println("Introduzca usuario");
//		usu = sc.next();
//		System.out.println("Introduzca contraseña");
//		pas = sc.next();
//		System.out.println("Introduzca bbdd");
//		base = sc.next();
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
