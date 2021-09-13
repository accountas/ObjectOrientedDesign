package lt.mif.ood.PasswordChecker.Validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUpperCaseValidator {

    @Test
    void disallowEmptyPasswords(){
        PasswordValidator validator = new UpperCaseValidator();
        assertFalse(validator.isValid(""));
    }

    @Test
    void allowPasswordsContainingUppercaseLetters(){
        PasswordValidator validator = new UpperCaseValidator();
        assertTrue(validator.isValid("aaaAAAA"));
    }

    @Test
    void disallowPasswordsNotContainingUppercaseLetters(){
        PasswordValidator validator = new UpperCaseValidator();
        assertFalse(validator.isValid("aaaaaaa"));
    }
}
