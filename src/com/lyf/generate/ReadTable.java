package com.lyf.generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyf.generate.dto.Column;
import com.lyf.utils.CodeResourceUtil;
import com.lyf.utils.NUtils;
import org.apache.commons.lang.StringUtils;

public class ReadTable{
    
    private Connection conn;
    
    private Statement stmt;
    
    private String sql;

    private static Map<String,String> NOTIN_COLUMN = new HashMap<>();

    static{
        if(NOTIN_COLUMN.size() == 0){
            NOTIN_COLUMN.put("","");
            NOTIN_COLUMN.put("","");
            NOTIN_COLUMN.put("","");
            NOTIN_COLUMN.put("","");
            NOTIN_COLUMN.put("","");
            NOTIN_COLUMN.put("","");
        }
    }
    
    public List<Column> readTableColumn(String tableName)throws Exception{
        List<Column> columntList = new ArrayList<Column>();
        ResultSet rs = null;
        try{
            Class.forName(CodeResourceUtil.getDiverName());
            conn = DriverManager.getConnection(CodeResourceUtil.getURL(),
                    CodeResourceUtil.getUserName(),
                    CodeResourceUtil.getPasword());
            stmt = conn.createStatement(1005, 1007);
            if (CodeResourceUtil.getDatabaseType().equals("mysql"))
                sql =
                    MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
                        new Object[] {TableConvert.getV(tableName.toUpperCase()),
                            TableConvert.getV(CodeResourceUtil.getDatabaseName())});
            if (CodeResourceUtil.getDatabaseType().equals("oracle"))
                sql =
                    MessageFormat.format("select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}  order by colstable.column_id",
                        new Object[] {TableConvert.getV(tableName.toUpperCase())});
            if (CodeResourceUtil.getDatabaseType().equals("postgresql"))
                sql =
                    MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ",
                        new Object[] {TableConvert.getV(tableName.toLowerCase())});
            rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                Column columnt = getColumn(rs);
                
                if (!"CREATE_BY".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"CREATE_DATE".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"UPDATE_BY".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"UPDATE_DATE".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"REMARKS".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"DEL_FLAG".equalsIgnoreCase(columnt.getFieldDbName())){
                    System.out.println(columnt.getFieldDbName());
                    columntList.add(columnt);
                }
            }
            
        }catch (ClassNotFoundException e){
            throw e;
        }catch (SQLException e){
            throw e;
        }finally{
            if(stmt != null){
                stmt.close();
            }
            
            if(conn != null){
                conn.close();
            }
            
            if(rs != null){
                rs.close();
            }
        }
        
        return columntList;
    }
    
    public Column getColumn(ResultSet rs)throws SQLException{
    	Column columnt = new Column();
        columnt.setFieldName(formatField(rs.getString(1).toLowerCase()));
        columnt.setFieldDbName(rs.getString(1).toUpperCase());
        columnt.setPrecision(TableConvert.getNullString(rs.getString(4)));
        columnt.setScale(TableConvert.getNullString(rs.getString(5)));
        columnt.setCharmaxLength(TableConvert.getNullString(rs.getString(6)));
        columnt.setNullable(TableConvert.getNullAble(rs.getString(7)));
        columnt.setFieldType(formatDataType(rs.getString(2).toLowerCase(), columnt.getPrecision(), columnt.getScale()));
        formatFieldClassType(columnt);
        columnt.setFiledComment(StringUtils.isBlank(rs.getString(3)) ? columnt.getFieldName() : rs.getString(3));
        
        return columnt;
    }

    /***
     * 根据查询结果获得行对象
     * @param dataList
     * @return
     * @throws SQLException
     */
    public static List<Column> getColumns(List<Map<String,Object>> dataList)throws SQLException{
        List<Column> columnList = new ArrayList<>();
        // 判断参数是否为空
        if(!NUtils.isEmpty(dataList)){
            Column column = null;
            String colName = null;
            for (Map<String,Object> data:dataList){
                column = new Column();
                colName = NUtils.convertToStr(data.get("colName"));
                column.setFieldName(formatField(colName.toLowerCase()));
                column.setFieldDbName(colName.toUpperCase());
                column.setPrecision(NUtils.convertToStr(data.get("numericPrecision")));
                column.setScale(NUtils.convertToStr(data.get("numericScale")));
                column.setCharmaxLength(NUtils.convertToStr(data.get("charmaxLength")));
                column.setNullable(NUtils.convertToStr(data.get("nullable")));
                column.setFieldType(formatDataType(NUtils.convertToStr(data.get("dataType")).toLowerCase(), column.getPrecision(), column.getScale()));
                formatFieldClassType(column);
                column.setFiledComment(NUtils.replaceNull(NUtils.convertToStr(data.get("comment")),column.getFieldName()));
            }
        }

        return columnList;
    }
    
    public List<Column> readOriginalTableColumn(String tableName)throws Exception{
        List<Column> columntList = new ArrayList<Column>();
        ResultSet rs = null;
        try
        {
            Class.forName(CodeResourceUtil.getDiverName());
            conn =
                DriverManager.getConnection(CodeResourceUtil.getURL(),
                    CodeResourceUtil.getUserName(),
                    CodeResourceUtil.getPasword());
            stmt = conn.createStatement(1005, 1007);
            if (CodeResourceUtil.getDatabaseType().equals("mysql"))
                sql =
                    MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
                        new Object[] {TableConvert.getV(tableName.toUpperCase()),
                            TableConvert.getV(CodeResourceUtil.getDatabaseName())});
            if (CodeResourceUtil.getDatabaseType().equals("oracle"))
                sql =
                    MessageFormat.format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}  order by colstable.column_id",
                        new Object[] {TableConvert.getV(tableName.toUpperCase())});
            if (CodeResourceUtil.getDatabaseType().equals("postgresql"))
                sql =
                    MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ",
                        new Object[] {TableConvert.getV(tableName.toLowerCase())});
            rs = stmt.executeQuery(sql);
            
            Column columnt = null;
            
            while (rs.next()){
                columnt = getColumn(rs);
                if (!"CREATE_BY".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"CREATE_DATE".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"UPDATE_BY".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"UPDATE_DATE".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"REMARKS".equalsIgnoreCase(columnt.getFieldDbName())
                    && !"DEL_FLAG".equalsIgnoreCase(columnt.getFieldDbName())){
                    columntList.add(columnt);
                }
            }
            
        }catch (ClassNotFoundException e){
            throw e;
        }catch (SQLException e){
            throw e;
        }finally{
            
            if (stmt != null){
                stmt.close();
            }
            
            if (conn != null){
                conn.close();
            }
            
            if (rs != null){
                rs.close();
            }
        }
        
        return columntList;
    }

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
    
    private static void formatFieldClassType(Column column){
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
    
    private static String formatDataType(String dataType, String precision, String scale){
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
    
}