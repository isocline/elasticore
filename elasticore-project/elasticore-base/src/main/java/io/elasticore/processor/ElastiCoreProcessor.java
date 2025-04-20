package io.elasticore.processor;


import io.elasticore.base.extract.FileBasedSourceFileAccessFactory;
import io.elasticore.base.extract.ModelExtractor;
import io.elasticore.base.model.ConstantParam;
import io.elasticore.base.util.ConsoleLog;
import lombok.SneakyThrows;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Map;
import java.util.Set;


@SupportedAnnotationTypes("io.elasticore.annotation.ElastiCore")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ElastiCoreProcessor extends AbstractProcessor {

    private String projectPath;
    private String generatedPath;
    private String modelPath;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        Map<String, String> options = processingEnv.getOptions();

        for (String key : options.keySet()) {
            ConsoleLog.print(" - " + key+": "+options.get(key));
        }
        this.projectPath = options.get("projectPath");
        this.generatedPath = options.get("generatedPath");
        this.modelPath = this.projectPath + ConstantParam.PROPERTY_ELCORE_HOME;

        //File f = new File(" C:\\workspace\\Isocline\\elasticore\\elasticore-project\\elasticore-template\\src\\main\\java\\io\\elasticore\\demo\\crm\\entity\\ContractGroup.java");

    }

    private boolean isProcess = false;

    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (TypeElement annotation : annotations) {

            roundEnv.getElementsAnnotatedWith(annotation).forEach(element -> {

                try {
                    ModelExtractor extractor = new ModelExtractor(this.modelPath);
                    extractor.extract(new FileBasedSourceFileAccessFactory(this.projectPath + "/"+this.generatedPath));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String message = "Hello, " + element.getSimpleName();

                String enclosingElementName = element.getEnclosingElement().toString();

                String packageNm = "io.elasticore.meta";
                String clsNm = "Mark";

                String qualifiedClassName = packageNm+ "."+clsNm;

                /*
                try {
                    JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(qualifiedClassName);
                    try (Writer writer = fileObject.openWriter()) {
                        writer.write("package " + packageNm + ";\n\n");
                        writer.write("public interface " + clsNm + " {\n");
                        writer.write("    public final static long GEN_TIME="+System.currentTimeMillis()+"L;\n");
                        writer.write("}\n");
                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                }
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);

                 */

                isProcess = true;

            });

            if(isProcess) {

                System.out.println("===================================================== ZZ");
                System.out.println("=====================================================");
                System.out.println("");
                System.out.println("");

                return true;
            }

        }

        return false;
    }
}
