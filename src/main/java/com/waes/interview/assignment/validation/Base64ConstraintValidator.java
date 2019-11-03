package com.waes.interview.assignment.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

/** Validator for base64 constraint */
public class Base64ConstraintValidator implements ConstraintValidator<Base64Constraint, String> {

  public boolean isValid(String obj, ConstraintValidatorContext context) {
    if (obj == null || obj.isBlank()) {
      return true;
    }
    byte[] decode;
    try {
      decode = Base64.getDecoder().decode(obj);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return decode != null;
  }
}
