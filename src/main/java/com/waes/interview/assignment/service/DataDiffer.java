package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffResult;

/**
 * interface for differing object
 *
 * @param <T>
 */
public interface DataDiffer<T> {

  DiffResult calculateDiff(T left, T right);
}
