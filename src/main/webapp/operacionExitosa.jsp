<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<title>Operaci&#243;n Exitosa</title>
</head>
<body>
	<div id="pagina">
		<jsp:include page="header.jsp" />

		<div id="cuerpo">
		<div id="mensaje"><h1>La operaci&#243;n ha sido exitosa</h1></div>
		<a href="<s:property value="#session.origen"/>"> Volver </a>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>