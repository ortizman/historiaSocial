<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" description="this tag includes the javascript apis required by the google maps tags (google maps api and jQuery). It must be placed into the 'head' html element" %>

<%@ attribute name="src" description="google maps api uri. If not entered 'http://maps.google.com/maps/api/js?sensor=false' is used"%>
<%@ attribute name="jquery" description="jQuery api uri. If not entered 'http://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.min.js'"%>

<%
if(src == null){
	src = "http://maps.google.com/maps/api/js?sensor=false";
}
%>

<%
if(jquery == null){
	jquery = "http://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.min.js";
}
%>
<script type="text/javascript" src="<%=jquery%>"></script>
<script type="text/javascript" src="<%=src%>"></script>


