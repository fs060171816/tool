package com.lyf.generate.datafrom.database;

import com.lyf.generate.dto.Column;
import com.lyf.generate.datafrom.CreateDataSource;
import com.lyf.utils.CodeResourceUtil;
import com.lyf.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 抽象类，代表数据库类数据源
 * Created by Administrator on 2018/3/28.
 */
public abstract class DataBaseSource implements CreateDataSource {

    /***
     * 查询生成数据
     * @return
     */
    public List<Map<String,Object>> queryGenerateData() throws Exception {
        return JDBCUtils.querySQL(getTableSql(CodeResourceUtil.getDatabaseName(),CodeResourceUtil.getTableName()));
    }

    /***
     * 根据表名加载行信息
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<Column> loadColumnsByTableName(String tableName)throws Exception{
        // 行信息列表
        List<Column> columnList = new ArrayList<Column>();
        return columnList;
    }

    /***
     * 获得数据来源SQL
     * @return
     */
    public abstract String getTableSql(String databseName, String tableName);

    /***
     * 获得行元数据来源SQL
     * @param tableName
     * @return
     */
    public abstract String getColumnsSql(String tableName);
}
