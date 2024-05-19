package cz.mk.persondb.core.command.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader {

    private static final ConsoleInputReader CONSOLE_INPUT_READER = new ConsoleInputReader();

    private final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public static ConsoleInputReader instance() {
        return CONSOLE_INPUT_READER;
    }

    private ConsoleInputReader() {}

    public synchronized String readInput() throws IOException {
        return consoleReader.readLine();
    }

    public void close() throws IOException {
        consoleReader.close();
    }

}