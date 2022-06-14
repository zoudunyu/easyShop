package com.yj.dbQuery;

import com.baomidou.mybatisplus.generator.config.querys.AbstractDbQuery;

public class XuguQuery extends AbstractDbQuery {
    @Override
    public String tablesSql() {
        return "SELECT A.TABLE_NAME, A.COMMENTS FROM ALL_TABLES A INNER JOIN ALL_SCHEMAS B ON A.SCHEMA_ID = B.SCHEMA_ID WHERE B.SCHEMA_NAME = '%s'";
    }

    @Override
    public String tableFieldsSql() {
        return "SELECT B.COL_NAME,B.TYPE_NAME,B.COMMENTS, '' AS KEY FROM ALL_TABLES A " +
                "INNER JOIN ALL_COLUMNS B ON A.TABLE_ID = B.TABLE_ID " +
                "INNER JOIN ALL_SCHEMAS C ON A.SCHEMA_ID = C.SCHEMA_ID " +
                "WHERE C.SCHEMA_NAME ='#schema' AND A.TABLE_NAME = '%s'";
    }

    @Override
    public String tableName() {
        return "TABLE_NAME";
    }

    @Override
    public String tableComment() {
        return "COMMENTS";
    }

    @Override
    public String fieldName() {
        return "COL_NAME";
    }

    @Override
    public String fieldType() {
        return "TYPE_NAME";
    }

    @Override
    public String fieldComment() {
        return "COMMENTS";
    }

    @Override
    public String fieldKey() {
        return "KEY";
    }
}
