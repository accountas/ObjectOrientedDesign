package com.company;

import java.util.HashMap;

public class PhoneValidator {

    private HashMap<String, CountryFormat> countryFormats;

    public PhoneValidator(){
        this.countryFormats = new HashMap<>();
        this.countryFormats.put("LV", new CountryFormat("+371", 8));
    }

    public boolean isPhoneNumberSymbolsValid(String phone) {
        if(StringHelpers.isNullOrEmpty(phone)){
            return false;
        }

        boolean allSymbolsValid = true;
        for(int i = 0; i < phone.length(); i++){
            char c = phone.charAt(i);
            allSymbolsValid &= (i == 0 && c == '+') || Character.isDigit(c);
        }

        return allSymbolsValid;
    }

    public String phoneNumberFormer(String phone) {
        if(!isPhoneNumberSymbolsValid(phone)){
            return "Phone number is not valid";
        }

        if(phone.startsWith("8")){
            return "+370" + phone.substring(1);
        } else {
            return phone;
        }
    }

    public boolean isOtherCountryPhoneNumberValid(String countryCode, String phoneNumber) {
        if(StringHelpers.isNullOrEmpty(countryCode) || !isPhoneNumberSymbolsValid(phoneNumber)){
            return false;
        }

        CountryFormat format = countryFormats.get(countryCode);
        if(format == null){
            return false;
        }

        boolean prefixMatch = phoneNumber.startsWith(format.prefix);
        boolean lengthMatch = phoneNumber.length() - format.prefix.length() == format.length;

        return  prefixMatch && lengthMatch;
    }

    public HashMap<String, CountryFormat> getCountryFormats() {
        return countryFormats;
    }

    public void setCountryFormats(HashMap<String, CountryFormat> countryFormats) {
        this.countryFormats = countryFormats;
    }

    public record CountryFormat(String prefix, int length) {
    }
}
