package lt.mif.ood.repositories;

import lt.mif.ood.models.User;
import lt.mif.ood.persistence.EntityListProvider;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleUserRepository implements UsersCrudRepository {

    private final EntityListProvider<User> userProvider;

    public SimpleUserRepository(EntityListProvider<User> userProvider) {
        this.userProvider = userProvider;
    }

    @Override
    public ArrayList<User> findAll() {
        return userProvider.load();
    }

    @Override
    public Optional<User> findById(long id) {
        var users = userProvider.load();
        return users.stream()
            .filter((user -> user.getId() == id))
            .findFirst();
    }

    @Override
    public User save(User user) {
        var users = userProvider.load();

        if (user.getId() != null) {
            //update
            var userExists = users.stream().anyMatch(u -> Objects.equals(u.getId(), user.getId()));
            if (!userExists) {
                throw new IllegalArgumentException();
            }

            users = users.stream()
                .map(u -> Objects.equals(u.getId(), user.getId()) ? user : u)
                .collect(Collectors.toCollection(ArrayList::new));
        } else {
            //add
            var newId = users.stream()
                .map(User::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;

            user.setId(newId);
            users.add(user);
        }

        userProvider.save(users);
        return user;
    }

    @Override
    public void deleteById(long id) {
        var users = userProvider.load()
            .stream()
            .filter(u -> u.getId() != id)
            .collect(Collectors.toCollection(ArrayList::new));
        userProvider.save(users);
    }
}