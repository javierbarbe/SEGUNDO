package beans;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

public class OperacionesBBDD {

	private Session sesion;
	private Transaction tx;
	private ArrayList<Operaciones> listaOps;
	
	
	public OperacionesBBDD( SessionFactory sf){
		sesion= sf.openSession();
		tx= sesion.beginTransaction();
		
	}
	
	public Usuarios checkCredienciales (Usuarios us){
		
		Usuarios devuelto = (Usuarios)sesion.createCriteria(Usuarios.class).add(Example.create(us)).uniqueResult();
		if(devuelto!=null){
			return devuelto;
		}
		return null;
	}
	
	public boolean eliminarUsuario (Usuarios userAEliminar){ 
		try{
			sesion.delete(userAEliminar);
			tx.commit();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			sesion.close();
		}
	
	}
	
	public boolean insertaUsuario(Usuarios userAInsertar){
		try{
			sesion.save(userAInsertar);
			tx.commit();
			return true; 
		}
		catch(Exception e){
			return false;
		}finally{
			sesion.close();
		}
	
	}
	
	public boolean modifTiempo ( int tiempoNuevo){
		try{
			
		System.out.println("el nuevo ttiempo es "+ tiempoNuevo);
		System.out.println("el tiempo antiguo es "+getTiempo());
		Tiempodevoluciones ti= new Tiempodevoluciones();
		ti.setTiempo(tiempoNuevo);
		Tiempodevoluciones tie = (Tiempodevoluciones)sesion.createCriteria(Tiempodevoluciones.class).add(Example.create(ti)).uniqueResult();
		
		sesion.delete(tie);
		sesion.save(ti);
		tx.commit();
		return true;
//		Query q = sesion.createSQLQuery("update tiempodevoluciones  set tiempo="+tiempoNuevo+" where"
//		+ " tiempo ="+getTiempo() );
//		int result =q.executeUpdate();
//		if(result==1){
//			return true;
//		}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	
	}
	
	public ArrayList<Operaciones> getListaOperaciones(Usuarios us){
		try{
			Operaciones aux = new Operaciones();
			aux.setDnicomprador(us.getDni());
		System.out.println("el usuario es "+ us.getLogin()+ " : "+us.getDni());
		listaOps = (ArrayList<Operaciones>)sesion.createCriteria(Operaciones.class).add(Example.create(aux)).list();
		for (Operaciones o : listaOps){
			System.out.println(o.getConcepto()+" vendedor : "+o.getDnivendedor());
		}
		 return listaOps;
		 }
		catch(Exception e){
			System.out.println(e.getCause()+" erroes al coger la lista de operaciones OperacionesBBDD");
			return null;
		}
	}
	
	public int getTiempo (){
		try{
		Query q = sesion.createSQLQuery("select tiempo from tiempodevoluciones");
		int tiempo = (Integer)(q.uniqueResult());
		return tiempo;
		}catch (Exception e){
			System.out.println("error cogiendo el tiempo de la bbd "+ e.getMessage());
			return -1;
		}
		
	}
}
