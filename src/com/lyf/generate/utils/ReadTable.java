package com.lyf.generate.utils;

import com.lyf.generate.datafrom.CreateDataSource;
import com.lyf.generate.datafrom.DataSourceFactory;
import com.lyf.generate.dto.Column;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lyf.generate.utils.NUtils.convertToStr;
import static com.lyf.generate.utils.NUtils.replaceNull;

public class ReadTable{
    
    private Connection conn;
    
    private Statement stmt;
    
    private String sql;

    private static Map<String,String> NOTIN_COLUMN = new HashMap<>();

    /***
     * 根据表名读取行对象
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<Column> readTableColumn(String tableName)throws Exception{
        List<Column> columnList = new ArrayList<Column>();
        // 获得数据源
        CreateDataSource dataSource = DataSourceFactory.getDataSource();
        // 返回类型
        List<Map<String,Object>> dataList = dataSource.queryColumnsData(TableConvert.getV(tableName.toUpperCase()));

        for(Map<String,Object> data:dataList){
            Column column = getColumn(data);

            if(!TableUtils.isExtColumn(column.getFieldDbName())){
                System.out.println(column.getFieldDbName());
                columnList.add(column);
            }
        }
        return columnList;
    }

    /***
     * 将数据库查询的Map对象转为Column对象
     * @param data
     * @return
     * @throws SQLException
     */
    private Column getColumn(Map<String,Object> data){

        // 字段名称
        String colName = convertToStr(data.get("COLNAME"));
        String colComment = convertToStr(data.get("COLCOMMENT"));

        Column column = new Column();
        column.setFieldName(TableUtils.formatField(colName.toLowerCase()));
        column.setFieldDbName(colName.toUpperCase());
        column.setPrecision(convertToStr(data.get("COLPRECISION")));
        column.setScale(convertToStr(data.get("COLSCALE")));
        column.setCharmaxLength(convertToStr(data.get("CHARLEN")));
        column.setNullable(convertToStr(data.get("NULLABLE")));
        column.setFieldType(TableUtils.formatDataType(convertToStr(data.get("DATATYPE")).toLowerCase(), column.getPrecision(), column.getScale()));
        column.setFiledComment(replaceNull(colComment,column.getFieldName()));

        TableUtils.formatFieldClassType(column);
        
        return column;
    }
}