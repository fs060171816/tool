package com.lyf.generate.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 常用方法集
 * @author lyf
 */
public class NUtils {

    /***
     * 判断一个字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.trim().length() < 1;
    }

    /***
     * 判断一个对象的字符串值是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        return isEmpty(convertToStr(obj));
    }

    /***
     * 判断一个集合是否为空
     * @param list
     * @return
     */
    public static boolean isEmpty(List<?> list){
        return list == null || list.size() < 1;
    }

    /***
     * 替换空值
     * @param str
     * @return
     */
    public static String replaceNull(String str){
        return replaceNull(str,"");
    }

    /***
     * 替换空值
     * @param str
     * @param defVal
     * @return
     */
    public static String replaceNull(String str,String defVal){
        return isEmpty(str) ? defVal : str;
    }

    /***
     * 将一个对象转为字符串
     * @param obj
     * @return
     */
    public static String convertToStr(Object obj){
        if(obj != null)
            return obj.toString();
        else
            return "";
    }

    /***
     * 将一个对象转为数字
     * @param obj
     * @return
     */
    public static Long convertToLong(Object obj){
        String str = convertToStr(obj);
        if(!isEmpty(str)){
            return new Long(str);
        }

        return new Long(0);
    }

    /***
     * 拼接字符串
     * @return
     */
    public static String appStrs(Object... strs){
        return appStrs(",", strs);
    }

    /***
     * 拼接字符串
     * @return
     */
    public static String appStrs(String sp,Object... strs){
        StringBuffer sb = new StringBuffer();
        // 循环
        if(strs != null && strs.length > 0){
            for(Object str : strs){
                sb.append(convertToStr(str)).append(sp);
            }
            if(!"".equals(sp))
                return sb.substring(0,sb.length()-1);
            else
                return sb.toString();
        }
        return "";
    }

    /***
     * 获得参数Map
     * @param key
     * @param val
     * @return
     */
    public static Map<String,Object> getParamsMap(String key, Object val){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(key, val);
        return params;
    }

    /***
     * 复制属性
     * @param source
     * @param target
     * @throws Exception
     */
    public static void copyProperties(Object source,Object target) throws Exception{
        copyProperties(source, target,true);
    }

    /***
     * 复制属性
     * @param source
     * @param target
     * @throws Exception
     */
    public static void copyProperties(Object source,Object target,boolean copyNull) throws Exception{
        if(source == null || target == null)
            return;

        // 字段映射
        Map<String,Field> targetMap = new HashMap<String, Field>();
        // 映射方字段数组
        Field[] targetFields = target.getClass().getDeclaredFields();
        // 循环
        for(Field field : targetFields){
            targetMap.put(field.getName().toLowerCase(),field);
        }

        // 字段数组
        Field[] sourceFields = source.getClass().getDeclaredFields();
        // 单个
        Field targetField = null;
        // 循环
        for(Field field : sourceFields){
            targetField = targetMap.get(field.getName().toLowerCase());
            if(targetField == null){
                continue;
            }

            // 判断是否类型相同
            if(targetField.getType().getName().equals(field.getType().getName())){
                field.setAccessible(true);
                targetField.setAccessible(true);

                if(!copyNull && isEmpty(field.get(source))){
                    continue;
                }
                targetField.set(target,field.get(source));
            }
        }
    }

    /***
     * 将对象转为Map
     * @param source
     * @throws Exception
     */
    public static Map<String,Object> convertToMap(Object source) throws Exception{
        Map<String,Object> resultMap = new HashMap<String, Object>();
        if(source == null)
            return resultMap;

        // 映射方字段数组
        Field[] fields = source.getClass().getDeclaredFields();
        // 循环
        for(Field field : fields){
            field.setAccessible(true);
            resultMap.put(field.getName(),field.get(source));
        }

        return resultMap;
    }

    /**
     * 根据集合移除Key值
     * @param keys
     */
    public static void removeMapByList(List<String> keys,Map<?,?> map){
        // 循环并移除
        if(!isEmpty(keys)){
            for(String key : keys){
                map.remove(key);
            }
        }
    }

    /***
     * 首字母大写
     * @param str
     * @return
     */
    public static String firstToUppercase(String str){
        return appStrs("",str.substring(0,1).toUpperCase(),str.substring(1).toLowerCase());
    }
}