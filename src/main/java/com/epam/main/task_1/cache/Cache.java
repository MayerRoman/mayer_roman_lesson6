package com.epam.main.task_1.cache;

import java.util.Map;

/**
 * Created by Mayer Roman on 19.05.2016.
 */
public interface Cache {

    void put(Map<Integer, String> cache);

    Map<Integer, String> get();
}
