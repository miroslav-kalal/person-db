package cz.mk.persondb.core.command.input;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.exception.UserInputException;

import java.io.IOException;
import java.util.function.Predicate;

public class ConsoleInputProvider implements InputProvider {

    public static final String ERROR_READING_INPUT = "Error while reading user input: ";
    private final String promptPrefix;

    private final String inputFormat;

    private final ConsoleInputReader consoleReader = ConsoleInputReader.instance();

    public ConsoleInputProvider(String commandName, String inputFormat) {
        if (commandName == null || commandName.isBlank()) {
            this.promptPrefix = "";
        } else {
            this.promptPrefix = Constants.EXECUTING_COMMAND_PROMPT.formatted(commandName);
        }

        if (inputFormat == null || inputFormat.isBlank()) {
            this.inputFormat = "";
        } else {
            this.inputFormat = inputFormat;
        }
    }

    public String readInput(String argumentName, Predicate<String> validator) {
        try {
            return readInputInner(argumentName, validator);
        } catch (IOException e) {
            System.out.println(ERROR_READING_INPUT + e.getMessage());
            throw new UserInputException(e.getMessage());
        }
    }

    private String readInputInner(String argumentName, Predicate<String> validator) throws IOException {
        var fullPrompt = createPrompt(argumentName);

        if (!fullPrompt.isBlank()) {
            System.out.println(fullPrompt);
        }

        var input = consoleReader.readInput();

        while (!validator.test(input)) {
            System.out.println(Constants.INVALID_INPUT_FORMAT + input);
            System.out.println(Constants.ENTER_ARGUMENT_PROMPT.formatted(argumentName) + inputFormat);
            input = consoleReader.readInput();
        }

        return input;
    }

    private String createPrompt(String argumentName) {
        var inputFormatInfo = inputFormat;

        if (!inputFormat.isBlank() && (!promptPrefix.isBlank() || (argumentName != null && !argumentName.isBlank()))) {
                inputFormatInfo = " (" + inputFormat + ")";
        }

        if (argumentName != null && !argumentName.isBlank()) {
            return promptPrefix + Constants.ENTER_ARGUMENT_PROMPT.formatted(argumentName) + inputFormatInfo;
        } else {
            return promptPrefix + inputFormat;
        }
    }

}
