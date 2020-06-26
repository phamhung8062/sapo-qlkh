package warehouseManagement.validation.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import warehouseManagement.validation.validator.TaxValidator;


@Constraint(validatedBy = TaxValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tax {
    String message() default "Mã số thuế không hơp lệ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
