package com.waes.interview.assignment.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DiffOperand {

  @NotNull(message = "Operand value is required")
  private String value;
}
