package com.matchup.userprofile.dto;


import com.matchup.common.dto.BaseDto;
import com.matchup.user.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfileDTO extends BaseDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String city;
    private String phone;
    private LocalDate birthDate;
    private String avatarUrl;

}
