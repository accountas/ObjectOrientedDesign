import com.company.PhoneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setUp(){
        phoneValidator = new PhoneValidator();
    }

    // ------------------------- Nėra kitų simbolių nei skaičių ------------------------

    @Test
    void PhoneNumberSymbolsValidationTest_Null(){
        assertFalse(phoneValidator.isPhoneNumberSymbolsValid(null));
    }

    @Test
    void PhoneNumberSymbolsValidationTest_Empty(){
        assertFalse(phoneValidator.isPhoneNumberSymbolsValid(" "));
    }

    @Test
    void PhoneNumberSymbolsValidationTest_Valid(){
        assertTrue(phoneValidator.isPhoneNumberSymbolsValid("+3701234567"));
    }

    @Test
    void PhoneNumberSymbolsValidationTest_InvalidContainsLetter(){
        assertFalse(phoneValidator.isPhoneNumberSymbolsValid("+370a234567"));
    }

    @Test
    void PhoneNumberSymbolsValidationTest_InvalidContainsSymbol(){
        assertFalse(phoneValidator.isPhoneNumberSymbolsValid("+370$234567"));
    }


    // -------------------------- Jei prasideda su 8, tai pakeičia į +370----------------------

    @Test
    void PhoneNumberFormerTest_Null(){
        assertEquals("Phone number is not valid", phoneValidator.phoneNumberFormer(null));
    }

    @Test
    void PhoneNumberFormerTest_EmptyNumber(){
        assertEquals("Phone number is not valid", phoneValidator.phoneNumberFormer(" "));
    }

    @Test
    void PhoneNumberFormerTest_ValidFormat(){
        assertEquals("+3701234567", phoneValidator.phoneNumberFormer("+3701234567"));
    }

    @Test
    void PhoneNumberFormerTest_InvalidFormat(){
        assertEquals("+37061234567", phoneValidator.phoneNumberFormer("861234567"));
    }


    // ----------- Yra galimybė pridėti naujų validavimo taisyklių pagal šalį (ilgis ir prefiksas) -------

    @Test
    void OtherCountryPhoneNumberTest_BothNull(){
        assertFalse (phoneValidator.isOtherCountryPhoneNumberValid(null, null));
    }

    @Test
    void OtherCountryPhoneNumberTest_FirstNull(){
        assertFalse (phoneValidator.isOtherCountryPhoneNumberValid(null, "+371123456789" ));
    }

    @Test
    void OtherCountryPhoneNumberTest_SecondNull(){
        assertFalse (phoneValidator.isOtherCountryPhoneNumberValid("LV", null ));
    }

    @Test
    void OtherCountryPhoneNumberTest_BothEmpty(){
        assertFalse (phoneValidator.isOtherCountryPhoneNumberValid("", ""));
    }

    @Test
    void OtherCountryPhoneNumberTest_FirstEmpty(){
        assertFalse (phoneValidator.isOtherCountryPhoneNumberValid("", "+37112345678"));
    }

    @Test
    void OtherCountryPhoneNumberTest_SecondEmpty(){
        assertFalse (phoneValidator.isOtherCountryPhoneNumberValid("LV", ""));
    }

    @Test
    void OtherCountryPhoneNumberLengthTest(){
        assertTrue (phoneValidator.isOtherCountryPhoneNumberValid("LV", "+37112345678"));
    }

    @Test
    void OtherCountryPhoneNumberPrefixTest(){
        assertTrue (phoneValidator.isOtherCountryPhoneNumberValid("LV", "+37112345678"));
    }

}