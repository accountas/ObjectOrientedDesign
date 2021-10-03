package com.company;

import java.util.Arrays;

public class PasswordChecker {

    private final String specialSymbols;

    public PasswordChecker() {
        this.specialSymbols = "@";
    }

    public boolean isPasswordLengthValid(String password, int minimumLength) {
        if(StringHelpers.isNullOrEmpty(password)){
            return false;
        }

        return password.length() >= minimumLength;
    }

    public boolean isStringContainsUpperCaseSymbol(String password) {
        if(StringHelpers.isNullOrEmpty(password)){
            return false;
        }

        boolean containsUppercase = false;
        for(char c : password.toCharArray()){
            containsUppercase |= Character.isUpperCase(c);
        }

        return containsUppercase;
    }

    public boolean isStringContainsSpecialCharacters(String password) {
        if(StringHelpers.isNullOrEmpty(password)){
            return false;
        }
        return StringHelpers.containsAnyOf(password, specialSymbols);
    }
}
