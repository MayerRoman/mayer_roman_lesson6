package com.epam.main.task_1.consumer.impl;

import com.epam.main.task_1.annotation.InjectCache;
import com.epam.main.task_1.cache.Cache;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
public class ParentConsumer extends GrandParentConsumer {

    @InjectCache(cacheName = "privateCache")
    private Cache parentPrivateCache;

    @InjectCache(cacheName = "publicCache")
    public Cache parentPublicCache;

    @InjectCache(cacheName = "protectedCache")
    protected Cache parentProtectedCache;

    @InjectCache(cacheName = "packagePrivateCache")
    Cache parentPackagePrivateCache;


    @Override
    public void execute() {
        System.out.println("ParentConsumer caches:");
        System.out.println(parentPrivateCache.get());
        System.out.println(parentPublicCache.get());
        System.out.println(parentProtectedCache.get());
        System.out.println(parentPackagePrivateCache.get());
        super.execute();
    }
}
