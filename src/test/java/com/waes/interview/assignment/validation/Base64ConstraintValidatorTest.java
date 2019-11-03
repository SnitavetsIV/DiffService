package com.waes.interview.assignment.validation;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class Base64ConstraintValidatorTest {

  @Test
  void isValid_null_true() {
    // Given
    Base64ConstraintValidator validator = new Base64ConstraintValidator();

    // When
    boolean valid = validator.isValid(null, null);

    // Then
    assertTrue(valid);
  }

  @Test
  void isValid_blank_true() {
    // Given
    Base64ConstraintValidator validator = new Base64ConstraintValidator();
    String blankValue = "     ";

    // When
    boolean valid = validator.isValid(blankValue, null);

    // Then
    assertTrue(valid);
  }

  @Test
  void isValid_validBase64_true() {
    // Given
    Base64ConstraintValidator validator = new Base64ConstraintValidator();
    String validBase64 = "VmFsaWQgQmFzZTY0IFN0cmluZw=="; // "Valid Base64 String"

    // When
    boolean valid = validator.isValid(validBase64, null);

    // Then
    assertTrue(valid);
  }

  @Test
  void isValid_notValidBase64_true() {
    // Given
    Base64ConstraintValidator validator = new Base64ConstraintValidator();
    String validBase64 = "Not Valid Base64 String";

    // When
    boolean valid = validator.isValid(validBase64, null);

    // Then
    assertFalse(valid);
  }
}
