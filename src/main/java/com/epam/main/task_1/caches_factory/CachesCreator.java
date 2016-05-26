package com.epam.main.task_1.caches_factory;

import com.epam.main.task_1.cache.Cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
class CachesCreator {

    public static Map<String, Cache> createCaches(ArrayList<Class<?>> classes) {
        ArrayList<Class<?>> cacheClasses = classes;

        com.epam.main.task_1.annotation.Cache cacheAnnotation;
        String cacheAnnotationName;

        HashMap<String, Cache> cacheTypesAndInstances = new HashMap<>();
        for (Class aClass : cacheClasses) {
            cacheAnnotation =
                    (com.epam.main.task_1.annotation.Cache) aClass.getAnnotation(com.epam.main.task_1.annotation.Cache.class);

            if (cacheAnnotation != null) {
                cacheAnnotationName = cacheAnnotation.name();

                try {
                    Cache createdCache = (Cache) aClass.newInstance();
                    cacheTypesAndInstances.put(cacheAnnotationName, createdCache);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return cacheTypesAndInstances;
    }
}
