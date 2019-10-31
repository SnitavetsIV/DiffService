package com.waes.interview.assignment.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

/** Entity to hold information regarding result of differing */
@Data
@JsonInclude(Include.NON_NULL)
public class DiffResult {

  private boolean configured;

  private Boolean equal;

  private Boolean lengthEqual;

  private List<DiffResultEntry> diffs;

  public static DiffResult notConfigured() {
    return new DiffResult();
  }
}
