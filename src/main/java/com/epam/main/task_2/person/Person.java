package com.epam.main.task_2.person;

/**
 * Created by Mayer Roman on 25.05.2016.
 */
public class Person {


    private String name;

    private int age;

    private Sexes sex;

    public Person(String name, int age, Sexes sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Person() {
        this.name = Names.getRandom();
        this.age = 1 + (int)(Math.random() * ((135 - 1) + 1));
        this.sex = Sexes.getRandom();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sexes getSex() {
        return sex;
    }

    public void setSex(Sexes sex) {
        this.sex = sex;
    }
}
