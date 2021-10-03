package lt.mif.ood.PasswordChecker.Validators;

public class LengthValidator implements PasswordValidator{

    public LengthValidator(int minimumLength) {
    }

    @Override
    public boolean isValid(String password) {
        return true;
    }
}
