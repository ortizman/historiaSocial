<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="estilo.css" rel="StyleSheet" type="text/css" id="css"/>
<script type="text/javascript" src="script/script.js"></script>
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="true" jquerytheme="mytheme" customBasepath="%{ctx}template/themes"/>
<script type="text/javascript"> 
$.struts2_jquery.require( [ 
"struts/js/base/jquery.ui.widget.min.js", 
"struts/js/base/jquery.ui.datepicker.min.js", 
"struts/i18n/jquery.ui.datepicker-es.min.js" 
]); 
var idPractica = 0;

function confirmar () {
	return confirm("Realmente desea eliminarla?");
}

function renderActions(cellvalue, options, rowObject) {
	console.info(rowObject["id"]);
	
    return ("<div style=\"text-align:center\">"
            	+"<a class=\"button\" title=\"Mostrar Detalle\" href=\"visualizarPractica.action?idPractica="+rowObject["id"]+"\"> <img src=\"images/16x16/ZoomIn.png\"> </a>"
            	+"<a class=\"button\" title=\"Editar la practica\" href=\"editarPractica.action?idPractica="+rowObject["id"]+"\"> <img src=\"images/16x16/Write2.png\"> </a>"
            	+"<a class=\"button\" onclick=\"return confirmar()\" title=\"Eliminar la practica\" href=\"eliminarPractica.action?idPractica="+rowObject["id"]+"\"> <img src=\"images/16x16/Minus.png\"> </a>"
       			+"</div>");
}
</script> 
<style type="text/css">
#gbox_gridtable_practicas{
margin-top: 4%;
}

</style>

<title>ABM Pr&#225;cticas</title>
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
    			<jsp:param value="practicas" name="abm"/>
    		</jsp:include>    		
			<div id="cuerpo">
			<div>
				<s:form id="pacienteid">
					<s:hidden name="idPaciente" value="%{idPaciente}"></s:hidden>					
				</s:form>
			</div>
			<div class="estiloDiv">
							<h3>Listado de Pr&aacute;cticas</h3>
					
							<s:url id="remoteurl" action="datosTablaPracticas"/>
							<s:url id="editurl" action="crudPracticas"/>
							<s:url id="tipoProb" action="fillSelectProbl"/>
							<s:url id="tipoPrac" action="fillSelectCodPrac"/>
							<s:url id="profesionales" action="fillSelectProf"/>
							<s:url id="pacientes" action="fillSelectPaciente"/>
							
							
							<div class="buttonImportan"><a href="creacionPractica.action">Agregar Pr&aacute;ctica</a> </div>														
							<sjg:grid	
								id="gridtable_practicas"
								caption="Prácticas"
								dataType="json"
								href="%{remoteurl}"
								pager="true"
								formIds="pacienteid"
								gridModel="practicas"
								rowList="5,10,15,20, 50"
								rowNum="10"
								rownumbers="false"
								editurl="%{editurl}"
								navigator="false"
								navigatorAddOptions="{reloadAfterSubmit:true, closeAfterAdd:true}"
								navigatorEdit="false"
								navigatorEditOptions="{reloadAfterSubmit:false,closeAfterEdit:true}"
						    	navigatorView="false"
						    	navigatorViewOptions="{}"
						    	navigatorDelete="false"
						    	navigatorDeleteOptions="{reloadAfterSubmit:true}"
								editinline="false"
				    			multiselect="false"
				    			autowidth="true"
				    			shrinkToFit="false"
				    			gridview="true"
								
							> 
								
								<sjg:gridColumn name="id" index="id" width="0" hidden="true" formatter="integer" editable="true" title="ID Practicas" sortable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="tratamiento.historiaSocial.paciente.nombreCompleto" width="130" jsonmap="tratamiento.historiaSocial.paciente.nombreCompleto"  index="tratamiento.historiaSocial.paciente.apellidos" title="Paciente" sortable="true" editable="true" edittype="select" editoptions="{dataUrl:'%{pacientes}'}" searchoptions="{sopt:['bw','cn']}"/>				
								<sjg:gridColumn name="fechaPractica" index="fechaPractica" width="120" title="Fecha Práctica" sortable="true" editoptions="{size: 12, maxlength: 10, dataInit: function (element) { $(element).datepicker({dateFormat: 'dd/mm/yy', changeYear: true}) } }" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaCarga" index="fechaCarga" width="120" title="Fecha Carga" edittype="text" editoptions="{size: 12, maxlength: 10, dataInit: function (element) { $(element).datepicker({dateFormat: 'dd/mm/yy', changeYear: true}) } }" sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="tipoPractica.id" width="200" jsonmap="tipoPractica.codigo" index="tipoPractica.codigo" title="Codigo Práctica" sortable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="tipoProblematica.id" width="200" jsonmap="tipoProblematica.codigo"  index="tipoProblematica.codigo" title="Codigo Problemática" sortable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="profesional.id" jsonmap="profesional.nombreCompleto" width="145" index="profesional.nombreCompleto" title="Profesional a Cargo" sortable="true"  searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="actions" id="id" width="77" editrules="{edithidden:true}" dataType="html" title="Acciones" formoptions="{rowpos: 5, colpos: 3}" formatter="renderActions"/>
							</sjg:grid>
					</div>

			</div>
			<jsp:include page="../../footer.jsp"/>
		</div>
	<%} %>

</body>
</html>