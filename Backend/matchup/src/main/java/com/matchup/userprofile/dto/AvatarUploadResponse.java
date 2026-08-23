package com.matchup.userprofile.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvatarUploadResponse {

    String avatarUrl;

}
