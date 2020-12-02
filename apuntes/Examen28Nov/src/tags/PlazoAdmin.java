package tags;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.hibernate.Query;

import beans.Articulos;


public class PlazoAdmin extends SimpleTagSupport{
	
	private HttpSession sess;
public void setSess(HttpSession sess) {
		this.sess = sess;
	}
@Override
public void doTag() throws JspException, IOException {
	
	JspWriter out = this.getJspContext().getOut();
	
	out.println("Dias maximos de demora "+sess.getAttribute("tiempoMod"));
	
}
}
