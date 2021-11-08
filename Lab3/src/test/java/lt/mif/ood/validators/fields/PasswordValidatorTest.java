package lt.mif.ood.validators.fields;

import lt.mif.ood.validators.fields.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    public void init() {
        int minLength = 8;
        String specialChars = "<.,>?/+=_-&^%$#@";

        passwordValidator = new PasswordValidator(minLength, specialChars);
    }

    @Test
    public void isShorterThan() {
        assertFalse(passwordValidator.isValid("AAA:("));
    }

    @Test
    public void doesNotHaveUppercase() {
        assertFalse(passwordValidator.isValid("\"123456??"));
    }

    @Test
    public void hasAtLeastOneSpecialSymbol() {
        assertTrue(passwordValidator.isValid("Abcd.abcd"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    public void emptyStringIsNotAllowed(String password) {
        assertFalse(passwordValidator.isValid(password));
    }
}
