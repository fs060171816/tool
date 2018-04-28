package com.lyf.generate.datafrom;

import java.util.List;
import java.util.Map;

/**
 * 生成数据源
 * Created by Administrator on 2018/3/28.
 */
public interface CreateDataSource {

    /***
     * 查询生成数据
     * @return
     */
    public List<Map<String,Object>> queryGenerateData() throws Exception;

    /***
     * 查询数据表数据
     * @return
     */
    public List<Map<String,Object>> queryColumnsData(String tableName) throws Exception;
}
