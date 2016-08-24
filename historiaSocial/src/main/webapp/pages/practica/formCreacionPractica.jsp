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
 <script src="script/formGrupoConv.js"></script> 
<script src="ckeditor/ckeditor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de alta - Nueva Pr&aacute;ctica</title>
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
});


$(function(){
	$("select:first").focus();
	$("li[class*='ui-state-default ui-corner-top']").click(function(){
		$("#"+$(this).attr("aria-controls")).find("textarea:first").focus();
		$("#"+$(this).attr("aria-controls")).find("input:first").focus();
	});
});
</script>

<c:if test="${tratamientoNoActivo && !empty idPaciente}">
<script type="text/javascript">
$(function(){
	$("#dialog").dialog({
        modal: true,
        resizable: false,
        title:"Mensaje de notificaci&oacute;n",
        width: 450,
        buttons: [
            {
	        	text: "Registrar ingreso", 
	        	click: function() {
	        		window.location.href = "registrarIngreso.action?idPaciente="+${idPaciente};	
	        		$(this).dialog("close"); 
	        	} 
        	},
            {
	        	text: "Volver", 
	        	click: function() {
	        			$(this).dialog("close"); 
	        	} 
        	}]

    });
});
</script>
</c:if>

<style type="text/css">

form label {
	width: 17%;
	float: left;
}

</style>

</head>
<body>
<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
	
		<div id="dialog" style="display: none;">
		  <p>Primero debe registrar un ingreso antes de agregar una pr&aacute;ctica</p>
		</div>	

		<div id="pagina">
			<jsp:include page="../../header.jsp">
			    <jsp:param value="false" name="conMenu"/>
    		</jsp:include>  
 			<div class="buttonReturn"><a href="abmPracticas.action" > Volver </a></div>
    		
			<div id="tabs" style="font-size: 14px">
			    <ul>
			        <li><a href="#fragment-1"><span>Carga de Pr&aacute;cticas</span></a></li>
				<s:set name="jspMenu" value="menu"/>
				
			    </ul>
			<s:actionerror/> 
			<form	
			<s:if test="oper == 'edit'">	    
				 action="modificarPractica.action"
			</s:if>
			<s:elseif test="oper == 'create'">
				action="salvarPractica.action"
			</s:elseif>
			<s:else>
				action="salvarPractica.action"
			</s:else>
			class="leftLabel" id="practica" method="post" name="Carga de Practica" target="_parent">
			    <div id="fragment-1">
				<div>
				<p><s:select 
					label="Profesional" 
					list="profesionales" 
					name="practica.profesional.id" 
					listKey="id"
       				listValue="nombreCompleto" 
					value='%{practica.profesional.{id}}'/></p>
				<p>
				
					<label title="Introdusca las priemas letras del apellidos del paciente" for="query">Paciente: </label>
					<input title="Introdusca las priemas letras del apellidos del paciente" type="text" name="query" id="paciente" size="35" value="${nombrePaciente}"/>
					
				<input name="idPaciente" type="hidden" id="pacienteId" value="${idPaciente}"/>
				
<%-- 				<s:select  --%>
<%-- 					label="Paciente"  --%>
<%-- 					id ="paciente" --%>
<%-- 					list="pacientes"  --%>
<%-- 					name="idPaciente"  --%>
<%-- 					listKey="id" --%>
<%--        				listValue="nombreCompleto"  --%>
<%-- 					value='%{practica.historiaSocial.paciente.{id}}'/> --%>
				
				</p>					
				<p>
				<label for="practica.fechaCarga">Fecha de Carga:</label>
				<input name="practica.fechaCarga" type="text" value="<s:property value="practica.fechaCarga"/>" class="datepicker" />
				
				<p>
				<label for="practica.fechaPractica">Fecha de Pr&aacute;ctica:</label>
				<input maxlength="250" name="practica.fechaPractica" value="<s:property value="practica.fechaPractica"/>" type="text" class="datepicker" /></p>
				<p><s:select 
					label="Tipo de Práctica" 
					list="tipoDePracticas" 
					name="practica.tipoPractica.id" 
					listKey="id"
       				listValue="codigo"
					value='%{practica.tipoPractica.{id}}'/></p>
				<p><s:select 
					label="Tipo de Problemática" 
					list="problematicas" 
					name="practica.tipoProblematica.id" 
					listKey="id"
       				listValue="codigo"
					value='%{practica.tipoProblematica.{id}}'/></p>	
				<p>Detalle:<textarea id="practica.detalle" name="practica.detalle" class="ckeditor"><s:property value="practica.detalle"/></textarea>
				</p>
				<s:if test="oper == 'edit'">	    
					<input name="practica.id" type="hidden" value="<s:property value="practica.id"/>"/>
					<input name="practica.tratamiento.id" type="hidden" value="<s:property value="practica.tratamiento.id"/>"/>
				</s:if>				
				</div>
			    </div>
			    <hr />
			    
			    <s:if test="oper == 'edit' || oper == 'create' || oper == ''">
					<p style="text-align: center;">
					<input name="submit" type="submit" value="Aceptar" />&nbsp; <input name="Cancelar" type="button" value="cancelar" onclick="$(location).attr('href','abmPracticas.action'); return false;"/>
					</p>
				</s:if>
			</form>
			
			</div>
			 
			<script type="text/javascript" src="script/jquery.autocomplete.min.js"></script>
			<script>
			$( "#tabs" ).tabs();
			$(".datepicker").datepicker({
				dateFormat: 'dd/mm/yy',
				changeMonth: true,
				changeYear: true,
				showOn: 'button',
				buttonImage: 'images/16x16/Calendar.png',
				firstDay: 1,
			});
			$(".datepicker").each(function( index ) {
				  $(this).datepicker("setDate", $(this).val());
			});
			$(".datepicker").datepicker($.datepicker.regional['es']); 

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
	
	
	<%} %>
</body>
</html>