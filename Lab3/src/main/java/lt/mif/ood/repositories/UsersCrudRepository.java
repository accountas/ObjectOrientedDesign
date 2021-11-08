package lt.mif.ood.repositories;

import lt.mif.ood.models.User;

import java.util.Optional;

public interface UsersCrudRepository {
    Iterable<User> findAll();
    Optional<User> findById(long id);
    User save(User user);
    void deleteById(long id);
}
