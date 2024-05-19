package cz.mk.persondb.person.exception;

import cz.mk.persondb.core.exception.ApplicationException;

public class InvalidPersonalIdException extends ApplicationException {

    public InvalidPersonalIdException(String message) {
        super(message);
    }

}
