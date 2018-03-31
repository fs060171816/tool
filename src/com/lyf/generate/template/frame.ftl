<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${functionName}</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/easyui/themes/bootstrap/easyui.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/easyui/themes/icon.css'/>"/>
	<script type="text/javascript" src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/easyui/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/ajax.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/syUtilIndex.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/view/${className}/${className}Frame.js'/>"></script>
</head>
<body>
	<div id="tb">
		<div class="datagrid-toolbar" id="menutoolbar">
			<a href="javascript:void(0)"  class="easyui-linkbutton" onclick="add${ClassName}()">
					<span class="l-btn-text icon-save" style="padding-left: 20px;">${functionName}新增</span>
			</a>
			<div class="datagrid-btn-separator" ></div>
			<a href="javascript:void(0)"  class="easyui-linkbutton" onclick="edit${ClassName}()">
					<span class="l-btn-text icon-edit" style="padding-left: 20px;">${functionName}修改</span>
			</a>
			<div class="datagrid-btn-separator" ></div>
			<a href="javascript:void(0)"  class="easyui-linkbutton" onclick="delete${ClassName}()">
					<span class="l-btn-text icon-remove" style="padding-left: 20px;">${functionName}撤销</span>
			</a>
		</div>
		<div style="clear: both">
			<form id="${ClassName}">
				<#list originalColumns as po>
				<span>
					${po.filedComment}:<input type="text" name="${po.fieldName}" id="${po.fieldName}" style="width:100px;" />
				</span>
				</#list>
				<a href="javascript:void(0)" id="search${ClassName}" class="easyui-linkbutton" onclick="search${ClassName}()">
					<span class="l-btn-text icon-search" style="padding-left: 20px;">查询</span>
				</a>
			</form>
		</div>
	</div>
	<table id="${className}Table"></table>
</body>
</html>