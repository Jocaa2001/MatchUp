package com.matchup.user.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.user.dto.CreateUserDTO;
import com.matchup.user.dto.UserDTO;
import com.matchup.user.entity.User;
import com.matchup.userprofile.dto.UserProfileDTO;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.repository.UserProfileRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {

    @Override
    @Mapping(source = "profile", target = "profile")
    UserDTO toDto(User entity);

    @Override
    User toEntity(UserDTO dto);

    UserProfileDTO toDto(UserProfile entity);

    UserProfile toEntity(UserProfileDTO dto);

    CreateUserDTO toDtoRegister(User entity);
}
