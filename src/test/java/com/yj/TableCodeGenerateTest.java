package com.yj;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.yj.converts.XuguTypeConvert;
import com.yj.dao.BaseDaoServiceImpl;
import com.yj.dbQuery.XuguQuery;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-28 17:06:31
 */
public class TableCodeGenerateTest {

    public static final String OUTPUT_DIR = "./src/main/java";
    public static final String PACKAGE_NAME = "com.邹敦宇.dao";
    private static final String AUTHOR = "邹敦宇";

    @Test
    public void generator() throws ClassNotFoundException {
        String jdbcDriverClassName = "com.mysql.cj.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://121.4.254.177:13306/easyshop";
        String jdbcUsername = "root";
        String jdbcPassword = "academy@db#ps";
        String[] tableNames = {
                "order"
        };
        // 数据源名称
        String dsName = "master";
        generateCode(jdbcDriverClassName, jdbcUrl, jdbcUsername, jdbcPassword, tableNames, dsName);
    }

    /**
     * 生成代码
     *
     * @param jdbcDriverClassName
     * @param jdbcUrl
     * @param jdbcUsername
     * @param jdbcPassword
     * @param tableNames
     * @param dsName
     * @return
     * @author 邹敦宇
     * @date 2022-05-28 17:05:16
     **/
    private static void generateCode(String jdbcDriverClassName, String jdbcUrl, String jdbcUsername,
            String jdbcPassword, String[] tableNames, String dsName) throws ClassNotFoundException {
        Class.forName(jdbcDriverClassName);
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(jdbcUrl, jdbcUsername,
                jdbcPassword);
        // 适配xugu数据库
        if (jdbcUrl.startsWith("jdbc:xugu")) {
            dataSourceConfigBuilder
                    .dbQuery(new XuguQuery())
                    .typeConvert(XuguTypeConvert.INSTANCE)
                    .schema(jdbcUsername);
        }
        FastAutoGenerator.create(dataSourceConfigBuilder)
                .globalConfig(builder -> builder
                        .disableOpenDir()
                        .outputDir(OUTPUT_DIR)
                        .author(AUTHOR)
                        .enableSwagger()
                        .dateType(DateType.ONLY_DATE)
                        .commentDate("yyyy-MM-dd HH:mm:ss"))
                .packageConfig(builder -> builder
                        .parent(PACKAGE_NAME + "." + dsName)
                        .serviceImpl("service"))
                .templateConfig(builder -> builder
                        .service("")
                        .serviceImpl("templates/service.java")
                        .controller(""))
                .injectionConfig(builder -> builder
                        .customMap(Collections.singletonMap("dsName", dsName)))
                .strategyConfig(builder -> builder
                        .addInclude(tableNames)
                        .entityBuilder()
                        .enableChainModel()
                        .enableLombok()
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .serviceBuilder()
                        .superServiceImplClass(BaseDaoServiceImpl.class)
                        .formatServiceImplFileName("%sService"))
                .execute();
    }
}
