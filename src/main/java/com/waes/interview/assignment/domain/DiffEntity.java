package com.waes.interview.assignment.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

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
