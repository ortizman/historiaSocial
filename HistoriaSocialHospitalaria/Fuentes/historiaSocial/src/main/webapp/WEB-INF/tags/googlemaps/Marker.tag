<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>

<%@ attribute name="map_var_name" description="name of the variable wich holds the google map instance from wich this field will take its value"%>
<%@ attribute name="index" description="index in the collection of markers of this map. if not specified, 0"%>
<%@ attribute name="latitude" required="true" rtexprvalue="true" description="id html attribute for the latitude input tag" %>
<%@ attribute name="longitude" required="true" rtexprvalue="true" description="id html attribute for the longitude input tag"%>
<%@ attribute name="title" description="title of the marker"%>
<%@ attribute name="window_max_width" description="max with of the info window. By default, there's no max" %>
<%@ attribute name="animation" description="DROP... By default, no animation for markers" %>
<%@ attribute name="icon" %>

<c:if test="${empty map_var_name}">
    <c:set var="map_var_name" value="map"></c:set>
</c:if>
<c:if test="${empty index}">
    <c:set var="index" value="0"></c:set>
</c:if>
${map_var_name}_markers[${index}] = new google.maps.Marker({
    position: new google.maps.LatLng(${latitude}, ${longitude})  
    ,map: ${map_var_name}
    ,title: "${title}"
    <c:if test="${!empty animation}">
    ,animation: google.maps.Animation.${animation}
    </c:if>
    <c:if test="${!empty icon}">
    ,icon: "${icon}"
    </c:if>
    });
        
<jsp:doBody var="body"/>

<c:set var="newLine" value="
"/>
<c:set var="space" value=" "/>
<c:set var="body" value="${f:trim(body)}" />

<c:if test="${!empty body}">
    ${map_var_name}_infoWindows[${index}] = new google.maps.InfoWindow({content: "${f:replace(body,newLine,space)}"});
    google.maps.event.addListener(${map_var_name}_markers[${index}], 'click', function() {
        //${map_var_name}.setCenter(${map_var_name}_markers[${index}].getPosition());
        ${map_var_name}_infoWindows[${index}].open(${map_var_name},${map_var_name}_markers[${index}]);
    });
</c:if>    
