package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.domain.DiffResultEntry;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Realisation for {@link DataDiffer} to differ Base64 data */
@Service
@Slf4j
public class Base64Differ implements DataDiffer<String> {

  @Override
  public DiffResult calculateDiff(String left, String right) {
    if (left == null || right == null) {
      return DiffResult.notConfigured();
    }
    byte[] leftData;
    byte[] rightData;

    try {
      leftData = Base64.getDecoder().decode(left);
      rightData = Base64.getDecoder().decode(right);
    } catch (IllegalArgumentException e) {
      log.error("tried to decode not base 64 string", e);
      return DiffResult.notConfigured();
    }
    DiffResult result = new DiffResult();
    result.setConfigured(true);
    if (leftData.length != rightData.length) {
      result.setEqual(false);
      result.setLengthEqual(false);
      return result;
    }
    result.setLengthEqual(true);

    List<DiffResultEntry> differences = findDifferences(leftData, rightData);

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
  private List<DiffResultEntry> findDifferences(byte[] leftData, byte[] rightData) {
    ArrayList<DiffResultEntry> result = new ArrayList<>();
    boolean isDiff = false;
    int lastPosition = 0;
    for (int i = 0; i < leftData.length; i++) {
      if (leftData[i] != rightData[i]) {
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
      result.add(new DiffResultEntry(lastPosition, leftData.length - lastPosition));
    }

    return result;
  }
}
