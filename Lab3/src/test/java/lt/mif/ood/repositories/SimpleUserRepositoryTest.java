package lt.mif.ood.repositories;

import lt.mif.ood.models.User;
import lt.mif.ood.persistence.EntityListProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleUserRepositoryTest {

    @Mock
    EntityListProvider<User> listProvider;

    @InjectMocks
    SimpleUserRepository userRepository;

    User user;

    @BeforeEach
    void beforeEach() {
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
    void findAll() {
        var users = new ArrayList<User>();
        doReturn(users).when(listProvider).load();

        assertEquals(userRepository.findAll(), users);
    }

    @Test
    void findById_exists_optionalOfTarget() {
        var users = new ArrayList<User>();
        users.add(new User(1L, "1", "", "", "", "", ""));
        users.add(new User(2L, "2", "", "", "", "", ""));
        users.add(new User(3L, "3", "", "", "", "", ""));

        doReturn(users).when(listProvider).load();

        assertEquals(userRepository.findById(2L), Optional.of(users.get(1)));
    }

    @Test
    void findById_doesNotExist_empty() {
        var users = new ArrayList<User>();
        users.add(new User(1L, "1", "", "", "", "", ""));
        users.add(new User(2L, "2", "", "", "", "", ""));
        users.add(new User(3L, "3", "", "", "", "", ""));
        doReturn(users).when(listProvider).load();

        assertEquals(userRepository.findById(4L), Optional.empty());
    }

    @Test
    void save_withId_updateAndReturn() {
        var users = new ArrayList<User>();
        users.add(new User(1L, "1", "", "", "", "", ""));
        doReturn(users).when(listProvider).load();

        var update = new User(1L, "new", "", "", "", "", "");

        var expectedList = new ArrayList<User>();
        expectedList.add(update);

        assertEquals(userRepository.save(update), update);
        verify(listProvider).save(expectedList);
    }

    @Test
    void save_noId_generateIdAddAndReturn() {
        var users = new ArrayList<User>();
        users.add(new User(1L, "1", "", "", "", "", ""));
        doReturn(users).when(listProvider).load();

        var update = new User(null, "new", "", "", "", "", "");
        assertEquals(userRepository.save(update).getId(), 2);

        update.setId(2L);
        var expectedList = new ArrayList<User>();
        expectedList.add(users.get(0));
        expectedList.add(update);

        verify(listProvider).save(expectedList);
    }

    @Test
    void deleteById_matchExists_remove() {
        var input = new ArrayList<User>();
        input.add(new User(1L, "1", "", "", "", "", ""));
        input.add(new User(2L, "2", "", "", "", "", ""));
        input.add(new User(3L, "3", "", "", "", "", ""));
        doReturn(input).when(listProvider).load();

        userRepository.deleteById(2L);

        var expected = new ArrayList<User>();
        expected.add(new User(1L, "1", "", "", "", "", ""));
        expected.add(new User(3L, "3", "", "", "", "", ""));

        verify(listProvider).save(expected);
    }

    @Test
    void deleteById_matchDoesNotExists_doNothing() {
        var input = new ArrayList<User>();
        input.add(new User(1L, "1", "", "", "", "", ""));
        input.add(new User(2L, "2", "", "", "", "", ""));
        input.add(new User(3L, "3", "", "", "", "", ""));
        doReturn(input).when(listProvider).load();

        userRepository.deleteById(4L);

        var expected = new ArrayList<User>();
        expected.add(new User(1L, "1", "", "", "", "", ""));
        expected.add(new User(2L, "2", "", "", "", "", ""));
        expected.add(new User(3L, "3", "", "", "", "", ""));

        verify(listProvider).save(expected);
    }
}