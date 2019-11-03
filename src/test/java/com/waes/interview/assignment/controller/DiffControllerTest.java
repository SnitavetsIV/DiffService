package com.waes.interview.assignment.controller;

import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.service.DiffService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiffControllerTest {

  @Test
  void fetchDiffResult_noEntityById_notFound() {
    // Given
    DiffService serviceMock = mock(DiffService.class);
    when(serviceMock.findById(anyLong())).thenReturn(Optional.empty());
    DiffController controller = new DiffController(serviceMock);

    // When
    ResponseEntity<DiffResult> diffResultResponseEntity = controller.fetchDiffResult(anyLong());

    // Then
    assertSame(diffResultResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
  }
}
