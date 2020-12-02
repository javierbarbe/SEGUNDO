package beans;


import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

public class ModeloOpBBDDArticulos {
	private SessionFactory sf;
	private Session sesion;
	private Transaction tx;
	private static ArrayList<Articulos> art = new ArrayList<>();

	public ArrayList<Articulos> getArt() {
		return art;
	}
	private void recargaSesion(){
		sesion = sf.openSession();
		tx= sesion.beginTransaction();
	}

	public Operaciones addArticuloOperaciones(Articulos art) {
		try {
			sesion = sf.openSession();
			tx = sesion.beginTransaction();
			//Tiempodevoluciones ti =(Tiempodevoluciones) sesion.createSQLQuery("select tiempo from tiempodevoluciones");
			Calendar fecha = new GregorianCalendar();
	
			Operaciones insertaEnOperaciones = new Operaciones();
			// System.out.println(art.getCodigo()+
			// art.getConcepto()+art.getPrecio());
			insertaEnOperaciones.setCodigo(art.getCodigo());
			insertaEnOperaciones.setDnivendedor(art.getDni());
			insertaEnOperaciones.setFecha(fecha.getTime());
			insertaEnOperaciones.setPrecio(art.getPrecio());
			
			insertaEnOperaciones.setConcepto(art.getConcepto());
			insertaEnOperaciones.setCanceable(true);
			sesion.save(insertaEnOperaciones);
			tx.commit();
			return insertaEnOperaciones;
		} catch (Exception e) {
			tx.rollback();
			System.out.println("erroers al registrar en operaciones  " + e.getMessage());
		} finally {
			sesion.close();
		}
		return null;
	}

	public boolean eliminaOperacion (Operaciones opeAEliminar){
		try{
			System.out.println("eliminando operacion de la tabla Operaciones");
			sesion = sf.openSession();
			tx=sesion.beginTransaction();
			sesion.delete(opeAEliminar);
			tx.commit();
			System.out.println("sin problems al eliminar la operacion de la tabla Operaciones");
			return true;
			
		}catch(Exception e){
			System.out.println("error al eliminar la  operacion de la tabla operaciones"+ e.getMessage());
		}finally{
			sesion.close();
		}
		
		return false;
	}
	
	public boolean insertarArticulo(Articulos art) {
		try {
			recargaSesion();
			sesion.save(art);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			System.out.println("error al insertar articulo" + e.getCause());
		} finally {
			sesion.close();
		}
		return false;
	}

	public ModeloOpBBDDArticulos(SessionFactory sf) {
		this.sf = sf;
		this.sesion = sf.openSession();
		tx = sesion.beginTransaction();
		cargaTablaProductos();
	}

	private void cargaTablaProductos() {

		art = (ArrayList<Articulos>) sesion.createCriteria(Articulos.class).list();
		if (art != null) {
			for (Articulos ar : art) {
				System.out.println(ar);
			}
			System.out.println("cargados todos los articulos");
		}
	}

	public ArrayList<Operaciones> getListaOperacionesUsuario(Usuarios user){
		try{
			recargaSesion();
			Operaciones aux = new Operaciones();
			// puede ser que sea vendedor?? 
			aux.setDnicomprador(user.getDni());
			ArrayList <Operaciones> listaOps = (ArrayList<Operaciones>)sesion.createCriteria(Operaciones.class)
												.add(Example.create(aux)).list();
			return listaOps;
			
		}catch(Exception e){
			System.out.println("error devolviendo la lista de Operaciones filtrada por usuario "+e.getMessage());
			return null;
		}finally{
			sesion.close();
		}
	}
	
