package com.example.word.common.validator;

import com.example.word.common.annotation.UserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserIdValidator implements ConstraintValidator<UserId, String> {

    private static final String USERID_PATTERN = "^[a-zA-Z0-9@.]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // value가 정규식과 일치하는지 확인
        return value.matches(USERID_PATTERN);
    }
}
