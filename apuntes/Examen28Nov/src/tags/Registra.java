package tags;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Registra extends SimpleTagSupport{

	private HttpSession sess;

	public void setSess(HttpSession sess) {
		this.sess = sess;
	}

	@Override
	public void doTag() throws JspException, IOException {
	JspWriter out = this.getJspContext().getOut();
	out.println("<a href='patternServlet1?op=registra'>Registrarse</a>");
	}
	
}
