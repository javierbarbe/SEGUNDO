<%@page import="beans.Articulos"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="beans.OperacionesBBDD"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/etiquetas/saludo.tld" prefix="mitag"%>
<%@ taglib uri="/WEB-INF/etiquetas/UltProductoPlazo.tld" prefix="plazo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>operaciones</title>
</head>
<body>

<jsp:useBean id="articuloAdd" class="beans.Articulos" scope="request"/>
<jsp:setProperty property="*" name="articuloAdd"/>
 <%  if(request.getParameter("elegido")!=null || 
 				request.getParameter("concepto")!=null || request.getParameter("precio")!=null
	 	){
	 %>
	
<jsp:forward page="articulos"/>
 <%} %> 

	<mitag:saludar sess="<%=session%>"></mitag:saludar>
	<div style="margin-left: 60%;">
	<plazo:prodYPlazo sess="<%=session%>"/>
	 </div>
	<form method="post" action="articulos">
		aqui hay que empotra la tabla de productos
		<%
		List<Articulos> lista = (List<Articulos>) session.getAttribute("tablaArticulos");
	%>
		<table border="2">
			<th>Producto</th>
			<th>Precio</th>
			<th>DNI DEL VENDEDOR</th>
			<%
				for (Articulos ar : lista) {
					out.println(
							"<tr  ><td > <input type='radio' name='elegido'  value= " + ar.getCodigo() + ">" + ar.getConcepto()
									+ "</td>" + "<td style=text-align:center;> " + ar.getPrecio() + " euros</td>" + "<td>" + ar.getDni() + "</td></tr>");
				}
			%>
			<tr>
			<td>
			
				<input type="submit" value="OfrecerProducto">
				
				<input type="submit" value="ComprarProducto">
				
			</td>
			<td>Nombre <input type="text" name="concepto"><br>
				Precio <input type="text" name="precio">
			</td>
		</table>
	
	</form>


</body>
</html>