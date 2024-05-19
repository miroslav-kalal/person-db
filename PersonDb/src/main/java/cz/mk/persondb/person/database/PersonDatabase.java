package cz.mk.persondb.person.database;

import cz.mk.persondb.person.entity.Person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class PersonDatabase {

    private final Set<Person> persons = new HashSet<>();

    private static PersonDatabase instance = new PersonDatabase();

    private PersonDatabase() {

    }

    public static PersonDatabase getInstance() {
        return instance;
    }

    public void save(Person person) {
        persons.add(person);
    }

    public Optional<Person> findByPredicate(Predicate<Person> personPredicate) {
        for(var person : persons) {
            if (personPredicate.test(person)) {
                return Optional.of(person);
            }
        }

        return Optional.empty();
    }

    public void delete(Person person) {
        persons.remove(person);
    }

}
