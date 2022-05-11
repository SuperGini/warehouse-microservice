package com.gini.validations.validators;




import com.gini.validations.annotation.ValidPartCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PartCountValidation implements ConstraintValidator<ValidPartCount, String> {

    @Override
    public boolean isValid(String partCount, ConstraintValidatorContext context) {

        BigDecimal count;
        try {
            count = new BigDecimal(partCount);
        } catch (NumberFormatException e) {
            setValidationErrorMessage(context, "wrong format");
            return false;
        } catch (NullPointerException e) {
            setValidationErrorMessage(context, "must not be null");
            return false;
        }

        if (isNegativeOrZero(count)) {
            setValidationErrorMessage(context, "must be a number greater than zero");
            return false;
        }

        if (isWithDecimals(count)) {
            setValidationErrorMessage(context, "must not have decimals");
            return false;
        }

        return true;
    }

    private boolean isNegativeOrZero(BigDecimal count) {
        return count.compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean isWithDecimals(BigDecimal count) {
        return count.scale() > 0;
    }

    private void setValidationErrorMessage(ConstraintValidatorContext context, String validationErrorMessage) {

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(validationErrorMessage)
                .addConstraintViolation();


    }
}
