package com.matchup.exception;


public class EntityNotFoundException extends RuntimeException {

    private final Object id;
    private final Object entity;

    public EntityNotFoundException(Object id, Object entity) {
        super(entity.getClass().getSimpleName() + " not found with id: " + id);
        this.id = id;
        this.entity = entity;
    }

    public Object getId() {
        return id;
    }

    public Object getEntity() {
        return entity;
    }
}