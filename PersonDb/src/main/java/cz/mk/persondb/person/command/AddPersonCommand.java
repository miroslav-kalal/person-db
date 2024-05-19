package cz.mk.persondb.person.command;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.command.Command;
import cz.mk.persondb.core.command.input.InputProvider;
import cz.mk.persondb.person.entity.Person;
import cz.mk.persondb.person.service.PersonService;
import cz.mk.persondb.person.validate.PersonalIdValidator;

import java.util.function.Predicate;

public class AddPersonCommand extends Command {

    private final InputProvider inputProvider;

    private final PersonService personService = PersonService.getInstance();

    public AddPersonCommand(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    @Override
    public void execute() {
        Predicate<String> nameValidator = (String input) -> input != null && !input.isBlank();
        var name = inputProvider.readInput(Constants.NAME, nameValidator);
        var surname = inputProvider.readInput(Constants.SURNAME, nameValidator);
        var personalId = inputProvider.readInput(Constants.PERSONAL_ID, PersonalIdValidator::isValid);

        personService.savePerson(new Person(name, surname, personalId));
    }

    @Override
    public String commandName() {
        return Constants.ADD;
    }

    public static String argumentsFormat() {
        return Constants.PERSON_INPUT_FORMAT;
    }
}
