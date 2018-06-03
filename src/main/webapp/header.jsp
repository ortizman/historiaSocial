<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 
<script type="text/javascript">
$(function(){
	$("#configuracion").click(function(){
		$(".subMenuP").hide();
		$(".menuElem").removeClass("menuSelected");
		$("#configuracion").addClass("menuSelected");
		$(".configuracionSubMenu").toggle();
		
		var link = $("#tipoDeProblematica_abm");
		if ( link.attr ( 'onclick' ) === undefined ) {
		    document.location = link.attr ( 'href' );
		} else {
		    link.click ();
		}
	});
});
</script>

<style>
<!--
	.prueba {
		background-color:darkorange; 
		padding-bottom: 10px; 
		padding-top: 5px;
		padding-left: 5px;
		padding-right: 5px;
	}
-->
</style>

<div id="header" class="headerLogin"> 
		<div id="tituloHeader">
		<!-- <a class="prueba">¡Prueba!</a>| --> 
			<a href="menu"> <s:text name="header.aplicationName"/> </a>
		</div>
		<div id="botonesHeader">
			<span id="userConnect"><s:text name="header.username"/> <s:property value="#session.profesionalLogin.nombre"/> <s:property value="#session.profesionalLogin.apellido"/>  </span>
			<a href="cerrarSesion" class="salir" id="cerrarsesion" ><s:text name="header.logout"/> </a>
		</div>
</div>

<%!
boolean comp(String sp, String servPath){
	return sp.startsWith(servPath);
}
%>
<%
String conMenu = request.getParameter("conMenu");

Boolean esDirector = (Boolean)session.getAttribute("esDirector");
String sp = request.getServletPath();
if(!"false".equals(conMenu)){
	if(esDirector != null){ %>
	<div class="menu">
		<div class="menuTop"></div>
		<ul class="menuBot">
			<li class="menuElem <% if(sp.compareToIgnoreCase("/abmPacientes.jsp")==0){ %> menuSelected <%}%>"><a href="abmPacientes.action"><s:text name="menu.patient"/></a></li>
			<li class="menuElem <%if(comp(sp, "/pages/practica/")){ %> menuSelected <%}%>"><a href="abmPracticas.action"><s:text name="menu.practices"/></a></li>
			<li class="menuElem <% if(comp(sp, "/pages/institucion/")){ %> menuSelected <%}%>"><a href="abmInstituciones.action"><s:text name="menu.institutions"/></a></li>
			<%  
			   if (esDirector){ %>
			   <li class="menuElem <%if(comp(sp, "/pages/abmProfesionales.jsp") || comp(sp, "/pages/profesional/")){ %> menuSelected <%}%>"><a href="abmProfesionales.action"><s:text name="menu.professional"/></a></li>
			   <li class="menuElem <%if(comp(sp, "/listado.jsp") || comp(sp, "/estadisticas.jsp") ){ %> menuSelected <%}%>"><a href="listado.action"><s:text name="menu.statistics"/></a></li>
			   
			<% } 
				if (esDirector){ %>
			<li class="menuElem" style="margin-left: 2%" id="configuracion"><a href="#"><s:text name="menu.configurations"/></a></li>
				<% } %>
		</ul>
	</div>
	<div class="subMenuP configuracionSubMenu" style="display: none">
		<div class="menuBotP">
			<div class="menuElemP tipoProblematicaSubMenu">
				<a href="abmTipoProblematicas.action" id="tipoDeProblematica_abm">Tipos de Problem&aacute;tica</a>
			</div>
			<div class="menuElemP especialidadSubMenu">
				<a href="abmEspecialidades.action">Especialidades</a>
			</div>
			<div class="menuElemP servicioSubMenu">
				<a href="abmServicios.action">Servicios</a>
			</div>
			<div class="menuElemP tipoPropiedadSubMenu">
				<a href="abmTipoPropiedad.action">Tipos de Propiedad</a>
			</div>
			<div class="menuElemP condicionesSubMenu">
				<a href="abmCondicionHabitacional.action">Condiciones Habitacionales</a>
			</div>
			<div class="menuElemP diagnosticoSubMenu">
				<a href="abmDiagnostico.action">Diagn&oacute;sticos</a>
			</div>
			<div class="menuElemP obraSocialSubMenu">
				<a href="abmObraSocial.action">Obras Sociales</a>
			</div>
		</div>
</div>
<%	 } 
}
%>