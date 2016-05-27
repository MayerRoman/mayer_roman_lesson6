package com.epam.main.task_1.caches_factory;

import com.epam.main.task_1.cache.Cache;

import java.util.List;
import java.util.Map;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
public class CachesFactory {

    private String cachePackage = "com.epam.main.task_1.cache.impl";

    public Map<String, Cache> getCaches() {

        List<Class<?>> cacheClasses = CachesFinder.findClassesInPackage(cachePackage);

        Map<String, Cache> createdCaches = CachesCreator.createCaches(cacheClasses);

        CachesFiller.fillCaches(createdCaches);

        return createdCaches;
    }
}
