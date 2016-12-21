<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
<script type="text/javascript" src="script/script.js"></script>
<script src="script/formGrupoConv.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de alta - Nuevo Paciente</title>
<link rel="stylesheet" href="jquery-ui.css">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<!-- <link href="structure.css" rel="stylesheet"> -->
<link href="form.css" rel="stylesheet">
<style type="text/css">
.leftLabel td {
}
</style>
<script src="ckeditor/ckeditor.js"></script>
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

/*
 * Si se le pasa una fecha (Ej: $("fecha").datepicker("getDate"))
 * devuleve la es tiempo transcurrido hasta la fecha actual, en formato: X año/s, Y meses.
 * si la fecha que se le pasa es vacia o undefined, devuelve un string vacio.
 */
function calcular_edad(fecha){
	if(fecha == "undefined" || fecha == "" || fecha == null){
		return "";
	}
	
    var today = new Date();
    var birthDate = new Date(fecha);
    
    var yy1 = today.getFullYear(), mm1 = today.getMonth(), dd1 = today.getDate(),
    yy2 = birthDate.getFullYear(), mm2 = birthDate.getMonth(), dd2 = birthDate.getDate();
    var dm1 = 0;

    if (dd1 < dd2) {
        mm1--;
        dm1 += DaysInMonth(yy2, mm2+1);
        
        dd1 = dm1 + (dd1 - dd2);
        dd2 = 0;
    }
    if (mm1 < mm2) {
        yy1--;
        mm1 += 12;
    }
    
    return (yy1-yy2)+" Años, "+(mm1-mm2)+" Meses y "+(dd1-dd2)+" Días";
}

function DaysInMonth(Y, M) {
    with (new Date(Y, M, 1, 12)) {
        setDate(0);
        return getDate();
    }
}

function setearEdad(datep, elem){
	var edad = calcular_edad(datep.datepicker("getDate"));
	if(edad == ""){
		elem.text("Calculo automático de la edad...");
	}
	else {
		elem.text(edad);
	}
	
}

$(function(){
	$("br").remove();
	$("input:first").focus();
	$("li[class*='ui-state-default ui-corner-top']").click(function(){
		$("#"+$(this).attr("aria-controls")).find("textarea:first").focus();
		$("#"+$(this).attr("aria-controls")).find("input:first").focus();
	});
	
	$("#paciente_paciente_fechaNacimiento").change(function(){
		var datepicker = $("#paciente_paciente_fechaNacimiento");
		var elem = $("#paciente_edad_value");
		setearEdad(datepicker, elem);
	});
	
	var datepicker = $("#paciente_paciente_fechaNacimiento");
	var elem = $("#paciente_edad_value");
	setearEdad(datepicker, elem);
});
</script>
</head>
<body>
<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>

		<div id="pagina">
			<jsp:include page="../../header.jsp">
			    <jsp:param value="false" name="conMenu"/>
	   		</jsp:include>
 			<div class="buttonReturn"><a href="abmPacientes.action" > Volver </a></div>
    		
			<div id="tabs" style="font-size: 14px">
			    <ul>
			        <li><a href="#fragment-1"><span>Datos Personales</span></a></li>
					<li><a href="#fragment-4"><span>Situación Social</span></a></li>
			    </ul>
			    
			<s:actionerror/>
