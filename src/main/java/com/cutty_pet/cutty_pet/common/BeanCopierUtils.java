package com.cutty_pet.cutty_pet.common;
import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class BeanCopierUtils {

    //    创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能
    static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<String, BeanCopier>();


    public static Object copy(Object srcObj, Object destObj) {

        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier = null;

        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, destObj, null);
        return destObj;
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}
