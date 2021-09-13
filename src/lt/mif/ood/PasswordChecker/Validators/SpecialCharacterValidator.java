package lt.mif.ood.PasswordChecker.Validators;

public class SpecialCharacterValidator implements PasswordValidator {
    public SpecialCharacterValidator(String specialCharacters) {
    }

    @Override
    public boolean isValid(String password) {
        return false;
    }
}
