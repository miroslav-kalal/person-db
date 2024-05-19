package cz.mk.persondb.person.service;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.person.database.PersonDatabase;
import cz.mk.persondb.person.entity.Person;
import cz.mk.persondb.person.exception.PersonAlreadyExistsException;
import cz.mk.persondb.person.exception.PersonNotFoundException;

import java.util.Objects;
import java.util.function.Predicate;

public class PersonService {

    private final PersonDatabase personDatabase;

    private static final PersonService instance = new PersonService();

    private PersonService() {
        personDatabase = PersonDatabase.getInstance();
    }

    public static PersonService getInstance() {
        return instance;
    }

    public void savePerson(Person person) {
        if (personDatabase.findByPredicate(createPersonalIdPredicate(person.personalId())).isPresent()) {
            throw new PersonAlreadyExistsException();
        }

        personDatabase.save(person);
    }

    public Person findPersonByPersonalId(String personalId) {
        return personDatabase.findByPredicate(createPersonalIdPredicate(personalId)).orElseThrow(() -> new PersonNotFoundException(Constants.PERSONAL_ID_INFO + personalId));
    }

    public void deleteByPersonalId(String personalId) {
        var person = findPersonByPersonalId(personalId);
        personDatabase.delete(person);
    }

    private Predicate<Person> createPersonalIdPredicate(String personalId) {
        return person -> Objects.equals(person.personalId(), personalId) || person.personalId().replace(Constants.PERSONAL_ID_SEPARATOR, "").equals(personalId);
    }

}
