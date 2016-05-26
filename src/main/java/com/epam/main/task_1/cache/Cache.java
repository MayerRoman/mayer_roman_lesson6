package com.epam.main.task_1.cache;

import java.util.HashMap;

/**
 * Created by Mayer Roman on 19.05.2016.
 */
public interface Cache {

    void put(HashMap<Integer, String> cache);

    HashMap<Integer, String> get();
}
