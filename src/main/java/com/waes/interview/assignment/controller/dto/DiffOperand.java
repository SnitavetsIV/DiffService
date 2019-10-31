package com.waes.interview.assignment.controller.dto;

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
  private String value;
}
