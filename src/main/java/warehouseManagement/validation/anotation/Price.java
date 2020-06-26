package warehouseManagement.validation.anotation;

import warehouseManagement.validation.validator.PriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriceValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {

    String message() default "price is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

