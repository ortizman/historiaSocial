<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<script type="text/javascript" src="script/script.js"></script>
<sj:head locale="es" jqueryui="true" jquerytheme="redmond"/>
<title>ABM Historias Clínicas</title>
</head>
<body>
	<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
		<div id="pagina">
			<jsp:include page="header.jsp"/>

			<div class="subMenu">
			<div class="subMenuTop"></div>
			<div class="subMenuBot">
				<div class="subMenuElem"><s:property value="#session.nombre"/> </div>
				<div class="subMenuElem"><a href="formModificarDatosPersonalesPaciente?idPaciente=<s:property value="idPaciente"/>"> Modificar Datos Personales </a> </div>
				<div class="subMenuElem"><a href="formModificarHistoriaSocialPaciente?idPaciente=<s:property value="idPaciente"/>"> Modificar Historia Social </a> </div>
				<div class="subMenuSelected"><a href="abmHistoriasClinicasPaciente?idPaciente=<s:property value="idPaciente"/>"> ABM Historias Cl&#237;nicas </a> </div>
				<div class="subMenuElem"><a href="abmPracticas?idPaciente=<s:property value="idPaciente"/>"> ABM Pr&#225;cticas </a></div>
			</div>
			</div>
			<s:if test="mensaje != null">
				<sj:dialog id="mydialog1" title="Notificación">
     				<s:property value="mensaje"/>
    			</sj:dialog>
    		</s:if>
			<div id="cuerpoPac">
			<table id="tabla">
				<caption>
					Historias Clínicas del paciente <s:property value="#session.nombre"/>
				</caption>
				<caption>
						<s:set name="cPags" value="cantPags"/>
						<s:set name="a" value="pagActual"/>
						<%
						Integer cant = Integer.parseInt(pageContext.getAttribute("cPags").toString());
						Integer actual = Integer.parseInt(pageContext.getAttribute("a").toString());
						for(int i = 1; i<=cant; i++){
							if(i==actual)			
						{ %>
							<div id="pageSelected"><a href="abmHistoriasClinicasPaciente?pagActual=<%= i %>&idPaciente=<s:property value="idPaciente"/>"><%= i %> </a></div>
						<%} else {%>
							<div id="page"><a href="abmHistoriasClinicasPaciente?pagActual=<%= i %>&idPaciente=<s:property value="idPaciente"/>"><%= i %> </a></div>
						<% } }%>
					</caption>
					<tr>
				   		<td class="tdAzulEncabezado">
				   			<div id="botonAgregar">
				   				<img src="images/Add-2.png" width="16" height="16" alt=""/><a href="formAgregarHistoriaClinicaPaciente?idPaciente=<s:property value="idPaciente"/>"> Agregar </a>
				   			</div>
				   		</td>
				   		<td class="tdAzulEncabezado"></td>
				   		<td class="tdAzulEncabezado"></td>
				   		<td class="tdAzulEncabezado"></td>		
			   		</tr>
				<tr>
					<th>Número de HC</th>
					<th>Sala</th>
					<th class="tdAzulBotones"></th>
					<th class="tdAzulBotones"></th>
				</tr>
				<s:iterator value="historiasClinicas" status="historiaClinica">
					<tr>
						<td><s:property value="numero"/></td>
						<td><s:property value="sala"/></td>
						<td class="tdAzulBotones"><a href="formModificarHistoriaClinicaPaciente?id=<s:property value="id"/>&idPaciente=<s:property value="idPaciente"/>"> <img src="images/Pencil.png" width="16" height="16" alt=""/> </a></td>
						<td class="tdAzulBotones"><a href="eliminarHistoriaClinicaPaciente?id=<s:property value="id"/>&idPaciente=<s:property value="idPaciente"/>&pagActual=<s:property value="pagActual"/>" onclick="javascript:return confirmar('la historia clinica')"> <img src="images/Delete.png" width="16" height="16" alt=""/> </a></td>
					</tr>
				</s:iterator>
			</table>
			<div id="Volver">
			<a href="modificarPaciente?id=<s:property value="idPaciente"/>"> Volver </a>
			</div>
			</div>
			<jsp:include page="footer.jsp"/>
		</div>
	<%} %>
</body>
</html>