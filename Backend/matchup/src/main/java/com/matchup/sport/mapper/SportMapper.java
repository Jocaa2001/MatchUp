package com.matchup.sport.mapper;


import com.matchup.common.mapper.BaseMapper;
import com.matchup.sport.dto.SportDTO;
import com.matchup.sport.entity.Sport;
import com.matchup.user.dto.UserDTO;
import com.matchup.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SportMapper extends BaseMapper<Sport, SportDTO> {
}
