package io.elasticore.springboot3.dbms;

import io.elasticore.runtime.port.DbmsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DbmsSqlExecutor {



    <I, O> List<O> inqueryList(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType);

    <I, O> Page<O> inqueryPage(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, Pageable pageable);

    <I, O> O inqueryFirst(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType);

    int executeUpdate(DbmsService dbmsSvcMeta, String methodName, Object input);
}