	public boolean vendArticulo (Articulos art){
		try{
			System.out.println("eliminando articulo de la bbdd");
		sesion= sf.openSession();
		tx = sesion.beginTransaction();
		sesion.delete(art);
		
		tx.commit();
		System.out.println("todo ha ido bien al eliminar metodo eliminaArticulo");
		return true;
		}catch (Exception e){
			tx.rollback();
			System.out.println(e.getMessage()+ " error eliminando articulos");
		}
		finally{
			sesion.close();
			
		}
		return false;
	}
public Operaciones recuperaOperacionPorCodigoDeOperacion (String codigo){
	try{
		recargaSesion();
		int cod= Integer.parseInt(codigo);
		Operaciones aux = new Operaciones();
		aux.setCodigo(Integer.parseInt(codigo));
		System.out.println(aux.getCodigo());
		ArrayList<Operaciones> encontrada = (ArrayList<Operaciones>)sesion.createCriteria(Operaciones.class).add(Example.create(aux)).list();
		for ( int i=0; i<encontrada.size(); i++){
			if(cod== encontrada.get(i).getCodigo()){
				aux= encontrada.get(i);
				return aux;
			}
		}
			return null;
		}catch(Exception e){
			System.out.println("problemas devolviendo operacion por su codigo de operacion");
			return null;
		}finally{
			sesion.close();
		}
}
		public Operaciones recuperaOperacionPorArticulo(Articulos art){
		try{
			recargaSesion();
			
			Operaciones aux = new Operaciones();
			aux.setConcepto(art.getConcepto());
			
			ArrayList<Operaciones> listaOperaciones = (ArrayList<Operaciones>)sesion.createCriteria(Operaciones.class).add(Example.create(aux)).list();
			for (Operaciones o : listaOperaciones){
				int cod1= o.getCodigo();
				
				System.out.println("el codigo de la operacion es: "+cod1);
				//System.out.println("el codigo del articulo es: "+cod2);
//				if(cod1==cod2){
//					return o;
//				}
			}return null;
		}catch(Exception e){
			
			System.out.println(e.getMessage()+ "error recogiendo operacion a traves de articulo");
			
			return null;
		}finally{
			sesion.close();
		}
	}

	public Articulos recuperaArticuloPorCodigoOperacion(String codigo){
		
		try{
			recargaSesion();
			int cod= Integer.parseInt(codigo);
			Operaciones aux = new Operaciones();
			aux.setCodigo(Integer.parseInt(codigo));
			System.out.println(aux.getCodigo());
			ArrayList<Operaciones> encontrada = (ArrayList<Operaciones>)sesion.createCriteria(Operaciones.class).add(Example.create(aux)).list();
			for ( int i=0; i<encontrada.size(); i++){
				if(cod== encontrada.get(i).getCodigo()){
					aux= encontrada.get(i);
				}
			}
			Articulos devolucion = new Articulos ();
			Calendar fecha = new GregorianCalendar();
			devolucion.setConcepto(aux.getConcepto());
			devolucion.setDni(aux.getDnivendedor());
			devolucion.setPrecio(aux.getPrecio());
			
			return devolucion;
			
		}catch (Exception e){
			System.out.println("errores al recuperar el articulo a traves del codigo de operacion");
		}
		return null;
	}
	
	public Articulos  recuperaArticuloPorCodigo(String codigo){
		try{
			recargaSesion();
			System.out.println("este codigo hace referencia a la operacion... de compra");
			//Operaciones oCompra = recuperaOperacionPorCodigoOperacion(codigo);
		 Articulos aux = new Articulos();
		 aux.setCodigo(Integer.parseInt(codigo));
		 int cod2 = Integer.parseInt(codigo);
		 System.out.println(aux.getCodigo()+ " el codigo dl artirclo elegido");
		ArrayList< Articulos> devolucion = (ArrayList<Articulos>) sesion.createCriteria(Articulos.class).add(Example.create(aux)).list();
		Articulos devolu = (Articulos) sesion.createCriteria(Articulos.class).add(Example.create(aux)).uniqueResult();
		if(devolu!=null){
			return devolu;
		}
		for (Articulos as: devolucion){
			int cod1 = as.getCodigo();
			if(cod1== cod2){
				System.out.println(as.getConcepto()+ " , "+ as.getCodigo()+ " , precio "+ as.getPrecio());
				return as;
			}
		}
		
		return null;
		
	
		}catch (Exception e){
			System.out.println("error al devolver articulo buscado por codigo "+e.getMessage());
			return null;
		}finally{
			sesion.close();
		}
	}
	
