package com.epam.main.task_1.caches_factory;

import com.epam.main.task_1.cache.Cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
public class CachesFactory {

    private String cachePackage = "com.epam.main.task_1.cache.impl";

    private static HashSet<String> annotatedCacheTypes = new HashSet<>();
    static {
        annotatedCacheTypes.add("privateCache");
        annotatedCacheTypes.add("packagePrivateCache");
        annotatedCacheTypes.add("protectedCache");
        annotatedCacheTypes.add("publicCache");
    }

    public Map<String, Cache> getCaches() {

        ArrayList<Class<?>> cacheClasses = CachesFinder.findClassesInPackage(cachePackage);

        Map<String, Cache> createdCaches = CachesCreator.createCaches(cacheClasses);

        CachesFiller.fillCaches(createdCaches, annotatedCacheTypes);

        return createdCaches;
    }
}
