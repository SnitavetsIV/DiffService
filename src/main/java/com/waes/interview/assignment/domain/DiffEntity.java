package com.waes.interview.assignment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/** Entity to hold information for differing */
@Data
@NoArgsConstructor
@Entity
public class DiffEntity {

  @Id private Long id;

  private String left;

  private String right;

  public DiffEntity(Long id) {
    this.id = id;
  }
}
