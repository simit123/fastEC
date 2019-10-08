package com.example.latte_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public final class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private Filer mFilter = null;
    private TypeMirror mTypeMirror = null;
    private String mPackageName = null;

    public void setFilter(Filer mFilter) {
        this.mFilter = mFilter;
    }

    @Override
    public Void visitString(String s, Void p) {

        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        mTypeMirror = t;
        generateJavaCode();
        return p;
    }

    private void generateJavaCode() {
        final TypeSpec targetActivity = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();
        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件").build();

        try {
            javaFile.writeTo(mFilter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
