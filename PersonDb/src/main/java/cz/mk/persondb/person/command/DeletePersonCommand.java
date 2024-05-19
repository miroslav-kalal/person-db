package cz.mk.persondb.person.command;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.command.Command;
import cz.mk.persondb.core.command.input.InputProvider;
import cz.mk.persondb.person.service.PersonService;
import cz.mk.persondb.person.validate.PersonalIdValidator;

public class DeletePersonCommand extends Command {

    private final InputProvider inputProvider;

    private final PersonService personService = PersonService.getInstance();

    public DeletePersonCommand(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    @Override
    public void execute() {
        var personalId = inputProvider.readInput(Constants.PERSONAL_ID, PersonalIdValidator::isValid);

        personService.deleteByPersonalId(personalId);
    }

    @Override
    public String commandName() {
        return Constants.DELETE;
    }

    public static String argumentsFormat() {
        return Constants.PERSONAL_ID_INPUT_FORMAT;
    }
}
