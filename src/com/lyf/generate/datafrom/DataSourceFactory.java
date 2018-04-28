package com.lyf.generate.datafrom;

import com.lyf.generate.datafrom.database.MySqlDataSource;
import com.lyf.generate.datafrom.database.OracleDataSource;
import com.lyf.generate.utils.CodeResourceUtil;

/**
 * 数据源简单工厂
 * Created by Administrator on 2018/3/28.
 */
public final class DataSourceFactory {
    private DataSourceFactory(){

    }

    public static CreateDataSource getDataSource(){
        // 目前只有一种数据源，所有仅判断数据库类型
        if (CodeResourceUtil.getDatabaseType().equals("mysql"))
            return new MySqlDataSource();
        else if (CodeResourceUtil.getDatabaseType().equals("oracle"))
            return new OracleDataSource();

        return null;
    }
}
