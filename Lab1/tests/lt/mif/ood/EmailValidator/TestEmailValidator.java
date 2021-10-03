package lt.mif.ood.EmailValidator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEmailValidator {

    @Test
    void allowValidEmails(){
        EmailValidator emailValidator = new EmailValidator();

        assertTrue(emailValidator.isValid("simple@example.com"));
        assertTrue(emailValidator.isValid("very.common@example.com"));
        assertTrue(emailValidator.isValid( "disposable.style.email.with+symbol@example.com"));
        assertTrue(emailValidator.isValid("other.email-with-hyphen@example.com"));
        assertTrue(emailValidator.isValid("x@example.com"));
        assertTrue(emailValidator.isValid("fully-qualified-domain@example.com"));
    }

    @Test
    void disallowEmailsWithoutAt(){
        EmailValidator emailValidator = new EmailValidator();
        assertFalse(emailValidator.isValid("wrong.com"));
    }

    @Test
    void disallowEmailsWithInvalidSymbols(){
        EmailValidator emailValidator = new EmailValidator();

        //none of the special characters in this local-part are allowed outside quotation marks
        assertFalse(emailValidator.isValid("\"(),:;<>@[\\]@example.com"));

        //no special in domain
        assertFalse(emailValidator.isValid("hello@,:;<>@.com"));
    }
}
