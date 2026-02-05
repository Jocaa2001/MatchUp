package com.matchup.userprofile.mapper;


import com.matchup.common.mapper.BaseMapper;
import com.matchup.user.entity.User;
import com.matchup.user.repository.UserRepository;
import com.matchup.userprofile.dto.UserProfileDTO;
import com.matchup.userprofile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserProfileMapper implements BaseMapper<UserProfile, UserProfileDTO> {

    @Autowired
    UserRepository userRepository;

    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
    public abstract UserProfile toEntity(UserProfileDTO dto);

    public abstract UserProfileDTO toDto(UserProfile entity);


    @Named("mapUser")
    protected User mapUser(Long id) {
        return id == null ? null : userRepository.getReferenceById(id);
    }
}
