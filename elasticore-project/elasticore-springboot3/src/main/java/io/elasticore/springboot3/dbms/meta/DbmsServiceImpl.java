package io.elasticore.springboot3.dbms.meta;


import io.elasticore.runtime.port.DbmsService;

import java.lang.annotation.Annotation;

public class DbmsServiceImpl implements DbmsService {

    private final String id;
    private final String datasource;
    private final String sqlSource;

    public DbmsServiceImpl(String id, String datasource, String sqlSource) {
        this.id = id;
        this.datasource = datasource;
        this.sqlSource = sqlSource;
    }

    @Override
    public String datasource() {
        return datasource;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String sqlSource() {
        return sqlSource;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return DbmsService.class;
    }
}
