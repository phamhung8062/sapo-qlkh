package warehouseManagement.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import warehouseManagement.validation.anotation.Status;

public class StatusValidator implements ConstraintValidator<Status, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext arg1) {
        try {
            return status.equals("true") || status.equals("false");
        } catch (Exception e) {
            return false;
        }
    }
}