<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>ABM Tipos de Problem&#225;ticas</title>
<!-- NUEVO -->
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
            	+"<a class=\"button\" title=\"Editar la problemática\" href=\"editarProblematica.action?id="+rowObject["id"]+"\"> <img src=\"images/16x16/Write2.png\"> </a>"
            	+"<a class=\"button\" onclick=\"return confirmar()\" title=\"Eliminar la problemática\" href=\"eliminarProblematica.action?id="+rowObject["id"]+"\"> <img src=\"images/16x16/Minus.png\"> </a>"
       			+"</div>");

}

$(function(){
	$("#configuracion").addClass("menuSelected");
	$(".configuracionSubMenu").show();
	$(".tipoProblematicaSubMenu").addClass("menuSelectedP");
});
</script> 
	<!-- AGREGADO -->
<style type="text/css">
	#gbox_gridtable_problematicas{
	margin-top: 4%;
	}
	</style>
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
			<div id="cuerpo">
					<div class="estiloDiv">
							<h3>Listado de los tipos de Problem&aacute;tica</h3>
							<s:url id="remoteurl" action="datosTablaTipoProblematica"/>
							<div class="buttonImportan"><a href="creacionProblematica.action">Agregar Problem&aacute;tica</a> </div>
							
							<sjg:grid	
								id="gridtable_problematicas"
								caption="Problemáticas"
								dataType="json"
								href="%{remoteurl}"
								pager="true"
								gridModel="tipoProblematicas"
								rowList="5,10,15,20"
								rowNum="5"
								rownumbers="true"
								autowidth="true"
								navigator="false"
								navigatorAddOptions="{height:180,reloadAfterSubmit:true, closeAfterAdd:true}"
								navigatorEdit="false"
								navigatorEditOptions="{height:180,reloadAfterSubmit:false,closeAfterEdit:true}"
						    	navigatorView="false"
						    	navigatorViewOptions="{width:350}"
						    	navigatorDelete="false"
						    	navigatorDeleteOptions="{height:100,width:350,reloadAfterSubmit:true}"
								editinline="false"
				    			multiselect="false"
				    			
				    		>
				    			
								<sjg:gridColumn name="id" index="id" hidden="true" formatter="integer" title="ID" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="codigo" index="codigo" title="Código" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="descripcion" index="descripcion" title="Descripción" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="actions" id="id" width="" editrules="{edithidden:true}" dataType="html" surl=""  index="" title="Acciones" formoptions="{rowpos: 5, colpos: 3}" formatter="renderActions"/>
							</sjg:grid>
						</div>
			</div>
			<jsp:include page="../../footer.jsp"/>
		</div>
	<% } %>
</body>
</html>