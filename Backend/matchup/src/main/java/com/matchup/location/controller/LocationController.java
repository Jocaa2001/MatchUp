package com.matchup.location.controller;

import com.matchup.common.controller.CrudController;
import com.matchup.location.dto.DistinctCityResponse;
import com.matchup.location.dto.LocationDTO;
import com.matchup.location.entity.Location;
import com.matchup.location.mapper.LocationMapper;
import com.matchup.location.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController()
@RequestMapping("/locations")
public class LocationController extends CrudController<Location, LocationDTO, LocationService, LocationMapper> {

    public LocationController(LocationService service, LocationMapper mapper) {
        super(service, mapper);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<DistinctCityResponse>> getDistinctCities() {
        return ResponseEntity.ok(service.getDistinctCities());
    }

}
