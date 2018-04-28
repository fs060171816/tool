package web.boss.services;

import java.util.List;
import java.util.Map;
import org.boss.models.${ClassName}Model;
import org.boss.services.${ClassName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.util.ResultModel;
import web.boss.dao.${ClassName}Dao;
import web.communal.AbstractBaseRedisDao;
import com.alibaba.dubbo.config.annotation.Service;

/**
 *	对外接口实现类
 *	@author ${classAuthor}
 */
@Service
public class Default${ClassName}Service extends AbstractBaseRedisDao<String,${ClassName}Model> implements ${ClassName}Service {

	@Autowired
	private ${ClassName}Dao ${className}Dao;
	
	/**
	 * 添加${functionName}信息
	 * @param ${className}Model
	 */
	public ResultModel add${ClassName}Data(${ClassName}Model ${className}Model){
		${className}Dao.add${ClassName}Model(${className}Model);
		return new ResultModel("${functionName}添加成功!");
	}
	
	/**
	 * 修改${functionName}信息
	 * @param ${className}Model
	 */
	public ResultModel update${ClassName}Data(${ClassName}Model ${className}Model){
		${className}Dao.update${ClassName}Model(${className}Model);
		return new ResultModel("${functionName}修改成功!");
	}
	
	/**
	 * 删除${functionName}信息
	 * @param ${className}Model
	 */
	public ResultModel delete${ClassName}Data(${ClassName}Model ${className}Model){
		${className}Dao.delete${ClassName}Model(${className}Model);
		return new ResultModel("${functionName}撤销成功!");
	}
	
	/**
	 * 根据${functionName}对象查找${functionName}信息
	 * @param ${className}Model
	 */
	public ${ClassName}Model load${ClassName}DataById(${ClassName}Model ${className}Model){
		return ${className}Dao.load${ClassName}ModelById(${className}Model);
	}
	
	/**
	 * 根据${functionName}对象查询${functionName}分页信息list
	 * @param Map
	 * @return
	 */
	public String searchPageData(Map<String, Object> params){
		return ${className}Dao.searchPageData(params);
	}
}
