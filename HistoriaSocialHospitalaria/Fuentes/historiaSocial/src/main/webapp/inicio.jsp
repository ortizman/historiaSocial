
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="struts/js/base/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/> 
<title>Página principal</title>
<link rel="Icono" href="images/icono_ludovica.ico"/>
<script type="text/javascript" src="script/notifit/notifIt.js"> </script>
<link rel="stylesheet" type="text/css" href="css/notifIt.css" />
</head>
<body>
	<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
		<div id="pagina">
			<jsp:include page="header.jsp"/>
			
			
			
			<div id="cuerpo">
				<div id="bienvenido"><h1>Bienvenido <s:property value="#session.profesionalLogin.nombre"/></h1>
				<img src="images/ludovica_sola.gif"/></div>
			</div>
			<jsp:include page="footer.jsp"/>
		</div>
	<% } %>
</body>
<script type="text/javascript">
	/*notif({
	  msg: "<b>Correcto!:</b> Ingreso satisfactoriamente al sistema",
	  type: "success",
	  position: "center",
	  height: 47,
	  opacity: 0.92
	  
	});*/
</script>
</html>