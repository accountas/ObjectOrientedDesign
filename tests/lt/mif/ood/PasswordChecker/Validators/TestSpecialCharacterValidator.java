package lt.mif.ood.PasswordChecker.Validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSpecialCharacterValidator {

    @Test
    void acceptPasswordsContainingAtLeastOneSpecialCharacter(){
        PasswordValidator validator = new SpecialCharacterValidator("!@#");
        assertTrue(validator.isValid("hello!"));
        assertTrue(validator.isValid("hello@"));
        assertTrue(validator.isValid("hello#"));
        assertTrue(validator.isValid("hello!@#"));
    }

    @Test
    void disallowPasswordsWithoutSpecialCharacters(){
        PasswordValidator validator = new SpecialCharacterValidator("!@#");
        assertFalse(validator.isValid("hello"));
        assertFalse(validator.isValid("hello?>]$%^&"));
    }

    @Test
    void failWithIllegalConstructorParameters(){
        assertThrows(IllegalArgumentException.class, () -> new SpecialCharacterValidator(""));
        assertThrows(IllegalArgumentException.class, () -> new SpecialCharacterValidator(null));
    }
}
