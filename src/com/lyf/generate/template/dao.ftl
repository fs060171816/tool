package ${packageName}.${moduleName}.dao;

import java.util.Map;
import org.boss.models.${ClassName}Model;

/**
 *	${functionName}dao接口
 *  @author ${classAuthor}
 *  @version 1.0
 */
public interface ${ClassName}Dao {

	/**
	 * 添加${functionName}信息
	 * @param ${className}Model
	 */
	public int add${ClassName}Model(${ClassName}Model ${className}Model);
	
	/**
	 * 修改${functionName}信息
	 * @param ${className}Model
	 */
	public int update${ClassName}Model(${ClassName}Model ${className}Model);
	
	/**
	 * 删除${functionName}信息
	 * @param ${className}Model
	 */
	public int delete${ClassName}Model(${ClassName}Model ${className}Model);
	
	/**
	 * 根据${functionName}对象查找${functionName}信息
	 * @param ${className}Model
	 */
	public ${ClassName}Model load${ClassName}ModelById(${ClassName}Model ${className}Model);
	
	/**
	 * 根据${functionName}对象查询${functionName}分页信息list
	 * @param params
	 * @return
	 */
	public String searchPageData(Map<String, Object> params);
}
