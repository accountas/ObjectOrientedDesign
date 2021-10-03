import com.company.PasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    PasswordChecker passwordChecker;

    @BeforeEach
    void setUp(){
        passwordChecker = new PasswordChecker();
    }

    // ----------------- Tikrina ar slaptažodžio ilgis netrumpesnis nei X (>=X) --------------------

    @Test
    void PasswordLengthIsNullTest (){
        assertFalse (passwordChecker.isPasswordLengthValid(null,11));
    }

    @Test
    void PasswordLengthIsEmptyTest (){
        assertFalse (passwordChecker.isPasswordLengthValid(" ",11));
    }

    @Test
    void PasswordLengthIsValidTest (){
        assertTrue (passwordChecker.isPasswordLengthValid("password123456",11));
    }

    @Test
    void PasswordLengthIsInvalidTest (){
        assertFalse (passwordChecker.isPasswordLengthValid("password",11));
    }


    // --------------------------------Tikrina ar yra Uppercase simbolių-----------------------------

    @Test
    void UpperCaseTest_PasswordNull(){
        assertFalse(passwordChecker.isStringContainsUpperCaseSymbol(null));
    }

    @Test
    void UpperCaseTest_PasswordEmpty(){
        assertFalse(passwordChecker.isStringContainsUpperCaseSymbol(" "));
    }

    @Test
    void UpperCaseTest_PasswordValid(){
        assertTrue(passwordChecker.isStringContainsUpperCaseSymbol("paAsword123"));
    }

    @Test
    void UpperCaseTest_PasswordInvalid(){
        assertFalse(passwordChecker.isStringContainsUpperCaseSymbol("password123"));
    }

    //-----------Tikrina ar yra specialus simbolis (specialių simbolių sąrašas turi būti konfiguruojamas)---

    @Test
    void SpecificCharacterTest_PasswordNull(){
        assertFalse(passwordChecker.isStringContainsSpecialCharacters(null));
    }

    @Test
    void SpecificCharacterTest_PasswordEmpty(){
        assertFalse(passwordChecker.isStringContainsSpecialCharacters(" "));
    }

    @Test
    void SpecificCharacterTest_PasswordValid(){
        assertTrue(passwordChecker.isStringContainsSpecialCharacters("P@ssword123"));
    }

    @Test
    void SpecificCharacterTest_PasswordInvalid(){
        assertFalse(passwordChecker.isStringContainsSpecialCharacters("PAssword123"));
    }

}