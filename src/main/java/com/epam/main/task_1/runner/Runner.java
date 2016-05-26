package com.epam.main.task_1.runner;

import com.epam.main.task_1.cache.Cache;
import com.epam.main.task_1.caches_factory.CachesFactory;
import com.epam.main.task_1.consumer.impl.Consumer;
import com.epam.main.task_1.injector.Injector;

import java.util.Map;

/**
 * Created by Mayer Roman on 19.05.2016.
 */
public class Runner {
    public void start() {
        Map<String, Cache> caches = new CachesFactory().getCaches();

        Consumer consumer = new Consumer();

        new Injector().inject(consumer, caches);

        consumer.execute();
    }

}
