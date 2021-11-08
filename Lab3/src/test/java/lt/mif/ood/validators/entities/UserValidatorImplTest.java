package lt.mif.ood.validators.entities;

import lt.mif.ood.models.User;
import lt.mif.ood.validators.fields.EmailValidator;
import lt.mif.ood.validators.fields.PasswordValidator;
import lt.mif.ood.validators.fields.PhoneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserValidatorImplTest {

    @Mock
    EmailValidator emailValidator;

    @Mock
    PasswordValidator passwordValidator;

    @Mock
    PhoneValidator phoneValidator;

    @InjectMocks
    UserValidatorImpl userValidator;

    User user;

    @BeforeEach
    void beforeEach(){
       user = new User(
            1L,
            "name",
            "surname",
            "phone",
            "email",
            "address",
            "password"
        );
    }


    @Test
    void isValid_invalidEmail_false(){
        doReturn(false).when(emailValidator).isValid(user.getEmail());
        doReturn(true).when(passwordValidator).isValid(user.getPassword());
        doReturn(true).when(phoneValidator).isValid(user.getPhone());

        assertFalse(userValidator.isValid(user));
    }

    @Test
    void isValid_invalidPassword_false(){
        doReturn(true).when(emailValidator).isValid(user.getEmail());
        doReturn(false).when(passwordValidator).isValid(user.getPassword());
        doReturn(true).when(phoneValidator).isValid(user.getPhone());

        assertFalse(userValidator.isValid(user));
    }

    @Test
    void isValid_invalidPhone_false(){
        doReturn(true).when(emailValidator).isValid(user.getEmail());
        doReturn(true).when(passwordValidator).isValid(user.getPassword());
        doReturn(false).when(phoneValidator).isValid(user.getPhone());

        assertFalse(userValidator.isValid(user));
    }

    @Test
    void isValid_emptyOrNullName_false(){
        doReturn(true).when(emailValidator).isValid(user.getEmail());
        doReturn(true).when(passwordValidator).isValid(user.getPassword());
        doReturn(true).when(phoneValidator).isValid(user.getPhone());

        user.setName("");
        assertFalse(userValidator.isValid(user));

        user.setName(null);
        assertFalse(userValidator.isValid(user));
    }

    @Test
    void isValid_emptyOrNullSurname_false(){
        doReturn(true).when(emailValidator).isValid(user.getEmail());
        doReturn(true).when(passwordValidator).isValid(user.getPassword());
        doReturn(true).when(phoneValidator).isValid(user.getPhone());

        user.setSurname("");
        assertFalse(userValidator.isValid(user));

        user.setSurname(null);
        assertFalse(userValidator.isValid(user));
    }

    @Test
    void isValid_emptyOrNullAddress_false(){
        doReturn(true).when(emailValidator).isValid(user.getEmail());
        doReturn(true).when(passwordValidator).isValid(user.getPassword());
        doReturn(true).when(phoneValidator).isValid(user.getPhone());

        user.setAddress("");
        assertFalse(userValidator.isValid(user));

        user.setAddress(null);
        assertFalse(userValidator.isValid(user));
    }

    @Test
    void isValid_allFieldsCorrect_true(){
        doReturn(true).when(emailValidator).isValid(user.getEmail());
        doReturn(true).when(passwordValidator).isValid(user.getPassword());
        doReturn(true).when(phoneValidator).isValid(user.getPhone());

        assertTrue(userValidator.isValid(user));
    }

}