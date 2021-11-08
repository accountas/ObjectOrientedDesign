package lt.mif.ood.services;

import lt.mif.ood.models.User;
import lt.mif.ood.repositories.UsersCrudRepository;
import lt.mif.ood.validators.entities.UserValidator;

import java.util.ArrayList;
import java.util.Optional;

public class UserService {
    private final UsersCrudRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UsersCrudRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public ArrayList<User> getAll(){
        return (ArrayList<User>) userRepository.findAll();
    }

    public User save(User user){
        if(!userValidator.isValid(user)){
            throw new IllegalArgumentException();
        }
        return userRepository.save(user);
    }

    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }
}
