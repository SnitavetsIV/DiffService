package com.waes.interview.assignment.controller;

import com.waes.interview.assignment.controller.dto.DiffOperand;
import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.service.DiffService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring REST Controller that provides endpoints to Diff Entity
 *
 * @author Ilya Snitavets
 */
@RestController
@RequestMapping("/v1/diff")
public class DiffController {

  private final DiffService diffService;

  @Autowired
  public DiffController(DiffService diffService) {
    this.diffService = diffService;
  }

  @PostMapping("/{id}/left")
  @ResponseStatus(HttpStatus.CREATED)
  public void leftOperand(@PathVariable Long id, @Valid @RequestBody DiffOperand operand) {
    DiffEntity diffEntity = diffService.findById(id).orElse(new DiffEntity(id));
    diffEntity.setLeft(operand.getValue());
    diffService.save(diffEntity);
  }

  @PostMapping("/{id}/right")
  @ResponseStatus(HttpStatus.CREATED)
  public void rightOperand(@PathVariable Long id, @RequestBody DiffOperand operand) {
    DiffEntity diffEntity = diffService.findById(id).orElse(new DiffEntity(id));
    diffEntity.setRight(operand.getValue());
    diffService.save(diffEntity);
  }

  @GetMapping("/{id}/diff")
  public ResponseEntity<DiffResult> fetchDiffResult(@PathVariable Long id) {
    DiffEntity diffEntity = diffService.findById(id).orElse(null);
    if (diffEntity == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(diffService.fetchResult(diffEntity));
  }
}
