package com.waes.interview.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.waes.interview.assignment.controller.dto.DiffOperand;
import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.service.DiffService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

  @Test
  void leftOperand_noEntityById_newEntityWithLeft() {
    // Given
    DiffOperand operand = new DiffOperand();
    operand.setValue("QmFzZTY0RGlmZmVyVGVzdCBsZWZ0IFN0cmluZw=="); // "Base64DifferTest left String"
    DiffService serviceMock = mock(DiffService.class);
    when(serviceMock.findById(anyLong())).thenReturn(Optional.empty());
    when(serviceMock.save(any())).thenAnswer(i -> i.getArguments()[0]); // return input param
    DiffController controller = new DiffController(serviceMock);

    // When
    DiffEntity diffEntity = controller.leftOperand(anyLong(), operand);

    // Then
    assertEquals(operand.getValue(), diffEntity.getLeft());
  }

  @Test
  void rightOperand_noEntityById_newEntityWithRight() {
    // Given
    DiffOperand operand = new DiffOperand();
    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCByaWdodCBTdHJpbmc=="); // "Base64DifferTest right String"
    DiffService serviceMock = mock(DiffService.class);
    when(serviceMock.findById(anyLong())).thenReturn(Optional.empty());
    when(serviceMock.save(any())).thenAnswer(i -> i.getArguments()[0]); // return input param
    DiffController controller = new DiffController(serviceMock);

    // When
    DiffEntity diffEntity = controller.rightOperand(anyLong(), operand);

    // Then
    assertEquals(operand.getValue(), diffEntity.getRight());
  }
}
