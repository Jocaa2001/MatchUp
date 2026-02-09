package com.matchup.user.dto;


import com.matchup.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends BaseDto {

    private Long userProfileId;
    private String email;
    private String password;
    private String role;

}
