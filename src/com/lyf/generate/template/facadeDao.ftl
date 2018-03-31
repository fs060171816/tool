package org.${moduleName}.services;

import java.util.Map;
import org.boss.models.${ClassName}Model;
import org.util.ResultModel;

/**
 * ${functionName}接口
 * @author ${classAuthor}
 *
 */
public interface ${ClassName}Service {

	/**
	 * 添加${functionName}信息
	 * @param ${className}Data
	 */
	public ResultModel add${ClassName}Data(${ClassName}Model ${className}Model);
	
	/**
	 * 修改${functionName}信息
	 * @param ${className}Data
	 */
	public ResultModel update${ClassName}Data(${ClassName}Model ${className}Model);
	
	/**
	 * 删除${functionName}信息
	 * @param ${className}Data
	 */
	public ResultModel delete${ClassName}Data(${ClassName}Model ${className}Model);
	
	/**
	 * 根据${functionName}对象查找${functionName}信息
	 * @param ${className}Model
	 */
	public ${ClassName}Model load${ClassName}DataById(${ClassName}Model ${className}Model);
	
	/**
	 * 根据${functionName}对象查询${functionName}分页信息list
	 * @param Map
	 * @return
	 */
	public String searchPageData(Map<String, Object> params);
}
