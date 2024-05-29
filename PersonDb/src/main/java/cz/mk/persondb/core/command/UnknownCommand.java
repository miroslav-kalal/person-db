package cz.mk.persondb.core.command;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.command.output.ConsoleOutputProvider;
import cz.mk.persondb.core.command.output.OutputProvider;

public class UnknownCommand extends Command {

    private final OutputProvider outputProvider = new ConsoleOutputProvider();

    @Override
    public void execute() {
        outputProvider.writeOutput(Constants.UNKNOWN_COMMAND_PROMPT);
    }

    @Override
    public String commandName() {
        return "";
    }

}
