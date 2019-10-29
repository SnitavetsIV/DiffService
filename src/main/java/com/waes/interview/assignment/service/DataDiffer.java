package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffResult;

public interface DataDiffer<T> {

  DiffResult calculateDiff(T left, T right);
}
