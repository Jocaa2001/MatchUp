package com.matchup.location.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.location.entity.Location;
import com.matchup.location.repository.LocationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class LocationService extends CrudServiceImpl<Location, LocationRepository> {
    public LocationService(LocationRepository repository) {
        super(repository);
    }
}
