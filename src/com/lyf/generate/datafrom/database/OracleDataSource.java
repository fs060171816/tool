package com.lyf.generate.datafrom.database;

/**
 * Created by Administrator on 2018/3/28.
 */
public class OracleDataSource extends DataBaseSource {

    @Override
    public String getTableSql(String databseName, String tableName) {
        StringBuffer sb = new StringBuffer("SELECT TABLE_NAME AS TBNAME,COMMENTS AS TBCOMM")
                .append("FROM USER_TAB_COMMENTS T WHERE T.TABLE_TYPE = 'TABLE' ");
        if(tableName != null) {
            sb.append(" AND T.TABLE_NAME IN('").append(tableName).append("')");
        }
        return tableName.toString();
    }
}
