package lt.mif.ood.services;

import lt.mif.ood.models.User;
import lt.mif.ood.persistence.UserListGsonProvider;
import lt.mif.ood.repositories.SimpleUserRepository;
import lt.mif.ood.validators.entities.UserValidatorImpl;
import lt.mif.ood.validators.fields.EmailValidator;
import lt.mif.ood.validators.fields.PasswordValidator;
import lt.mif.ood.validators.fields.PhoneValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTestIntegration {

    @TempDir
    Path tempDir;

    @Test
    void test(){
        var userListProvider = new UserListGsonProvider(tempDir.resolve("test.json").toString());
        var userRepository = new SimpleUserRepository(userListProvider);

        var userValidator = new UserValidatorImpl(
            new EmailValidator(),
            new PhoneValidator("+370", 8),
            new PasswordValidator(3, "?")
        );
        var userService = new UserService(userRepository, userValidator);

        assertEquals(userService.getAll().size(), 0);

        var validUser1 = new User(
            null,
            "vardenis",
            "pavardenis",
            "+37061234567",
            "pastas@gmail.com",
            "Vilnius",
            "PASS?????"
        );

        var validUser2 = new User(
            null,
            "vardenis1",
            "pavardenis1",
            "+37061234567",
            "pastasss@gmail.com",
            "Kaunas",
            "PASS?????"
        );

        var newID1 = userService.save(validUser1).getId();
        var newID2 = userService.save(validUser2).getId();

        assertEquals(userService.getAll().size(), 2);
        assertEquals(newID1, 1L);
        assertEquals(newID2, 2L);

        var updatedUser =  new User(
            1L,
            "alksdjalskdj",
            "pavardenis",
            "+37061234567",
            "pastas@gmail.com",
            "Vilnius",
            "PASS?????"
        );

        assertEquals(userService.save(updatedUser), updatedUser);
        assertEquals(userService.findById(1L), Optional.of(updatedUser));

        userService.delete(1L);
        assertEquals(userService.getAll().size(), 1);
        assertEquals(userService.findById(1L), Optional.empty());
    }


}