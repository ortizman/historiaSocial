<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<title>Modificar Datos Personales del Paciente</title>
<sj:head locale="es" jqueryui="true" jquerytheme="redmond"/>
</head>
<body>
<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="login.jsp"/>
	<%} else { %>
		<div id="pagina">
			<jsp:include page="header.jsp"/>

			<div class="subMenu">
			<div class="subMenuTop"></div>
			<div class="subMenuBot">
				<div class="subMenuElem"> <s:property value="#session.nombre"/> </div>
				<div class="subMenuSelectedMod"><a href="formModificarDatosPersonalesPaciente?idPaciente=<s:property value="idPaciente"/>"> Modificar Datos Personales </a> </div>
				<div class="subMenuElem"><a href="formModificarHistoriaSocialPaciente?idPaciente=<s:property value="idPaciente"/>"> Modificar Historia Social </a> </div>
				<div class="subMenuElem"><a href="abmHistoriasClinicasPaciente?idPaciente=<s:property value="idPaciente"/>"> ABM Historias Cl&#237;nicas </a> </div>
				<div class="subMenuElem"><a href="abmPracticas?idPaciente=<s:property value="idPaciente"/>"> ABM Pr&#225;cticas </a></div>
			</div>
			</div>
			
			<div class="subMenu2">
			<div class="subMenuTop2"></div>
			<div class="subMenuBot2">
				
				<div class="subMenuSelected"><a href="formModificarDatosPersonalesPaciente?idPaciente=<s:property value="idPaciente"/>">Datos Personales </a> </div>
				<div class="subMenuElem"><a href="formModificarViviendaPaciente?idPaciente=<s:property value="idPaciente"/>"> Vivienda </a> </div>
			</div>
			</div>
			<div id="cuerpoPacDatos">
			<div id="formulario">
			<div  class="encabezadoForm"><s:label>Modificar Datos Personales</s:label></div>
			<s:form action="modificarDatosPersonalesPaciente" class="formAgregar">
				<s:hidden name="idPaciente" />
				<s:hidden name="idD" />
				<s:hidden name="idV" />
					<div class="campo"><s:textfield name="nombres" label="Nombres" maxlength="30"/></div>
					<div class="campo"><s:textfield name="apellidos" label="Apellidos" maxlength="30"/></div>
					<div class="campo"><s:textfield name="documento" label="Documento" maxlength="20"/></div>
					<sj:datepicker 
				      id="fechaNacimiento" 
				      name="fechaNacimiento"
				      displayFormat="dd/mm/yy" 
				      maxDate="today" 
				      label="Fecha de Nacimiento " 
				      buttonImage="images/calendar-icon.gif" 
				      buttonImageOnly="true"
		      			/>
		      		<s:label value="(dd/mm/yyyy)"></s:label>
	      		<s:reset value="Borrar Campos"/>
				<s:submit value="Modificar"/>
			
			</s:form>
			</div>
			
			<a href="modificarPaciente?id=<s:property value="idPaciente"/>"> Volver </a>
			</div>
			<jsp:include page="footer.jsp"/>
		</div>
	<%} %>
</body>
</html>