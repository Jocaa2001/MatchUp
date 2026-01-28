package com.matchup.user.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.user.dto.UserDTO;
import com.matchup.user.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
