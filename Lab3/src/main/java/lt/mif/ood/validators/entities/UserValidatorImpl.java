package lt.mif.ood.validators.entities;

import lt.mif.ood.models.User;
import lt.mif.ood.validators.fields.EmailValidator;
import lt.mif.ood.validators.fields.PasswordValidator;
import lt.mif.ood.validators.fields.PhoneValidator;

import java.util.ArrayList;
import java.util.function.Function;

public class UserValidatorImpl implements UserValidator{

    private final EmailValidator emailValidator;
    private final PhoneValidator phoneValidator;
    private final PasswordValidator passwordValidator;

    public UserValidatorImpl(EmailValidator emailValidator, PhoneValidator phoneValidator, PasswordValidator passwordValidator) {
        this.emailValidator = emailValidator;
        this.phoneValidator = phoneValidator;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public boolean isValid(User user) {
        if(user == null){
            throw new NullPointerException("User can't be null");
        }

        var validations = new ArrayList<Function<User, Boolean>>();
        validations.add((u) -> emailValidator.isValid(u.getEmail()));
        validations.add((u) -> phoneValidator.isValid(u.getPhone()));
        validations.add((u) -> passwordValidator.isValid(u.getPassword()));
        validations.add((u) -> u.getAddress() != null && !u.getAddress().isEmpty());
        validations.add((u) -> u.getName() != null && !u.getName().isEmpty());
        validations.add((u) -> u.getSurname() != null && !u.getSurname().isEmpty());

        var numErrors = validations.stream()
            .map((validator) -> validator.apply(user))
            .filter((valid) -> !valid)
            .count();

        return numErrors == 0;
    }
}
