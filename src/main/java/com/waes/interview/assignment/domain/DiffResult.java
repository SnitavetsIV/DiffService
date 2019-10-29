package com.waes.interview.assignment.domain;

import java.util.List;
import lombok.Data;

@Data
public class DiffResult {

  private boolean configured;

  private Boolean equal;

  private Boolean lengthEqual;

  private List<DiffResultEntry> diffs;

  public static DiffResult notConfigured() {
    return new DiffResult();
  }
}
