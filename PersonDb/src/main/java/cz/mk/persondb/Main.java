package cz.mk.persondb;


import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.CommandExecutor;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        System.out.println(Constants.PROGRAM_STARTED_PROMPT);
        try {
            new CommandExecutor().run();
        } catch (IOException e) {
            System.out.println(Constants.ERROR_OCCURRED + e.getMessage());
        } catch (Exception e) {
            System.out.println(Constants.UNEXPECTED_ERROR + e.getMessage());
        } finally {
            System.out.println(Constants.PROGRAM_TERMINATE_PROMPT);
        }
    }

}
