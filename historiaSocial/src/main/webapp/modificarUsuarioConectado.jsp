<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<title><s:property value="#session.user" />
</title>
</head>
<body>
	<%
		String user = (String) session.getAttribute("user");
		if (user == null) {
	%>
	<jsp:forward page="login.jsp" />
	<%
		} else {
	%>
	<div id="pagina">
		<jsp:include page="header.jsp" />

		<div id="cuerpo">
			<div id="formulario">
				<div class="encabezadoForm">
					<s:label>Modificar Datos Personales</s:label>
				</div>
				<s:form action="modificarUsuarioConectado.action" class="formAgregar">
					<s:hidden name="id" value="%{id}" />
					<s:textfield name="nombre" label="Nombre" maxlength="30" />
					<s:textfield name="apellido" label="Apellido" maxlength="30" />
					<s:textfield name="user" label="Usuario" maxlength="20" />
					<s:password name="pass" label="Contraseña" maxlength="20" tooltip="(No complete este campo si no desea cambiar la contraseña)" />
<%-- 					<s:label --%>
<%-- 						value="(No complete este campo si no desea cambiar la contraseña)"></s:label> --%>
					<s:textfield name="matricula" label="Matricula" maxlength="20" />
					<s:select label="Especialidades" list="especialidades"
						name="especialidad" listValue="especialidad"
						listKey="especialidad" multiple="true" />
					<s:reset value="Borrar Campos" />
					<s:submit value="Modificar" />
				</s:form>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>