<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="script/script.js"></script>
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="true" jquerytheme="mytheme" customBasepath="%{ctx}template/themes"/>
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<script type="text/javascript"> 
$.struts2_jquery.require([ 
"struts/js/base/jquery.ui.widget.min.js", 
"struts/js/base/jquery.ui.datepicker.min.js", 
"struts/i18n/jquery.ui.datepicker-es.min.js" 
]);

var sel_id;
$.subscribe('rowselect', function(event, data) {
    sel_id = event.originalEvent.id;
});

$.subscribe('complete', function(request, status) {
    $("#del_gridtable_pacientes").css("display", "none");
    $(".eliminarPacienteClass").click(function(e){
    	//Obtengo la fila a partir del evento. Esto se hace porque en la cadena de handlers de javascript
    	//se ejecuta primero el eliminar y despues el seleccionar,
    	//Con lo cual, si tenemos seleccionada una fila y hacemos click en eliminar en otra
    	//se eliminaria la fila anteriormente seleccionada.
    	var tr = e.currentTarget.parentNode.parentNode.parentNode;
    	$("#del_gridtable_pacientes").trigger("click");
    });
    
    /*
     * Personalizo los mensajes y dialogos de JGrid 
     */
   	$.jgrid.del.msg = "¿Desea eliminar el Paciente seleccionado?";
});

var idPaciente = 0;

function renderActions(cellvalue, options, rowObject) {
	
    return ("<div style=\"text-align:center\">"
            	+"<a class=\"button\" title=\"<s:text name='patient.tooltip.edit'/>\" href=\"editarPaciente.action?paciente.id="+rowObject["id"]+"\"> <img src=\"images/16x16/Write2.png\"> </a>"
            	+"<a class=\"button\" title=\"<s:text name='patient.tooltip.cohabitantgroup'/>\" href=\"listGrupoConviviente.action?idPaciente="+rowObject["id"]+"\"> <img src=\"images/16x16/Group.png\"> </a>"
            	+"<a class=\"button\" title=\"<s:text name='patient.tooltip.recordincome'/>\" href=\"registrarIngreso.action?idPaciente="+rowObject["id"]+"\"> <img src=\"images/16x16/Go In.png\"> </a>" 
            	+"<a class=\"button\" title=\"<s:text name='patient.tooltip.recorddischarged'/>\" href=\"registrarAlta.action?idPaciente="+rowObject["id"]+"\"> <img src=\"images/16x16/Go Out.png\"> </a>"
				+"<div style=\"border-right: 2px solid black; display: inline\"></div>"
            	+"<a class=\"button\" title=\"<s:text name='patient.tooltip.historysocial'/>\" href=\"historiaSocial.action?idPaciente="+rowObject["id"]+"\"> <img src=\"images/16x16/Folder2.png\"> </a>"             			
       			+"</div>");
}

</script>

<script type="text/javascript">

</script> 
<style type="text/css">
 
#FrmGrid_gridtable .DataTD {
border: 2px;
border-right-style: solid;
border-color: #A6C9E2;
}

#FrmGrid_gridtable .CaptionTD {
padding-left: 10px;
}

#gbox_gridtable_practicas .ui-jqgrid-titlebar {
	display: none;
}

#gbox_gridtable_practicas .ui-resizable-handle {
	display: none;
}
#formPactica .ui-jqgrid {
	border: 0px;
}

#gbox_gridtable_pacientes{
margin-top: 4%;
}

</style>
<title><s:text name="patient.pagetitle"/></title>
</head>
<body>
	<% String user = (String)session.getAttribute("user"); 
		if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
	<%} else { %>
		<div id="pagina">
			<jsp:include page="header.jsp"/>

			<s:if test="mensaje != null">
				<sj:dialog id="mydialog1" title="Notificación">
     				<s:property value="mensaje"/>
    			</sj:dialog>
    		</s:if>
    		<jsp:include page="subMenu.jsp"> 
    			<jsp:param value="pacientes" name="abm"/>
    		</jsp:include>   
			<div id="cuerpo">
				<div class="estiloDiv">
				
							<h3><s:text name='patient.grilltitle'/></h3>
					
							<s:url id="remoteurl" action="datosTablaPacientes"/>
							<s:url id="editurl" action="crudPaciente"/>
							
							<s:url id="profesionales" action="fillSelectProf"/>
							
							<div class="buttonImportan"><a class="eliminarPacienteClass" href="#"><s:text name='patient.delpatient'/> </a> </div>
							<div class="buttonImportan"><a href="creacionPaciente.action"><s:text name='patient.addpatient'/> </a> </div>
							
							<sjg:grid	
								id="gridtable_pacientes"
								draggable="true"
								caption="Pacientes"
								dataType="json"
								href="%{remoteurl}"
								editurl="%{editurl}"
								pager="true"
								gridModel="pacientes"
								rowList="10,15,20"
								rowNum="10"
								rownumbers="true"
								onSelectRowTopics="rowselect"
								onCompleteTopics="complete"
								navigator="true"
								navigatorView="false"
								navigatorAdd="false"
								navigatorAddOptions="{width:900, reloadAfterSubmit:true, closeAfterAdd:true}"
								navigatorEdit="false"
								navigatorSearch="true"
								navigatorSearchOptions="{draggable:true, sopt:['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc'], multipleSearch: false}"
								navigatorEditOptions="{width:900, reloadAfterSubmit:false,closeAfterEdit:true}"
						    	navigatorViewOptions="{}"
						    	navigatorDelete="true"
						    	navigatorDeleteOptions="{reloadAfterSubmit:true}"
						    	droppable="true"
								editinline="false"
				    			multiselect="false"
				    			autowidth="true"
				    			shrinkToFit="false"
				    			width="50"
				    			
							> 
							
								<sjg:gridColumn name="id" index="id" hidden="true" editable="true" editrules="{edithidden:false}" title="ID Paciente" sortable="true"  searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="nombres" fixed="false" width="200" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 9995) }}" index="nombres" title="Nombres" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="apellidos" fixed="false" width="150" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 9996) }}" index="apellidos" title="Apellido" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="documento" fixed="false" width="120" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 9997) }}" index="documento" title="Documento" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaNacimiento" fixed="false" search="false" width="110" index="fechaNacimiento" title="Fecha de Nacimiento" edittype="text" editoptions="{size: 12, maxlength: 10, dataInit: function (element) { $(element).datepicker({dateFormat: 'dd/mm/yy', changeYear: true}); {$(element).attr('tabindex', 9998)} } }" sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="domicilio.id" index="domicilio.id" title="" hidden="true" editrules="{edithidden:false}" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
