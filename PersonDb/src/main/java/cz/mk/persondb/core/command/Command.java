package cz.mk.persondb.core.command;


public abstract class Command {

    public abstract void execute();

    public boolean isTerminating() {
        return false;
    }

    public abstract String commandName();

}
