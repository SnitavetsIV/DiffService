package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.*;

class DiffServiceImplTest {

  @Test
  void findById_idNull_empty() {
    // Given
    DiffServiceImpl diffService = new DiffServiceImpl(null, null);

    // When
    Optional<DiffEntity> entityOpt = diffService.findById(null);

    // Then
    assertTrue(entityOpt.isEmpty());
  }

  @Test
  void save_diffEntityNull_null() {
    // Given
    DiffServiceImpl diffService = new DiffServiceImpl(null, null);

    // When
    DiffEntity entityOpt = diffService.save(null);

    // Then
    assertNull(entityOpt);
  }

  @Test
  void fetchResult_diffEntityNull_notConfigured() {
    // Given
    DiffServiceImpl diffService = new DiffServiceImpl(null, null);

    // When
    DiffResult diffResult = diffService.fetchResult(null);

    // Then
    assertFalse(diffResult.isConfigured());
  }
}
