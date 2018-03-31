package com.lyf.generate.datafrom.database;

import static com.lyf.utils.CodeResourceUtil.getDatabaseName;

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
    public String getColumnsSql(String tableName) {
        return null;
    }
}