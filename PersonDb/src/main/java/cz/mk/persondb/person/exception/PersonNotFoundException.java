package cz.mk.persondb.person.exception;

import cz.mk.persondb.commons.Constants;
import cz.mk.persondb.core.exception.ApplicationException;

public class PersonNotFoundException extends ApplicationException {

    public PersonNotFoundException(String info) {
        super(Constants.PERSON_NOT_FOUND + info);
    }

}
