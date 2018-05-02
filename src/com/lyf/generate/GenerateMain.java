package com.lyf.generate;

import com.google.common.collect.Maps;
import com.lyf.generate.datafrom.CreateDataSource;
import com.lyf.generate.datafrom.DataSourceFactory;
import com.lyf.generate.dto.Column;
import com.lyf.generate.utils.DateUtils;
import com.lyf.generate.utils.FreeMarkers;
import com.lyf.generate.utils.ReadTable;
import com.lyf.generate.utils.StringHelper;
import freemarker.template.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

// 静态导入所有配置属性
import static com.lyf.generate.utils.CodeResourceUtil.*;

/**
 * 代码生成器
 * @author szs
 * @version 2013-03-15
 */
public class GenerateMain{

    private static Logger logger = LoggerFactory.getLogger(GenerateMain.class);

    public static void main(String[] args) throws Exception{
        // 获得数据源
        CreateDataSource dataSource = DataSourceFactory.getDataSource();
        // 返回类型
        List<Map<String,Object>> dataList = dataSource.queryGenerateData();
        if(dataList != null && dataList.size() > 0){
            String tbName = null,comment = null;
            System.out.println(dataList);
            for(Map<String,Object> data:dataList){
                tbName = String.valueOf(data.get("TBNAME"));
                comment = String.valueOf(data.get("TBCOMM"));

                createCodeByTable(tbName, comment == null ? "" : comment);
            }
        }
    }

    /***
     * 创建代码文件
     * @param tableName
     * @param functionName
     * @throws Exception
     */
    private static void createCodeByTable(String tableName, String functionName) throws Exception{
        System.out.println("开始生成：" + tableName);

        // 包名
        String packageName = StringUtils.lowerCase(getPackageName());
        // 文件分隔符
        String separator = File.separator;
        // java路径
        String javaPath = getJavaPath() + separator + StringUtils.lowerCase(packageName).replace(".", separator);
        // view路径
        String viewPath = getViewPath();
        // class路径
        String classPath = new DefaultResourceLoader().getResource("").getFile().getPath();
        // 代码模板配置
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(classPath + separator + "htd/generate/template"));
        
        // 获得模板变量
        Map<String, Object> model = createModel(tableName,functionName);
        // 开始名称
        String beginName = javaPath + separator + model.get("moduleName") + separator;

        ReadTable dbFiledUtil = new ReadTable();
        List<Column> columns = dbFiledUtil.readTableColumn(tableName);
        model.put("columns", columns);
        model.put("originalColumns", columns);
        for (Column c:columns){
            if (c.getFieldName().toLowerCase().equals("id")) {
                model.put("primary_key_type", c.getFieldType());
                break;
            }
        }
        
        // 生成 Model
        String filePath = beginName + "model" + separator + model.get("ClassName") + "Model.java";
        createFileFromTemplate(cfg,model,"model.ftl",filePath);
        
        // 生成 mapping
        filePath = beginName + "mapping" + separator + model.get("ClassName") + ".xml";
        createFileFromTemplate(cfg,model,"ibatis.ftl",filePath);
        
        // 生成 service
        filePath = beginName + "services" + separator + model.get("ClassName") + "Mapping.java";
        createFileFromTemplate(cfg,model,"service.ftl",filePath);
        
        // 生成 dao
        filePath = beginName + "dao" + separator + model.get("ClassName") + "Dao.java";
        createFileFromTemplate(cfg,model,"dao.ftl",filePath);
        
        // 生成 impl
        filePath = beginName + "impl" + separator+ "Default" + model.get("ClassName") + "Dao.java";
        createFileFromTemplate(cfg,model,"impl.ftl",filePath);
        
        // 生成 facadeDao
        filePath = beginName + "facadeDao" + separator + model.get("ClassName") + "Service.java";
        createFileFromTemplate(cfg,model,"facadeDao.ftl",filePath);
        
        // 生成 facadeImpl
        filePath = beginName + "facadeImpl" + separator+"Default" + model.get("ClassName") + "Service.java";
        createFileFromTemplate(cfg,model,"facadeImpl.ftl",filePath);
        
        // 生成 controller
        filePath = beginName + "controller" + separator + model.get("ClassName") + "Controller.java";
        createFileFromTemplate(cfg,model,"controller.ftl",filePath);
        
        // 生成 frame
        filePath = beginName + "jsp" + separator + model.get("className") + "Frame.jsp";
        createFileFromTemplate(cfg,model,"frame.ftl",filePath);
        
        // 生成frame js
        filePath = beginName + "jsp" + separator + model.get("className") + "Frame.js";
        createFileFromTemplate(cfg,model,"framejs.ftl",filePath);
        
        // 生成frame js
        filePath = beginName + "jsp" + separator + model.get("className") + "Edit.jsp";
        createFileFromTemplate(cfg,model,"edit.ftl",filePath);
      
        System.out.println("生成成功：" + tableName);
        
        logger.info(filePath);
        logger.info("代码生成成功！");
    }
    
    /**
     * 将内容写入文件
     * @param content
     * @param filePath
     */
    public static void writeFile(String content, String filePath){
        try{
            File f = new File(filePath);
            if(!f.exists())
                f.getParentFile().mkdirs();
            
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * 根据模板生成文件
     * @param cfg
     * @param model
     * @param tempName
     * @param filePath
     * @throws Exception
     */
    private static void createFileFromTemplate(Configuration cfg,Map<String, Object> model,String tempName,String filePath) throws Exception {
        String content = FreeMarkers.renderTemplate(cfg.getTemplate(tempName), model);
        writeFile(content, filePath);
        logger.info(filePath);
    }

    /***
     * 创建Model数据
     * @param tableName
     * @return
     */
    private static Map<String, Object> createModel(String tableName,String functionName){
        // 包名
        String packageName = StringUtils.lowerCase(getPackageName());
        // 模块名，例：sys
        String moduleName = StringUtils.lowerCase(getModuleName());
        // 子模块名（可选）
        String subModuleName = getSubModuleName();
        // 类名，例：user
        String className = StringHelper.getClassName(tableName, getTableRemovePrefixes());
        // 类作者，例：szs
        String classAuthor = getClassAuthor();

        Map<String, Object> model = Maps.newHashMap();
        model.put("packageName", packageName);
        model.put("DBtableName", tableName);
        model.put("moduleName", moduleName);
        model.put("subModuleName", StringUtils.isNotBlank(subModuleName) ? "" + StringUtils.lowerCase(subModuleName): "");
        model.put("className", StringUtils.uncapitalize(className));
        model.put("ClassName", StringUtils.capitalize(className));
        model.put("classAuthor", StringUtils.isNotBlank(classAuthor) ? classAuthor : "Generate Tools");
        model.put("classVersion", DateUtils.getDate());
        model.put("functionName", functionName);
        model.put("tableName",moduleName + getSepSubModuleName(subModuleName,"_") + "_" + className);
        model.put("urlPrefix",moduleName + getSepSubModuleName(subModuleName,"/") + "/" + className);
        model.put("viewPrefix", "/modules/"+ model.get("urlPrefix"));
        model.put("permissionPrefix", moduleName + getSepSubModuleName(subModuleName,":") + ":" + className);

        return model;
    }

    /***
     * 返回子模块名称
     * @param subModuleName
     * @param separator
     * @return 依据子模块名称是否为空返回
     */
    private static String getSepSubModuleName(String subModuleName,String separator){
        return StringUtils.isNotBlank(subModuleName) ? separator + StringUtils.lowerCase(subModuleName) : "";
    }
}