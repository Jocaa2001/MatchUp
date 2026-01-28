package com.matchup.sport.dto;

import com.matchup.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class SportDTO extends BaseDto {

    private String name;

}
