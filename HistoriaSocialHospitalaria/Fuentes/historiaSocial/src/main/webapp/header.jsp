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


<div id="header" class="headerLogin"> 
		<div id="tituloHeader">
			<a href="menu"> Hospital de Niños La Plata </a>
		</div>
		<div id="botonesHeader">
			<span id="userConnect">Usuario: <s:property value="#session.profesionalLogin.nombre"/> <s:property value="#session.profesionalLogin.apellido"/>  </span>
			<a href="cerrarSesion" class="salir" id="cerrarsesion" >Salir</a>
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
			<li class="menuElem <% if(sp.compareToIgnoreCase("/abmPacientes.jsp")==0){ %> menuSelected <%}%>"><a href="abmPacientes.action">Pacientes</a></li>
			<li class="menuElem <%if(comp(sp, "/pages/practica/")){ %> menuSelected <%}%>"><a href="abmPracticas.action">Pr&#225;cticas</a></li>
			<li class="menuElem <% if(comp(sp, "/pages/institucion/")){ %> menuSelected <%}%>"><a href="abmInstituciones.action">Instituciones</a></li>
			<%  
			   if (esDirector){ %>
			   <li class="menuElem <%if(comp(sp, "/pages/abmProfesionales.jsp") || comp(sp, "/pages/profesional/")){ %> menuSelected <%}%>"><a href="abmProfesionales.action">Profesionales</a></li>
<%-- 			   <li class="menuElem <%if(sp.compareToIgnoreCase("/abmTipoProblematicas.jsp")==0){ %> menuSelected <%}%>"><a href="abmTipoProblematicas.action">Tipo de Problem&#225;ticas</a></li> --%>
<%-- 			   <li class="menuElem <%if(sp.compareToIgnoreCase("/abmEspecialidades.jsp")==0){ %> menuSelected <%}%>"><a href="abmEspecialidades.action">Especialidades</a></li> --%>
			   <li class="menuElem <%if(sp.compareToIgnoreCase("/listado.jsp")==0){ %> menuSelected <%}%>"><a href="listado.action">Estad&#237;sticas</a></li>
			   
			<% } %>
<%-- 			<li class="menuElem <%if(request.getServletPath().compareToIgnoreCase("/formModificarUsuarioConectado.jsp")==0){ %> menuSelected <%}%>"><a href="formModificarUsuarioConectado.action"><s:property value="#session.user"/></a></li> --%>
			
			<%  
			   if (esDirector){ %>
			<li class="menuElem" style="margin-left: 2%" id="configuracion"><a href="#">Configuraci&oacute;n</a></li>
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