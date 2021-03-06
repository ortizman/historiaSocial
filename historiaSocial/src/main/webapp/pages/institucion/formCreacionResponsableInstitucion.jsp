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
<title>Formulario de alta - Nuevo Responsable De Instituci&oacute;n</title>
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

<c:if test="${!empty idPaciente}">
<script type="text/javascript">
$(function(){
	$( "#dialog" ).dialog({
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
</head>

<body>
<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
	
		<div id="dialog" style="display: none;">
		  <p>Primero debe registrar un ingreso antes de agregar un responsable de instituci&aacute;n</p>
		</div>	

		<div id="pagina">
			<jsp:include page="../../header.jsp">
			    <jsp:param value="false" name="conMenu"/>
    		</jsp:include>  
 			<div class="buttonReturn"><a href="responsableInstitucion.action" > Volver </a></div>
    		
			<div id="tabs" style="font-size: 14px">
			    <ul>
			        <li><a href="#fragment-1"><span>Carga de Responsables De Instituciones</span></a></li>
				<s:set name="jspMenu" value="menu"/>
				
			    </ul>
			<s:actionerror/> 
			<form	
			<s:if test="oper == 'edit'">	    
				 action="modificarResponsableInstitucion.action"
			</s:if>
			<s:elseif test="oper == 'create'">
				action="salvarResponsableInstitucion.action"
			</s:elseif>
			<s:else>
				action="salvarResponsableInstitucion.action"
			</s:else>
			class="leftLabel" id="practica" method="post" name="Carga de Practica" target="_parent">
			    <div id="fragment-1">
				<div>
				<p>Nombre: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="institutionResponsible.name" type="text" value="<s:property value="institutionResponsible.name"/>" />
				<p>Apellido: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="institutionResponsible.lastname" type="text" value="<s:property value="institutionResponsible.lastname"/>" />
				<p>E-mail: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="institutionResponsible.email" type="text" value="<s:property value="institutionResponsible.email"/>" />
				<p>Tel&eacute;fono: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="institutionResponsible.phoneNumbers" type="text" value="<s:property value="institutionResponsible.phoneNumbers"/>" />
				
				<s:if test="oper == 'edit'">	    
					<input name="institutionResponsible.id" type="hidden" value="<s:property value="institutionResponsible.id"/>"/>
				</s:if>			
					
				</div>
			    </div>
			    <hr />
			    
			    <s:if test="oper == 'edit' || oper == 'create' || oper == ''">
					<p style="text-align: center;">
					<input name="submit" type="submit" value="Aceptar" />&nbsp; <input name="Cancelar" type="button" value="Cancelar" onclick="$(location).attr('href','responsableInstitucion.action'); return false;"/>
					</p>
				</s:if>
			</form>
			
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