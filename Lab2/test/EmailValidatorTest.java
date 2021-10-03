import com.company.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    EmailValidator emailValidator;

    @BeforeEach
    void setUp(){
        emailValidator = new EmailValidator();
    }

    //-----------------------Turi @ simbolį------------------------

    @Test
    void EmailEtaValidationTest_EmailNull(){
        assertFalse(emailValidator.isEmailConsistEta(null));
    }

    @Test
    void EmailEtaValidationTest_EmailEmpty(){
        assertFalse(emailValidator.isEmailConsistEta(" "));
    }

    @Test
    void EmailEtaValidationTest_EmailValid(){
        assertTrue(emailValidator.isEmailConsistEta("eta@gmail.com"));
    }

    @Test
    void EmailEtaValidationTest_EmailInvalid(){
        assertFalse(emailValidator.isEmailConsistEta("etaAgmail.com"));
    }

    //-----------------------Neturi neleistinų simbolių-------------------

    @Test
    void EmailIllegalSymbolsValidationTest_Null(){
        assertTrue(emailValidator.isEmailConsistIllegalSymbols(null));
    }

    @Test
    void EmailIllegalSymbolsValidationTest_Empty(){
        assertTrue(emailValidator.isEmailConsistIllegalSymbols(" "));
    }

    @Test
    void EmailIllegalSymbolsValidationTest_NoIllegalSymbols(){
        assertFalse(emailValidator.isEmailConsistIllegalSymbols("eta@gmail.com"));
    }

    @Test
    void EmailIllegalSymbolsValidationTest_IllegalSymbolsFound(){
        assertTrue(emailValidator.isEmailConsistIllegalSymbols("eta @gmail.com"));
    }


    //------------------------------Turi teisingą domeną ir TLD-----------------

    // Domain

    @Test
    void EmailDomainValidationTest_DomainNull(){
        assertFalse(emailValidator.isEmailDomainValid(null));
    }

    @Test
    void EmailDomainValidationTest_DomainEmpty(){
        assertFalse(emailValidator.isEmailDomainValid(" "));
    }

    @Test
    void EmailDomainValidationTest_DomainValid(){
        assertTrue(emailValidator.isEmailDomainValid("eta@gmail.com"));
    }

    @Test
    void EmailDomainValidationTest_DomainInvalid(){
        assertFalse(emailValidator.isEmailDomainValid("eta#kazkas.aa"));
    }

    // TLD

    @Test
    void EmailTLDValidationTest_TLDNull(){
        assertFalse(emailValidator.isEmailTLDValid(null));
    }

    @Test
    void EmailTLDValidationTest_TLDEmpty(){
        assertFalse(emailValidator.isEmailTLDValid(" "));
    }

    @Test
    void EmailTLDValidationTest_TLDValid(){
        assertTrue(emailValidator.isEmailTLDValid("eta@gmail.com"));
    }

    @Test
    void EmailTLDValidationTest_TLDInvalid(){
        assertFalse(emailValidator.isEmailTLDValid("eta#kazkas.aa"));
    }


}