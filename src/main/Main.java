package main;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

    // ----- IMPERATIVE APPROACH ❌ ------ \\ to filter every females
//        List<Person> females = new ArrayList<>();
//
//        for (Person person : people) {
//
//          if (person.getGender().equals(Gender.FEMALE)) {
//            females.add(person);
//          }
//        }
//
//        females.forEach(System.out::println);
    // ----- IMPERATIVE APPROACH ❌  END------ \\

    // -----✅✅ DECLARATIVE APPROACH ✅✅ ------ \\
        //  ------- FILTER
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE)) // filtert(makeUPobject -> obj.filetering.condition
                .collect(Collectors.toList()); // collect back the result into a  new list

//        System.out.println("\n======= Filter =============");
//        females.forEach(System.out::println);
//        System.out.println("======= Filter =============\n");
        // --------- FILTER END

        // --------- SORT
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());

//        System.out.println("\n======= Sort =============");
//        sorted.forEach(System.out::println);
//        System.out.println("======= Sort =============\n");
        // --------- SORT END

        // --------- ALLMATCH (true if every object in the collection  match the condition)
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 8);

        System.out.println("\n======= ALL MATCH =============");
        System.out.println(allMatch);
        System.out.println("======= ALLMATCH =============\n");
        // --------- ALLMATCH END

        // --------- ANYMATCH (true if at least one object in the collection match the condition)
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 123);

        System.out.println("\n======= ANY MATCH =============");
        System.out.println(anyMatch);
        System.out.println("======= ANY MATCH =============\n");
        // --------- ANYMATCH END

        // --------- NONEMATCH (true if no object in the collection match the condition) (reverse of any match)
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Sosso"));

        System.out.println("\n======= ANY MATCH =============");
        System.out.println(noneMatch);
        System.out.println("======= ANY MATCH =============\n");
        // --------- NONEMATCH END

        // --------- MAX & MIN
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
        // --------- MAX & MIN END

        //---------- GROUP
        Map<Gender, List<Person>> mapped = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        mapped.forEach(((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
        }));
        //---------- GROUP END

        System.out.println("\n==== Example ====\n");
        //  EXAMPLE
        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemaleAge.ifPresent(System.out::println);


    // -----✅✅ DECLARATIVE APPROACH ✅✅  END------ \\
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}