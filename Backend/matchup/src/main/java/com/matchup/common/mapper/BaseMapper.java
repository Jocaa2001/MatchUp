package com.matchup.common.mapper;

import com.matchup.common.dto.BaseDto;
import com.matchup.common.entity.BaseEntity;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

    D toDto(E entity);
    E toEntity(D dto);

}
