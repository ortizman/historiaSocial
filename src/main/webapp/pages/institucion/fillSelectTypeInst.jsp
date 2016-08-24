<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="ar.com.historiasocial.entities.InstitutionType" %>

<select> 
<s:iterator value="tiposInstituciones" >
	<option value='<s:property value="id" />'><s:property value="name" /></option> 
</s:iterator>
</select>

