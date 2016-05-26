package com.epam.main.task_1.consumer.impl;

import com.epam.main.task_1.annotation.InjectCache;
import com.epam.main.task_1.cache.Cache;

/**
 * Created by Mayer Roman on 19.05.2016.
 */
public class Consumer extends ParentConsumer {

    @InjectCache(cacheName = "privateCache")
    private Cache privateCache;

    @InjectCache(cacheName = "publicCache")
    public Cache publicCache;

    @InjectCache(cacheName = "protectedCache")
    protected Cache protectedCache;

    @InjectCache(cacheName = "packagePrivateCache")
    Cache packagePrivateCache;

    @Override
    public void execute() {
        System.out.println("Consumer caches:");
        System.out.println(privateCache.get());
        System.out.println(publicCache.get());
        System.out.println(protectedCache.get());
        System.out.println(packagePrivateCache.get());
        super.execute();
    }

}
