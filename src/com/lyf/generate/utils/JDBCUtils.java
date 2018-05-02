package com.lyf.generate.utils;

// 静态导入所有配置属性
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lyf.generate.utils.CodeResourceUtil.*;

/**
 * JDBC相关操作
 * Created by Administrator on 2018/3/28.
 */
public class JDBCUtils {

    /***
     * 返回执行的SQL
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Map<String,Object>> querySQL(String sql) throws Exception{
        System.out.println(sql);

        // 返回值
        List<Map<String,Object>> result = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            // 创建连接
            Class.forName(getDiverName());
            conn = DriverManager.getConnection(getURL(), getUserName(), getPasword());
            // 获得返回值
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            // 获得有多少行返回值
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();

            Map<String,Object> rsMap = null;
            while (rs.next()){
                rsMap = new HashMap<>();
                for(int i=1;i<= count;i++){
                    rsMap.put(metaData.getColumnName(i),rs.getString(i));
                }
                result.add(rsMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null)
                rs.close();
            if(stm != null)
                stm.close();
            if(conn != null)
                conn.close();
        }
        return result;
    }
}
