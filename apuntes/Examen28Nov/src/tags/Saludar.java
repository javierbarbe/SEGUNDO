package tags;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import beans.Operaciones;
import beans.Usuarios;

public class Saludar  extends SimpleTagSupport{

	private HttpSession sess;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		Usuarios userLog= (Usuarios)sess.getAttribute("userLogueado");
		out.println("<div>");
		out.println("Hola "+ userLog.getLogin()+"<br>");
		out.println("<a href='listadoOperaciones.jsp' >Listado Pedidos Realizados</a><br>");
		Operaciones ultimaOp =(Operaciones) sess.getAttribute("ultimaOperacion");
		if(ultimaOp!=null){
			out.println("<a href='patternServlet1?op=deshacerUltima' >Deshacer ultima operacion</a><br>");
		}
		out.println("<a href='index.jsp' >Desconectarse</a><br>");
		out.println("<a href='patternServlet1?op=eliminar' >Darse de Baja</a><br>");
		out.println("</div>");
	}

	public void setSess(HttpSession sess) {
		this.sess = sess;
	}
	
}
