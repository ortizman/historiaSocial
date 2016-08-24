<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="map_var_name" description="name of the variable wich holds the google map instance from wich this field will take its value"%>
<%@ attribute name="latitude_id"  rtexprvalue="true" required="true" description="id html attribute for the latitude input tag" %>
<%@ attribute name="longitude_id" rtexprvalue="true" required="true" description="id html attribute for the longitude input tag"%>

<c:if test="${empty map_var_name}">
    <c:set var="map_var_name" value="map"></c:set>
</c:if>
<c:if test="${empty latitude_id}">
    <c:set var="latitude_id" value="latitude"></c:set>
</c:if>
<c:if test="${empty longitude_id}">
    <c:set var="longitude" value="longitude"></c:set>
</c:if>

var __lat_lng_marker__ = new google.maps.Marker({
    position: ${map_var_name}.getCenter(),  
    map: ${map_var_name},
});

google.maps.event.addListener(${map_var_name}, "center_changed", function(){
   document.getElementById("${latitude_id}").value = ${map_var_name}.getCenter().lat().toString();
   document.getElementById("${longitude_id}").value = ${map_var_name}.getCenter().lng().toString();
   __lat_lng_marker__.setPosition(${map_var_name}.getCenter());
});