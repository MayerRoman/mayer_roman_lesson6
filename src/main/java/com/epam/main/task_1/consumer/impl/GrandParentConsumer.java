package com.epam.main.task_1.consumer.impl;

import com.epam.main.task_1.annotation.InjectCache;
import com.epam.main.task_1.cache.Cache;
import com.epam.main.task_1.consumer.Consumer;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
public class GrandParentConsumer implements Consumer {

    @InjectCache(cacheName = "privateCache")
    private Cache grandParentPrivateCache;

    @InjectCache(cacheName = "publicCache")
    public Cache grandParentPublicCache;

    @InjectCache(cacheName = "protectedCache")
    protected Cache grandParentProtectedCache;

    @InjectCache(cacheName = "packagePrivateCache")
    Cache grandParentPackagePrivateCache;


    @Override
    public void execute() {
        System.out.println("GrandParentConsumer caches:");
        System.out.println(grandParentPrivateCache.get());
        System.out.println(grandParentPublicCache.get());
        System.out.println(grandParentProtectedCache.get());
        System.out.println(grandParentPackagePrivateCache.get());
    }
}
