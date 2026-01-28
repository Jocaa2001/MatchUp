package com.matchup.location.dto;


import com.matchup.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LocationDTO extends BaseDto {

    private String name;
    private String city;
    private String address;
    private boolean indoor;
    private String description;

}
