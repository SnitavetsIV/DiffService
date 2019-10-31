package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffResult;

/**
 * interface for differing object
 *
 * @param <T>
 */
public interface DataDiffer<T> {

  /**
   * Calculate difference between objects and return results with details
   *
   * @param left left object to differ
   * @param right right object to differ
   * @return object with difference result
   */
  DiffResult calculateDiff(T left, T right);
}
