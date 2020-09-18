package com.validation;

import org.springframework.stereotype.Component;

@Component
public class BadgeNumberValidator implements BadgeValidator {

    @Override
    public boolean isNumeric(String badgeNumber) {
        try {
            Integer.parseInt(badgeNumber);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

//    @Override
//    public boolean isParamCorrect(String paramName) {
//        return paramName.equals("badge_number");
//    }
}
