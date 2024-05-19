package cz.mk.persondb.core.command;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.command.input.ConsoleInputProvider;

public class ContinueCommand extends Command {

    private final ConsoleInputProvider inputProvider;

    private static final String CONTINUE = "y";

    private static final String TERMINATE = "n";

    private boolean isTerminating = false;

    public ContinueCommand(ConsoleInputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    @Override
    public void execute() {
        var input = inputProvider.readInput("", in -> CONTINUE.equals(in) || TERMINATE.equals(in));
        isTerminating = input.equals(TERMINATE);
    }

    @Override
    public boolean isTerminating() {
        return isTerminating;
    }

    @Override
    public String commandName() {
        return Constants.CONTINUE;
    }

    public static String argumentsFormat() {
        return Constants.CONTINUE_INPUT_FORMAT;
    }
}
