package org.boss.models;

/**
 * ${functionName} Model
 * @author ${classAuthor}
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ${ClassName}Model implements java.io.Serializable {
	<#list originalColumns as po>
	/**
	 ** ${po.filedComment}
	 **/
	private ${po.fieldType} ${po.fieldName};
	
	</#list>
	
	public ${ClassName}Model(){}
	
	<#list originalColumns as po>
	/**
	 *方法: 取得${po.filedComment}
	 *@return: ${po.fieldType}  ${po.filedComment}
	 */
	public ${po.fieldType} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.filedComment}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	
	</#list>
	
}