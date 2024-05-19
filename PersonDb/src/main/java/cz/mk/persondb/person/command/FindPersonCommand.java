package cz.mk.persondb.person.command;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.command.Command;
import cz.mk.persondb.core.command.input.InputProvider;
import cz.mk.persondb.person.exception.InvalidPersonalIdException;
import cz.mk.persondb.person.service.PersonService;
import cz.mk.persondb.person.validate.PersonalIdValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FindPersonCommand extends Command {

    public static final String PRINT_PERSON_INFO = """
            Name: %s
            Surname: %s
            Age: %s
            Personal id: %s%n
            """;
    private final InputProvider inputProvider;

    private final PersonService personService = PersonService.getInstance();

    public FindPersonCommand(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    @Override
    public void execute() {
        var input = inputProvider.readInput(Constants.PERSONAL_ID, PersonalIdValidator::isValid);

        var person = personService.findPersonByPersonalId(input);

        int age = computeAge(person.personalId());

        System.out.printf(PRINT_PERSON_INFO, person.name(), person.surname(), age, person.personalId());
    }

    private int computeAge(String personalId) {
        var now = new Date();
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat(Constants.PERSONAL_ID_DATE_PATTERN).parse(personalId.substring(0, 6));
        } catch (ParseException e) {
            throw new InvalidPersonalIdException("Invalid birth date part of personal id: " + personalId);
        }

        Calendar nowCalendar = getCalendar(now);
        Calendar birthCalendar = getCalendar(birthDate);

        int diff = nowCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        if (birthCalendar.get(Calendar.MONTH) > nowCalendar.get(Calendar.MONTH) ||
                (birthCalendar.get(Calendar.MONTH) == nowCalendar.get(Calendar.MONTH) && birthCalendar.get(Calendar.DATE) > nowCalendar.get(Calendar.DATE))) {
            diff--;
        }

        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTime(date);
        return cal;
    }

    @Override
    public String commandName() {
        return Constants.FIND;
    }

    public static String argumentsFormat() {
        return Constants.PERSONAL_ID_INPUT_FORMAT;
    }
}
