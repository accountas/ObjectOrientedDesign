package lt.mif.ood.PhoneValidator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTelephoneNumberFormat {

    @Test
    void failWithNullPrefixes(){
        assertThrows(IllegalArgumentException.class, () -> {
            new TelephoneNumberFormat(10, null, "8");
        });
    }

    @Test
    void failWithEmptyPrefixes(){
        assertThrows(IllegalArgumentException.class, () -> {
            new TelephoneNumberFormat(10, "+370", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new TelephoneNumberFormat(10, "", "8");
        });
    }

    @Test
    void failWithNegativeLength(){
        assertThrows(IllegalArgumentException.class, () -> {
            new TelephoneNumberFormat(-8, "+370", "8");
        });
    }

    @Test
    void failWithPrefixesWithoutPlus(){
        assertThrows(IllegalArgumentException.class, () -> {
            new TelephoneNumberFormat(8, "370", "8");
        });
    }
}
