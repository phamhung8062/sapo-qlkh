package warehouseManagement.validation.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



import warehouseManagement.validation.validator.PhoneValidator;

@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Phone {
    String message() default "Định dạng số điện thoại khồng hợ lệ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}