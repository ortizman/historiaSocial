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
Integer idPaciente = (Integer)request.getAttribute("idPaciente");
String user = (String)session.getAttribute("user");
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
			        <li><a href="#fragment-3"><span>Historia Social del Paciente: </span></a></li>
					<s:set name="jspMenu" value="menu"/>
			    </ul>
							<h3>Ingresos y Egresos del Servicio Social</h3>
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
						    	navigatorViewOptions=""
						    	navigatorDelete="false"
						    	navigatorAdd="false"
						    	navigatorDeleteOptions="{reloadAfterSubmit:true}"
								editinline="false"
				    			multiselect="false"
							>
								<sjg:gridColumn name="id" index="id" hidden="true" formatter="integer" title="ID Conviviente" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="fechaIngreso" index="fechaIngreso" title="Fecha Ingreso" sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="FechaInterCons" index="FechaInterCons" title="Fecha Inter Cons." sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaInternacion" index="fechaInternacion" title="Fecha Internación" sortable="true" editable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="detalleDiagnosticoIngreso"  index="detalleDiagnosticoIngreso" title="Diagnóstico de Ingreso" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaAltaIniciada" index="fechaAltaIniciada" title="Alta Iniciada" sortable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaAltaEjecutada" index="fechaAltaEjecutada" title="Alta Ejecutada" sortable="true" formatter="date" formatoptions="{newformat : 'd/m/Y', srcformat : 'Y-m-d H:i:s'}" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="detalleDiagnosticoAlta" index="detalleDiagnosticoAlta" title="Diagnostico de Egreso" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="actions" search="false" fixed="false" width="100" editrules="{edithidden:true}" dataType="html"  index="" title="Acciones" formatter="renderActions"/>								
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
								<sjg:gridColumn name="paciente" index="apellido" jsonmap="tratamiento.historiaSocial.paciente.nombreCompleto" title="Pacienete" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaPractica" jsonmap="fechaPractica" index="nombres" title="Fecha Práctica" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="fechaCarga" jsonmap="fechaCarga" index="vinculo" title="Fecha Carga" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
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
			
			</script>
    		
    	<jsp:include page="../../footer.jsp"/>		
    	</div>
	
	
	<%} %>
 
</body>

</html>	