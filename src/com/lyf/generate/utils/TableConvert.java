package com.lyf.generate.utils;
import org.apache.commons.lang.StringUtils;

public final class TableConvert {

    private TableConvert(){}

    public static String getNullAble(String nullable){
        if("YES".equalsIgnoreCase(nullable) || "Y".equalsIgnoreCase(nullable) || "t".equals(nullable)) {
            return "Y";
        }else if("NO".equalsIgnoreCase(nullable) || "N".equalsIgnoreCase(nullable) || "f".equals(nullable)) {
            return "N";
        }else {
            return null;
        }
    }

    public static String getNullString(String nullable){
        if(StringUtils.isBlank(nullable))
            return "";
        else
            return nullable;
    }

    public static String getV(String s){
        return NUtils.appStrs("","'",s,"'");
    }
}