package com.matchup.exception;


import com.matchup.common.entity.BaseEntity;

public class EntityNotFoundException extends RuntimeException {

    private final Long id;

    public EntityNotFoundException(Long id) {
        super("Entity with id = " + id + " not found");
        this.id = id;
    }

    public Object getId() {
        return id;
    }


}