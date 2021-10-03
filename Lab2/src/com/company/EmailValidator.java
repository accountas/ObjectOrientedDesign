package com.company;

public class EmailValidator {

    public final String illegalCharacters;

    public EmailValidator() {
        illegalCharacters = " ";
    }

    public boolean isEmailConsistEta(String email) {
        if(StringHelpers.isNullOrEmpty(email)){
            return false;
        } else {
            return email.contains("@");
        }
    }

    public boolean isEmailConsistIllegalSymbols(String email) {
        if(StringHelpers.isNullOrEmpty(email)){
            return true;
        }
        return StringHelpers.containsAnyOf(email, illegalCharacters);
    }

    public boolean isEmailDomainValid(String email) {
        if(StringHelpers.isNullOrEmpty(email)){
            return false;
        }
        return email.contains("@");
    }

    public boolean isEmailTLDValid(String email) {
        if(StringHelpers.isNullOrEmpty(email)){
            return false;
        }
        return email.contains("@");
    }
}
