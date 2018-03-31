package ${packageName}.${moduleName}.dao.impl;

import java.util.Map;
import net.sf.json.JSONArray;
import org.boss.models.${ClassName}Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import web.boss.dao.${ClassName}Dao;
import web.boss.mappings.${ClassName}Mapping;

/**
 *	${functionName}dao实现类
 *	@author ${classAuthor}
 */
@Transactional
@Service("${className}")
public class Default${ClassName}Dao implements ${ClassName}Dao {

	@Autowired
	private ${ClassName}Mapping ${className}Mapping;
	
	/**
	 * 添加${functionName}信息
	 * @param ${className}Model
	 */
	public int add${ClassName}Model(${ClassName}Model ${className}Model) {
		${className}Model.set${ClassName}id(${className}Mapping.loadSequences());
		${className}Mapping.add${ClassName}Model(${className}Model);
		return 1;
	}
	/**
	 * 修改${functionName}信息
	 * @param ${className}Model
	 */
	public int update${ClassName}Model(${ClassName}Model ${className}Model){
		${className}Mapping.update${ClassName}Model(${className}Model);
		return 1;
	}
	
	/**
	 * 删除${functionName}信息
	 * @param ${className}Model
	 */
	public int delete${ClassName}Model(${ClassName}Model ${className}Model){
		${className}Mapping.delete${ClassName}Model(${className}Model);
		return 1;
	}
	
	/**
	 * 根据${functionName}对象查找${functionName}信息
	 * @param ${className}Model
	 */
	public ${ClassName}Model load${ClassName}ModelById(${ClassName}Model ${className}Model){
		return ${className}Mapping.load${ClassName}ModelById(${className}Model);
	}
	
	/**
	 * 根据${functionName}对象查询${functionName}信息list
	 * @param ${className}Model
	 * @return
	 */
	public String searchPageData(Map<String, Object> params){
		return "{\"total\":"+${className}Mapping.count${ClassName}Model(params)+",\"rows\":"+
				JSONArray.fromObject(${className}Mapping.list${ClassName}Model(params).toArray())+"}";
	}
}
