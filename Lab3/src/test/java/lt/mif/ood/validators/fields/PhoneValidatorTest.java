package lt.mif.ood.validators.fields;

import lt.mif.ood.validators.fields.PhoneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {

    private PhoneValidator phoneValidator;

    @BeforeEach
    public void init() {
        String prefix = "LT";
        int length = 12;

        phoneValidator = new PhoneValidator(prefix, length);
    }

    @ParameterizedTest
    @ValueSource(strings = {"+370tyuioplk", "-3700000000"})
    public void hasNotOnlyNumbers(String phoneNumber) {
        assertFalse(phoneValidator.isValid(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"800000000", "+37000000000"})
    public void hasOnlyNumber(String phoneNumber) {
        assertTrue(phoneValidator.isValid(phoneNumber));
    }

    @Test
    public void doesNotConvertsNumber8() {
        assertEquals("8", phoneValidator.convert("8"));
    }

    @Test
    public void doesNotConvertsNumber80() {
        assertEquals("80", phoneValidator.convert("80"));
    }

    @Test
    public void convertsValidNumber() {
        assertEquals("+37000000000", phoneValidator.convert("800000000"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    public void emptyStringIsNotAllowed(String phoneNumber) {
        assertFalse(phoneValidator.isValid(phoneNumber));
    }
}
