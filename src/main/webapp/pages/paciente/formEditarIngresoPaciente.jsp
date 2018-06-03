<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar el ingreso de un paciente</title>
<link rel="stylesheet" href="jquery-ui.css">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<!-- <link href="structure.css" rel="stylesheet"> -->
<link href="form.css" rel="stylesheet">
<style type="text/css">

.wwgrp {
	margin-top: 2%;
}

#wwlbl_formEditarIngreso_motivo{
	padding-bottom: 25px;
}
</style>
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="false" jquerytheme="mytheme"
	customBasepath="%{ctx}template/themes" />

<script src="ckeditor/ckeditor.js"></script>	
<script src="script/jquery-ui.js"></script>
<script src="script/jquery.ui.datepicker-es.js"></script>
<script src="script/tinymce/tinymce.min.js"></script>
</head>
<body>
	<%
		Long idPaciente = Long.valueOf(request.getParameter("idPaciente"));
		String user = (String) session.getAttribute("user");
		if (user == null) {
	%>
	<jsp:forward page="/login.jsp"></jsp:forward>
	
	<%
		} else {
	%>

	<div id="pagina">
		<jsp:include page="../../header.jsp">
				<jsp:param value="false" name="conMenu" />
		</jsp:include>
		
		<div class="buttonReturn">
			<a href="abmPacientes.action" id="buttonReturn"> Volver </a>
		</div>

		<div id="tabs" style="font-size: 14px">
			<ul>
				<li><a href="#fragment-2"><span>Datos del Ingreso</span> </a></li>
				<s:set name="jspMenu" value="menu" />
			</ul>
			<div id="fragment-2" class="leftLabel">
				<s:form action="" method="POST" id="formEditarIngreso"
					cssClass="leftLabel" name="Editar Ingreso"
					theme="css_xhtml">

					<div id="div1">
						<s:hidden id="idPaciente" name="idPaciente" value="%{#parameters['idPaciente']}" />
						<s:hidden id="tratamientoId" name="tratamiento.id" value="%{tratamiento.id}" />
						<input type="hidden" id="motivo_html" name="motivo_html" />
						<s:textfield name="fechaIngreso" key="ingreso.fechaIngreso" label="Fecha de Ingreso al Hospital (Ambulatorio)" size="15" value="%{tratamiento.fechaIngreso}" cssClass="datepicker" />
						<s:textfield name="fechaServicioSocial" key="ingreso.fechaServicioSocial" label="Fecha de IC. a Servicio Social" size="15" value="%{tratamiento.fechaServicioSocial}" cssClass="datepicker" />
						<div class="textarea">
							<s:textarea name="motivo" id="motivo" key="ingreso.motivo" label="Diagnóstico Médico al Ingreso" value="%{tratamiento.motivo}" cssClass="ckeditor"/>
						</div> 
						<s:select list="%{#session.profesionales}" value="%{profesionalesValues}" name="profesionales" multiple="true" listKey="id" listValue='apellido + ", " + nombre + "  - Matricula: "+ matricula' label="Trabajadores Sociales Intervinientes" size="7" cssStyle="width:80%">
						</s:select>
					</div>
					<input type="submit" style="margin: 4% 0% 4% 0%" tabindex="13" name="submit" id="submit_btn" value="Editar Ingreso" />
				</s:form>
			</div>
		</div>
		<jsp:include page="../../footer.jsp" />
	</div>


	<%
		}
	%>
</body>
<script>
$(document).ready(function(){
	$("br").remove();
	$("input:first").focus();
	
	$("#tabs").tabs();
	$(".datepicker")
		.datepicker(
				{
					dateFormat : 'dd/mm/yy',
					changeMonth : true,
					changeYear : true,
					showOn : 'button',
					buttonImage : '<%=request.getContextPath()%>/images/16x16/Calendar.png',
		firstDay: 1,
	});
	$(".datepicker").each(function( index ) {
		  $(this).datepicker("setDate", $(this).val());
	});
	$(".datepicker").datepicker($.datepicker.regional['es']);
	
	//Envio por AJAX los datos del registro de ingreso
	$('#formEditarIngreso').submit(function() {
		
	      var fechaIngresoHospital = $("input#formEditarIngreso_fechaIngreso").val();
	      var fechaServicioSocial = $("input#formEditarIngreso_fechaServicioSocial").val();
	      
	      $('#motivo').val(tinyMCE.get('motivo').getContent());
	      var motivo = jQuery('#motivo').serialize();
	      
	      var profesionales = $("select#formEditarIngreso_profesionales").val();
	      var idPaciente = $("input#idPaciente").val();
	      var idtratamiento = $("input#tratamientoId").val();
	      
	      var dataString = 'idPaciente='+ idPaciente +	
	      				   '&tratamiento.id='+ idtratamiento +
	      				   '&tratamiento.fechaIngreso='+ fechaIngresoHospital +
	      				   '&tratamiento.fechaServicioSocial='+ fechaServicioSocial + 
	      				   '&tratamiento.'+ motivo + 
	      				   '&profesionales=' + profesionales;

	      $('#motivo_html').val(tinyMCE.get('motivo').getContent());
	      
	      $.ajax({
	    	  type: "POST",  
	    	  url: "editarIngreso.action",  
	    	  data: dataString,  
	    	  success: function(){
	    		  alert("El ingreso del paciente se registro correctamente! \nPrecione Aceptar para volver al Listado de Pacientes");
	    		  window.location.href = $('#buttonReturn').attr('href');
	    	  },
	    	  error: function(){
	    		  alert("Ocurrio un error al registrar el ingreso del paciente");
	    	  }
	      });
	      return false;
	  }); 
	
	tinyMCE.init({
	        // General options
	        mode: "textareas",
	        theme: "modern",
	        width: "100%",
	        height: "30%",
	        plugins: [
	        	"advlist autolink link image lists charmap print preview hr anchor pagebreak",
				"searchreplace wordcount visualchars fullscreen insertdatetime media nonbreaking",
				"save table contextmenu directionality emoticons template paste textcolor"          
	        ],
	        
	        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | print preview media fullpage | forecolor backcolor",
	
	  		// Selector
	        editor_selector: "textAreaTiny",
	
	});
});	
</script>
</html>