package com.waes.interview.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Structure to hold concrete place of difference */
@Data
@AllArgsConstructor
public class DiffResultEntry {

  private Integer offset;
  private Integer length;
}
