package cz.mk.persondb.core.command.input;

import java.io.IOException;
import java.util.function.Predicate;

public interface InputProvider {

    String readInput(String argumentName, Predicate<String> validator);

}
