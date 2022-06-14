package com.yj.converts;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.STRING;

/**
 * 虚谷字段类型转换
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2020/11/13 15:05
 */
public class XuguTypeConvert implements ITypeConvert {
    public static final XuguTypeConvert INSTANCE = new XuguTypeConvert();

    public XuguTypeConvert() {
    }

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        return TypeConverts.use(fieldType)
                .test(TypeConverts.containsAny(new CharSequence[]{"char", "text", "json", "enum"}).then(
                        DbColumnType.STRING))
                .test(TypeConverts.contains("bigint").then(DbColumnType.LONG))
                .test(TypeConverts.containsAny(new CharSequence[]{"tinyint(1)", "bit"}).then(DbColumnType.BOOLEAN))
                .test(TypeConverts.contains("int").then(DbColumnType.INTEGER))
                .test(TypeConverts.contains("decimal").then(DbColumnType.BIG_DECIMAL))
                .test(TypeConverts.contains("clob").then(DbColumnType.STRING))
                .test(TypeConverts.contains("blob").then(DbColumnType.BLOB))
                .test(TypeConverts.contains("binary").then(DbColumnType.BYTE_ARRAY))
                .test(TypeConverts.contains("float").then(DbColumnType.FLOAT))
                .test(TypeConverts.contains("double").then(DbColumnType.DOUBLE))
                .test(TypeConverts.contains("numeric").then(DbColumnType.BIG_DECIMAL))
                .test(TypeConverts.containsAny(new CharSequence[]{"date", "time", "year"}).then(
                        (t) -> toDateType(config, t)))
                .or(DbColumnType.STRING);
    }

    /**
     * 转换为日期类型
     *
     * @param config
     * @param type
     * @return com.baomidou.mybatisplus.generator.config.rules.IColumnType
     * @author 邹敦宇
     * @date 2022-05-28 17:25:56
     **/
    public static IColumnType toDateType(GlobalConfig config, String type) {
        String dateType = type.replaceAll("\\(\\d+\\)", "");
        switch (config.getDateType()) {
            case ONLY_DATE:
                return DbColumnType.DATE;
            case SQL_PACK:
                switch (dateType) {
                    case "date":
                    case "year":
                        return DbColumnType.DATE_SQL;
                    case "time":
                        return DbColumnType.TIME;
                    default:
                        return DbColumnType.TIMESTAMP;
                }
            case TIME_PACK:
                switch (dateType) {
                    case "date":
                        return DbColumnType.LOCAL_DATE;
                    case "time":
                        return DbColumnType.LOCAL_TIME;
                    case "year":
                        return DbColumnType.YEAR;
                    default:
                        return DbColumnType.LOCAL_DATE_TIME;
                }
        }
        return STRING;
    }
}

