package cz.mk.persondb.core.command.output;

public class ConsoleOutputProvider implements OutputProvider {

    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }

}
