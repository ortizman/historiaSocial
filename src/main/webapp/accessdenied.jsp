<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<title>Login</title>
</head>
<body>
	<% String user = (String)session.getAttribute("user"); 
		if (user != null){ %>
		<div id="pagina">
			<jsp:include page="header.jsp"/>
			
			<div id="cuerpo">
				<div id="formularioLogin">
					<h2>Acceso Denegado.</h2>
					<h4>El usuario con el que se encuentra logueado actualmente al sistema, no cuenta con los permisos suficientes para acceder a esta funcinalidad.</h4>
					
					
				</div>
			</div>
			
			<jsp:include page="footer.jsp"/>
		</div>
	<% }
	else {%>
		<jsp:forward page="login.jsp"></jsp:forward>
	<% } %>
</body>
</html>