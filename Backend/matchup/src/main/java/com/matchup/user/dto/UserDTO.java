package com.matchup.user.dto;


import com.matchup.common.dto.BaseDto;
import lombok.Data;

@Data
public class UserDTO extends BaseDto {

    private String email;
    private String password;
    private String role;

}
