<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script/script.js"></script>
<script src="ckeditor/ckeditor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de alta - Nueva Instituci&oacute;n</title>
<link rel="stylesheet" href="jquery-ui.css">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<!-- <link href="structure.css" rel="stylesheet"> 
este estilo rompe todo el codigo <link href="form.css" rel="stylesheet">  -->
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="false" jquerytheme="mytheme" customBasepath="%{ctx}template/themes"/>
<script src="script/jquery-ui.js"></script>
<script src="script/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript">
$(function() {
	var op = "<%= (String)request.getAttribute("oper") %>";
	if(op == 'view'){
		$(":input").attr('readonly', 'readonly');
	}
	$("br").remove();
});


	

$(function(){
	
	$("select:first").focus();
	$("li[class*='ui-state-default ui-corner-top']").click(function(){
		$("#"+$(this).attr("aria-controls")).find("textarea:first").focus();
		$("#"+$(this).attr("aria-controls")).find("input:first").focus();
	});
});
</script>
<link href="form.css" rel="stylesheet">
<style type="text/css">
.leftLabel td {
}
</style>

<script type="text/javascript">
$(function(){
$("#formInstitucion").submit(function(event){
	$("input[name$='nombre'] , [name$='number'], [name$='street']").each(function(i, e){
		if ($(e).val() == ""){
			alert("Alguno de los campos obligatorios no fue completado. Por favor, revise el formulario y vuelva a intentarlo ");
			event.preventDefault();
			return false;
		}
	});
});
});
</script>
</head>
<body>

<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
	
		<div id="dialog" style="display: none;">
		  <p>Primero debe registrar un ingreso antes de agregar una Instituci&oacute;n</p>
		</div>	

		<div id="pagina">
			<jsp:include page="../../header.jsp">
			    <jsp:param value="false" name="conMenu"/>
    		</jsp:include>  
 			<div class="buttonReturn"><a href="abmInstituciones.action" > Volver </a></div>
    		
			<div id="tabs" style="font-size: 14px">
			    <ul>
			        <li><a href="#fragment-1"><span>Carga de Instituciones</span></a></li>
				<s:set name="jspMenu" value="menu"/>
				
			    </ul>
			<s:actionerror/> 
<!-- 			<form	 -->
<%-- 			<s:if test="oper == 'edit'">	     --%>
<!-- 				 action="modificarInstitucion.action" -->
<%-- 			</s:if> --%>
<%-- 			<s:elseif test="oper == 'create'"> --%>
<!-- 				action="salvarInstitucion.action" -->
<%-- 			</s:elseif> --%>
<%-- 			<s:else> --%>
<!-- 				action="salvarInstitucion.action" -->
<%-- 			</s:else> --%>
			<s:form action="%{action}" method="POST" id="formInstitucion" cssClass="leftLabel"  name="Alta Institucion" target="_parent" theme="css_xhtml">
			    <div id="fragment-1">
					<div>
						<s:hidden name="institucion.id" key="id" value="%{institucion.id}"/>
					
						<s:textfield name="institucion.nombre" label="Nombre" size="35" value="%{institucion.nombre}" required="true"/>
						<s:textfield name="institucion.detail" label="Detalle" size="35" value="%{institucion.detail}"/>
						<s:textfield name="institucion.email" label="Email" key="email" size="35" value="%{institucion.email}"/>
						<s:textfield name="institucion.telefono" label="Telefono" size="35" value="%{institucion.telefono}"/>
						<s:select label="Responsable" name="institucion.responsible.id" list="%{responsablesInstituciones}" listKey="id" value="%{institucion.responsible.{id}}" ></s:select>	
						<s:select label="Tipo" name="institucion.type.id" list="%{tiposInstituciones}" value="%{institucion.type.{id}}" listKey="id" listValue="name" ></s:select>					
						
						<s:hidden name="institucion.location.id" value="%{institucion.location.id}"/>	
						<s:textfield name="institucion.location.province" label="Provincia" key="provincia" size="35" value="%{institucion.location.province}"/>
						<s:textfield name="institucion.location.city" label="Ciudad" key="ciudad" size="35" value="%{institucion.location.city}"/>
						<s:textfield name="institucion.location.street" label="Calle" key="calle" size="35" value="%{institucion.location.street}" required="true"/>
						<s:textfield name="institucion.location.number" label="Altura" key="altura" size="35" value="%{institucion.location.number}" required="true"/>
						<s:textfield name="institucion.calleX" label="Entre " key="entre" size="35" value="%{institucion.calleX}"/> 
						<s:textfield name="institucion.calleY" label="Y " key="Y" size="35" value="%{institucion.calleY}"/>
						
					</div>
			    </div>
			    <hr />
			    
			    <s:if test="action != 'view'">
					<p style="text-align: center;">
					<input name="submit" type="submit" value="Aceptar" />&nbsp; <input name="Cancelar" type="button" value="cancelar" onclick="$(location).attr('href','abmInstituciones.action'); return false;"/>
					</p>
				</s:if>
			</s:form>
			
			</div>
			 
			<script>
			$( "#tabs" ).tabs();
			$(".datepicker").datepicker({
				dateFormat: 'dd/mm/yy',
				changeMonth: true,
				changeYear: true,
				showOn: 'button',
				buttonImage: '<%=request.getContextPath() %>/images/16x16/Calendar.png',
				firstDay: 1,
			});
			$(".datepicker").each(function( index ) {
				  $(this).datepicker("setDate", $(this).val());
			});
			$(".datepicker").datepicker($.datepicker.regional['es']); 
			
			</script>
    		
    	<jsp:include page="../../footer.jsp"/>		
    	</div>
	
	
	<%} %>
</body>
</html>