package lt.mif.ood.PasswordChecker.Validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLengthValidator {

    @Test
    void negativeMinimumLengthShouldNotBeAllowed(){
        assertThrows(IllegalArgumentException.class, () -> new LengthValidator(-1));
    }

    @Test
    void acceptPasswordsWithMinimumLength(){
        PasswordValidator lengthValidator = new LengthValidator(5);
        assertEquals(true, lengthValidator.isValid("aaaaa"));
    }

    @Test
    void acceptPasswordsLongerThanMinimumLength(){
        PasswordValidator lengthValidator = new LengthValidator(5);
        assertEquals(true, lengthValidator.isValid("aaaaaaa"));
    }

    @Test
    void disallowPasswordsShorterThanMinimumLength(){
        PasswordValidator lengthValidator = new LengthValidator(5);
        assertEquals(true, lengthValidator.isValid("aa"));
    }
}
