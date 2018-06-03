<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<script type="text/javascript" src="script/script.js"></script>
<script src="script/jquery-ui.js"></script>

<sj:head jqueryui="true"/>
<sj:head locale="es" jqueryui="true" jquerytheme="redmond"/>
<title>ABM Instituciones</title>
</head>
<body>
	<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
		<div id="pagina">
			<jsp:include page="../../header.jsp"/>

			<s:if test="mensaje != null">
				<sj:dialog id="mydialog1" title="Notificación">
     				<s:property value="mensaje"/>
    			</sj:dialog>
    		</s:if>	
    		<jsp:include page="../../subMenu.jsp"> 
    			<jsp:param value="institucion" name="abm"/>
    		</jsp:include>
			<div id="cuerpo">
				<div id="formularioInstCercanas">
							<h3 style="font-family: sans-serif;">Instituciones Cercanas por paciente</h3>
					
							<s:form action="findInstitutions">
								<s:fielderror />
								<s:hidden name="id" id="pacienteId"  /> 
 								
								<s:textfield label="Paciente" name="query" id="paciente"  value="%{nombrePaciente}" />
								 
								<s:select label="Distancia" name="delta" list="%{map}"/> 
								<s:submit value="buscar" />
							</s:form>
					</div>
					
			</div>

			<script type="text/javascript" src="script/jquery.autocomplete.min.js"></script>
			<script type="text/javascript">
			$('#paciente').autocomplete({
				serviceUrl : 'pacienteAutoComplete',
				onSelect : function(suggestion){
					$("#pacienteId").val(suggestion.data);
				},

				autoSelectFirst: true,
				
				minChars: 3,

				deferRequestBy: 300
			
			});
			</script>
			<jsp:include page="../../footer.jsp"/>
		</div>
	<% } %>
</body>
</html>