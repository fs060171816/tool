package ${packageName}.${moduleName}.controller;

import java.util.HashMap;
import java.util.Map;
import org.boss.services.${ClassName}Service;
import org.boss.models.${ClassName}Model;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageableData;
import org.util.ResultModel;

/**
 * ${functionName}controller
 * @author ${classAuthor}
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/${className}")
public class ${ClassName}Controller{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ${ClassName}Service ${className}Service;
	
	/**
	 * 跳转到${functionName}管理页面
	 * @return
	 */
	@RequestMapping(value="${className}Frame")
	public String ${className}Frame(){
		return "${className}/${className}Frame";
	}
	
	/**
	 * 查询${functionName}信息list
	 */
	@RequestMapping(value="${className}List",produces = "text/html; charset=utf-8")
	public @ResponseBody String ${className}List(PageableData pageableData,${ClassName}Model ${className}Model){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pageableData", pageableData);
		params.put("${className}Model", ${className}Model);
		return ${className}Service.searchPageData(params);
	}
	
	/**
	 * ${functionName}操作跳转操作页面
	 * @return
	 */
	@RequestMapping(value="${className}Edit")
	public String ${className}Edit(String method,${ClassName}Model ${className}Model,Model model){
		if("edit".equals(method)){
			model.addAttribute("obj", ${className}Service.load${ClassName}DataById(${className}Model));
		}
		return "${className}/${className}Edit";
	}
	
	/**
	 * 保存${functionName}信息
	 */
	@RequestMapping(value="add${ClassName}",produces = "application/json;charset=utf-8")
	public @ResponseBody ResultModel add${ClassName}(${ClassName}Model ${className}Model){
		return ${className}Service.add${ClassName}Data(${className}Model);
	}
	
	/**
	 * 修改${functionName}信息
	 */
	@RequestMapping(value="edit${ClassName}",produces = "application/json;charset=utf-8")
	public @ResponseBody ResultModel edit${ClassName}(${ClassName}Model ${className}Model){
		return ${className}Service.update${ClassName}Data(${className}Model);
	}
	
	/**
	 * 删除${functionName}信息
	 * @param ${className}Model
	 * @param response
	 */
	@RequestMapping(value="delete${ClassName}",produces = "application/json;charset=utf-8")
	public @ResponseBody ResultModel delete${ClassName}(${ClassName}Model ${className}Model){
		return ${className}Service.delete${ClassName}Data(${className}Model);
	}
}
