<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.List"%>
<%@page import="beans.Operaciones"%>
<%@page import="beans.OperacionesBBDD"%>
<%@page import="beans.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Usuarios us =(Usuarios) session.getAttribute("userLogueado");
	List<Operaciones> listaOp= (List<Operaciones>)session.getAttribute("operacioneAceptadas");
	Calendar fecha = new GregorianCalendar();
	
	
	%>
	
LISTA OPERACIONES ACEPTADAS DE <%= us.getLogin() %>
<form method="post" action="articulos?ope=eliminarVarios">
<table border="4"><th></th><th>Nombre Articulo</th><th>DNI vendedor</th><th>Fecha</th><th>Hora</th><th>Puede devolverse</th>
<% int cantidadEliminar = 0;
for (Operaciones op : listaOp){
	if(op.getTiempodevolucion()!=null){
	boolean cancelable ;
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(op.getFecha());
	calendar.add(Calendar.DAY_OF_YEAR, op.getTiempodevolucion());
	String style = "";
	String check= "";
	String nombre = "articulosEliminar"+cantidadEliminar;
	//Date f= op.getFecha().getDate()+ op.getTiempodevolucion();
	if((calendar).compareTo(fecha)!=-1){
		System.out.println("la fecha es "+op.getFecha().getTime());//= fecha.getTime().getDate()){
		style= "style= color:blue;";
		cancelable= true; 
	}else{
		style= "style= color:green;";
		cancelable=false;
		check="disabled";
	}
	out.print("<tr "+style+"><td><input type='checkbox' "+check+" name="+nombre+" value="+op.getCodigo()
	+"></td><td>"+ nombre+" "+op.getConcepto()+"</td><td>"+op.getDnivendedor()+"</td>"
	+"<td>"+op.getFecha().getDate()+"/"+ op.getFecha().getMonth()+"/"+(op.getFecha().getYear()+1900)
			+"</td>"+
				"<td>"+op.getFecha().getHours()+":"+op.getFecha().getMinutes()+"</td>"+
			
				"<td>"+cancelable+"</td></tr>");
	cantidadEliminar++;
	}}
	%>

</table>
<input type="submit"   value="Anular pedidos">
<a href="operaciones.jsp">Vuelve atras</a>
</form>
</body>
</html>