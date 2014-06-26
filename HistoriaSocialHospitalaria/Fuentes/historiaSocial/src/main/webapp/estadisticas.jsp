<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<title>Estadísticas</title>
<sj:head locale="es" jqueryui="true" jquerytheme="redmond"/>
<style type="text/css">
form label {
	float: none;
}
</style>
</head>
<body>
<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
		<jsp:include page="header.jsp"/>
		<div id="pagina">
			
			<div id="cuerpo">
			<div id="cuerpoGrafico">
				<div id="menuEstadistica">
				<s:form  action="estadisticaCalcular">
					
					<s:radio list="#{'problematica':'Tipo de problemática','practica':'Tipo de práctica'}" name="estadistica" label="Estadistica por"></s:radio>
					<s:submit value="Calcular"/>
				</s:form>
				</div>
				<div id="grafico">
				<sjc:chart id="chartPie"  cssStyle="width: 600px; height: 400px;"  pie="true"  pieLabel="true" onHoverTopics="chartHover" >
					<s:iterator value="porcentajes" status="stat">
							<sjc:chartData id="%{key}"  label="%{key}" data="%{value}" 	/>
    				</s:iterator>
    			</sjc:chart>
				</div>
				<div id="pieGrafico">
				</div>
			</div>	
		</div>
		<jsp:include page="footer.jsp"/>
		</div>
	<%} %>
</body>
</html>