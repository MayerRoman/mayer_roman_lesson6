package com.epam.main.task_1.caches_factory;

import com.epam.main.task_1.cache.Cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
class CachesFiller {

    public static void fillCaches(Map<String, Cache> createdCaches, HashSet<String> annotatedCacheTypes) {
        HashSet<String> cacheTypes = annotatedCacheTypes;

        String createdCacheType;
        for (Map.Entry<String, Cache> cacheEntry : createdCaches.entrySet()) {
            createdCacheType = cacheEntry.getKey();

            if (cacheTypes.contains(createdCacheType)) {
                cacheFiller(cacheEntry.getValue(), createdCacheType);
            }
        }
    }

    private static void cacheFiller(Cache cache, String cacheType) {
        HashMap<Integer, String> caches = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            caches.put(i, cacheType);
        }
        cache.put(caches);
    }

}
