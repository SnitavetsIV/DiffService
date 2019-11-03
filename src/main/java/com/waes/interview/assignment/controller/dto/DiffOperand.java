package com.waes.interview.assignment.controller.dto;

import com.waes.interview.assignment.validation.Base64Constraint;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Structure to store incoming request
 *
 * @author Ilya Snitavets
 */
@Data
public class DiffOperand {

  @NotNull(message = "Operand value is required")
  @Base64Constraint(message = "Operand value should be valid base64")
  private String value;
}
