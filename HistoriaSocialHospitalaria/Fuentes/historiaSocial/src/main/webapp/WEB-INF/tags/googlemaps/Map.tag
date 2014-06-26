<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="latitude" rtexprvalue="true" required="true" %>
<%@ attribute name="longitude" rtexprvalue="true" required="true" %>
<%@ attribute name="container_id" required="true" %>
<%@ attribute name="zoom" %>
<%@ attribute name="map_type" description="valid values: ROADMAP, SATELLITE, TERRAIN, HYBRID"%>
<%@ attribute name="map_var_name" %>

<c:set var="TYPE_PREFIX" value="google.maps.MapTypeId."></c:set>
<c:if test="${empty zoom}">
    <c:set var="zoom" value="15"></c:set>
</c:if>
<c:if test="${empty map_type}">
    <c:set var="map_type" value="ROADMAP"></c:set>
</c:if>
<c:if test="${empty map_var_name}">
    <c:set var="map_var_name" value="map"></c:set>
</c:if>
<script type="text/javascript">
  jQuery(function() {
    var ${map_var_name} = new google.maps.Map(
    		document.getElementById("${container_id}"), 
    		{zoom: ${zoom},
    		 center: new google.maps.LatLng(${latitude}, ${longitude}),
             mapTypeId: ${TYPE_PREFIX}${map_type}}
    );
    var ${map_var_name}_markers = new Array();
    var ${map_var_name}_infoWindows = new Array();
    <jsp:doBody />
  });
</script>
