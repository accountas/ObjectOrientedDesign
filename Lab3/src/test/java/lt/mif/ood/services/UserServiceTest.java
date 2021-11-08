package lt.mif.ood.services;

import lt.mif.ood.models.User;
import lt.mif.ood.repositories.UsersCrudRepository;
import lt.mif.ood.validators.entities.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UsersCrudRepository usersRepository;

    @Mock
    UserValidator userValidator;

    @InjectMocks
    UserService userService;

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
    void getAll() {
        var users = new ArrayList<User>();
        users.add(user);
        users.add(user);

        doReturn(users).when(usersRepository).findAll();

        assertEquals(userService.getAll(), users);
    }

    @Test
    void save_validUser_returnUpdatedUser() {
        doReturn(true).when(userValidator).isValid(user);
        doReturn(user).when(usersRepository).save(user);

        assertEquals(userService.save(user), user);
        verify(usersRepository).save(user);
    }

    @Test
    void save_invalidUser_throwIllegalArgumentException() {
        doReturn(false).when(userValidator).isValid(user);
        assertThrows(IllegalArgumentException.class, () -> userService.save(user));
        verify(usersRepository, never()).save(user);
    }

    @Test
    void findById_userExists_presentOptional() {
        doReturn(Optional.of(user)).when(usersRepository).findById(1L);
        assertEquals(userService.findById(1L), Optional.of(user));
    }

    @Test
    void findById_userExists_emptyOptional() {
        doReturn(Optional.empty()).when(usersRepository).findById(1L);
        assertEquals(userService.findById(1L), Optional.empty());
    }
}