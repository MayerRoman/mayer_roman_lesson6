package com.epam.main.task_1.cache.impl;

import com.epam.main.task_1.cache.Cache;

import java.util.Map;

/**
 * Created by Mayer Roman on 26.05.2016.
 */
@com.epam.main.task_1.annotation.Cache(name = "packagePrivateCache")
public class PackagePrivateCache implements Cache {

    private Map<Integer, String> cache;

    @Override
    public void put(Map<Integer, String> cache) {
        this.cache = cache;
    }

    @Override
    public Map<Integer, String> get() {
        return cache;
    }
}