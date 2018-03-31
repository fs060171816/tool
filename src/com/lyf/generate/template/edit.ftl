<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.tab_class tbody td{ padding:5px; border-bottom:1px #cccccc dotted}
</style>
<script type="text/javascript">
	function checkForm(){
		return true;
	}
</script>
<form method="post" id="form1">
	<input type="hidden" id="<#list originalColumns as po>${po.fieldName}<#break></#list>" name="<#list originalColumns as po>${po.fieldName}<#break></#list>" value="${r"${"}obj.<#list originalColumns as po>${po.fieldName}<#break></#list>${r"}"}"/>
	<table width="100%" cellpadding="0" cellspacing="1" class="tab_class">
		<tbody>
			<#list originalColumns as po>
			<tr>
				<td width="120" align="right" bgcolor="#f4f4f4">${po.filedComment}：<span style='color:red;'>*</span></td>
				<td bgcolor="#ffffff">
					<input type="text" id="${po.fieldName}" name="${po.fieldName}" value="${r"${"}obj.${po.fieldName}${r"}"}" />
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
</form>