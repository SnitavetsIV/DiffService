package com.waes.interview.assignment.controller;

import com.waes.interview.assignment.controller.dto.DiffOperand;
import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.service.DiffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Spring REST Controller that provides endpoints to Diff Entity
 *
 * @author Ilya Snitavets
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/diff")
public class DiffController {

  private final DiffService diffService;

  /**
   * Endpoint to save left part of the diff entity. If entity is already exists by {id} - left part
   * will be updated, otherwise new diff entity will be created and left part will be set.
   *
   * @param id - identifier for diff entity
   * @param operand - request with value of left operand
   */
  @PostMapping("/{id}/left")
  @ResponseStatus(HttpStatus.CREATED)
  public void leftOperand(@PathVariable Long id, @Valid @RequestBody DiffOperand operand) {
    DiffEntity diffEntity = diffService.findById(id).orElse(new DiffEntity(id));
    diffEntity.setLeft(operand.getValue());
    diffService.save(diffEntity);
  }

  /**
   * Endpoint to save left part of the diff entity. If entity is already exists by {id} - right part
   * will be updated, otherwise new diff entity will be created and right part will be set.
   *
   * @param id - identifier for diff entity
   * @param operand - request with value of right operand
   */
  @PostMapping("/{id}/right")
  @ResponseStatus(HttpStatus.CREATED)
  public void rightOperand(@PathVariable Long id, @RequestBody DiffOperand operand) {
    DiffEntity diffEntity = diffService.findById(id).orElse(new DiffEntity(id));
    diffEntity.setRight(operand.getValue());
    diffService.save(diffEntity);
  }

  /**
   * Endpoint for getting the results of diff operation.
   *
   * @param id - identifier for diff entity
   * @return the result whether left and right part are equal or differ and if differ at which place
   *     exactly.
   */
  @GetMapping("/{id}")
  public ResponseEntity<DiffResult> fetchDiffResult(@PathVariable Long id) {
    return diffService
        .findById(id)
        .map(e -> ResponseEntity.ok(diffService.fetchResult(e)))
        .orElse(ResponseEntity.notFound().build());
  }
}
