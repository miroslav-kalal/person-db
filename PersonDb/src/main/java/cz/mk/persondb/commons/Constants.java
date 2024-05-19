package cz.mk.persondb.commons;

public final class Constants {

    public static final String UNEXPECTED_ERROR = "Unexpected error: ";

    private Constants() {}

    public static final String ERROR_OCCURRED = "Error occurred: ";

    public static final String PROGRAM_STARTED_PROMPT = "Person Database started.";

    public static final String PROGRAM_TERMINATE_PROMPT = "Person Database shutting down.";

    public static final String PERSONAL_ID_SEPARATOR = "/";

    public static final String PERSON_ALREADY_EXISTS = "Person already exists!";

    public static final String PERSONAL_ID_INFO = "personal id: ";

    public static final String ENTER_ARGUMENT_PROMPT = "Enter %s: ";

    public static final String EXECUTING_COMMAND_PROMPT = "Action: %s%n";

    public static final String NAME = "name";

    public static final String SURNAME = "surname";

    public static final String PERSONAL_ID = "personalId";

    public static final String COMMAND_FORMAT_PROMPT = "add - adds a new person, delete - removes an existing person, find - returns an existing person";

    public static final String CONTINUE_PROMPT = "Do you want to continue? ";

    public static final String ADD = "add";

    public static final String DELETE = "delete";

    public static final String FIND = "find";

    public static final String INVALID_INPUT_FORMAT = "Invalid input format: ";

    public static final String PERSONAL_ID_INPUT_FORMAT = "Personal id must be in either YYMMDDXXXX or YYMMDD/XXXX format and be divisible by 11";

    public static final String PERSON_INPUT_FORMAT = "Name and surname must not be empty, " + PERSONAL_ID_INPUT_FORMAT;

    public static final String UNKNOWN_COMMAND_PROMPT = "Unknown command: ";

    public static final String CONTINUE = "continue";

    public static final String CONTINUE_INPUT_FORMAT = "'y' to continue or 'n' to end the program";

    public static final String PERSON_NOT_FOUND = "No person found with ";

    public static final String PERSONAL_ID_DATE_PATTERN = "yyMMdd";
}
