package cz.mk.persondb.person.validate;


import cz.mk.persondb.commons.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class PersonalIdValidator {

    private static final String PERSONAL_ID_REGEX = "^\\d{6}/?\\d{4}$";

    private PersonalIdValidator() {}

    public static boolean isValid(String personalId) {
        return personalId != null && personalId.matches(PERSONAL_ID_REGEX) && isValidDivision(personalId) && isValidBirthDate(personalId);
    }

    private static boolean isValidDivision(String personalId) {
        var onlyDigitsPersonalId = personalId.replace(Constants.PERSONAL_ID_SEPARATOR, "");
        var personalIdNumber = Long.parseLong(onlyDigitsPersonalId);
        return personalIdNumber % 11 == 0;
    }

    private static boolean isValidBirthDate(String birthId) {
        SimpleDateFormat birthDateFormat = new SimpleDateFormat(Constants.PERSONAL_ID_DATE_PATTERN);

        var birthDate = birthId.substring(0,6);

        Date date = null;
        try {
            date = birthDateFormat.parse(birthDate);
            if (!birthDate.equals(birthDateFormat.format(date))) {
                date = null;
            }
        } catch (ParseException e) {
            return false;
        }

        return date != null;
    }

}
