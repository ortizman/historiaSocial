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
<sj:head jqueryui="true"/>
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="true" jquerytheme="mytheme" customBasepath="%{ctx}template/themes"/>
<title>Instituciones</title>
<style type="text/css">
.buttonImportante{
	text-align: right;
	margin-right: 5px;

}

.buttonImportante a, .buttonReturnte a {
	border-radius: 4px 4px 4px 4px;
	color: white !important;
	font-size: 18px;
	font-weight: 300;
	padding: 8px 13px 10px;
	text-shadow: none;
	background-color: #DD4814;
}

#gbox_gridtable_instituciones{
	margin-top: 4%;
}

</style>
<script type="text/javascript"> 
$.struts2_jquery.require( [ 
"struts/js/base/jquery.ui.widget.min.js", 
"struts/js/base/jquery.ui.datepicker.min.js", 
"struts/i18n/jquery.ui.datepicker-es.min.js" 
]); 

var sel_id;
$.subscribe('rowselect', function(event, data) {
    sel_id = event.originalEvent.id;
});

var idInstitucion = 0;

function confirmar () {
	return confirm("Realmente desea eliminarla?");
}

function renderActions(cellvalue, options, rowObject) {
	console.info(rowObject["id"]);
	
    return ("<div style=\"text-align:center\">"
            	+"<a class=\"button\" title=\"Mostrar Detalle\" href=\"visualizarInstitucion.action?id="+rowObject["id"]+"\"> <img src=\"images/16x16/ZoomIn.png\"> </a>"
            	+"<a class=\"button\" title=\"Editar la institucion\" href=\"editarInstitucion.action?id="+rowObject["id"]+"\"> <img src=\"images/16x16/Write2.png\"> </a>"
            	+"<a class=\"button\" onclick=\"return confirmar()\" title=\"Eliminar la institucion\" href=\"eliminarInstitucion.action?id="+rowObject["id"]+"\"> <img src=\"images/16x16/Minus.png\"> </a>"
       			+"</div>");
    

}

</script> 
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
    			<jsp:param value="institucion" name="abm"/>
    		</jsp:include>
			<div id="cuerpo">

						<div class="estiloDiv">
							<h3>Listado de Instituciones</h3>
					
							<s:url id="remoteurl" action="datosTablaInstituciones"/>
							<s:url id="tipoInst" action="fillSelectTypeInst"/>
							<s:url id="respInst" action="fillSelectResp"/>
							<s:url id="editurl" action="crudInstitucion"/>
							
							<div class="buttonImportan"><a href="creacionInstitucion.action">Agregar Instituci&oacute;n</a> </div>							
							<sjg:grid	
								id="gridtable_instituciones"
								caption="Instituciones"
								dataType="json"
								href="%{remoteurl}"
								pager="true"
								gridModel="instituciones"
								rowList="5,10,15,20,50"
								rowNum="5"
								rownumbers="true"
								autowidth="true"
								editurl="%{editurl}"
								navigator="true"
								navigatorAdd="false"
								navigatorSearch="false"
								navigatorAddOptions="{reloadAfterSubmit:true, closeAfterAdd:true}"
								navigatorEdit="false"
								navigatorEditOptions="{reloadAfterSubmit:false,closeAfterEdit:true}"
						    	navigatorView="true"
						    	navigatorViewOptions="{}"
						    	navigatorDelete="false"
						    	navigatorDeleteOptions="{reloadAfterSubmit:true}"
								editinline="false"
				    			multiselect="false"
								
							>
								<sjg:gridColumn name="id" index="id" hidden="true" formatter="integer" title="ID Especialidad" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="nombre" index="nombre" title="Nombre" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="type.id" jsonmap="type.name" index="type.name" title="Tipo" edittype="select" editoptions="{dataUrl:'%{tipoInst}'}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="responsible.id" jsonmap="responsible.nombreCompleto" index="responsible.lastname" title="Responsable" edittype="select" editoptions="{dataUrl:'%{respInst}'}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
<%-- 								<sjg:gridColumn name="detail" index="detail" title="Detalle" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/> --%>
								<sjg:gridColumn name="telefono" index="telefono" title="Telefono" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="location.id"  index="location.id" hidden="true" formatter="integer" title="ID location" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="location.province" editrules="{edithidden:true}" hidden="true" index="location.province" title="Provincia" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="location.city" index="location.city" title="Ciudad" sortable="true"  editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="location.street" hidden="true" index="location.street" title="Calle" sortable="true" editrules="{edithidden:true}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="location.number" hidden="true" index="location.number" title="Numero" sortable="true" editrules="{edithidden:true}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="email" index="email" title="Email" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="actions" id="id" width="" editrules="{edithidden:true}" dataType="html" surl=""  index="" title="Acciones" formoptions="{rowpos: 5, colpos: 3}" formatter="renderActions"/>
								
							</sjg:grid>
							
						</div>
					
				
			</div>
			
			<jsp:include page="../../footer.jsp"/>
		</div>
	<% } %>
</body>
</html>