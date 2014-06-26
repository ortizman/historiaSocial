<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<select> 
<s:iterator value="pacientes" >
	<option value='<s:property value="id" />'><s:property value="apellidos" />, <s:property value="nombres" /></option> 
</s:iterator>
</select>