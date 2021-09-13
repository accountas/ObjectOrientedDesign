package lt.mif.ood.PhoneValidator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhoneValidator {

    @Test
    void allowNumbersMatchingFormat(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertTrue(phoneValidator.isValid("Lithuania", "+37012345678"));
        assertTrue(phoneValidator.isValid("Lithuania", "812345678"));
    }

    @Test
    void disallowNonDigitsInNumbers(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertFalse(phoneValidator.isValid("Lithuania", "+A701345678"));
        assertFalse(phoneValidator.isValid("Lithuania", "A12345678"));
        assertTrue(phoneValidator.isValid("Lithuania", "+37012345678"));
    }

    @Test
    void disallowNumbersWithWrongLength(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertFalse(phoneValidator.isValid("Lithuania", "+3701234567"));
        assertFalse(phoneValidator.isValid("Lithuania", "+370123456789"));
        assertFalse(phoneValidator.isValid("Lithuania", "81234567"));
        assertFalse(phoneValidator.isValid("Lithuania", "8123456789"));
    }

    @Test
    void disallowNumbersWithWrongInternationalPrefix(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertFalse(phoneValidator.isValid("Lithuania", "+99912345678"));
    }

    @Test
    void disallowNumbersWithWrongTrunkPrefix(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertFalse(phoneValidator.isValid("Lithuania", "912345678"));
    }

    @Test
    void changeMatchingTrunkToInternationalPrefix(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertEquals("+37012345678", phoneValidator.toInternational("Lithuania", "812345678"));
        assertEquals("+370123", phoneValidator.toInternational("Lithuania", "8123"));
        assertEquals("+37012345678", phoneValidator.toInternational("Lithuania", "+37012345678"));
        assertEquals("123", phoneValidator.toInternational("Lithuania", "123"));
    }

    @Test
    void supportMultipleFormats(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));
        phoneValidator.addFormat("MiddleEarth", new TelephoneNumberFormat(9, "+1954", "7"));

        assertTrue(phoneValidator.isValid("Lithuania", "+3701234567"));
        assertTrue(phoneValidator.isValid("MiddleEarth", "+1954123456789"));
        assertFalse(phoneValidator.isValid("Lithuania", "+1954123456789"));
    }

    @Test
    void failWithNonExistingFormat(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.isValid("whoops", "+37064316299");
        });
    }

    @Test
    void failWithNullFormat(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.isValid(null, "+37064316299");
        });
    }

    @Test
    void failWithNullNumber(){
        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.addFormat("Lithuania", new TelephoneNumberFormat(8, "+370", "8"));

        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.isValid("Lithuania", null);
        });
    }
}
