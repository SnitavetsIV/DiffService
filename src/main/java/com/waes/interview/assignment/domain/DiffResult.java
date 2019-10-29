package com.waes.interview.assignment.domain;

import java.util.List;
import lombok.Data;

@Data
public class DiffResult {

  private boolean configured;

  private boolean equal;

  private boolean lengthEqual;

  private List<DiffResultEntry> diffs;
}