	public Articulos getArticulo(Articulos artiEliminar) {
		try {
			sesion= sf.openSession();
			List<Articulos> devuelto = (List<Articulos>) sesion.createCriteria(Articulos.class).add(Example.create(artiEliminar)).list();
			for (Articulos ar : devuelto){
				int codig = ar.getCodigo();
				int cod2 =artiEliminar.getCodigo();
				if (codig== cod2){
				
				return ar;
				}
			System.out.println("el articulo seleccionado a traves del codigo es: "
					+ ar.getPrecio()+ " "+ ar.getConcepto());
			}
			return null;
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " error al recuperar  articulo por codigo");
		}finally{
			sesion.close();
		}
		return null;
	}

	public Operaciones registraVentaOperaciones(Articulos art, String dni) {
		try {
			sesion = sf.openSession();
			OperacionesBBDD opbd = new OperacionesBBDD(sf);
			tx = sesion.beginTransaction();
			Calendar fecha = new GregorianCalendar();
			Operaciones insertaEnOperaciones = new Operaciones();
			insertaEnOperaciones.setDnicomprador(dni);
			insertaEnOperaciones.setCodigo(art.getCodigo());
			insertaEnOperaciones.setDnivendedor(art.getDni());
			insertaEnOperaciones.setFecha(fecha.getTime());
			insertaEnOperaciones.setPrecio(art.getPrecio());
			System.out.println(opbd.getTiempo()+ " es el tiempo addicional");
			insertaEnOperaciones.setTiempodevolucion(opbd.getTiempo());
			insertaEnOperaciones.setConcepto(art.getConcepto());
			insertaEnOperaciones.setCanceable(true);
			sesion.save(insertaEnOperaciones);
			tx.commit();
			return insertaEnOperaciones;
		} catch (Exception e) {
			System.out.println("erroers al registrar en operaciones  " + e.getMessage());
		} finally {
			sesion.close();
		}
return null;
	}

	public Operaciones registraVentaOperaciones(Articulos art, Usuarios user2) {
		try {
			sesion = sf.openSession();
			OperacionesBBDD opbd = new OperacionesBBDD(sf);
			tx = sesion.beginTransaction();
			Calendar fecha = new GregorianCalendar();
			Operaciones insertaEnOperaciones = new Operaciones();
			
			insertaEnOperaciones.setDnicomprador(user2.getDni());
			insertaEnOperaciones.setCodigo(art.getCodigo());
			insertaEnOperaciones.setDnivendedor(art.getDni());
			//insertaEnOperaciones.setUsuarios(devuelveElUserDelArticulo(art));
			insertaEnOperaciones.setFecha(fecha.getTime());
			insertaEnOperaciones.setPrecio(art.getPrecio());
			System.out.println(opbd.getTiempo()+ " es el tiempo addicional");
			insertaEnOperaciones.setTiempodevolucion(opbd.getTiempo());
			insertaEnOperaciones.setConcepto(art.getConcepto());
			insertaEnOperaciones.setCanceable(true);
			sesion.save(insertaEnOperaciones);
			tx.commit();
			return insertaEnOperaciones;
		} catch (Exception e) {
			System.out.println("erroers al registrar en operaciones  " + e.getMessage());
		} finally {
			sesion.close();
		}
return null;
	}
	
	public Operaciones addArticuloOperaciones(Articulos art, Usuarios user2) {
		try {
			sesion = sf.openSession();
			tx = sesion.beginTransaction();
			//Tiempodevoluciones ti =(Tiempodevoluciones) sesion.createSQLQuery("select tiempo from tiempodevoluciones");
			Calendar fecha = new GregorianCalendar();
	
			Operaciones insertaEnOperaciones = new Operaciones();
			// System.out.println(art.getCodigo()+
			// art.getConcepto()+art.getPrecio());
			insertaEnOperaciones.setCodigo(art.getCodigo());
			insertaEnOperaciones.setDnivendedor(art.getDni());
			//insertaEnOperaciones.setDnivendedor(art.getDni());
			insertaEnOperaciones.setFecha(fecha.getTime());
			insertaEnOperaciones.setPrecio(art.getPrecio());
			
			insertaEnOperaciones.setConcepto(art.getConcepto());
			insertaEnOperaciones.setCanceable(true);
			sesion.save(insertaEnOperaciones);
			tx.commit();
			return insertaEnOperaciones;
		} catch (Exception e) {
			tx.rollback();
			System.out.println("erroers al registrar en operaciones  " + e.getMessage());
		} finally {
			sesion.close();
		}
		return null;
	}
	
	public boolean eliminaArticulo (Articulos art){
		try{
			System.out.println("eliminando articulo de la bbdd");
		sesion= sf.openSession();
		tx = sesion.beginTransaction();
		sesion.delete(art);
		
		tx.commit();
		System.out.println("todo ha ido bien al eliminar metodo eliminaArticulo");
		return true;
		}catch (Exception e){
			tx.rollback();
			System.out.println(e.getMessage()+ " error eliminando articulos");
		}
		finally{
			sesion.close();
			
		}
		return false;
	}
}
