<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:useBean id="usuarioAInsertar" class="beans.Usuarios" scope="request"/>
<jsp:setProperty property="*" name="usuarioAInsertar"/>

<%
if (request.getParameter("login") !=null &&
    request.getParameter("clave")!= null &    
    request.getParameter("dni") !=null ) {
	%>
	
	
	<jsp:forward page="patternServlet1?op=registrarenBBDD"/>
	
	<%
}

%>

<body>


<form method="post" >
	
Nombre Usuario
<input type="text" name="login" ><br>

Password
<input type="text" name="clave" ><br>

NIF
<input type="text" name="dni" ><br>

<input type="submit" value="registrate">
</form>
</body>
</html>