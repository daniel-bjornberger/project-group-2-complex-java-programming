package se.iths.complexjavaproject.mudders.security;

import se.iths.complexjavaproject.mudders.model.UserModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserModel userModel = (UserModel) obj;
        return userModel.getPassword().equals(userModel.getMatchingPassword());
    }
}