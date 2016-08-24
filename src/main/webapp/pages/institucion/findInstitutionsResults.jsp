<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="google.maps" tagdir="/WEB-INF/tags/googlemaps"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
var changeMapCenter = function(){};
</script>
<title>Busqueda de Instituciones</title>
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
							<h3 style="font-family: sans-serif;">Instituciones Cercanas por paciente</h3>
							<google.maps:Script />
							<div id="results" class="results" style="display: block;">
							    <div id="search-form" class="form" style="display: block;">
							        <s:form action="findInstitutions.action">
							            <s:select name="delta" list="%{map}"/>
							            <s:hidden name="id" value="%{pacient.id}"></s:hidden> 
							            <s:submit value="buscar" />
							        </s:form>
							    </div>
								<div id="results-list" class="list">
									<ul class="results-list">
									    <c:if test="${empty institutions}">
									      El paciente no tiene instituciones tan cercanas
									     </c:if>
									     <c:if test="${!empty institutions}">
										     <s:iterator value="institutions">
										 	   <li><a onclick="changeMapCenter(new google.maps.LatLng(<s:property value="location.latitude" />,<s:property value="location.longitude" />))" href="#" title="Click para ubicar la institución" ><s:property value="nombre" /></a></li>
										     </s:iterator>
										</c:if>
									</ul>
								</div>
								<div id="results-map" class="map" style="width:700px;height:500px; text-align: center;"></div>
								<div style="clear:both"></div>
								
								
								<s:set var="pacient" value="pacient" scope="request" />
							    <google.maps:Map container_id="results-map" latitude="${pacient.location.latitude}" longitude="${pacient.location.longitude}" zoom="13">
							        <google.maps:Marker index="0" longitude="${pacient.location.longitude}" latitude="${pacient.location.latitude}" title="${pacient.nombres}" icon="images/pacient.png">
							                <h3>Paciente <s:property value="pacient.nombres" /> <s:property value="pacient.apellidos" /> </h3>
							                <p>
							                    <strong>Dirección:</strong> <s:property value="pacient.location.street" /> n° <s:property value="pacient.location.number" /> , <s:property value="pacient.location.city" /> , <s:property value="pacient.location.province" /> 
							                </p>
							        </google.maps:Marker>
							        <s:iterator value="institutions" var="institution" status="st">
							            <s:set var="institution" value="institution" scope="request" />
							            <s:set var="index" value="#st.index" scope="request" />
							            <google.maps:Marker index="${index+1}" longitude="${institution.location.longitude}" latitude="${institution.location.latitude}" title="${institution.nombre}" icon="images/hospital.png">
							                <h3><s:property value="nombre" /></h3>
							                <p>
							                    <s:property value="detail" />
							                </p>
							                <p>
							                    <s:property value="location.street"/> <s:property value="location.number"/>, <s:property value="location.city"/>, <s:property value="location.province"/>
							                </p>
							                <p>
							                    <strong>e-mail:</strong><s:property value="email" />
							                    <strong>tel:</strong><s:property value="phoneNumbers" />
							                </p>
							                <p>
							                    <strong>Responsable:</strong> <s:property value="responsible.lastname"/>, <s:property value="responsible.name"/>                    
							                </p>
							            </google.maps:Marker>            
							        </s:iterator>
							        changeMapCenter = function (latLng){
							            map.setCenter(latLng);
							        }
							    </google.maps:Map>	
							</div>					

				
			</div>
			
			<jsp:include page="../../footer.jsp"/>
		</div>
	<% } %>
</body>
</html>