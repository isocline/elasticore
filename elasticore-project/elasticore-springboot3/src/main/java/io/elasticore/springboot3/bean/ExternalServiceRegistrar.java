package io.elasticore.springboot3.bean;

import io.elasticore.runtime.annotation.ExternalService;
import org.reflections.Reflections;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

public class ExternalServiceRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

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

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        String currentPackage = this.getClass().getPackage().getName();

        String basePackage = getParentPackage(currentPackage, 2);
        Reflections reflections = new Reflections(basePackage);

        // @ExternalService 어노테이션이 붙은 클래스 검색
        Set<Class<?>> services = reflections.getTypesAnnotatedWith(ExternalService.class);

        for (Class<?> serviceClass : services) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(serviceClass); // Bean 클래스 설정
            definition.setInstanceSupplier(() -> createProxy(serviceClass)); // 프록시 생성
            // definition.setInstanceSupplier(() ->
            // ServiceProxyFactory.createService(serviceClass));
            registry.registerBeanDefinition(serviceClass.getSimpleName(), definition); // Bean 등록
        }
    }

    private Object createProxy(Class<?> serviceClass) {
        ExternalService externalService = serviceClass.getAnnotation(ExternalService.class);

        return HttpServiceProxyFactory.createService(serviceClass, externalService, environment);
    }

}
