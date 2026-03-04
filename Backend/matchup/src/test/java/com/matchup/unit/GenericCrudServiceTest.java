package com.matchup.unit;

import com.matchup.common.entity.BaseEntity;
import com.matchup.common.service.CrudServiceImpl;
import com.matchup.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public abstract class GenericCrudServiceTest<E extends BaseEntity> {

    protected CrudServiceImpl<E> service;

    protected abstract JpaRepository<E, Long> getRepository();

    protected abstract CrudServiceImpl<E> createService();

    protected abstract E createEntity();

    @BeforeEach
    void setup() {
        service = createService();
    }

    @Test
    void getAllShouldReturnEntities() {
        E entity = createEntity();
        when(getRepository().findAll()).thenReturn(List.of(entity));

        List<E> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals(entity, result.getFirst());
        verify(getRepository()).findAll();
    }

    @Test
    void getByIdShouldReturnEntity() {
        E entity = createEntity();
        entity.setId(1L);
        when(getRepository().findById(1L)).thenReturn(java.util.Optional.of(entity));

        E result = service.getById(1L);

        assertEquals(entity.getId(), result.getId());
        verify(getRepository()).findById(1L);
    }

    @Test
    void getByIdShouldThrowIfNotFound() {
        when(getRepository().findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getById(1L));
        verify(getRepository()).findById(1L);
    }

    @Test
    void createShouldSaveEntity() {
        E entity = createEntity();
        entity.setId(1L);
        when(getRepository().save(entity)).thenReturn(entity);

        E result = service.create(entity);

        assertEquals(entity, result);
        verify(getRepository()).save(entity);
    }

    @Test
    void shouldUpdateThrowIfNotExists() {
        E entity = createEntity();
        entity.setId(1L);
        when(getRepository().existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.update(1L, entity));
        verify(getRepository()).existsById(1L);
    }

    @Test
    void shouldUpdateSaveIfExists() {
        E entity = createEntity();
        entity.setId(1L);
        when(getRepository().existsById(1L)).thenReturn(true);
        when(getRepository().save(entity)).thenReturn(entity);

        E result = service.update(1L, entity);

        assertEquals(entity, result);
        verify(getRepository()).existsById(1L);
        verify(getRepository()).save(entity);
    }

    @Test
    void deleteShouldCallRepository() {
        Long id = 1L;
        doNothing().when(getRepository()).deleteById(id);

        service.delete(id);

        verify(getRepository()).deleteById(id);
    }
}