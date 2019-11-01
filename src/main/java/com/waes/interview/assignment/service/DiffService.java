package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import java.util.Optional;

/** Service to manipulate {@link DiffEntity} object */
public interface DiffService {

  /**
   * Find entity by it's identifier
   *
   * @param id - identifier for diff entity
   * @return optional with diff entity inside, or {@link Optional#empty()} if diff entity not found
   */
  Optional<DiffEntity> findById(Long id);

  /**
   * Save or update entity
   *
   * @param diffEntity - entity to save or update
   * @return entity with updated or saved information
   */
  DiffEntity save(DiffEntity diffEntity);

  /**
   * @param diffEntity - entity to calculate differ
   * @return result of differing
   */
  DiffResult fetchResult(DiffEntity diffEntity);
}
