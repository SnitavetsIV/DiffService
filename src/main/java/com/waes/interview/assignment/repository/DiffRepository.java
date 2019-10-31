package com.waes.interview.assignment.repository;

import com.waes.interview.assignment.domain.DiffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Interface for access to Diff Entity in database */
@Repository
public interface DiffRepository extends JpaRepository<DiffEntity, Long> {}
