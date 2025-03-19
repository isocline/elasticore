package io.elasticore.springboot3.bean;

import io.elasticore.runtime.port.DbmsService;
import io.elasticore.runtime.port.ExternalService;
import io.elasticore.springboot3.dbms.DbmsServiceProxyFactory;
import io.elasticore.springboot3.http.HttpServiceProxyFactory;
import org.reflections.Reflections;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;
import java.util.List;

public class PortServiceRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext; // Store the ApplicationContext instance
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment; // Environment 객체 저장
    }

    /**
     * 주어진 패키지 경로에서 부모 패키지를 계산하는 유틸리티 메서드
     *
     * @param packageName 현재 패키지 이름
     * @param levelsUp    상위로 이동할 레벨 수
     * @return 계산된 상위 패키지 이름
     */
    private String getParentPackage(String packageName, int levelsUp) {
        String[] parts = packageName.split("\\.");
        if (parts.length <= levelsUp) {
            throw new IllegalArgumentException(
                    "Cannot navigate " + levelsUp + " levels up from package: " + packageName);
        }
        return String.join(".", java.util.Arrays.copyOf(parts, parts.length - levelsUp));
    }


    private String detectMainPackage() {
        List<String> candidates = SpringFactoriesLoader.loadFactoryNames(SpringBootApplication.class, getClass().getClassLoader());

        for (String className : candidates) {
            try {
                Class<?> mainClass = Class.forName(className);
                if (mainClass.isAnnotationPresent(SpringBootApplication.class)) {
                    return mainClass.getPackage().getName();
                }
            } catch (ClassNotFoundException ignored) {
                // 클래스가 없으면 무시
            }
        }
        throw new IllegalStateException("Could not detect main package");
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        //String currentPackage = this.getClass().getPackage().getName();
        //String basePackage = getParentPackage(currentPackage, 2);

        String basePackage = detectMainPackage();
        Reflections reflections = new Reflections(basePackage);

        // @ExternalService 어노테이션이 붙은 클래스 검색
        Set<Class<?>> extPortServices = reflections.getTypesAnnotatedWith(ExternalService.class);
        // port
        for (Class<?> serviceClass : extPortServices) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(serviceClass); // Bean 클래스 설정
            definition.setInstanceSupplier(() -> createExternalPortProxy(serviceClass)); // 프록시 생성
            // definition.setInstanceSupplier(() ->
            // ServiceProxyFactory.createService(serviceClass));
            registry.registerBeanDefinition(serviceClass.getSimpleName(), definition); // Bean 등록
        }

        // dbms
        Set<Class<?>> dbmsServices = reflections.getTypesAnnotatedWith(DbmsService.class);
        for (Class<?> serviceClass : dbmsServices) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(serviceClass); // Bean 클래스 설정
            definition.setInstanceSupplier(() -> createDbmsPortProxy(serviceClass)); // 프록시 생성
            // definition.setInstanceSupplier(() ->
            // ServiceProxyFactory.createService(serviceClass));
            registry.registerBeanDefinition(serviceClass.getSimpleName(), definition); // Bean 등록
        }

    }

    private Object createExternalPortProxy(Class<?> serviceClass) {
        ExternalService externalService = serviceClass.getAnnotation(ExternalService.class);
        return HttpServiceProxyFactory.createService(serviceClass, externalService, applicationContext, environment);
    }

    private Object createDbmsPortProxy(Class<?> serviceClass) {
        DbmsService externalService = serviceClass.getAnnotation(DbmsService.class);
        return DbmsServiceProxyFactory.createService(serviceClass, externalService, applicationContext, environment);
    }

}
