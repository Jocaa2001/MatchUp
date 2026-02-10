package com.matchup.common.service;

import com.matchup.common.entity.BaseEntity;
import com.matchup.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CrudService <E extends BaseEntity>{

    List<E> getAll();

    E getById(Long id) throws EntityNotFoundException;

    boolean existsById(Long id);

    E create(E entity);

    E update(Long id, E entity) throws EntityNotFoundException;

    void delete(Long id);

}
