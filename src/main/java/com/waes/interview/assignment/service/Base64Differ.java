package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffResult;
import org.springframework.stereotype.Service;

@Service
public class Base64Differ implements DataDiffer<String> {

  @Override
  public DiffResult calculateDiff(String left, String right) {
    if (left == null || right == null) {
      return DiffResult.notConfigured();
    }
    DiffResult result = new DiffResult();
    result.setConfigured(true);
    if (left.length() != right.length()) {
      result.setEqual(false);
      result.setLengthEqual(false);
      return result;
    }

    return result;
  }

}
