package com.matchup.user.dto;


import lombok.Data;

@Data
public class CreateUserDTO {

    private String email;
    private String password;
    private String role;

}
