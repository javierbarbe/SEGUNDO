import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Seguir2 {
	Scanner sc = new Scanner(System.in);
	Conectar proceso;
	String consulta;
	private String usu = "root", pas = "despliegue", base = "base1";
	ArrayList <String> lista = new ArrayList<String>();

	public Seguir2() {

		// TODO Auto-generated constructor stub
		// System.out.println("Introduzca usuario");
		// String usu = sc.next();
		// System.out.println("Introduzca contraseña");
		// String pas = sc.next();
		// System.out.println("Introduzca bbdd");
		// String base = sc.next();
		proceso = new Conectar(usu, pas, base);
		con = proceso.getConnection();
		if (con == null) {
			System.out.println("CONEXION IMPOSIBLE");
		} else {
			System.out.println("CONEXION CORRECTA");
			//
			inserta();
			confirmar();

		}

		proceso.cerrar(con);
	} // fin de constructor x defecto

	private void crearTabla(String tabla){
		consulta= "CREATE TABLE "+tabla +" (";
	}
	private ArrayList aniadeCampos(String campo){
		lista.add(campo);
	return lista;
	}
	
	private void inserta() {
		Statement st;
		System.out.println("introduce nombre a agregar");
		String nombre = sc.next();

		System.out.println("introduce apellido a agregar");
		String apellido = sc.next();

		System.out.println("introduce numero a agregar");
		int numero = sc.nextInt();

		System.out.println("introduce edad a agregar");
		int edad = sc.nextInt();

		try {
			st = con.createStatement();
			consulta = "INSERT INTO UNO (nom,num,ape,edad) VALUES ('" + nombre + "', " + numero + ", '" + apellido
					+ "'," + edad + ")";
			int i = st.executeUpdate(consulta);
			System.out.println("creadas " + i + " tuplas");
		} catch (SQLException e) {
			System.out.println("error al crear el statement");
			proceso.errores(e, consulta);
			// System.out.println(e.getMessage());
			// e.printStackTrace();
		}

	} // fin de inserta

	private void confirmar() {
		String respuesta = "";
		try {
			if (respuesta.equalsIgnoreCase("S")) {

				con.commit();

			} else {

				con.rollback();

			}
		} catch (SQLException e) {
			System.out.println("error al hacer commit/rollback");
			System.out.println(e.getMessage());
		}

	}

	// atributos
	private Connection con;
}