<%-- 			<form action="<s:property value="submitAction"/>" method="post" id="paciente" class="leftLabel" name="Alta Paciente" target="_parent"> --%>
			<s:form action="%{submitAction}" method="POST" id="paciente" cssClass="leftLabel"  name="Alta Paciente" target="_parent" theme="css_xhtml">
				<div id="fragment-1">
					<div>
						<s:hidden name="paciente.id" value="%{paciente.id}" />
					 	<s:textfield name="paciente.apellidos" key="Apellidos" label="Apellidos" size="35" value="%{paciente.apellidos}" requiredLabel="true"/>
					 	<s:textfield name="paciente.nombres" label="Nombres" key="Nombres" size="35" value="%{paciente.nombres}" requiredLabel="true"/>
						
						<div style="display: block;">
						<s:fielderror fieldName="paciente.documento"/>
						<s:textfield name="paciente.documento" key="Numero de DNI" size="10" maxlength="8" value="%{paciente.documento}" /> 
						<i>Ingrese el numero de documento <strong>sin puntos</strong>. Ejemplo: 54321012</i>
						</div>
						<div class="radio">
							<s:radio name="paciente.sexo" label="Sexo" key="Sexo" list="#{'Masculino':'Masculino', 'Femenino':'Femenino'}" requiredLabel="true"></s:radio>
						</div>	
						<div class="date">
							<s:textfield cssClass="datepicker" name="paciente.fechaNacimiento" label="Fecha de Nacimiento*" key="Fecha de Nacimiento" size="30" value="%{paciente.fechaNacimiento}" requiredLabel="true"/>
						</div>
						<label class="label">Edad: </label> <span id="paciente_edad_value" class="inputData_edad">Calculo automatico de la edad...</span>
						<s:textfield cssClass="datepicker" name="paciente.fechaInicioServSocial" label="Fecha de inicio de Servicio Social" key="Fecha de inicio de Servicio Social" size="30" value="%{paciente.fechaInicioServSocial}"/>
						<s:textfield cssClass="datepicker" name="paciente.fechaFinServicio" label="Fecha de fin de Servicio Social" key="Fecha de fin de Servicio Social" size="30" value="%{paciente.fechaFinServicio}"/>
						<s:textfield name="paciente.lugarDeNacimiento" size="35" key="Lugar de Nacimiento" value="%{paciente.lugarDeNacimiento}"/>
						<s:textfield name="paciente.location.street" size="35" key="Calle" value="%{paciente.location.street}"/>
						<s:textfield name="paciente.location.number" size="10" key="Numero" label="Altura" value="%{paciente.location.number}"/>
						<s:textfield name="paciente.domicilio.calleX" size="15" key="Entre" value="%{paciente.domicilio.calleX}"/>
						<s:textfield name="paciente.domicilio.calleY" size="15" key="Y" value="%{paciente.domicilio.calleY}"/>
						
						<s:textfield name="paciente.domicilio.barrio" size="35" key="Barrio" value="%{paciente.domicilio.barrio}"/>
						<s:textfield name="paciente.location.city" size="35" key="Localidad" value="%{paciente.location.city}"/>
						<s:textfield name="paciente.location.province" size="35" key="Provincia" value="%{paciente.location.province}"/>
						
						<s:textfield name="paciente.telefono" size="35" key="Telefono Fijo" value="%{paciente.telefono}"/>
						<s:textfield name="paciente.celular" size="35" key="Celular" value="%{paciente.celular}"/>
						
						<input name="paciente.location.id" type="hidden" value="0" />
					</div>
				</div>
				<div id="fragment-2" style="display: none">
					<div>

						<p>
							Fecha de Ingreso al Hospital: <input
								name="paciente.historiaSocial.fechaIngreso" size="9" type="text"
								class="datepicker"
								value="<s:property value="paciente.historiaSocial.fechaIngreso"/>" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Fecha de IC. a Servicio Social:<input
								name="paciente.historiaSocial.fechaICServicioSocial" size="9"
								type="text" class="datepicker"
								value="<s:property value="paciente.historiaSocial.fechaICServicioSocial"/>" />
						</p>
						<p>
							Fecha de inicio de Servicio Social: <input
								name="paciente.historiaSocial.fechaInicio" size="9" type="text"
								class="datepicker"
								value="<s:property value="paciente.historiaSocial.fechaInicio"/>" />
						</p>
						<p>
							Motivo de Intervenci&oacute;n:
							<textarea cols="95"
								name="paciente.historiaSocial.motivoIntervencionSocial" rows="1"
								value="<s:property value="paciente.historiaSocial.motivoIntervencionSocial"/>"></textarea>
						</p>
						<p>
							Trabajadores Sociales Intervinientes: 
							<select multiple="multiple"	name="trabSociales" size="4">
								<option value="1">Isabel Bosco</option>
								<option value="2">Juana Molina</option>
								<option value="3">Juana Molina</option>
							</select>
						</p>
						<p>
						<div style="display: block;">Otros profesionales intervinientes:</div>
						<textarea cols="50"
							name="paciente.historiaSocial.otrosProfesinalesInterv" rows="2">
							<s:property value="paciente.historiaSocial.otrosProfesinalesInterv" />
						</textarea>
						</p>

					</div>
				</div>
				<div id="fragment-4">
					<div>
						<div class="textarea">
							<s:textarea name="paciente.beneficionPlanesSubsidios" label="Beneficios, Planes, Subsidios" value="%{paciente.beneficionPlanesSubsidios}" cssClass="ckeditor"/>
						</div>
						
						<s:select  label="Condiciones habitacionales" name="paciente.condicionesHabitacionales.id" list="%{condicionesHabitacionales}" listKey="id" listValue="condicion" value="%{paciente.condicionesHabitacionales.id}" ></s:select>
						<s:select  label="Tipo de Propiedad" name="paciente.tipoDePropiedad.id" list="%{tipoDePropiedades}" listKey="id" listValue="tipoDePropiedad" value="%{paciente.tipoDePropiedad.id}" ></s:select>
						<s:select  label="Centro de Salud de referencia" name="paciente.centroSaludReferencia.id" list="%{instituciones}" listKey="id" listValue="nombre" value="%{paciente.centroSaludReferencia.id}" ></s:select>
						<s:textarea name="paciente.otrasInstitucionesIntervinientes" cols="95" rows="2" label="Otras Instituciones intervinientes"></s:textarea>
						<s:textarea name="paciente.accesibilidad" label="Accesibilidad" cols="95" rows="2"></s:textarea>
					</div>

				</div>
				<div id="fragment-6" style="display: none">
					<div>
						<p>
							Fecha de alta iniciada: <input size="10" name="fecha" type="text"
								class="datepicker" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Fecha de alta ejecutada: <input type="text" size="10"
								class="datepicker" />
						</p>
						<p>
							Motivos que modificaron la fecha de alta:&nbsp;
							<textarea cols="59" name="motivosQModificaronFechaallta" rows="1"></textarea>
						</p>
						<p>
							Responsable adulto al alta:
							<textarea cols="70" name="responsableAlta" rows="1"></textarea>
						</p>
						<p>
							Diagn&oacute;stico: <select name="diagnostico"><option
									value="Diagnostico1">Diagnostico1</option>
							</select>
						</p>
						<p>
							Condiciones para el egreso:
							<textarea cols="70" name="condicionesEgreso" rows="2"></textarea>
						</p>
						<p>Instituciones responsables del seguimiento
							ambulatorio:&nbsp;</p>
						<p style="margin-left: 480px;">
							<input checked="checked" name="Hospital" type="checkbox"
								value="hospital" />Hospital
						</p>
						<p style="margin-left: 480px;">
							<input name="Instituti de Salud Local" type="checkbox"
								value="asd" />Instituto de Salud Local
						</p>
						<p style="margin-left: 480px;">
							<input type="checkbox" />Servicio Zonal
						</p>
						<p style="margin-left: 480px;">
							<input type="checkbox" />Servicio Local
						</p>
						<p style="margin-left: 480px;">
							<input type="checkbox" />Otras Instituciones
						</p>
						<p>
							Profesionales responsables del seguimiento:
							<textarea cols="80" name="responsablesDelSeguimiento" rows="1"></textarea>
						</p>
						<p>
							Condiciones pendientes a tratar o indicaciones:
							<textarea cols="80" name="indicacionesPendientes" rows="1"></textarea>
						</p>
						<p style="margin-left: 480px;">&nbsp;</p>
						<p style="text-align: justify; margin-left: 40px;">&nbsp;</p>
					</div>

				</div>
				<s:if test="oper == 'edit' || oper == 'create' || oper == ''">
				<hr />
					<p style="text-align: center;">
						<input type="submit" id="paciente_Aceptar"  name="Aceptar" value="Aceptar"/> &nbsp; <input type="submit" id="paciente_Cancelar" name="Cancelar" value="Cancelar" onclick="$(location).attr('href','abmPacientes.action'); return false;"/>
					</p> 
				</s:if>
			</s:form>
		</div>

			 
			<script>
			$( "#tabs" ).tabs();
			$(".datepicker").datepicker({
				maxDate: 'today',
				dateFormat: 'dd/mm/yy',
				changeMonth: true,
				changeYear: true,
				showOn: 'button',
				buttonImage: '<%=request.getContextPath() %>/images/16x16/Calendar.png',
				firstDay: 1,
				yearRange: "-20:+0",
			});
			$(".datepicker").each(function( index ) {
				  $(this).datepicker("setDate", $(this).val());
			});
			$(".datepicker").datepicker($.datepicker.regional['es']); 
			
			CKEDITOR.editorConfig = function( config ) {
			    config.language = 'es';
			    config.uiColor = '#AADC6E';
			};
			
			</script>
    		
    	<jsp:include page="../../footer.jsp"/>		
    </div>
	
	
	<%} %>
 
</body>
</html>