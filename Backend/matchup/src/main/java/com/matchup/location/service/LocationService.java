package com.matchup.location.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class LocationService extends CrudServiceImpl<Location> {
    public LocationService(JpaRepository<Location, Long> repository) {
        super(repository);
    }
}
