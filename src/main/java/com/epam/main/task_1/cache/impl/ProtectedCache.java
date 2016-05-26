package com.epam.main.task_1.cache.impl;

import com.epam.main.task_1.cache.Cache;

import java.util.HashMap;

/**
 * Created by Mayer Roman on 26.05.2016.
 */
@com.epam.main.task_1.annotation.Cache(name = "protectedCache")
public class ProtectedCache implements Cache {

    private HashMap<Integer, String> cache;

    @Override
    public void put(HashMap<Integer, String> cache) {
        this.cache = cache;
    }

    @Override
    public HashMap<Integer, String> get() {
        return cache;
    }
}