package com.gini.validations.validators;

import com.gini.validations.annotation.ValidCurrency;
import com.gini.validations.utility.AcceptedCurrency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidation implements ConstraintValidator<ValidCurrency, String> {

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(returnMessage())
                .addConstraintViolation();

        return AcceptedCurrency
                .getCurrenciesList()
                    .contains(currency);
    }

    private String returnMessage(){
       return String
               .format("invalid currency, accepts only %s", AcceptedCurrency.getCurrenciesList());
    }
}
