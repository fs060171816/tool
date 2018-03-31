var datagrid;
$(function(){
	datagrid = $('#${className}Table').datagrid({
		toolbar:"#tb",
		pagination : true,
		pagePosition : 'bottom',
		url:'/${className}/${className}List',
		sortName : '<#list originalColumns as po>${po.fieldName}<#break></#list>',
		sortOrder : 'asc',
		idField : '<#list originalColumns as po>${po.fieldName}<#break></#list>',
		//queryParams : {'status' : '1'},
		rownumbers:true,
		checkOnSelect:true,
		fit:true,
		fitColumns:true,
		singleSelect:true,
		selectOnCheck :true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 100 ],
		columns:[[
			<#list originalColumns as po>
			<#if !po_has_next>{field:'${po.fieldName}',width:60,title:'${po.filedComment}',align:'center'}, <#break></#if>
			{field:'${po.fieldName}',width:60,title:'${po.filedComment}',align:'center'}, 
			</#list>
		]]
	})
})

//查询信息
function search${ClassName}() {
	datagrid.datagrid('load', sy.serializeObject($('#${ClassName}')));
}

function add${ClassName}(){
	var p = parent.sy.dialog({
			title : '${functionName}新增',
			href : '/${className}/${className}Edit?method=create',
			width : 800,
			height : 500,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '/${className}/add${ClassName}',
						onSubmit:function(){
							return parent.checkForm();
						},
						success : function(data) {
							parent.$.messager.progress('close');
							var result = jQuery.parseJSON(data);
							var message = '信息添加失败!';
							if(result.status == 'success')
							{
								message = result.msg;
								datagrid.datagrid('reload');
								p.dialog('close');
							}
							parent.sy.messagerShow({
								msg : message,
								title : '提示'
							});
						}
					});
				}
			}],
			onLoad : function() {
			}
		});
}

function edit${ClassName}(){
	var data = datagrid.datagrid('getSelected');
	if(data == null){alert("请选择一条数据!");return;}
	var p = parent.sy.dialog({
			title : '${functionName}维护',
			href : '/${className}/${className}Edit?method=edit&<#list originalColumns as po>${po.fieldName}<#break></#list>='+data.<#list originalColumns as po>${po.fieldName}<#break></#list>,
			width : 800,
			height : 500,
			buttons : [ {
				text : '${functionName}修改',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '/${className}/edit${ClassName}',
						onSubmit:function(){
							return parent.checkForm();
						},
						success : function(data) {
							var result = jQuery.parseJSON(data);
							var message = '信息修改失败!';
							if(result.status == 'success')
							{
								message = result.msg;
								datagrid.datagrid('reload');
								p.dialog('close');
							}
							parent.sy.messagerShow({
								msg : message,
								title : '提示'
							});
						}
					});
				}
			}],
			onLoad : function() {
			}
		});
}

function delete${ClassName}(){
	var data = datagrid.datagrid('getSelected');
	if(data == null){alert("请选择一条数据!");return;}
	if(confirm("是否撤销此信息?"))
	{
		var url = "/${className}/delete${ClassName}?<#list originalColumns as po>${po.fieldName}<#break></#list>="+data.<#list originalColumns as po>${po.fieldName}<#break></#list>;
		var result=new MyJqueryAjax(url,null,null,'JSON').request();
		if(result.status == 'success'){
			datagrid.datagrid('reload');
		}
		parent.sy.messagerShow({
			msg : result.msg,
			title : '提示'
		});
	}
}