package com.example.fastec.generators;


import com.example.latte_annotations.annotations.AppRegisterGenerator;
import com.example.latte_core.app.wechat.templates.AppRegisterTemplate;

@AppRegisterGenerator(
        packageName = "com.example.fastec",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
