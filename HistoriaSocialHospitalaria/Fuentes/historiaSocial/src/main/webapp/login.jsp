<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<title><s:text name="login.title"/></title>
</head>
<body>
	<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<div id="pagina">
			<jsp:include page="header.jsp"/>
			
			<div class="menu">
			<div class="menuTop"></div>
				<div class="menuBot">
				<div class="menuSelected"> <a href="#"><s:text name="login.title"/></a> </div> 
			</div>
			</div>
			<div id="cuerpo">
				<div id="formularioLogin">
					<s:form action="iniciarSesion">
						<s:textfield name="usuario" key="login.username" maxlength="20"/>
						<s:password name="pass" key="login.password" maxlength="20"/>
						<s:submit key="login.submit"/>
					</s:form>
				</div>
			</div>
			
			<jsp:include page="footer.jsp"/>
		</div>
	<% }
	else {%>
		<jsp:forward page="inicio.jsp"></jsp:forward>
	<% } %>
</body>
</html>