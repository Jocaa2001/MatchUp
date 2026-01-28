package com.matchup.location.mapper;


import com.matchup.common.mapper.BaseMapper;
import com.matchup.location.dto.LocationDTO;
import com.matchup.location.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper extends BaseMapper<Location, LocationDTO> {
}
