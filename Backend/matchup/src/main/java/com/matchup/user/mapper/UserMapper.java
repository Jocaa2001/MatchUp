package com.matchup.user.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.user.dto.UserDTO;
import com.matchup.user.entity.User;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.repository.UserProfileRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements BaseMapper<User, UserDTO> {

    @Autowired
    protected UserProfileRepository userProfileRepository;

    @Mapping(source = "userProfileId", target = "profile", qualifiedByName = "mapProfile")
    public abstract User toEntity(UserDTO dto);

    @Mapping(source = "profile.id", target = "userProfileId")
    public abstract UserDTO toDto(User entity);

    @Named("mapProfile")
    protected UserProfile mapProfile(Long id) {
        return id == null ? null : userProfileRepository.getReferenceById(id);
    }
}
