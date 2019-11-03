package com.waes.interview.assignment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/** Constraint to validate that value is valid base64 string */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Base64ConstraintValidator.class)
@Documented
public @interface Base64Constraint {

  String message() default "{waes.validation.constraints.base64.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
