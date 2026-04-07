package com.matchup.sport.dto;

import com.matchup.common.dto.BaseDto;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class SportDTO extends BaseDto {

    @NotBlank(message = "Sport name is required")
    @Size(min = 2, max = 50, message = "Sport name must be between 2 and 50 characters")
    private String name;

}
