package com.lyf.generate.utils;
import java.util.ResourceBundle;

public class CodeResourceUtil{
	
	private static final ResourceBundle bundlePath = ResourceBundle.getBundle("htd/generate/generate_config");
    
    public CodeResourceUtil(){
    }
    
    public static final String getDiverName(){
        return bundlePath.getString("diver_name");
    }
    
    public static final String getURL(){
        return bundlePath.getString("url");
    }
    
    public static final String getUserName(){
        return bundlePath.getString("username");
    }
    
    public static final String getPasword(){
        return bundlePath.getString("password");
    }
    
    public static final String getDatabaseName(){
        return bundlePath.getString("database_name");
    }

    public static final String getTableName(){
        return bundlePath.getString("table_name");
    }
    
    public static final String getPackageName(){
        return bundlePath.getString("packageName");
    }
    
    public static String getModuleName(){
        return bundlePath.getString("moduleName");
    }
    
    public static final String getSubModuleName(){
        return bundlePath.getString("subModuleName");
    }
    
    public static final String getClassAuthor(){
        return bundlePath.getString("classAuthor");
    }
    
    public static final String getTableRemovePrefixes(){
        return bundlePath.getString("tableRemovePrefixes");
    }
    
    public static final String getSystemEncoding(){
        return bundlePath.getString("system_encoding");
    }
    
    public static final String getDatabaseType(){
        return bundlePath.getString("database_type");
    }
    
    public static final String getJavaPath(){
        return bundlePath.getString("javaPath");
    }
    
    public static final String getViewPath(){
        return bundlePath.getString("viewPath");
    }
}