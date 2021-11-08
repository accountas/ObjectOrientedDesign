package lt.mif.ood.validators.fields;

public class PhoneValidator {
    private String prefix;
    private int length;
    public PhoneValidator(String prefix, int length) {
        this.prefix = prefix;
        this.length = length;
    }

    public boolean isValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.equals("") || phoneNumber.equals("+"))
            return false;
        char[] phoneAsArray = phoneNumber.toCharArray();
        boolean invalid = false;
        for (int i = 0; i < phoneAsArray.length; i++) {
            if (i == 0 && ((phoneAsArray[i] >= 48 && phoneAsArray[i] <= 57) || phoneAsArray[i] == '+'))
                continue;
            else if (phoneAsArray[i] >= 48 && phoneAsArray[i] <= 57)
                continue;
            invalid = true;
        }
        if(invalid)
            return false;
        return true;
    }

    public String convert(String s) {
        char[] phoneAsArray = s.toCharArray();
        char[] convertedNumber = new char[phoneAsArray.length + 3];

        switch (prefix) {
            case "LT":
                if ( phoneAsArray[0] == '8' && convertedNumber.length == length) {
                char[] prefix = {'+', '3', '7', '0'};
                System.arraycopy(prefix,  0, convertedNumber, 0, prefix.length);
                System.arraycopy(phoneAsArray,  1, convertedNumber, prefix.length, phoneAsArray.length - 1);
                return String.valueOf(convertedNumber);
                }
                break;
            case "RU":
                if (phoneAsArray[0] == '8' && convertedNumber.length == length) {
                    char[] prefix = {'+', '7'};
                    System.arraycopy(prefix,  0, convertedNumber, 0, prefix.length);
                    System.arraycopy(phoneAsArray,  1, convertedNumber, prefix.length, phoneAsArray.length - 1);
                    return String.valueOf(convertedNumber);
                }
                break;
        }
        return s;
    }

}
