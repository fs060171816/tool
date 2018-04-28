package com.lyf.generate.datafrom.database;

/**
 * Created by Administrator on 2018/3/28.
 */
public class MySqlDataSource extends DataBaseSource{

    @Override
    public String getTableSql(String databseName,String tableName) {
        StringBuffer sb = new StringBuffer("SELECT TABLE_NAME AS TBNAME,TABLE_COMMENT AS TBCOMM")
        .append("FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '")
        .append(databseName).append("'");
        if(tableName != null){
            sb.append(" AND TABLE_NAME IN('").append(tableName).append("')");
        }
        return sb.toString();
    }

    @Override
    public String getColumnsSql() {
        StringBuffer sb = new StringBuffer("SELECT COLUMN_NAME COLNAME,DATA_TYPE DATATYPE,");
        sb.append("COLUMN_COMMENT COLCOMMENT,NUMERIC_PRECISION COLPRECISION,")
        .append("NUMERIC_SCALE COLSCALE,CHARACTER_MAXIMUM_LENGTH CHARLEN,IS_NULLABLE NULLABLE")
        .append("FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = {0} AND TABLE_SCHEMA = {1}");

        return sb.toString();
    }
}
