package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import java.util.Optional;

public interface DiffService {

  Optional<DiffEntity> findById(Long id);

  DiffEntity save(DiffEntity diffEntity);

  DiffResult fetchResult(DiffEntity diffEntity);
}
