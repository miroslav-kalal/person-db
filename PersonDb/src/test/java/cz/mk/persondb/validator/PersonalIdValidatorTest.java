package cz.mk.persondb.validator;

import cz.mk.persondb.person.validate.PersonalIdValidator;

public class PersonalIdValidatorTest {

    public void testValidPersonalId() {
        assert PersonalIdValidator.isValid("9501011300");
    }

    public void testValidPersonalId2() {
        assert PersonalIdValidator.isValid("9501021398");
    }

    public void testValidPersonalIdWithSlash() {
        assert PersonalIdValidator.isValid("950101/1300");
    }

    public void testPersonalIdWithSlashNotDivisibleByEleven() {
        assert !PersonalIdValidator.isValid("950101/0100");
    }

    public void testInvalidPersonalId() {
        assert !PersonalIdValidator.isValid("9501010000");
    }

    public void testShortPersonalId() {
        assert !PersonalIdValidator.isValid("950101000");
    }

    public void testLongPersonalId() {
        assert !PersonalIdValidator.isValid("95010100000");
    }

    public void testInvalidBirthDatePersonalId() {
        assert !PersonalIdValidator.isValid("9599010000");
    }

    public void testAlphabetCharacterPersonalId() {
        assert !PersonalIdValidator.isValid("950101130a");
    }


    public void testSpecialCharacterPersonalId() {
        assert !PersonalIdValidator.isValid("95010$130");
    }


    public void testWhiteCharacterPersonalId() {
        assert !PersonalIdValidator.isValid("95010 1300");
    }

    public void testTrailingWhitespacePersonalId() {
        assert !PersonalIdValidator.isValid("9501011300 ");
    }
}
