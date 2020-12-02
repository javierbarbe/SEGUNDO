package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.Articulos;
import beans.ModeloOpBBDDArticulos;
import beans.Operaciones;
import beans.OperacionesBBDD;
import beans.Usuarios;

/**
 * Servlet implementation class ServletExamen
 */
@WebServlet("/ServletExamen")
public class ServletExamen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private SessionFactory sf;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		try{
		 sf = new Configuration().configure().buildSessionFactory();
		 
		}catch(Exception e){
			System.out.println("error al crear sessionfactory "+e.getMessage());
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExamen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opeElegida = request.getParameter("op");
		OperacionesBBDD op = new OperacionesBBDD(sf);
		ModeloOpBBDDArticulos opArt = new ModeloOpBBDDArticulos(sf);
		HttpSession sesion = request.getSession();
		
		System.out.println(opeElegida);
		switch (opeElegida) {
		case "registra": response.sendRedirect("registro.jsp");
			
			break;
		case "eliminar": Usuarios userAEliminar = (Usuarios)sesion.getAttribute("userLogueado");
			op.eliminarUsuario(userAEliminar);
			System.out.println("usuraio eliminado putamadre");
			break;
		case "deshacerUltima":  System.out.println("entrando en deshacer ultima opcion");
								Operaciones opeAEliminar = (Operaciones)sesion.getAttribute("ultimaOperacion");
								Articulos ultimoArticulo = (Articulos)sesion.getAttribute("ultArt");
								System.out.println(opeAEliminar.getConcepto()+ " el articulo de la ultima op antes de eliminarla");
								ArrayList <Articulos> listaArticulos= (ArrayList<Articulos>)sesion.getAttribute("tablaArticulos");
								
								if(opeAEliminar.getDnicomprador()==null){  // es una oferta no una compra, ELIMINAR DE LA TABLA ARTICULOS
									
									opArt.eliminaArticulo(ultimoArticulo);
									listaArticulos.remove(listaArticulos.size()-1);
									
								}else{ // DEVOLVER A LA TABLA ARTICULOS ES UNA COMPRA
									System.out.println(ultimoArticulo.getConcepto()+" en el servlet viendo que es lo ultimo que he comprado ");
									opArt.insertarArticulo(ultimoArticulo);
									ArrayList<Operaciones> listaOps= (ArrayList<Operaciones>) sesion.getAttribute("operacioneAceptadas"); //, op.getListaOperaciones(contrastado));
									listaOps.remove(listaOps.size()-1);
									listaArticulos.add(ultimoArticulo);
									// eliminar DE la bbdd OPERACIONES ... PUES LO HEMOS CANCELADO
									opArt.eliminaOperacion(opeAEliminar);
									
								}
								sesion.setAttribute("tablaArticulos", listaArticulos);
								sesion.removeAttribute("ultimaOperacion");//, null);
								RequestDispatcher rd = request.getRequestDispatcher("operaciones.jsp");
								rd.forward(request, response);
//								ArrayList<Operaciones> listaOps= (ArrayList<Operaciones>)sesion.getAttribute("operacioneAceptadas");
//								Operaciones ultima =listaOps.get(listaOps.size()-1);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opeElegida = request.getParameter("op");
		System.out.println(opeElegida);
		HttpSession sesion = request.getSession();
		OperacionesBBDD op = new OperacionesBBDD(sf);
		sesion.setAttribute("tiempoMod", op.getTiempo());
		switch (opeElegida) {
		case "modifTiempo" :  int tiempoNuevo = Integer.parseInt(request.getParameter("tiempo"));
								op.modifTiempo(tiempoNuevo);
								sesion.setAttribute("tiempoMod", tiempoNuevo);
								RequestDispatcher rd1 = request.getRequestDispatcher("pantallaAdmin.jsp");
								rd1.forward(request, response);
			break;
			
	
		
		case "registrarenBBDD": System.out.println("estoy intentando insertar");
				Usuarios user =(Usuarios) request.getAttribute("usuarioAInsertar");
				if(user.getClave()!=null && user.getDni()!=null && user.getLogin()!=null){
					op.insertaUsuario(user);
					System.out.println("usuario insertado correctamente");
					response.sendRedirect("index.jsp");
				}
			break;	
		case "acceso" :  // COMPROBAR CREDENCIALES
			Usuarios user2 = (Usuarios) request.getAttribute("user");
			if(op.checkCredienciales(user2)!=null){
				System.out.println("has accedido correctamente CRACK");
				sesion.setAttribute("sf", sf);
				sesion.setAttribute("userLogueado", op.checkCredienciales(user2));
				// si el usuario es el administrador vamos al panel de administrar
				if(user2.getLogin().equals("admin")){
					RequestDispatcher rd2 = request.getRequestDispatcher("pantallaAdmin.jsp");
					rd2.forward(request, response);
				}else{
					Usuarios contrastado = op.checkCredienciales(user2);
				ModeloOpBBDDArticulos artic= new ModeloOpBBDDArticulos(sf);
				for(Operaciones ope : op.getListaOperaciones(contrastado)){
					System.out.println(ope.getConcepto()+ " "+ ope.getPrecio()+" €"+ ope.getCodigo());
				}
				sesion.setAttribute("operacioneAceptadas", op.getListaOperaciones(contrastado));
				sesion.setAttribute("tablaArticulos", artic.getArt());
				RequestDispatcher rd = request.getRequestDispatcher("operaciones.jsp");
				rd.forward(request, response);
				}
			}else{
				response.sendRedirect("registro.jsp");
			}
			break;
			
		
		default:
			break;
		}
		
		
		
	}

}
