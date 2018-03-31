package ${packageName}.${moduleName}.mappings;

import java.util.List;
import java.util.Map;
import org.boss.models.${ClassName}Model;

/**
 * ${functionName}mybatis接口
 * @author ${classAuthor}
 * @version 1.0
 */
public interface ${ClassName}Mapping{
	
	/**
	 * 查询序列号
	 * @return
	 */
	public Integer loadSequences();
	
	/**
	 * 添加${functionName}信息
	 * @param ${className}Model
	 */
	public void add${ClassName}Model(${ClassName}Model ${className}Model);
	
	/**
	 * 修改${functionName}信息
	 * @param ${className}Model
	 */
	public void update${ClassName}Model(${ClassName}Model ${className}Model);
	
	/**
	 * 删除${functionName}信息
	 * @param ${className}Model
	 */
	public void delete${ClassName}Model(${ClassName}Model ${className}Model);
	
	/**
	 * 根据${functionName}对象查找${functionName}信息
	 * @param ${className}Model
	 */
	public ${ClassName}Model load${ClassName}ModelById(${ClassName}Model ${className}Model);
	
	/**
	 * 根据${functionName}对象查询${functionName}个数 
	 * @param params
	 * @return
	 */
	public int count${ClassName}Model(Map<String, Object> params);
	
	/**
	 * 根据${functionName}对象信息查询list
	 * @param params
	 * @return
	 */
	public List<${ClassName}Model> list${ClassName}Model(Map<String, Object> params);

}
