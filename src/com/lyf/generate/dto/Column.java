package com.lyf.generate.dto;

public class Column {

    public static final String OPTION_REQUIRED = "required:true";
    
    public static final String OPTION_NUMBER_INSEX = "precision:2,groupSeparator:','";
    
    public Column(){
        filedComment = "";
        fieldType = "";
        classType = "";
        classType_row = "";
        optionType = "";
        charmaxLength = "";
    }
    
    private String fieldDbName;
    private String fieldName;
    private String filedComment;
    private String fieldType;
    private String classType;
    private String classType_row;
    private String optionType;
    private String charmaxLength;
    private String precision;
    private String scale;
    private String nullable;

    public String getNullable(){
        return nullable;
    }

    public void setNullable(String nullable){
        this.nullable = nullable;
    }

    public String getPrecision(){
        return precision;
    }

    public String getScale(){
        return scale;
    }

    public void setPrecision(String precision){
        this.precision = precision;
    }

    public void setScale(String scale){
        this.scale = scale;
    }

    public String getOptionType(){
        return optionType;
    }

    public void setOptionType(String optionType){
        this.optionType = optionType;
    }

    public String getClassType(){
        return classType;
    }

    public void setClassType(String classType){
        this.classType = classType;
    }

    public String getFieldType(){
        return fieldType;
    }

    public void setFieldType(String fieldType){
        this.fieldType = fieldType;
    }

    public String getFieldName(){
        return fieldName;
    }

    public void setFieldName(String fieldName){
        this.fieldName = fieldName;
    }

    public String getFiledComment(){
        return filedComment;
    }

    public void setFiledComment(String filedComment){
        this.filedComment = filedComment;
    }

    public String getClassType_row(){
        if(classType != null && classType.indexOf("easyui-") >= 0)
            return classType.replaceAll("easyui-", "");
        else
            return classType_row;
    }

    public void setClassType_row(String classType_row){
        this.classType_row = classType_row;
    }

    public String getCharmaxLength(){
        if(charmaxLength == null || "0".equals(charmaxLength))
            return "";
        else
            return charmaxLength;
    }

    public void setCharmaxLength(String charmaxLength){
        this.charmaxLength = charmaxLength;
    }

    public String getFieldDbName(){
        return fieldDbName;
    }

    public void setFieldDbName(String fieldDbName){
        this.fieldDbName = fieldDbName;
    }
}