package lt.mif.ood.PasswordChecker;

import lt.mif.ood.PasswordChecker.Validators.LengthValidator;
import lt.mif.ood.PasswordChecker.Validators.SpecialCharacterValidator;
import lt.mif.ood.PasswordChecker.Validators.UpperCaseValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPasswordChecker {

    @Test
    void onlyAllowPasswordsPassingAllValidators(){
        PasswordChecker passwordChecker = new PasswordChecker();

        //should mocks be used here when doing TDD?
        passwordChecker.addValidator(new LengthValidator(5));
        passwordChecker.addValidator(new SpecialCharacterValidator("!"));
        passwordChecker.addValidator(new UpperCaseValidator());

        assertTrue(passwordChecker.isValid("Aaaaa!"));
        assertFalse(passwordChecker.isValid("Aaaaa"));
        assertFalse(passwordChecker.isValid("a!!!!!!"));
    }

    @Test
    void allowAllPasswordsWhenNoValidators(){
        PasswordChecker passwordChecker = new PasswordChecker();
        assertTrue(passwordChecker.isValid("a"));
        assertTrue(passwordChecker.isValid("aa"));
        assertTrue(passwordChecker.isValid(""));
    }

    @Test
    void failWithNullPassword(){
        PasswordChecker passwordChecker = new PasswordChecker();
        assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.isValid(null);
        });
    }

    @Test
    void failWithNullValidator(){
        PasswordChecker passwordChecker = new PasswordChecker();
        assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.addValidator(null);
        });
    }
}
