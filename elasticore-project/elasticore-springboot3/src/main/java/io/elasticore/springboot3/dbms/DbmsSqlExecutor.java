package io.elasticore.springboot3.dbms;

import io.elasticore.runtime.port.DbmsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface DbmsSqlExecutor {



    <I, O> List<O> inqueryList(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    <I, O> Page<O> inqueryPage(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, Pageable pageable) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    <I, O> O inqueryFirst(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    int executeUpdate(DbmsService dbmsSvcMeta, String methodName, Object input) throws IOException, InvocationTargetException, NoSuchMethodException;
}
