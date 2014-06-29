<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<script type="text/javascript" src="script/script.js"></script>


<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="true" jquerytheme="mytheme"
	customBasepath="%{ctx}template/themes" />

<title>Listado de Pr&#225;cticas</title>
<style type="text/css">
form label {
	float: none;
}

.autocomplete-suggestions { border: 1px solid #999; background: #FFF; overflow: auto; }
.autocomplete-suggestion { padding: 2px 5px; white-space: pre-line; overflow: visible; margin: 5px; }
.autocomplete-selected { background: #F0F0F0; }
.autocomplete-suggestions strong { font-weight: normal; color: #3399FF; }

</style>
</head>
<body>
	<script>
		function disable(){
			filtro = document.getElementById("filtrofechaprof");
			filtro1 = document.getElementById("filtrofecha");
			filtro2 = document.getElementById("filtrofechapaciente");
			selectpac = document.getElementById("paciente");
			selectprof = document.getElementById("profesional");
			inicio = document.getElementById("inicio");
			fin = document.getElementById("fin");
			inicio.removeAttribute("disabled");
			fin.removeAttribute("disabled");
			if (filtro.checked) {
				selectpac.selectedIndex = "Seleccione un paciente";
				selectpac.setAttribute("disabled", "true");
				selectprof.removeAttribute("disabled");
			} else {
				if (filtro2.checked) {
					selectprof.selectedIndex = "Seleccione un profesional";
					selectprof.setAttribute("disabled", "true");
					selectpac.removeAttribute("disabled");
				} else {
					selectpac.setAttribute("disabled", "true");
					selectprof.setAttribute("disabled", "true");
					selectpac.selectedIndex = "Seleccione un paciente";
					selectprof.selectedIndex = "Seleccione un profesional";
					if (!filtro1.checked) {
						inicio.setAttribute("disabled", "true");
						fin.setAttribute("disabled", "true");
					}
				}

			}
		}
	</script>
	<%
		String user = (String) session.getAttribute("user");
		if (user == null) {
	%>
	<jsp:forward page="/login.jsp"></jsp:forward>
	<%
		} else {
	%>
	<jsp:include page="header.jsp" />

	<jsp:include page="subMenu.jsp">
		<jsp:param value="estadisticas" name="abm" />
	</jsp:include>
	
	<div id="pagina">
		<div id="cuerpo">
			<s:form id="formu" action="listado.action">
				<s:radio id="filtro"
					list="#{'fecha':'Fecha','fechaprof':'Fecha y Profesional','fechapaciente':'Fecha y Paciente'}"
					onchange="disable()" name="filtro" label="Filtrar por"></s:radio>

				<s:select list="profesionales" disabled="false" headerKey="-1"
					headerValue="Seleccione un profesional" value="profesional"
					listKey="id" listValue="nombreCompleto" name="profesional"
					label="Profesional" id="profesional">
				</s:select>
				
				<tr>
					<td><label title="Introdusca las priemas letras del apellidos del paciente" for="query">Paciente: </label></td>
					<td><input title="Introdusca las priemas letras del apellidos del paciente" type="text" name="query" id="paciente" size="35" /></td>
					
				</tr>
				<input name="paciente" type="hidden" id="pacienteId" value="${paciente}"/>
				
				<sj:datepicker id="inicio" name="inicio" displayFormat="dd/mm/yy"
					maxDate="today" label="Fecha Inicio "
					buttonImage="images/16x16/Calendar.png"
					buttonImageOnly="true" />
				<sj:datepicker id="fin" name="fin" displayFormat="dd/mm/yy"
					maxDate="+1d" label="Fecha Fin "
					buttonImage="images/16x16/Calendar.png"
					buttonImageOnly="true" />

				<s:submit value="Filtrar" />
			</s:form>
			<div id="cuerpo">


				<div class="estiloDiv">
					<h3>Pr&aacute;cticas realizadas</h3>

					<s:url id="remoteurl" action="datosTablaPractica" />
					<s:url id="editurl" action="crudPracticas" />

					<sjg:grid id="gridtable" caption="Prácticas" dataType="json"
						href="%{remoteurl}" pager="true" gridModel="practicas"
						rowList="5,10,15,20, 50" rowNum="5" rownumbers="true"
						formIds="formu" autowidth="true" editurl="%{editurl}"
						navigator="false"
						navigatorAddOptions="{reloadAfterSubmit:true, closeAfterAdd:true}"
						navigatorEdit="false"
						navigatorEditOptions="{reloadAfterSubmit:false,closeAfterEdit:true}"
						navigatorView="false" navigatorViewOptions="{}"
						navigatorDelete="false"
						navigatorDeleteOptions="{reloadAfterSubmit:true}"
						editinline="false" multiselect="false">

						<sjg:gridColumn name="id" index="id" hidden="true"
							formatter="integer" editable="true" title="ID Practicas"
							sortable="true" searchoptions="{sopt:['eq']}" />
						<sjg:gridColumn name="historiaSocial.id"
							jsonmap="historiaSocial.paciente.nombreCompleto"
							index="historiaSocial.paciente.nombreCompleto" title="Paciente"
							sortable="true" editable="true" edittype="select"
							editoptions="{dataUrl:'%{pacientes}'}"
							searchoptions="{sopt:['bw','cn']}" />
						<sjg:gridColumn name="localidad" index="localidad"
							title="Localidad" sortable="true" editable="true"
							searchoptions="{sopt:['bw','cn']}" />
						<sjg:gridColumn name="fechaPractica" index="fechaPractica"
							title="Fecha Práctica" sortable="true"
							editoptions="{size: 12, maxlength: 10, dataInit: function (element) { $(element).datepicker({dateFormat: 'dd/mm/yy', changeYear: true}) } }"
							formatter="date"
							formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"
							editable="true" searchoptions="{sopt:['bw','cn']}" />
						<sjg:gridColumn name="fechaCarga" index="fechaCarga"
							title="Fecha Carga" edittype="text"
							editoptions="{size: 12, maxlength: 10, dataInit: function (element) { $(element).datepicker({dateFormat: 'dd/mm/yy', changeYear: true}) } }"
							sortable="true" editable="true" formatter="date"
							formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}"
							searchoptions="{sopt:['bw','cn']}" />
						<sjg:gridColumn name="tipoPractica.id"
							jsonmap="tipoPractica.codigo" index="tipoPractica.codigo"
							title="Codigo Práctica" sortable="true" editable="true"
							edittype="select" editoptions="{dataUrl:'%{tipoPrac}'}"
							searchoptions="{sopt:['eq']}" />
						<sjg:gridColumn name="tipoProblematica.id"
							jsonmap="tipoProblematica.codigo" index="tipoProblematica.codigo"
							title="Codigo Problemática" sortable="true" edittype="select"
							editoptions="{dataUrl:'%{tipoProb}'}" editable="true"
							searchoptions="{sopt:['bw','cn']}" />
						<sjg:gridColumn name="profesional.id"
							jsonmap="profesional.nombreCompleto"
							index="profesional.nombreCompleto" title="Profesional a Cargo"
							sortable="true" editable="true" edittype="select"
							editoptions="{dataUrl:'%{profesionales}'}"
							searchoptions="{sopt:['bw','cn']}" />
					</sjg:grid>
				</div>

			</div>
			<div id="botonEstadisticas"></div>
			<script type="text/javascript" src="script/jquery.autocomplete.min.js"></script>
			<script>
				disable();
				$('#paciente').autocomplete(
						{
							serviceUrl : 'pacienteAutoComplete',
							onSelect : function(suggestion){
								$("#pacienteId").val(suggestion.data);
							},

							minChars: 3,

							deferRequestBy: 300
						
						});
			</script>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>