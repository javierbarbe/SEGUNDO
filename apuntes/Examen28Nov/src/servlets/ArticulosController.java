package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.Articulos;
import beans.ModeloOpBBDDArticulos;
import beans.Operaciones;
import beans.OperacionesBBDD;
import beans.Usuarios;

/**
 * Servlet implementation class ArticulosController
 */
@WebServlet("/ArticulosController")
public class ArticulosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticulosController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {System.out.println("SOY MARICON");
			String ope= request.getParameter("ope");
			System.out.println(ope+ " es la operacion");
			HttpSession sesion = request.getSession();
			System.out.println("en el dopost");
			SessionFactory sf = (SessionFactory) sesion.getAttribute("sf");
			ModeloOpBBDDArticulos op = new ModeloOpBBDDArticulos(sf);
			OperacionesBBDD opBBDD = new OperacionesBBDD(sf);
			Usuarios user2 = (Usuarios) sesion.getAttribute("userLogueado");
			if(ope!=null){
			switch(ope){
			case "eliminarVarios":  System.out.println("en elimina varios articulos");
									ArrayList <Articulos> artTotales = new ArrayList<Articulos>();
									ArrayList<Operaciones> listaOperaciones = op.getListaOperacionesUsuario(user2);
							
									for (int a= 0; a<listaOperaciones.size();a++){
										System.out.println("articulosEliminar"+a);
										//System.out.println(op.getArt().get(a).getConcepto()+" el articulo elegido para eliminar");
										String codigo = request.getParameter("articulosEliminar"+a);
										
										if(codigo!=null){
											System.out.println(codigo+ " el codigo de la operacion del articulo a eliminar");
											
											Articulos artEliminable=op.recuperaArticuloPorCodigoOperacion(codigo);
											System.out.println(artEliminable.getConcepto()+" el articulo recuperado por dcodigo"
													+ ""+ artEliminable.getCodigo());
											Operaciones opEliminar = op.recuperaOperacionPorCodigoDeOperacion(codigo);
											if(op.eliminaOperacion(opEliminar)){
												System.out.println("operacion eliminada correctamente");
												op.addArticuloOperaciones(artEliminable);
												op.insertarArticulo(artEliminable);
												artTotales = (ArrayList<Articulos>)sesion.getAttribute("tablaArticulos");
												artTotales.add(artEliminable);
												sesion.setAttribute("tablaArticulos", artTotales);
												sesion.removeAttribute("ultimaOperacion");
											}
										}
									}
									sesion.setAttribute("operacioneAceptadas", opBBDD.getListaOperaciones(user2));
									RequestDispatcher rd31 = request.getRequestDispatcher("listadoOperaciones.jsp");
									rd31.forward(request, response);
				break;
			}
			}else{
			
			
			System.out.println(user2.getLogin());
			String codigo = (request.getParameter("elegido"));
			System.out.println(codigo + " es el  codigo ");
			String concepto = request.getParameter("concepto");
			String precio = request.getParameter("precio");
			System.out.println(concepto + " la caja concepto");
			System.out.println(precio + "la caja precio");
			Operaciones ultimaInsertada;
			if (codigo != null && concepto.equals("") && precio.equals("")) {
				System.out.println("comprando articulos");
				Calendar fecha = new GregorianCalendar();
				System.out.println(codigo + " es el cod del artiuclo");
				List<Articulos> listaArticulos = op.getArt();
				// Operaciones insertaEnOperaciones = new Operaciones();

				Articulos artiEliminar = new Articulos();
				artiEliminar.setCodigo(Integer.parseInt(codigo));
				Articulos aux1 = op.getArticulo(artiEliminar);
				System.out.println("dentro de articulocontroller... despues de buscar el articulo a eliminar");
				// REGISTRO EN LA TABLA OPERACIONES
				 //ultimaInsertada =op.registraVentaOperaciones(aux1, user2.getDni());
				 ultimaInsertada =op.registraVentaOperaciones(aux1, user2);
				// ACTUALIZO EN SESION LAS OPERACIONES ACEPTADAS
				sesion.setAttribute("operacioneAceptadas", opBBDD.getListaOperaciones(user2));
				
				System.out.println("eliminando va bien");
				int index = 0;
				for (int i = 0; i < listaArticulos.size(); i++) {
					int codi1 = listaArticulos.get(i).getCodigo();
					int cod2 = aux1.getCodigo();
					if (codi1 == cod2) {
						index = i;
					}

				}
				System.out.println(listaArticulos.indexOf(index));
				listaArticulos.remove((index));
				op.eliminaArticulo(aux1);
				sesion.setAttribute("tablaArticulos", listaArticulos);
				sesion.setAttribute("ultArt", aux1);
				System.out.println("eliminado y redirigiendo");
				
				response.sendRedirect("operaciones.jsp");
			} else {
				System.out.println("estamos ofertando articulos");
				Articulos arti = new Articulos();

				arti.setConcepto(concepto);
				arti.setPrecio(Integer.parseInt(precio));
				arti.setDni(user2.getDni());
				op.insertarArticulo(arti);
				op.getArt().add(arti);

				ultimaInsertada=op.addArticuloOperaciones(arti, user2);
		
				sesion.setAttribute("tablaArticulos", op.getArt());
				sesion.setAttribute("userLogueado", user2);
				sesion.setAttribute("ultArt", arti);

				response.sendRedirect("operaciones.jsp");
				System.out.println(
						"añadiendo productos ... tengo que recoger en un bean!! da problemas... a pelo todo OK ");

			}// despues del IF ELSE
			
			 System.out.println("=====la ultima operacion registrada es=====m");
			 System.out.println(ultimaInsertada.getConcepto());
			 System.out.println("==========================================================");
			sesion.setAttribute("ultimaOperacion", ultimaInsertada);
			}
		} catch (Exception e) {
			System.out.println("error en el dopost del controlador articulos");
			System.out.println(e+" "+e.getMessage() + " "+ e.getLocalizedMessage()+" "+ e.getCause());
		}
	}

}
