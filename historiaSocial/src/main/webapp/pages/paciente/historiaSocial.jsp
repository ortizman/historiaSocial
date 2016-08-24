<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script/script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Historia Social del Paciente - </title>
<link rel="stylesheet" href="jquery-ui.css">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<!-- <link href="structure.css" rel="stylesheet"> -->
<s:url var="ctx" value="/" />
<sj:head locale="es" jqueryui="false" jquerytheme="mytheme" customBasepath="%{ctx}template/themes"/>
<script src="script/jquery-ui.js"></script>
<%-- <script src="script/jquery.ui.datepicker-es.js"></script> --%>

</head>
<body>
<% 
Long idPaciente = (Long)request.getAttribute("idPaciente");
String user = (String)session.getAttribute("user");
if (user == null){ %>
		<jsp:forward page="/login.jsp"></jsp:forward>
<%} else { %>

		<div id="pagina"> 
			<jsp:include page="../../header.jsp">
			    <jsp:param value="false" name="conMenu"/>
	   		</jsp:include>

			<div class="buttonReturn"><a href="abmPacientes.action" > Volver </a></div>
			<div id="tabs" style="font-size: 16px;">
			    <ul>
			        <li style="float: right;"><a href="#fragment-3"><span>Historia Social del Paciente</span></a></li>
<%-- 					<s:set name="jspMenu" value="menu"/> --%>
			    </ul>
			    <div class="pacienteDetalle">
			    	<div class="pDato"><label>Nombre:</label><div><s:property value="paciente.nombres" default="-"/> </div></div>
			    	<div class="pDato"><label>Apellido:</label><div><s:property value="paciente.apellidos" default="-" /></div></div>
			    	<div class="pDato"><label>Fecha de Nacimiento:</label><div><s:date name="paciente.fechaNacimiento" format="dd/MM/yyyy"/></div></div>
			    	<div class="pDato"><label>Lugar de Nacimiento:</label><div><s:property value="paciente.lugarDeNacimiento" default="-" /></div></div>
			    	<div class="pDato"><label>Documento:</label><div><s:property value="paciente.documento" default="-" /></div></div>
			    	<div class="pDato"><label>Sexo:</label><div><s:property value="paciente.sexo" default="-" /></div></div>
			    	<div class="pDato"><label>Celular:</label><div><s:property value="paciente.celular" default="-" /></div></div>
			    	<div class="pDato"><label>Teléfono :</label><div><s:property value="paciente.telefono" default="-" /></div></div>
			    	<div class="pDato"><label>Inicio Servicio Social:</label><div><s:date name="paciente.fechaInicioServSocial" format="dd/MM/yyyy"/></div></div>
			    </div>
			    
							<h3>Ingresos y Egresos al Servicio Social</h3>
							<s:url id="subgridpracticas" action="datosTablaPracticasPorTratamiento" includeParams="get"/>
							<s:url id="subgridpracticasedit" action="crudPracticas"/>
							<s:url id="ambulatoriasURL" action="datosHistoriaSocialAmbulatoria" includeParams="get"/>
							<s:url id="ingresoegresoURL" action="datosHistoriaSocialIngresoEgreso" includeParams="get"/>
							<s:url id="editurl" action="crudConviviente" includeParams="get"/>
							
							<sjg:grid	
								id="gridtableIngresoEgreso"
								caption="Ingresos / Egresos"
								dataType="json"
								href="%{ingresoegresoURL}"
								pager="true"
								gridModel="tratamientos"
								rowList="5,10,15,20"
								rowNum="5"
								rownumbers="false"
								autowidth="true"
								editurl="%{editurl}"
								navigator="true"
								navigatorAddOptions="{reloadAfterSubmit:true}"
								navigatorEdit="false"
								navigatorSearch="false"
								navigatorEditOptions="{reloadAfterSubmit:true,closeAfterEdit:true, height:450, width:340}"
						    	navigatorView="false"
						    	navigatorDelete="false"
						    	navigatorAdd="false"
						    	navigatorDeleteOptions="{reloadAfterSubmit:true}"
								editinline="false"
				    			multiselect="false"
				    			pagerButtons="false"
							>
							
							<sjg:grid	
								id="gridtable_practicas"
								caption="Prácticas"
								dataType="json"
								subGridUrl="%{subgridpracticas}"
								pager="false"
								formIds="pacienteid"
								gridModel="practicas"
								rowList="2,5,10,20"
								rowNum="10"
								rownumbers="false"
								editurl="%{subgridpracticasedit}"
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
				    			gridview="true"
				    			width="950"
								
							> 
								
								<sjg:gridColumn name="id" index="id" width="0" hidden="true" formatter="integer" editable="true" title="ID Practicas" sortable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="fechaPractica" index="fechaPractica" width="120" title="Fecha Práctica" sortable="true" editoptions="{size: 12, maxlength: 10, dataInit: function (element) { $(element).datepicker({dateFormat: 'dd/mm/yy', changeYear: true}) } }" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaCarga" index="fechaCarga" title="Fecha Carga" edittype="text" editoptions="{size: 12, maxlength: 10, dataInit: function (element) { $(element).datepicker({dateFormat: 'dd/mm/yy', changeYear: true}) } }" sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="tipoPractica.id"  jsonmap="tipoPractica.codigo" index="tipoPractica.codigo" title="Codigo Práctica" sortable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="tipoProblematica.id"  jsonmap="tipoProblematica.codigo"  index="tipoProblematica.codigo" title="Codigo Problemática" sortable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="profesional.id" jsonmap="profesional.nombreCompleto" width="145" index="profesional.nombreCompleto" title="Profesional a Cargo" sortable="true"  searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="actions" id="id" width="100" editrules="{edithidden:true}" dataType="html" title="Acciones" formatter="renderActionsPracticas"/>
							</sjg:grid>
							
							
							
							
							
								<sjg:gridColumn name="idtratamiento" jsonmap="id" title="" index="id" hidden="true" sortable="true" editable="true" key="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="fechaIngreso" index="fechaIngreso" width="100" title="Fecha Ingreso" sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="FechaInterCons" index="FechaInterCons" width="140" title="Fecha Inter Cons." sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaInternacion" index="fechaInternacion" width="100" title="Fecha Internación" sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="detalleDiagnosticoIngreso"   index="detalleDiagnosticoIngreso" title="Diagnóstico de Ingreso" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaAltaIniciada" index="fechaAltaIniciada"  width="120" title="Alta Iniciada" sortable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaAltaEjecutada" index="fechaAltaEjecutada" width="120" title="Alta Ejecutada" sortable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="detalleDiagnosticoAlta" index="detalleDiagnosticoAlta" title="Diagnostico de Egreso" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="actions" search="false" fixed="false" width="150" editrules="{edithidden:true}" dataType="html"  index="" title="Acciones" formatter="renderActionsIngEgr"/>								
							</sjg:grid>
				
				<h3>Prácticas Ambulatorias</h3>
				
							<s:url id="remoteurl" action="datosTablaPersonas" includeParams="get"/>
							<s:url id="editurl" action="crudConviviente" includeParams="get"/>
							
							<sjg:grid	
								id="gridtableAmbulatoria"
								caption="Ambulatorias"
								dataType="json"
								href="%{ambulatoriasURL}"
								pager="true"
								gridModel="practicasAmbulatorias"
								rowList="5,10,15,20"
								rowNum="5"
								rownumbers="false"
								autowidth="true"
								
								editurl="%{editurl}"
								navigator="true"
								navigatorAddOptions="{reloadAfterSubmit:true}"
								navigatorEdit="false"
								navigatorSearch="false"
								navigatorEditOptions="{reloadAfterSubmit:true,closeAfterEdit:true, height:450, width:340}"
						    	navigatorView="false"
						    	navigatorViewOptions=""
						    	navigatorDelete="false"
						    	navigatorAdd="false"
						    	navigatorDeleteOptions="{reloadAfterSubmit:true}"
								editinline="false"
				    			multiselect="false"
							>
								<sjg:gridColumn name="id" index="id" hidden="true" formatter="integer" title="ID Conviviente" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="fechaPractica" jsonmap="fechaPractica" index="nombres"  title="Fecha Práctica" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaCarga" jsonmap="fechaCarga" index="vinculo" title="Fecha Carga" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="codigoPractica"  hidden="true" jsonmap="tipoPractica.codigo" editrules="{edithidden:true}" index="obraSocial" title="Obra Social" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="CodigoProblematica" jsonmap="tipoProblematica.codigo" index="nacionalidad" title="Codigo Problematica" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="profesionalCargo " index="educacion" jsonmap="profesional.nombreCompleto" title="Profesional a Cargo" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="actions" search="false" fixed="false" width="100" editrules="{edithidden:true}" dataType="html"  index="" title="Acciones" formatter="renderActions"/>								
							</sjg:grid>
				</div>
				
				
<!-- 				<hr /> -->
				
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
			
			//Envio por AJAX los datos para editar el conviviernte.
			function editConviviente() {  
			    // validate and process form here 
			      var idConv = $("input#idConviviente").val();
			      var nombresVal = $("input#nombres").val();
			      var nroVal = $("input#nro").val(); 			    
			      var edadVal = $("input#edad").val();  
			      var apellidoVal = $("input#apellido").val();
			      var vinculoVal = $("select#vinculo").val();
			      var nacionalidadVal = $("select#nacionalidad").val();
			      var estadoCivilVal = $("select#estadoCivil").val();
			      var educacionVal = $("select#educacion").val();
			      var ocupacionVal = $("select#ocupacion").val();
			      var obraSocialVal = $("select#obraSocial").val();
			      var ingresosVal = $("select#ingresos").val();
			      var conviveVal = $("input#convive").val();
// 			      var idPaciente = $("input#idPaciente").val();
			      
			      var dataString = 'id='+ idConv +
			      				   '&nro='+ nroVal +
			      				   '&nombres='+ nombresVal + 
			      				   '&apellido='+ apellidoVal + 
			      				   '&edad=' + edadVal + 
			      				   '&vinculo=' + vinculoVal + 
			      				   '&nacionalidad=' + nacionalidadVal + 
			      				   '&estadoCivil=' + estadoCivilVal + 
			      				   '&educacion=' + educacionVal + 
			      				   '&ocupacion=' + ocupacionVal + 
			      				   '&obraSocial.id=' + obraSocialVal +
			      				   '&convive=' + $(".convive").is(":checked") +			      				   
			      				   '&ingresos=' + ingresosVal;
			      
			      $.ajax({
			    	  type: "POST",  
			    	  url: "editConviviente.action",  
			    	  data: dataString,  
			    	  success: function() {
			    		  $("#gridtableConviviente").trigger("reloadGrid");
			    		  reseteo();
			    	  } 
			      });
			      return false;
			  }; 			
			
			
			//Envio por AJAX los datos del conviviernte.
			$(".buttonSubConviviente").click(function() {  
			    // validate and process form here  
			      var nombresVal = $("input#nombres").val();
			      var nroVal = $("input#nro").val(); 			    
			      var edadVal = $("input#edad").val();  
			      var apellidoVal = $("input#apellido").val();
			      var vinculoVal = $("select#vinculo").val();
			      var nacionalidadVal = $("select#nacionalidad").val();
			      var estadoCivilVal = $("select#estadoCivil").val();
			      var educacionVal = $("select#educacion").val();
			      var ocupacionVal = $("select#ocupacion").val();
			      var obraSocialVal = $("select#obraSocial").val();
			      var ingresosVal = $("select#ingresos").val();
			      var conviveVal = $("input#convive").val();
			      var idPaciente = $("input#idPaciente").val();
			      
			      var dataString = 'idPaciente='+ idPaciente +	
			      				   '&id='+ 0 +
			      				   '&nro='+ nroVal +
			      				   '&nombres='+ nombresVal + 
			      				   '&apellido='+ apellidoVal + 
			      				   '&edad=' + edadVal + 
			      				   '&vinculo=' + vinculoVal + 
			      				   '&nacionalidad=' + nacionalidadVal + 
			      				   '&estadoCivil=' + estadoCivilVal + 
			      				   '&educacion=' + educacionVal + 
			      				   '&ocupacion=' + ocupacionVal + 
			      				   '&obraSocial.id=' + obraSocialVal +
			      				   '&convive=' + $(".convive").is(":checked") +			      				   
			      				   '&ingresos=' + ingresosVal;
			      
			      $.ajax({
			    	  type: "POST",  
			    	  url: "agregarConviviente.action",  
			    	  data: dataString,  
			    	  success: function() {
			    		  $("#gridtableConviviente").trigger("reloadGrid");
			    	  } 
			      });
			      return false;
			  }); 
			
			function getConviviente(idConviviente){
				data = 'conviviente.id='+idConviviente;
				$.ajax({
					  dataType: "json",
					  url: "editarConviviente.action",
					  data: data,
					  success: function(a,b,c){
						  if("success" === b){
							  $("#toggle").effect( "highlight", {color:"#D6D6D6"}, 1500 );
							  
							  conv = a.conviviente;
						      $("input#nombres").val(conv.nombres);
						      $("input#nro").val(conv.nro); 			    
						      $("input#edad").val(conv.edad);  
						      $("input#apellido").val(conv.apellido);
						      $("select#vinculo").val(conv.vinculo);
						      $("select#nacionalidad").val(conv.nacionalidad);
						      $("select#estadoCivil").val(conv.estadoCivil);
						      $("select#educacion").val(conv.educacion);
						      $("select#ocupacion").val(conv.ocupacion);
						      $("select#obraSocial").val(conv.obraSocial.id);
						      $("select#ingresos").val(conv.ingresos);
						      $("input#convive").prop('checked', conv.convive);
						      $("input#idConviviente").val(conv.id);
						      
						      if($("input#convCancel").length === 0){ 
							      var r=$('<input type="button" id="convCancel" value="Cancel" onclick="reseteo()" style="display: inline;" />');
							      $("input#submit_btn").after(r);
						      }
						      
						      if($("input#convEdit").length === 0){
							      var c=$('<input type="button" style="display: inline;" tabindex="13" name="submit" class="buttonEditConviviente" onclick="editConviviente()" id="convEdit" value="Editar Conviviente" />');
							      $("input#submit_btn").after(c);
							      $("input#submit_btn").hide();
						      }
						      
						  }
					  },
					  error: function(){
						  alert("Se produjo un error al intentar editar el conviviente");
					  }
					});
				
				
			}
			
			function reseteo(){
				$("#formConviviente")[0].reset();
				$("input#idConviviente").val("");
				
				if($("input#convEdit").length >= 1){
					$("input#convEdit").remove();
				}
				
				if($("input#convCancel").length >= 1){
					$("input#convCancel").remove();
				}
				
				$("input#submit_btn").show();
			}
			
			function renderActions(cellvalue, options, rowObject) {
				
			    return ("<div style=\"text-align:center\">"
			            	+"<a class=\"button\" title=\"<s:text name='conviviente.tooltip.edit'/>\" href=\"#\" onclick=\"getConviviente("+rowObject["id"]+")\"> <img src=\"images/16x16/Write2.png\"> </a>"
			       			+"</div>");
			}
			
			function renderActionsIngEgr(cellvalue, options, rowObject) {
				
			    return ("<div style=\"text-align:center\">"
			            	+"<a class=\"button\" title=\"Ver las prácticas realizadas en este periodo\" href=\"#\" onclick=\"getConviviente("+rowObject["id"]+")\"> <img src=\"images/16x16/ZoomIn.png\"/> <span class=\"text-action\">Ver Prácticas</span> </a>"
			       			+"</div>");
			}

			function confirmar () {
				return confirm("Realmente desea eliminarla?");
			}

			function renderActionsPracticas(cellvalue, options, rowObject) {
				console.info(rowObject["id"]);
				
			    return ("<div style=\"text-align:center\">"
			            	+"<a class=\"button\" title=\"Mostrar Detalle\" href=\"visualizarPractica.action?idPractica="+rowObject["id"]+"\"> <img src=\"images/16x16/ZoomIn.png\"> </a>"
			            	+"<a class=\"button\" title=\"Editar la practica\" href=\"editarPractica.action?idPractica="+rowObject["id"]+"\"> <img src=\"images/16x16/Write2.png\"> </a>"
			            	+"<a class=\"button\" onclick=\"return confirmar()\" title=\"Eliminar la practica\" href=\"eliminarPractica.action?idPractica="+rowObject["id"]+"\"> <img src=\"images/16x16/Minus.png\"> </a>"
			       			+"</div>");
			}

			
			</script>
			
    	</div>
	
	
	<%} %>

   	<jsp:include page="../../footer.jsp"/>		
 
</body>

<style type="text/css">
	<!--
	.ui-jqgrid .ui-jqgrid-bdiv {
	  position: relative; 
	  margin: 0em; 
	  padding:0; 
	  /*overflow: auto;*/ 
	  overflow-x:hidden; 
	  overflow-y:auto; 
	  text-align:left;
	}
	-->
</style>

</html>	