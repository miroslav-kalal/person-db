package cz.mk.persondb.person.exception;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.exception.ApplicationException;

public class PersonAlreadyExistsException extends ApplicationException {

    public PersonAlreadyExistsException() {
        super(Constants.PERSON_ALREADY_EXISTS);
    }

}
