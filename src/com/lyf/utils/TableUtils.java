package com.lyf.utils;

import com.lyf.generate.dto.Column;
import org.apache.commons.lang.StringUtils;

/**
 * 数据表读取工具集
 * Created by lyf on 2018/4/28.
 */
public final class TableUtils {

    private TableUtils(){}

    /***
     * 将数据库字段格式化成Java字段
     * @param field
     * @return
     */
    public static String formatField(String field){
        // 构建字段
        StringBuilder sb = new StringBuilder();
        // 字段分隔
        String[] strs = field.split("_");
        // 循环构建
        for (String str : strs){
            if(sb.length() != 0)
                sb.append(NUtils.firstToUppercase(str.toLowerCase()));
            else
                sb.append(str.toLowerCase());
        }
        return sb.toString();
    }

    public static void formatFieldClassType(Column column){
        String fieldType = column.getFieldType();
        String scale = column.getScale();

        column.setClassType("inputxt");
        if (!"N".equals(column.getNullable()))
            column.setOptionType("*");
        if ("datetime".equals(fieldType) || fieldType.contains("time"))
            column.setClassType("easyui-datetimebox");
        else if ("date".equals(fieldType))
            column.setClassType("easyui-datebox");
        else if (fieldType.contains("int"))
            column.setOptionType("n");
        else if("number".equals(fieldType)){
            if(StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0)
                column.setOptionType("d");
        }
        else if ("float".equals(fieldType) || "double".equals(fieldType) || "decimal".equals(fieldType))
            column.setOptionType("d");
        else if ("numeric".equals(fieldType))
            column.setOptionType("d");
    }

    public static String formatDataType(String dataType, String precision, String scale){
        if (dataType.contains("char"))
            dataType = "String";
        else if (dataType.contains("bigint"))
            dataType = "Long";
        else if (dataType.contains("int"))
            dataType = "Integer";
        else if (dataType.contains("float"))
            dataType = "Float";
        else if (dataType.contains("double"))
            dataType = "Double";
        else if (dataType.contains("number")){
            if (StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0)
                dataType = "BigDecimal";
            else if (StringUtils.isNotBlank(precision) && Integer.parseInt(precision) > 10)
                dataType = "Long";
            else
                dataType = "Integer";
        }
        else if (dataType.contains("decimal"))
            dataType = "BigDecimal";
        else if (dataType.contains("time"))
            dataType = "Timestamp";
        else if (dataType.contains("date"))
            dataType = "Date";
        else if (dataType.contains("clob"))
            dataType = "Clob";
        else if (dataType.contains("numeric"))
            dataType = "BigDecimal";
        else
            dataType = "Object";
        return dataType;
    }

    /***
     * 是否扩展字段
     * @param fieldDbName
     * @return
     */
    public static boolean isExtColumn(String fieldDbName){
        String extColName = "CREATE_BY|CREATE_DATE|UPDATE_BY|UPDATE_DATE|REMARKS|DEL_FLAG|";
        return extColName.contains(fieldDbName.toUpperCase());
    }
}
