package com.matchup.userprofile.mapper;


import com.matchup.common.mapper.BaseMapper;
import com.matchup.user.mapper.UserMapper;
import com.matchup.userprofile.dto.UserProfileDTO;
import com.matchup.userprofile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserProfileMapper extends BaseMapper<UserProfile, UserProfileDTO> {

    @Mapping(source = "user", target = "user")
    UserProfileDTO toDto(UserProfile entity);

    @Mapping(source = "user", target = "user")
    UserProfile toEntity(UserProfileDTO dto);

}
