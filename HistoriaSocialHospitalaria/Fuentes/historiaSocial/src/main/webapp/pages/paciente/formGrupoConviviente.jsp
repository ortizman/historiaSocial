<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script/script.js"></script>
<script src="script/formGrupoConv.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de alta - Nuevo Conviviente</title>
<link rel="stylesheet" href="jquery-ui.css">
<link href="estilo.css" rel="StyleSheet" type="text/css" id="css" />
<!-- <link href="structure.css" rel="stylesheet"> -->
<link href="form.css" rel="stylesheet">
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

$(function(){
	$("input:first").focus();
	$("li[class*='ui-state-default ui-corner-top']").click(function(){
		$("#"+$(this).attr("aria-controls")).find("textarea:first").focus();
		$("#"+$(this).attr("aria-controls")).find("input:first").focus();
	});
});
</script>
</head>
<body>
<% 
Long idPaciente = (Long) request.getAttribute("idPaciente");
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
			        <li><a href="#fragment-3"><span>Grupo Conviviente</span></a></li>
					<s:set name="jspMenu" value="menu"/>
			    </ul>
			<form action="" name="agregarConvivienteForm">
				<div id="fragment-3" class="leftLabel">
			    	
					<ul>
					<input id="idPaciente" name="idPaciente" type="hidden" value="<%=idPaciente %>" />
					<div class="column1">
						<li id="foli1" class="notranslate"><label class="desc"
							id="title1" for="Field1"> Nro </label>
							<div>
								<input id="nro" name="nro" type="text"
									class="field text medium" value="" maxlength="255" tabindex="1" 
									onkeyup="" />
							</div></li>
						<li id="foli2" class="notranslate"><label class="desc"
							id="title2" for="Field2"> Apellido <span
								id="req_2" class="req">*</span>
						</label>
							<div>
								<input id="apellido" name="apellido" type="text"
									class="field text medium" value="" maxlength="255"
									tabindex="2" onkeyup="" required />
							</div>
							<p class="instruct" id="instruct2">
								<small>Apellido del conviviente</small>
							</p></li>
						<li id="foli21" class="notranslate"><label class="desc"
							id="title21" for="Field21"> Nombres <span
								id="req_21" class="req">*</span>
						</label>
							<div>
								<input id="nombres" name="nombres" type="text"
									class="field text medium" value="" maxlength="255"
									tabindex="3" onkeyup="" required />
							</div>
							<p class="instruct" id="instruct2">
								<small>Nombre del conviviente</small>
							</p></li>
						<li id="foli12" class="notranslate       "><label
							class="desc" id="title12" for="Field12"> V&iacute;nculo </label>
							<div>
								<select id="vinculo" name="vinculo" class="field select medium"
									tabindex="4">
									<option value="Madre" selected="selected">Madre</option>
									<option value="Padre">Padre</option>
									<option value="Hermano">Hermano</option>
									<option value="Hermano">Hermana</option>
									<option value="Padrino">Padrino</option>
									<option value="Madrina">Madrina	</option>
									<option value="Abuelo">Abuelo</option>
									<option value="Abuela">Abuela</option>
									<option value="Bisabuelo">Bisabuelo</option>
									<option value="Bisabuela">Bisabuela</option>
									<option value="Tío/a">Tío/a</option>
									<option value="Amigo/a">Amigo/a</option>
									<option value="Otro">Otro</option>
								</select>
							</div></li>
						<li id="foli6" class="notranslate      "><label class="desc"
							id="title6" for="Field6"> Edad </label>
							<div>
								<input id="edad" name="edad" type="text"
									class="field text medium" value="" maxlength="255" tabindex="5"
									onkeyup="" />
							</div></li>
						<li id="foli14" class="notranslate       "><label
							class="desc" id="title14" for="Field14"> Nacionalidad </label>
							<div>
								<select id="nacionalidad" name="nacionalidad" class="field select medium"
									tabindex="6">
									<option value="Argentina" selected="selected">Argentina</option>
									<option value="Boliviana">Boliviana</option>
									<option value="Chilena">Chilena</option>
									<option value="Colombiana">Colombiana</option>
									<option value="Paraguaya">Paraguaya</option>
									<option value="Peruana">Peruana</option>
									<option value="Uruguaya">Uruguaya</option>
									<option value="Venezolana">Venezolana</option>
									<option value="Ecuatoriana">Ecuatoriana</option>
									<option value="Brasileña">Brasileña</option>
									<option value="Otro">Otro</option>
								</select>								
							</div></li>								
					</div> <!-- div con columna 1 -->
					<div class="column2"> <!-- div con columna 2 -->
						<li id="foli15" class="notranslate       "><label
							class="desc" id="title15" for="Field15"> Estado civil </label>
							<div>
								<select id="estadoCivil" name="estadoCivil" class="field select medium"
									tabindex="7">
									<option value="Soltero" selected="selected">Soltero</option>
									<option value="Casado">Casado</option>
									<option value="Divorciado">Divorciado</option>
									<option value="Viudo">Viudo</option>
									<option value="Viudo">Otro</option>
								</select>
							</div></li>
						<li id="foli16" class="notranslate       "><label
							class="desc" id="title16" for="Field16"> Educacion </label>
							<div>
								<select id="educacion" name="educacion" class="field select medium"
									tabindex="8">
									<option value="Primaria incompleta" selected="selected">Primaria Incompleta</option>
									<option value="Primaria" selected="selected">Primaria</option>
									<option value="Segundaria incompleta">Segundaria Incompleta</option>
									<option value="Segundaria">Segundaria</option>
									<option value="Universitaria incompleta">Universitaria Incompleta</option>
									<option value="Universitaria">Universitaria</option>
									<option value="Otro">Otro</option>
								</select>
							</div></li>						
						<li id="foli17" class="notranslate       "><label
							class="desc" id="title17" for="Field17"> Ocupacion </label>
							<div>
								<select id="ocupacion" name="ocupacion" class="field select medium"
									tabindex="9">
									<option value="Desocupado" selected="selected">Desocupado</option>
									<option value="Contratado">Contratado</option>
									<option value="Relacion de dependencia" selected="selected">Relación de dependencia</option>
									<option value="Otro">Otro</option>
								</select>
							</div></li>
						<li id="foli18" class="notranslate       "><label
							class="desc" id="title18" for="Field18"> Obra Social </label>
							<div>
								
								<s:select name="obraSocial.id" list="%{obrasSociales}" id="obraSocial" value="%{obraSocial.id}" listKey="id" listValue="codigoObraSocial + \' ' + plan">
								</s:select>								
							</div></li>
						<li id="foli19" class="notranslate       "><label
							class="desc" id="title19" for="Field19"> Ingresos </label>
							<div>
								<select id="ingresos" name="ingresos" class="field select medium"
									tabindex="11">
									<option value="Jornada" selected="selected">Jornada</option>
									<option value="Semanal">Semanal</option>
									<option value="Quincenal">Quincenal</option>
									<option value="Mensual">Mensual</option>
									<option value="Mensual">Otro</option>
								</select>
							</div></li>
						<li id="foli20" class="notranslate">
							<fieldset>
								<!-- [if !IE | (gte IE 8)]>
								<legend id="title20" class="desc"> Convive? </legend>
								<![endif] -->
								<![if lt IE 8]>
								<label id="title20" class="desc">
								Convive?
								</label>
								<![endif]>
								<div>
									<span> <input id="convive" name="convive"
										type="checkbox" class="field checkbox convive"
										value="true" tabindex="12" />
									</span>
								</div>
							</fieldset>
						</li>
						</div> <!-- fin div con columna 2 -->
					</ul>
					
					<input type="button" style="display: block;" tabindex="13" name="submit" class="buttonSubConviviente" id="submit_btn" value="Agregar Conviviente" />  
					</form>
							<h3>Listado de Convivientes</h3>
					
							<s:url id="remoteurl" action="datosTablaPersonas" includeParams="get"/>
							<s:url id="editurl" action="crudConviviente" includeParams="get"/>
							
							<sjg:grid	
								id="gridtableConviviente"
								caption="Convivientes"
								dataType="json"
								href="%{remoteurl}"
								pager="true"
								gridModel="personas"
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
						    	navigatorView="true"
						    	navigatorViewOptions=""
						    	navigatorDelete="true"
						    	navigatorAdd="false"
						    	navigatorDeleteOptions="{reloadAfterSubmit:true}"
								editinline="false"
				    			multiselect="false"
							>
								<sjg:gridColumn name="id" index="id" hidden="true" formatter="integer" title="ID Conviviente" sortable="true" editable="true" searchoptions="{sopt:['eq']}"/>
								<sjg:gridColumn name="apellido" index="apellido" title="Apellido" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="nombres" index="nombres" title="Nombre" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="vinculo" index="vinculo" title="Vinculo" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="obraSocial.id"  hidden="true" jsonmap="obraSocial.codigoObraSocial" editrules="{edithidden:true}" index="obraSocial" title="Obra Social" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="nacionalidad" index="nacionalidad" title="Nacionalidad" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="educacion" index="educacion" title="Educacion" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="edad" index="edad" title="Edad" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="convive" index="convive" title="Convive" hidden="false" editrules="{edithidden:true}" edittype="select" formatter="select" editoptions="{value:{true:'Si', false:'No'}}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="ingresos" index="ingresos" title="Ingresos" hidden="true" editrules="{edithidden:true}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="estadoCivil" index="estadoCivil" title="Estado Civil" hidden="true" editrules="{edithidden:true}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>
								<sjg:gridColumn name="ocupacion" index="ocupacion" title="Ocupacion" hidden="true" editrules="{edithidden:true}" sortable="true" editable="true" searchoptions="{sopt:['bw','cn']}"/>								
								
								
							</sjg:grid>
						</div>
				
				
				<hr />
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
			
			</script>
    		
    	<jsp:include page="../../footer.jsp"/>		
    	</div>
	
	
	<%} %>
 
</body>
</html>	