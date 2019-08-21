package com.example.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by admin on 2019/8/19.
 */

public enum  EcIcons implements Icon{
    icon_scan('\ue681'),
    icon_ali_pay('\ue679')

    ;

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
