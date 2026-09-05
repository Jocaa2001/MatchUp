package com.matchup.location.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.location.dto.DistinctCityResponse;
import com.matchup.location.entity.Location;
import com.matchup.location.repository.LocationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationService extends CrudServiceImpl<Location, LocationRepository> {
    public LocationService(LocationRepository repository) {
        super(repository);
    }

    public List<DistinctCityResponse> getDistinctCities() {
        return repository.findDistinctCities()
                .stream()
                .map(city -> {
                    DistinctCityResponse response = new DistinctCityResponse();
                    response.setCity(city);
                    return response;
                })
                .toList();
    }

}
