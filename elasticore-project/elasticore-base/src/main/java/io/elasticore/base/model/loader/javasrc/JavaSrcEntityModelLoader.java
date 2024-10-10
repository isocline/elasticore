package io.elasticore.base.model.loader.javasrc;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.loader.module.AbstractModelLoader;
import io.elasticore.base.util.ConsoleLog;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JavaSrcEntityModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<Entity> {


    @SneakyThrows
    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {

        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

        String code = new String(Files.readAllBytes(Paths.get(source.getFilepath())));
        CompilationUnit compilationUnit = StaticJavaParser.parse(code);

        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(classDeclaration -> {
            loadModel(ctx, classDeclaration);
        });

        return false;
    }

    public boolean loadModel(ModelLoaderContext ctx, ClassOrInterfaceDeclaration classDeclaration) {
        System.out.println("class : " + classDeclaration.getName());


        String entityNm = classDeclaration.getNameAsString();

        Items<Field> items = Items.create(Field.class);

        classDeclaration.getFields().forEach(fieldDeclaration -> {

            Map<String, Annotation> annotationMap = new HashMap<>();


            // 필드에 정의된 어노테이션 확인
            if (fieldDeclaration.getAnnotations().isNonEmpty()) {

                for (AnnotationExpr annotation : fieldDeclaration.getAnnotations()) {

                    String annotationName = annotation.getName().asString().toLowerCase();
                    String annotationFullTxt = annotationName;

                    //System.out.println("필드 어노테이션: " + annotationName);

                    Annotation annotation1 = Annotation.create(annotationName, annotationFullTxt, null);
                    annotationMap.put(annotationName, annotation1);
                }
            }

            // 필드 주석 확인
            fieldDeclaration.getComment().ifPresent(comment -> {
                Annotation annotation1 = Annotation.create("label", comment.getContent(), null);
                annotationMap.put("label", annotation1);
            });


            // 필드 이름 및 타입 출력
            fieldDeclaration.getVariables().forEach(variable -> {
                String fieldNm = variable.getName().asString();
                String type = variable.getType().asString();

                System.out.println("  - " + fieldNm +": "+type);

                Field field = Field.builder().name(fieldNm)
                        .type(type)
                        .isPrimaryKey(annotationMap.containsKey("id"))
                        .unique(annotationMap.containsKey("unique"))
                        .annotationMap(annotationMap).build();

                items.addItem(field);
            });

        });

        Entity entity = Entity.create(ctx.getDomainId(), entityNm, null, items);
        ;

        ctx.getEntityItems().addItem(entity);

        return false;
    }


}
