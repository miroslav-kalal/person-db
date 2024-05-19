package cz.mk.persondb.core.command;

import cz.mk.persondb.commons.Constants;

public class UnknownCommand extends Command {

    @Override
    public void execute() {
        System.out.println(Constants.UNKNOWN_COMMAND_PROMPT);
    }

    @Override
    public String commandName() {
        return "";
    }

}
