package io.elasticore.processor;


import io.elasticore.base.extract.FileBasedSrcCodeWriterFactory;
import io.elasticore.base.extract.FilerBasedSrcCodeWriterFactory;
import io.elasticore.base.extract.ModelExtractor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;


@SupportedAnnotationTypes("io.elasticore.annotation.ElastiCore")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ElastiCoreProcessor extends AbstractProcessor {

    private String projectPath;
    private String modelPath;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        Map<String, String> options = processingEnv.getOptions();
        System.err.println(">>>>>>>>>>>> " + getSupportedOptions().toString());
        for (String key : options.keySet()) {
            System.out.println("==" + key);
        }
        this.projectPath = options.get("projectPath");
        this.modelPath = this.projectPath + "/src/main/resources/blueprint";
        //this.modelPath = this.projectPath+"/src/main/java/model-blueprint";
        System.err.println("projectPath>>>>>>>>>>>> " + this.projectPath);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {



        for (TypeElement annotation : annotations) {
            roundEnv.getElementsAnnotatedWith(annotation).forEach(element -> {

                try {
                    ModelExtractor extractor = new ModelExtractor(this.modelPath);
                    extractor.extract(new FileBasedSrcCodeWriterFactory(this.projectPath + "/src/main/java"));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                String message = "Hello, " + element.getSimpleName();

                String enclosingElementName = element.getEnclosingElement().toString();

                String packageNm = "io.elasticore.meta";
                String clsNm = "Mark";

                String qualifiedClassName = packageNm+ "."+clsNm;

                try {
                    JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(qualifiedClassName);
                    try (Writer writer = fileObject.openWriter()) {
                        writer.write("package " + packageNm + ";\n\n");
                        writer.write("public interface " + clsNm + " {\n");
                        writer.write("    public final static long GEN_TIME="+System.currentTimeMillis()+"L;\n");
                        writer.write("}\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
            });
        }
        return true;
    }
}
