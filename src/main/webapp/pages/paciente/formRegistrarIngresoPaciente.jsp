<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ingreso de un paciente</title>
<link rel="stylesheet" href="jquery-ui.css">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<!-- <link href="structure.css" rel="stylesheet"> -->
<link href="form.css" rel="stylesheet">
<style type="text/css">

.wwgrp {
	margin-top: 2%;
}

</style>
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="false" jquerytheme="mytheme"
	customBasepath="%{ctx}template/themes" />

<script src="ckeditor/ckeditor.js"></script>	
<script src="script/jquery-ui.js"></script>
<script src="script/jquery.ui.datepicker-es.js"></script>
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
	 <div id="dialog-modal-exito" title="Exito" style="display: none">
	 	<p>El ingreso del paciente se registro correctamente!. <br/> Precione Ok para volver al Listado de Pacientes</p>
	 </div>
	 	 <div id="dialog-modal-error" title="Error" style="display: none">
	 	<p>"Ocurrio un error al registrar el ingreso del paciente"</p>
	 </div>
	 
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
				<s:form action="" method="POST" id="formIngreso"
					cssClass="leftLabel" name="Registrar Ingreso" target="_parent"
					theme="css_xhtml">

					<div id="div1">
						<s:hidden id="idPaciente" name="idPaciente" value="%{idPaciente}" />
						<s:hidden id="tratamientoId" name="tratamiento.id" value="%{tratamiento.id}" />
						<input type="hidden" id="motivo_html" name="motivo_html" />
						<s:textfield name="fechaIngreso" key="tratamiento.fechaIngreso" label="Fecha de Ingreso al Hospital (Ambulatorio)" size="15" value="%{tratamiento.fechaIngreso}" cssClass="datepicker" />
						<s:textfield name="fechaServicioSocial" key="tratamiento.fechaServicioSocial" label="Fecha de IC. a Servicio Social" size="15" value="%{tratamiento.fechaServicioSocial}" cssClass="datepicker" />
						<s:textfield name="fechaDeInternacion" key="tratamiento.fechaDeInternacion" label="Fecha de Internación" size="15" value="%{tratamiento.fechaInternacion}" cssClass="datepicker" />
						<s:select name="diagnostico.id" list="%{diagnosticos}" id="diagnosticoId" key="tratamiento.diagnostico" value="%{tratamiento.diagnosticoTabuladoAlIngreso.id}" listKey="id" listValue='diagnost' label="Tipo de Diagnóstico">
						</s:select>
						<div class="textarea">
							<s:textarea name="motivo" id="motivo" key="tratamiento.motivo" label="Diagnóstico Médico al Ingreso" value="%{tratamiento.detalleDiagnosticoIngreso}" cssClass="ckeditor"/>
						</div> 
						<s:select list="%{#session.profesionales}" value="%{profesionalesValues}" name="profesionales" multiple="true" listKey="id" listValue='apellido + ", " + nombre + "  - Matricula: "+ matricula' label="Trabajadores Sociales Intervinientes" size="7" cssStyle="width:80%">
						</s:select>
					</div>
					<input type="submit" style="margin: 4% 0% 4% 0%" tabindex="13" name="submit" id="submit_btn" value="Registrar Ingreso" />
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
	$('#formIngreso').submit(function() {
		
	      var fechaIngresoHospital = $("input#formIngreso_fechaIngreso").val();
	      var fechaServicioSocial = $("input#formIngreso_fechaServicioSocial").val();
	      var fechaInternacion = $("input#formIngreso_fechaDeInternacion").val();
	      var motivo = CKEDITOR.instances['motivo'].getData();
	      var profesionales = $("select#formIngreso_profesionales").val();
	      var idPaciente = $("input#idPaciente").val();
	      var idtratamiento = $("input#tratamientoId").val();
	      var idDiagnostico = $("select#diagnosticoId").val();
	      
	      var dataString = 'idPaciente='+ idPaciente +
	      				   '&tratamiento.id='+ idtratamiento +
	      				   '&tratamiento.fechaIngreso='+ fechaIngresoHospital +
	      				   '&tratamiento.fechaServicioSocial='+ fechaServicioSocial + 
	      				   '&tratamiento.fechaInternacion='+ fechaInternacion + 
	      				   '&tratamiento.detalleDiagnosticoIngreso='+ motivo + 
	      				   '&tratamiento.diagnosticoTabuladoAlIngreso.id='+ idDiagnostico +
	      				   '&profesionales=' + profesionales;

	      //$('#motivo_html').val(tinyMCE.get('motivo').getContent());
	      
	      $.ajax({
	    	  type: "POST",  
	    	  url: "${actionExecute}",//"saveIngreso.action",  
	    	  data: dataString,  
	    	  success: function(){
	    		  $(function() {
	    			    $( "#dialog-modal-exito" ).dialog({
	    			      modal: true,
	    			      buttons: {
	    			      "Ok": function() {
	    			          $( this ).dialog( "close" );
	    			          window.location.href = $('#buttonReturn').attr('href');
	    			        }
	    			      }
	    			    });
	    			});
	    	  },
	    	  error: function(){
	    		  $( "#dialog-modal-error" ).dialog({
    			      modal: true,
    			      buttons: {
    			      "Ok": function() {
    			          $( this ).dialog( "close" );
    			        }
    			      }
    			    });
	    	  }
	      });
	      return false;
	  }); 
	
});	
</script>
</html>