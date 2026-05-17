package com.matchup.user.dto;


import com.matchup.common.dto.BaseDto;
import lombok.Data;


@Data
public class CreateUserDTO extends BaseDto {

    private String email;
    private String password;

}
