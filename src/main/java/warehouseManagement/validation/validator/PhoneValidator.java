package warehouseManagement.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import warehouseManagement.validation.anotation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext arg1) {
        try {
            return (phone == null) || (phone.length() == 0) || (phone.length() >= 10 && phone.matches("^[\\d]*$"));
        } catch (Exception e) {
            return false;
        }
    }
}   