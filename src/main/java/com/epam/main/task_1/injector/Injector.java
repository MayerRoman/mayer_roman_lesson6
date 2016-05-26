package com.epam.main.task_1.injector;

import com.epam.main.task_1.annotation.InjectCache;
import com.epam.main.task_1.cache.Cache;
import com.epam.main.task_1.caches_factory.CachesFactory;
import com.epam.main.task_1.consumer.Consumer;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * Created by Mayer Roman on 19.05.2016.
 */
public class Injector {

    public void inject(Consumer consumer, Map<String, Cache> caches) {
        Class<?> consumerClass = consumer.getClass();

        ArrayList<Field> classFields = new ArrayList<>();
        extractFields(classFields, consumerClass);

        for (Field field : classFields) {
            injectCache(field, consumer, caches);
        }
    }

    private void extractFields(ArrayList<Field> fields, Class<?> classForExtracting) {
        Field[] thisClassFields = classForExtracting.getDeclaredFields();
        Collections.addAll(fields, thisClassFields);

        Class parentClass = classForExtracting.getSuperclass();
        if (parentClass != null) {
            extractFields(fields, parentClass);
        }
    }

    private void injectCache(Field field, Consumer consumer, Map<String, Cache> caches) {
        Annotation annotation = field.getAnnotation(InjectCache.class);
        if (annotation == null) {
            return;
        }

        InjectCache injectCache = (InjectCache) annotation;
        String cacheName = injectCache.cacheName();

        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);

            try {
                field.set(consumer, caches.get(cacheName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            field.setAccessible(false);
        } else {
            try {
                field.set(consumer, caches.get(cacheName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

