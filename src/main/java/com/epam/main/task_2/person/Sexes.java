package com.epam.main.task_2.person;

/**
 * Created by Mayer Roman on 25.05.2016.
 */
public enum  Sexes {
    male,
    female;

    public static Sexes getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
