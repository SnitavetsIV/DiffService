package com.waes.interview.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Structure to hold concrete place of difference */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiffResultEntry {

  private Integer offset;
  private Integer length;
}
