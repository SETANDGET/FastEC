package com.mgzxc.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by MG_ZXC on 2018/5/7.
 */
public enum EcIcons implements Icon{
    icon_coffe('\ue692');
    char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return  name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
