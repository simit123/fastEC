package com.example.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Author: WuYang
 * Date:2019/8/6
 * Description: 配置文件的存储以及获取
 */

public class Configurator {
    //WeakHashMap 中的键值对在不使用的时候会回收，最大限度的防止内存泄漏，比hashMap 要好
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    //final类型的变量命名要全大写并且用下划线隔开 LATTE_CONFIGS

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        initIcons();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    //静态内部类实现单列
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }


    /**
     * 配置api_host
     *
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 检查配置是否完成
     */
    private void checkConfiguration(){
        //写类变量和方法变量的时候尽量让它的不可变性达到最大化，即以后的程序开发中不需要修改的变量，使用final 修饰
        //1.避免修改 2.性能优化
        final boolean isReady = (boolean)LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,please call configure");
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;

    }

    /**
     * 根据key 获取各个配置项信息
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")//忽略警告的注解
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

    private void initIcons(){
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1;i < ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }

    }

}
