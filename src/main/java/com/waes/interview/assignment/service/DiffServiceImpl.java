package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import com.waes.interview.assignment.repository.DiffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiffServiceImpl implements DiffService {

  private final DiffRepository repository;
  private final DataDiffer<String> dataDiffer;

  @Autowired
  public DiffServiceImpl(DiffRepository repository, DataDiffer<String> dataDiffer) {
    this.repository = repository;
    this.dataDiffer = dataDiffer;
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
    if (diffEntity == null) {
      return DiffResult.notConfigured();
    }
    return dataDiffer.calculateDiff(diffEntity.getLeft(), diffEntity.getRight());
  }
}
