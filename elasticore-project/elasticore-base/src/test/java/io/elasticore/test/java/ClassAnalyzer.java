package io.elasticore.test.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
//import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ClassAnalyzer {

    public static void main(String[] args) throws Exception {
        // 소스 파일 경로
        String filePath = "C:\\workspace\\xsk_project\\jps-poc\\src\\main\\java\\com\\xsolcorpkorea\\kb\\poc\\pf\\entity\\CaseAssmdExpense.java";
        String code = new String(Files.readAllBytes(Paths.get(filePath)));

        // 타입 해석을 위한 SymbolSolver 설정
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

        // 소스 코드를 파싱하여 CompilationUnit 생성
        CompilationUnit compilationUnit = StaticJavaParser.parse(code);

        // 클래스 선언을 찾음 (여러 클래스가 있을 수 있음)
        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(classDeclaration -> {
            // 클래스 이름 출력
            System.out.println("클래스 이름: " + classDeclaration.getName());

            // 상속 여부 확인
            if (classDeclaration.getExtendedTypes().isNonEmpty()) {
                System.out.println("상속받은 클래스: ");
                for (ClassOrInterfaceType extendedType : classDeclaration.getExtendedTypes()) {
                    System.out.println(extendedType.getName());
                }
            }

            // 구현된 인터페이스 확인
            if (classDeclaration.getImplementedTypes().isNonEmpty()) {
                System.out.println("구현된 인터페이스: ");
                for (ClassOrInterfaceType implementedType : classDeclaration.getImplementedTypes()) {
                    System.out.println(implementedType.getName());
                }
            }

            // 클래스에 정의된 어노테이션 확인
            if (classDeclaration.getAnnotations().isNonEmpty()) {
                System.out.println("클래스 어노테이션: ");
                for (AnnotationExpr annotation : classDeclaration.getAnnotations()) {
                    System.out.println(annotation.getName());
                    try {
                        ResolvedAnnotationDeclaration resolvedAnnotation = annotation.resolve();
                        System.out.println("어노테이션 타입: " + resolvedAnnotation.getName());
                    }catch (Throwable e) {

                    }
                    //resolvedAnnotation.ifPresent(ann -> System.out.println("어노테이션 타입: " + resolvedAnnotation.getName()));
                }
            }

            // 필드 선언을 분석
            System.out.println("필드: ");
            classDeclaration.getFields().forEach(fieldDeclaration -> {
                // 필드 이름 및 타입 출력
                fieldDeclaration.getVariables().forEach(variable -> {
                    System.out.println("필드 이름: " + variable.getName());
                    System.out.println("필드 타입: " + variable.getType());
                });

                // 필드에 정의된 어노테이션 확인
                if (fieldDeclaration.getAnnotations().isNonEmpty()) {
                    System.out.println("필드 어노테이션: ");
                    for (AnnotationExpr annotation : fieldDeclaration.getAnnotations()) {
                        System.out.println(annotation.getName());
                    }
                }

                // 필드 주석 확인
                fieldDeclaration.getComment().ifPresent(comment -> {
                    System.out.println("필드 주석: " + comment.getContent());
                });
            });

            // 메서드 선언 분석 (선택사항: 필요시 추가)
            System.out.println("메서드: ");
            classDeclaration.getMethods().forEach(method -> {
                System.out.println("메서드 이름: " + method.getName());
                method.getComment().ifPresent(comment -> {
                    System.out.println("메서드 주석: " + comment.getContent());
                });

                // 메서드에 정의된 어노테이션 확인
                if (method.getAnnotations().isNonEmpty()) {
                    System.out.println("메서드 어노테이션: ");
                    for (AnnotationExpr annotation : method.getAnnotations()) {
                        System.out.println(annotation.getName());
                    }
                }
            });
        });
    }
}
