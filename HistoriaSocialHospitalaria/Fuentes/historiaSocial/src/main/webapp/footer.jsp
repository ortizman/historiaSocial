<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="footer">
	<div class="pie"><s:text name="footer.institutionName"/></div>
	<div class="pie">Direcci&#243;n: <s:text name="footer.institutionAddress"/></div>
	<div class="pie">Tel&#233;fono: <s:text name="footer.institutionContact"/> </div>
</div>

<!-- Este css corrige el bug de jqgrid donde siempre queda el scroll horizontal  -->
<style type="text/css">
	<!--
	.ui-jqgrid .ui-jqgrid-bdiv {
	  position: relative; 
	  margin: 0em; 
	  padding:0; 
	  /*overflow: auto;*/ 
	  overflow-x:hidden; 
	  overflow-y:auto; 
	  text-align:left;
	}
	-->
</style>