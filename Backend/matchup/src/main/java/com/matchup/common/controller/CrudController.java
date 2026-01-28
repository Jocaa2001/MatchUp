package com.matchup.common.controller;

import com.matchup.common.dto.BaseDto;
import com.matchup.common.entity.BaseEntity;
import com.matchup.common.mapper.BaseMapper;
import com.matchup.common.service.CrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class CrudController<E extends BaseEntity,
                            Dto extends BaseDto,
                            Service extends CrudService<E>,
                            Mapper extends BaseMapper<E,Dto>> {

    protected final Service service;
    protected final Mapper mapper;

    public CrudController(Service service,Mapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Dto> getAll(){
        return service.getAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public Dto getById(@PathVariable Long id){
        return mapper.toDto(service.getById(id));
    }

    @PostMapping("")
    public Dto create(@RequestBody Dto dto){
        E entity = mapper.toEntity(dto);
        entity = service.create(entity);
        return mapper.toDto(entity);
    }

    @PutMapping("/{id}")
    public Dto update(@PathVariable Long id, @RequestBody Dto dto){
        E entity = mapper.toEntity(dto);
        entity = service.update(id, entity);
        return mapper.toDto(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
