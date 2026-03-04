package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.location.entity.Location;
import com.matchup.location.repository.LocationRepository;
import com.matchup.location.service.LocationService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class LocationServiceTest extends GenericCrudServiceTest<Location>{

    @Mock
    private LocationRepository repository;

    @Override
    protected JpaRepository<Location, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Location> createService() {
        return new LocationService(repository);
    }

    @Override
    protected Location createEntity() {
        return Location.builder().build();
    }
}
