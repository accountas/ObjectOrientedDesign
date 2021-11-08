package lt.mif.ood.validators.fields;

public class PasswordValidator {
    private int minLength;
    private String specialChars;
    public PasswordValidator(int minLength, String specialChars) {
        this.minLength = minLength;
        this.specialChars = specialChars;
    }

    public boolean isValid(String s) {
        return checkLength(s) & checkUppercase(s) & checkSpecialSymbol(s);
    }
    private boolean checkLength(String s) {
        if(s == null)
            return false;
        if(s.length() <= minLength)
            return false;
        return true;
    }
    private boolean checkUppercase(String s) {
        if(s == null)
            return false;
        char[] password = s.toCharArray();
        for (char i: password) {
            if(i >= 65 && i <= 90)
                return true;
        }
        return false;
    }
    private boolean checkSpecialSymbol(String s) {
        if(s == null)
            return false;
        char[] password = s.toCharArray();
        char[] specialCharacters = specialChars.toCharArray();
        for (char i : specialCharacters) {
            for (char j : password) {
                if(i == j)
                    return true;
            }
        }
        return false;
    }

}
