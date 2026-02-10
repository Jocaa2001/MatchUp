package com.matchup.common.service;

import com.matchup.common.entity.BaseEntity;
import com.matchup.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;


public abstract class CrudServiceImpl<E extends BaseEntity> implements CrudService<E> {

    protected final JpaRepository<E, Long> repository;

    protected CrudServiceImpl(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<E> getAll() {return repository.findAll();}

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public E getById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    public E update(Long id, E entity) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
         repository.deleteById(id);
    }

}
