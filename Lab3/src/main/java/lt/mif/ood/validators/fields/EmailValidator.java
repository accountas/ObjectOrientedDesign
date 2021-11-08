package lt.mif.ood.validators.fields;

public class EmailValidator {
    private boolean atFound;
    private final char[] allowedOutsideQuotes = "!#$%&'*+-/=?^_`{|}~0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] allowedInsideQuotes = "!#$%&'*+-/=?^_`{|}~0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ(),:;<>@[]. ".toCharArray();
    private final char[] allowedDomain = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private char[] afterAt;

    public boolean isValid(String email) {
        afterAt = new char[255];
        if (email != null)
            if (multipleAtCheck(email))
                if (localPartValidCheck(email))
                    return TLDCheck(email);
        return false;

    }
    private boolean TLDCheck(String email) {
        boolean found;
        int lastDotIndex = 0;
        for (int i = 0; afterAt[i] != '\0'; i++) {
            if(afterAt[i] == '.')
                lastDotIndex = i;
            found = false;
            for (char k : allowedDomain) {
                if (i == 0 && afterAt[i] == k) {
                    found = true;
                    break;
                } else if (i != 0 && (afterAt[i] == k || (afterAt[i] >= 48 && afterAt[i] <= 57) || afterAt[i] == '.' || afterAt[i] == '-')) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        for (char i : allowedDomain) {
            if(afterAt[lastDotIndex - 1] == i || (afterAt[lastDotIndex - 1] >= 48 && afterAt[lastDotIndex - 1] <= 57))
                return true;
        }
        return false;
    }

    private boolean multipleAtCheck(String email) {
        int atCount = 0;
        boolean inQuotes = false;
        char[] emailAsChar = email.toCharArray();
        for (char i : emailAsChar) {
            if (i == '"')
                inQuotes = !inQuotes;
            if (i == '@' && !inQuotes)
                atCount++;
        }
        return atCount <= 1;
    }
    private boolean localPartValidCheck(String email) {
        atFound = false;
        boolean inQuotes = false;

        char[] intermediate = new char[64];
        char[] beforeAt = new char[0];

        char[] emailAsChar = email.toCharArray();
        for (int i = 0; i < emailAsChar.length; i++) {
            if (emailAsChar[i] == '"') {
                inQuotes = !inQuotes;
                intermediate[i] = '"';
            } else if (emailAsChar[i] == '\\' && emailAsChar[i + 1] == '"' && inQuotes ) {
                intermediate[i] = emailAsChar[i];
                i++;
                intermediate[i] = emailAsChar[i];
                continue;
            } else if (inQuotes) {
                intermediate[i] = emailAsChar[i];
                continue;
            } else {
                if (emailAsChar[i] != '@')
                    intermediate[i] = emailAsChar[i];
                else {
                    beforeAt = new char[i];
                    System.arraycopy(intermediate, 0, beforeAt, 0, i);
                    System.arraycopy(emailAsChar, i + 1, afterAt, 0, emailAsChar.length - i - 1);
                    atFound = true;
                    break;
                }
            }
        }

        if (!atFound || beforeAt[0] == '.' || beforeAt[beforeAt.length - 1] == '.')
            return false;
        boolean found;
        for (int i = 0; i < beforeAt.length; i++) {
            found = false;
            if (beforeAt[i] == '"') {
                inQuotes = !inQuotes;
                continue;
            } else if (inQuotes) {
                if (beforeAt[i] == '"') {
                    inQuotes = false;
                    continue;
                } else if (i + 1 < beforeAt.length && ((beforeAt[i] == '\\' && beforeAt[i + 1] == '\\') ||
                        (beforeAt[i] == '\\' && beforeAt[i + 1] == '"'))) {
                    i++;
                    break;
                }
                for (char k: allowedInsideQuotes) {
                    if (beforeAt[i] == k) {
                        found = true;
                        break;
                    }
                }
            }
            if (i + 1 < beforeAt.length && beforeAt[i] == '.' && beforeAt[i + 1] != '.')
                continue;
            else
                for (char k: allowedOutsideQuotes) {
                    if (beforeAt[i] == k) {
                        found = true;
                        break;
                    }
                }
            if (!found)
                return false;
        }
        return true;
    }
}
