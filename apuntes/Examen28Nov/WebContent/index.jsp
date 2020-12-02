<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.Usuarios"%>
	  <%@ taglib uri="/WEB-INF/etiquetas/registro.tld" prefix="reg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>examen NOV</title>
</head>
	<jsp:useBean id="user" class="beans.Usuarios" scope="request" />
		<jsp:setProperty property="*" name="user" />
		
		<%  if(request.getParameter("login") != null && request.getParameter("clave")!=null){
		System.out.println("Somos distintos de null");
		System.out.println(request.getParameter("login"));
		System.out.println(request.getParameter("clave"));
		System.out.println("==========================="); %>
		<jsp:forward page="patternServlet1?op=acceso"/>
		<%
	}
	%>
	<% session.invalidate();
		session= null ;%>
<body>
	<div style="background-color: light-blue;">
		<reg:registra sess="<%= session %>"></reg:registra>
	</div>
	
	<div>
		<h1>FORMULARIO ENTRADA REGISTRO</h1>
	
		<form method="post">
			Login: <input type="text" name="login" value="aa"> <br> 
			Pass: <input type="text" name="clave" value="aa"> <br>
			<input type="submit" value="Entra">
		</form>
	</div>
</body>
</html>