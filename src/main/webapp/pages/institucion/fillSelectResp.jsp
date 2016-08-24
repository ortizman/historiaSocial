<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<select> 
<s:iterator value="responsablesInstituciones" >
	<option value='<s:property value="id" />'><s:property value="name" />, <s:property value="lastname" /></option> 
</s:iterator>
</select>

