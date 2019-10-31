package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.domain.DiffResultEntry;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/** Realisation for {@link DataDiffer} to differ Base64 data */
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

    List<DiffResultEntry> differences = findDifferences(left, right);

    result.setEqual(differences.isEmpty());
    result.setDiffs(differences);
    return result;
  }

  /**
   * Compares parameters and returns List of {@link DiffResultEntry} objects with information about
   * starting position of difference and ength of difference.
   *
   * @param leftData - data of Left object
   * @param rightData - data of Right object
   * @return - List of Pair objects
   */
  private List<DiffResultEntry> findDifferences(String leftData, String rightData) {
    ArrayList<DiffResultEntry> result = new ArrayList<>();
    boolean isDiff = false;
    int lastPosition = 0;
    for (int i = 0; i < leftData.length(); i++) {
      if (leftData.charAt(i) != rightData.charAt(i)) {
        if (!isDiff) {
          isDiff = true;
          lastPosition = i;
        }
      } else {
        if (isDiff) {
          result.add(new DiffResultEntry(lastPosition, i - lastPosition));
          isDiff = false;
          lastPosition = i;
        }
      }
    }
    if (isDiff) {
      result.add(new DiffResultEntry(lastPosition, leftData.length() - lastPosition));
    }

    return result;
  }
}