<%-- 								<sjg:gridColumn name="domicilio.calle" hidden="true" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 10002) }}" editrules="{edithidden:true}" index="domicilio.calle" title="Calle" sortable="true" editable="true" formoptions="{rowpos: 7, colpos: 2}" searchoptions="{sopt:['bw','cn']}"/> --%>
<%-- 								<sjg:gridColumn name="domicilio.numero" hidden="true" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 10003) }}" editrules="{edithidden:true}"  index="domicilio.numero" title="Numero" sortable="true" editable="true" formoptions="{rowpos: 8, colpos: 2}" searchoptions="{sopt:['bw','cn']}"/> --%>
<%-- 								<sjg:gridColumn name="domicilio.piso" hidden="true" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 10004) }}" editrules="{edithidden:true}" index="domicilio.piso" title="Piso" sortable="true" editable="true" formoptions="{rowpos: 9, colpos: 2}" searchoptions="{sopt:['bw','cn']}"/> --%>
<%-- 								<sjg:gridColumn name="domicilio.departamento" hidden="true" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 10005) }}" editrules="{edithidden:true}" index="domicilio.departamento" title="Dpto" sortable="true" editable="true" formoptions="{rowpos: 10, colpos: 2}" searchoptions="{sopt:['bw','cn']}"/> --%>
								<sjg:gridColumn name="vivienda.id" hidden="true"  index="vivienda.id" title="" sortable="true" editable="true" editrules="{edithidden:false}" searchoptions="{sopt:['eq']}"/>
<%-- 								<sjg:gridColumn name="vivienda.estado" hidden="true" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 9999) }}" editrules="{edithidden:true}"  index="vivienda.estado" title="Estado" sortable="true" editable="true" formoptions="{rowpos: 2, colpos: 2}" searchoptions="{sopt:['bw','cn']}"/> --%>
<%-- 								<sjg:gridColumn name="vivienda.propiedad" hidden="true" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 10000) }}" editrules="{edithidden:true}" index="vivienda.propiedad" title="Propiedad" sortable="true" editable="true" formoptions="{rowpos: 3, colpos: 2}" searchoptions="{sopt:['bw','cn']}"/> --%>
<%-- 								<sjg:gridColumn name="vivienda.tipo" hidden="true" editrules="{edithidden:true}" index="vivienda.tipo" title="Tipo" sortable="true" editable="true" edittype="select" formoptions="{rowpos: 4, colpos: 2}" editoptions="{value: 'PH:PH; dpto:Dpto', dataInit: function (element) {$(element).attr('tabindex', 10001) }}" searchoptions="{sopt:['bw','cn']}"/> --%>
								
								<sjg:gridColumn name="fechaInicioServSocial" search="false" fixed="false" width="110" index="fechaInicioServSocial" title="Fecha Ini. de SS" sortable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="historiaSocial.id" index="historiaSocial_ID" title="" hidden="true" edittype="text" sortable="true" editrules="{edithidden:false}" editable="true" searchoptions="{sopt:['eq']}"/>
<%-- 								<sjg:gridColumn name="historiaSocial.motivoIntervencionSocial" editoptions="{dataInit: function (element) {$(element).attr('tabindex', 10006) }}" hidden="true" editrules="{edithidden:true}" edittype="textarea"  index="historiaSocial.motivoIntervencionSocial" title="Motivo Intervencion Social" sortable="true" editable="true" formoptions="{rowpos: 2, colpos: 3}" searchoptions="{sopt:['bw','cn']}"/> --%>
								<sjg:gridColumn name="historiaSocial.tratamientoAmbulatorio.fechaInicio" search="false" jsonmap="historiaSocial.tratamientoAmbulatorio.fechaIngreso" width="110" index="historiaSocial.tratamientoAmbulatorio.fechaInicio" title="Fecha Ing. al Hosp" sortable="true" editable="true" formoptions="{rowpos: 3, colpos: 3}" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>

								<sjg:gridColumn name="actions" search="false" id="id" fixed="false" width="120" editrules="{edithidden:true}" dataType="html"  index="" title="Acciones" formoptions="{rowpos: 5, colpos: 3}" formatter="renderActions"/>
							</sjg:grid>
						</div>
						
					
				
			</div>
			
			<jsp:include page="footer.jsp"/>
		</div>
	<%} %>
</body>
</html>