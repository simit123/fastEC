package com.example.fastec.generators;

import com.example.latte_annotations.annotations.EntryGenerator;
import com.example.latte_core.app.wechat.templates.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.example.fastec",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
