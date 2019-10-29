package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.repository.DiffRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiffServiceImpl implements DiffService {

  private final DiffRepository repository;

  @Autowired
  public DiffServiceImpl(DiffRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<DiffEntity> findById(Long id) {
    if (id == null) {
      return Optional.empty();
    }
    return repository.findById(id);
  }

  @Override
  public DiffEntity save(DiffEntity diffEntity) {
    if (diffEntity == null) {
      return null;
    }
    return repository.save(diffEntity);
  }

  @Override
  public DiffResult fetchResult(DiffEntity diffEntity) {

    return null;
  }
}
