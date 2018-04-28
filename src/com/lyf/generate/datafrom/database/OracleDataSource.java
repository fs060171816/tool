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

    @Override
    public String getColumnsSql() {
        StringBuffer sb = new StringBuffer("SELECT COLSTABLE.COLUMN_NAME COLNAME,COLSTABLE.DATA_TYPE DATATYPE,")
        .append("COMMENTSTABLE.COMMENTS COLCOMMENT,COLSTABLE.DATA_PRECISION COLPRECISION,")
        .append("COLSTABLE.DATA_SCALE COLSCALE,COLSTABLE.CHAR_LENGTH CHARLEN,COLSTABLE.NULLABLE")
        .append("FROM USER_TAB_COLS COLSTABLE INNER JOIN USER_COL_COMMENTS COMMENTSTABLE ON ")
        .append("COLSTABLE.COLUMN_NAME = COMMENTSTABLE.COLUMN_NAME WHERE ")
        .append("COLSTABLE.TABLE_NAME = COMMENTSTABLE.TABLE_NAME AND ")
        .append("COLSTABLE.TABLE_NAME = {0} ORDER BY COLSTABLE.COLUMN_ID");

        return sb.toString();
    }
}
