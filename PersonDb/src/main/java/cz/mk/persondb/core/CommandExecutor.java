package cz.mk.persondb.core;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.command.Command;
import cz.mk.persondb.core.command.ContinueCommand;
import cz.mk.persondb.core.command.UnknownCommand;
import cz.mk.persondb.core.command.input.ConsoleInputProvider;
import cz.mk.persondb.core.command.input.ConsoleInputReader;
import cz.mk.persondb.core.command.output.ConsoleOutputProvider;
import cz.mk.persondb.core.command.output.OutputProvider;
import cz.mk.persondb.core.exception.ApplicationException;
import cz.mk.persondb.person.command.AddPersonCommand;
import cz.mk.persondb.person.command.DeletePersonCommand;
import cz.mk.persondb.person.command.FindPersonCommand;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CommandExecutor {

    public static final String COMMAND = "command";

    private final OutputProvider outputProvider = new ConsoleOutputProvider();

    private final List<Command> commands = List.of(
            new AddPersonCommand(new ConsoleInputProvider(Constants.ADD, AddPersonCommand.argumentsFormat())),
            new FindPersonCommand(new ConsoleInputProvider(Constants.FIND, FindPersonCommand.argumentsFormat()), outputProvider),
            new DeletePersonCommand(new ConsoleInputProvider(Constants.DELETE, DeletePersonCommand.argumentsFormat()))
    );

    private final UnknownCommand unknownCommand = new UnknownCommand();

    private final ContinueCommand continueCommand = new ContinueCommand(new ConsoleInputProvider(Constants.CONTINUE_PROMPT, ContinueCommand.argumentsFormat()));

    public void run() throws IOException {
        try {
            var inputProvider = new ConsoleInputProvider("", Constants.COMMAND_FORMAT_PROMPT);
            do {
                executeUserCommand(inputProvider);
            } while (!isUserTerminating());
        } catch (Exception e) {
            outputProvider.writeOutput(Constants.ERROR_OCCURRED + e.getMessage());
        } finally {
            ConsoleInputReader.instance().close();
        }
    }

    private void executeUserCommand(ConsoleInputProvider inputProvider) {
        try {
            findCommand(inputProvider.readInput(COMMAND, in -> true)).execute();
        } catch (ApplicationException e) {
            outputProvider.writeOutput(Constants.ERROR_OCCURRED + e.getMessage());
        }
    }

    private boolean isUserTerminating() {
        continueCommand.execute();
        return continueCommand.isTerminating();
    }

    private Command findCommand(String commandName) {
        for (var command : commands) {
            if (Objects.equals(command.commandName(), commandName)) {
                return command;
            }
        }

        return unknownCommand;
    }

}
