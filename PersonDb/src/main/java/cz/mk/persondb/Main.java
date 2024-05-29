package cz.mk.persondb;


import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.CommandExecutor;
import cz.mk.persondb.core.command.output.ConsoleOutputProvider;
import cz.mk.persondb.core.command.output.OutputProvider;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        OutputProvider outputProvider = new ConsoleOutputProvider();

        outputProvider.writeOutput(Constants.PROGRAM_STARTED_PROMPT);
        try {
            new CommandExecutor().run();
        } catch (IOException e) {
            outputProvider.writeOutput(Constants.ERROR_OCCURRED + e.getMessage());
        } catch (Exception e) {
            outputProvider.writeOutput(Constants.UNEXPECTED_ERROR + e.getMessage());
        } finally {
            outputProvider.writeOutput(Constants.PROGRAM_TERMINATE_PROMPT);
        }
    }

}
