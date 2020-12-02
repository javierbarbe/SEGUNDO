<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/etiquetas/saludo.tld" prefix="saludo" %>
    <%@ taglib uri="/WEB-INF/etiquetas/plazoAdmin.tld" prefix="ultProdPlazo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body> 

<saludo:saludar sess="<%=session%>"/>
<div style="margin-left:60%;">

<form action="patternServlet1?op=modifTiempo" method="post">
NUEVO TIEMPO A DEVOLVER
<input type="text" name="tiempo">
<input type="submit" value="Cambiar">
</form>

</body>
</html>