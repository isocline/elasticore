package io.elasticore.springboot3.dbms.meta;

public class SqlQueryInfo {

    private String sql;

    private String nativeQuery;

    private String id;

    private String sqlSourceFilePath;

    private long lastModifiedTime;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqlSourceFilePath() {
        return sqlSourceFilePath;
    }

    public void setSqlSourceFilePath(String sqlSourceFilePath) {
        this.sqlSourceFilePath = sqlSourceFilePath;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public boolean getNativeQuery() {
        if("false".equals(nativeQuery)) {
            return false;
        }
        return true;
    }

    public void setNativeQuery(String nativeQuery) {
        this.nativeQuery = nativeQuery;
    }
}
