package com.example.latte_compiler.compiler;

import com.example.latte_annotations.annotations.AppRegisterGenerator;
import com.example.latte_annotations.annotations.EntryGenerator;
import com.example.latte_annotations.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SuppressWarnings("unused")
@AutoService(Process.class)
public class LatteProcessor extends AbstractProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {

        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();

        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }

        return types;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;

    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);

        return true;
    }


    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor) {

        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }

    }


    private void generateEntryCode(RoundEnvironment env) {

       final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFilter(processingEnv.getFiler());
       scan(env,EntryGenerator.class,entryVisitor);

    }

    private void generatePayEntryCode(RoundEnvironment env) {

        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setFilter(processingEnv.getFiler());
        scan(env,PayEntryGenerator.class,payEntryVisitor);

    }

    private void generateAppRegisterCode(RoundEnvironment env) {

        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor();
        appRegisterVisitor.setFilter(processingEnv.getFiler());
        scan(env,AppRegisterGenerator.class,appRegisterVisitor);

    }
}
