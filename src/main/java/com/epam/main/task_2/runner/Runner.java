package com.epam.main.task_2.runner;

import com.epam.main.task_2.person.Person;
import com.epam.main.task_2.person.Sexes;

import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Mayer Roman on 25.05.2016.
 */
public class Runner {

    public void start() {
        Supplier<Person> personSupplier = Person::new;

        System.out.println("getAverageAge:");
        getAverageAge(Stream.generate(personSupplier));

        System.out.println("\n" + "findPersonsWithSameName:");
        findPersonsWithSameName(Stream.generate(personSupplier));

        System.out.println("\n" + "sortingInAscendingAge:");
        sortingInAscendingAge(Stream.generate(personSupplier));

        System.out.println("\n" + "filterUnderage:");
        filterUnderage(Stream.generate(personSupplier));

        System.out.println("\n" + "applyFunction reduceWomanAge toStream:");
        Consumer<Person> reduceWomanAge = person -> {
            if (person.getAge() > 10 && person.getSex() == Sexes.FEMALE) {
                person.setAge(person.getAge() - 10);
            }
            System.out.println(person);
        };
        applyFunctionToStream(Stream.generate(personSupplier), reduceWomanAge);
    }

    private void getAverageAge(Stream<Person> personStream) {

        OptionalDouble averageAge = personStream
                .limit(10L)
                .mapToInt(Person::getAge)
                .average();
        System.out.println("average age: " + averageAge.getAsDouble());

    }

    private void findPersonsWithSameName(Stream<Person> personStream) {

        personStream.limit(100L)
                .map(Person::getName)
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .forEach((name, count) -> {
                    if (count > 1) System.out.println(name + ": " + count);
                });

    }

    private void sortingInAscendingAge(Stream<Person> personStream) {
        personStream = showStreamBeforeChange(personStream);

        personStream.limit(10L)
                .sorted((a, b) -> a.getAge() - b.getAge())
                .forEach(System.out::println);

    }

    private void applyFunctionToStream(Stream<Person> personStream, Consumer<Person> function) {
        personStream = showStreamBeforeChange(personStream);

        personStream.limit(10L)
                .forEach(function);

    }

    private void filterUnderage(Stream<Person> personStream) {
        personStream = showStreamBeforeChange(personStream);

        personStream.limit(10L)
                .filter((person) -> person.getAge() < 18)
                .forEach(System.out::println);

    }

    private Stream<Person> showStreamBeforeChange(Stream<Person> personStream) {
        List<Person> persons = personStream.limit(10L).collect(Collectors.toList());
        System.out.println("Stream before change:");
        persons.forEach(System.out::println);
        System.out.println("Stream after change:");
        return persons.stream();

    }


}
