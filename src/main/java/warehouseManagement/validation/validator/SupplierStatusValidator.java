package warehouseManagement.validation.validator;

import warehouseManagement.validation.anotation.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SupplierStatusValidator implements ConstraintValidator<Status, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext arg1) {
        try {
            return status.equals("true") || status.equals("false");
        } catch (Exception e) {
            return false;
        }
    }
}
