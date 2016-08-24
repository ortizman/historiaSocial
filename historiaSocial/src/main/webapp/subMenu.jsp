<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
boolean comp(String sp, String servPath){
	return sp.contains(servPath);
}
%>
<%
String abm = request.getParameter("abm");
String sp = request.getServletPath();
Boolean esDirector = (Boolean)session.getAttribute("esDirector");
if(esDirector != null){
	if(abm.equalsIgnoreCase("institucion")){ %>
	<div class="subMenuP">
		<div class="menuBotP">
			<div class="menuElemP <% if(comp(sp, "InstitucionesCercanas.jsp")){ %> menuSelectedP <%}%>"><a href="newSearch.action">Instituciones cercanas</a></div>
			<div class="menuElemP <% if(comp(sp, "abmTipoInstitucion.jsp")){ %> menuSelectedP <%}%>"><a href="institutionTypeIndex.action">Nuevo tipo de Instituci&oacute;n</a></div>
			<div class="menuElemP <% if(comp(sp, "abmResponsableInstitucion.jsp")){ %> menuSelectedP <%}%>"><a href="responsableInstitucion.action">Nuevo Responsable</a></div>
		</div>
	</div> 
<% 	}
	if(abm.equalsIgnoreCase("practicas")){ %>
	<div class="subMenuP">
		<div class="menuBotP">
			<div class="menuElemP <% if(comp(sp, "abmTipoPracticas.jsp")){ %> menuSelectedP <%}%>"><a href="abmTipoPracticas.action">Tipo de Pr&aacute;cticas</a></div>
		</div>
	</div>	
	
<% 	}
	if(abm.equalsIgnoreCase("profesional")){ %>
	<div class="subMenuP">
		<div class="menuBotP">
			<div class="menuElemP <% if(comp(sp, "abmServicios.jsp")){ %> menuSelectedP <%}%>"><a href="abmServicios.action">Listado de Servicios</a></div>
		</div>
	</div>	
<%
	}
	
	if(abm.equalsIgnoreCase("pacientes")){ %>
	<div class="subMenuP">
		<div class="menuBotP">
			<div style="display: none;" class="menuElemP <% if(comp(sp, "historicoPacientes.jsp")){ %> menuSelectedP <%}%>"><a href="historicoPacientes.action">Historico De Pacientes</a></div>
		</div>
	</div>	
<%
	}
	
	if(abm.equalsIgnoreCase("estadisticas")){ %>
	<div class="subMenuP">
		<div class="menuBotP">
			<div class="menuElemP <% if(comp(sp, "estadisticas.jsp")){ %> menuSelectedP <%}%>"><a href="estadisticas.jsp">Ver Estadísticas</a></div>
		</div>
	</div>	
<%
	}
}
%>
