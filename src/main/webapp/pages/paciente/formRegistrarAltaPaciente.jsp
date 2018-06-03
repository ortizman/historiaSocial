<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dar el Alta a un paciente</title>
<link rel="stylesheet" href="jquery-ui.css">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<!-- <link href="structure.css" rel="stylesheet"> -->
<link href="form.css" rel="stylesheet">
<style type="text/css">
.checkboxLabel{
	width: inherit;
	float: none;
}

.wwgrp {
	margin-top: 2%;
}

</style>
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="false" jquerytheme="mytheme"
	customBasepath="%{ctx}template/themes" />
<script src="script/jquery-ui.js"></script>
<script src="script/jquery.ui.datepicker-es.js"></script>
<script src="ckeditor/ckeditor.js"></script>	
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
				<li><a href="#fragment-6"><span>Datos para el Alta</span> </a></li>
				<s:set name="jspMenu" value="menu" />
			</ul>
			<div id="fragment-6" class="leftLabel">
						
				<s:form action="" method="POST" id="formAltaPaciente"
					cssClass="leftLabel" name="Registrar Alta" target="_parent"
					theme="css_xhtml">

					<div id="div1">
						<s:hidden id="idPaciente" name="idPaciente" value="%{#parameters['idPaciente']}" />
						<s:hidden id="tratamientoId" name="tratamiento.id" value="%{tratamiento.id}" />
						
						<s:textfield name="fechaAltaIniciada" id="fechaAltaIniciada" key="alta.fechaAlta" label="Fecha de alta iniciada" size="20" value="%{fechaAltaIniciada}" cssClass="datepicker" />
						<s:textfield cssClass="datepicker" id="fechaAltaEjecutada" name="fechaAltaEjecutada" label="Fecha de Alta Ejecutada" key="Fecha de Alta Ejecutada" size="20" value="%{paciente.fechaAltaEjecutada}"/>
						<s:textarea name="motivosModifFechaAlta" id="motivosModifFechaAlta" key="alta.motivoModif" label="Motivos que modificaron la fecha de alta" value="%{motivoModificacionFecha}" cols="100" rows="3" />
						<s:textfield name="responsableAdultoAlta" id="responsableAdultoAlta" key="alta.responsableAdulto" label="Responsable adulto al alta" size="25" value="%{responsableAdultoAlta}"/>
						<s:select name="diagnosticoTabuladoAlta.id" id="diagnosticoTabuladoAlta" list="%{diagnosticos}" key="tratamiento.diagnosticoTabuladoAlta" value="%{tratamiento.diagnostico.id}" listKey="id" listValue='diagnost' label="Tipo de Diagnóstico">
						</s:select>
						<div class="textarea">
							<s:textarea name="detalleDiagnosticoAlta" id="detalleDiagnosticoAlta" key="alta.Diagnostico" label="Diagnóstico" value="%{Diagnostico}" cols="100" rows="3" cssClass="ckeditor"/>
						</div>
						<s:textarea name="condicionesEgreso" id="condicionesEgreso" key="alta.CondicionesEgreso" label="Condiciones para el Egreso" value="%{CondicionesEgreso}" cols="100" rows="3" />
						<div id="checkboxes">
							<s:checkboxlist list="#{'Hospital':'Hospital','InstitutoDeSaludLocal':'Instituto de Salud Local','ServicioZonal':'Servicio Zonal','OtrasInstituciones':'Otras Instituciones'}" label="Instituciones Responsables del Seguimiento" name="institucionResponsableSeguimiento" id="institucionResponsableSeguimiento"></s:checkboxlist>
						</div>
						<s:textarea name="profesionalesResponsablesSeguimiento" id="profesionalesResponsablesSeguimiento" key="alta.ProfesionalesResponsables" label="Profesionales responsables del seguimiento" value="%{profesionalesResponsablesSeguimiento}" cols="100" rows="3" />
						<s:textarea name="condicionesParaEgreso" id="condicionesParaEgreso" key="alta.pendientes" label="Condiciones pendientes a tratar o indicaciones" value="%{pendientes}" cols="100" />
						
					</div>
					<input type="submit" style="margin: 4% 0% 4% 0%" tabindex="13" name="submit" id="submit_btn" value="Registrar Alta" />
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
	$('#formAltaPaciente').submit(function() {

			var instResponsables="";
			$('#checkboxes input:checked').each(function() {
				instResponsables +=($(this).attr('value')) + " " ;
			});
	      
	      var idPaciente = $("input#idPaciente").val();
	      var idtratamiento = $("input#tratamientoId").val();
	      var diagnosticoTabuladoAlta = $("select#diagnosticoTabuladoAlta").val();
		  var fechaAltaIniciada = $("input#fechaAltaIniciada").val();
		  var fechaAltaEjecutada = $("input#fechaAltaEjecutada").val();
		  var motivosModifFechaAlta = $("textarea#motivosModifFechaAlta").val();
		  var responsableAdultoAlta = $("input#responsableAdultoAlta").val();
		  var detalleDiagnosticoAlta = CKEDITOR.instances['detalleDiagnosticoAlta'].getData();
		  var condicionesEgreso = $("textarea#condicionesEgreso").val();
		  var profesionalesResponsablesSeguimiento = $("textarea#profesionalesResponsablesSeguimiento").val();
		  var condicionesParaEgreso = $("textarea#condicionesParaEgreso").val();
	      
	      var dataString = 'idPaciente='+ idPaciente +	
	      				   '&tratamiento.id='+ idtratamiento +
	      				   '&tratamiento.diagnosticoTabuladoAlta.id='+ diagnosticoTabuladoAlta +
	      				   '&tratamiento.fechaAltaIniciada='+ fechaAltaIniciada + 
	      				   '&tratamiento.fechaAltaEjecutada='+ fechaAltaEjecutada + 
	      				   '&tratamiento.motivosModifFechaAlta='+ motivosModifFechaAlta +
	      				   '&tratamiento.responsableAdultoAlta='+ responsableAdultoAlta +
	      				   '&tratamiento.detalleDiagnosticoAlta='+ detalleDiagnosticoAlta +
	      				   '&tratamiento.condicionesEgreso='+ condicionesEgreso +
	      				   '&tratamiento.institucionResponsableSeguimiento='+ instResponsables +
	      				   '&tratamiento.profesionalesResponsablesSeguimiento='+ profesionalesResponsablesSeguimiento +
	      				   '&tratamiento.condicionesParaEgreso='+ condicionesParaEgreso;

	      $.ajax({
	    	  type: "POST",  
	    	  url: "saveAlta.action",  
	    	  data: dataString,  
	    	  success: function(){
	    		  alert("El Alta del paciente se registro correctamente! \nPrecione Aceptar para volver al Listado de Pacientes");
	    		  window.location.href = $('#buttonReturn').attr('href');
	    	  },
	    	  error: function(){
	    		  alert("Ocurrio un error al registrar el Alta del paciente");
	    	  }
	      });
	      return false;
	  }); 
	
	
});	
</script>
</html>