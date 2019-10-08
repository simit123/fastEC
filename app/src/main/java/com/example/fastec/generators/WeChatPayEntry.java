package com.example.fastec.generators;


import com.example.latte_annotations.annotations.PayEntryGenerator;
import com.example.latte_core.app.wechat.templates.WXPayEntryTemplate;


@PayEntryGenerator(
        packageName = "com.example.fastec",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
