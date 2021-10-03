package com.company;

import java.util.HashSet;

public class StringHelpers {

    static boolean isNullOrEmpty(String s){
        return s == null || s.isEmpty();
    }

    static boolean containsAnyOf(String source, String special){
        if(isNullOrEmpty(source) || isNullOrEmpty(special)){
            return false;
        }

        boolean contains = false;
        for(char c : source.toCharArray()){
            contains |= special.indexOf(c) != -1;
        }
        return contains;
    }
}
