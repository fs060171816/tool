<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.${moduleName}.mappings.${ClassName}Mapping">

    <resultMap id="${className}Map" type="${className}Model">
		<#list originalColumns as po>
		<result column="${po.fieldDbName}" property="${po.fieldName}" />
		</#list>
	</resultMap>

	<!-- 查询条件   -->
	<sql id="queryDynSql">
		<#list originalColumns as po>
		<if test="${className}Model.${po.fieldName} != null and ${className}Model.${po.fieldName} != ''"><![CDATA[ and r.${po.fieldDbName} = ${r"#{"}${className}Model.${po.fieldName}${r"}"}]]></if>
		</#list>
	</sql>
	
	<!-- 查询序列号 -->
	<select id="loadSequences" resultType="java.lang.Integer">
		<![CDATA[ select SEQ_${ClassName}.NEXTVAL from dual ]]>
	</select>
	
	<!-- 添加  -->
	<insert id="add${ClassName}Model" parameterType="${className}Model" >
		<![CDATA[insert into ${DBtableName}(]]>
		<#list originalColumns as po>
		<if test="${po.fieldName} != null and ${po.fieldName} != ''"><#if !po_has_next><![CDATA[${po.fieldDbName}]]></if><#break></#if><![CDATA[${po.fieldDbName},]]></if>
		</#list>
		<![CDATA[) values(]]>
		<#list originalColumns as po>
		<if test="${po.fieldName} != null and ${po.fieldName} != ''"><#if !po_has_next><![CDATA[${r"#{"}${po.fieldName}${r"}"}]]></if><#break></#if><![CDATA[${r"#{"}${po.fieldName}${r"}"},]]></if>
		</#list>
		<![CDATA[ )]]>
	</insert>
	
	<!-- 修改  -->
	<update id="update${ClassName}Model" parameterType="${className}Model">
		<![CDATA[update ${DBtableName} ]]>
		<set>
		<#list originalColumns as po>
			<#if !po_has_next><if test="${po.fieldName} != null"><![CDATA[ ${po.fieldDbName} = ${r"#{"}${po.fieldName}${r"}"}]]></if><#break></#if>
			<if test="${po.fieldName} != null and ${po.fieldName} != ''"><![CDATA[ ${po.fieldDbName} = ${r"#{"}${po.fieldName}${r"}"},]]></if>
		</#list>
		</set>
		<![CDATA[ where <#list originalColumns as po> ${po.fieldName} = ${r"#{"}${po.fieldName}${r"}"}<#break></#list> ]]>
	</update>
	
	<!-- 删除 -->
	<update id="delete${ClassName}Model" parameterType="${className}Model">
		<![CDATA[delete from  ${DBtableName} where <#list originalColumns as po>${po.fieldDbName}=${r"#{"}${po.fieldName}${r"}"}<#break></#list> ]]>
	</update>
	
	<!-- 查询单个根据id -->
	<select id="load${ClassName}ModelById" parameterType="${className}Model" resultMap="${className}Map">
		<![CDATA[ select <#list originalColumns as po><#if !po_has_next>r.${po.fieldDbName}<#break></#if>r.${po.fieldDbName},</#list> from ${DBtableName} r where <#list originalColumns as po>${po.fieldDbName}=${r"#{"}${po.fieldName}${r"}"}<#break></#list> ]]>
	</select>
	
	<!-- 查询个数 -->
	<select id="count${ClassName}Model" parameterType="java.util.Map" resultType="java.lang.Integer">
		<![CDATA[
			select count(1) from ${DBtableName} r  where 1=1
		]]>
		<include refid="queryDynSql" />
	</select>
	
	<!-- 查询,返回到list -->
	<select id="list${ClassName}Model" parameterType="java.util.Map" resultMap="${className}Map">
		<![CDATA[
			select <#list originalColumns as po><#if !po_has_next>AA.${po.fieldName}<#break></#if>AA.${po.fieldName},</#list> from ( 
				select <#list originalColumns as po>r.${po.fieldDbName},</#list>ROW_NUMBER() OVER(order by ${r"${"}pageableData.sort${r"}"} ${r"${"}pageableData.order${r"}"} )  RowNumber from ${DBtableName} r  where 1=1
		]]>
		<include refid="queryDynSql" />
		<![CDATA[
			) AA where AA.RowNumber BETWEEN ${r"#{"}pageableData.first${r"}"} and ${r"#{"}pageableData.last${r"}"}
		]]>
	</select>
</mapper>