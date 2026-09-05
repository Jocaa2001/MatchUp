package com.matchup.location.repository;

import com.matchup.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("""
    SELECT DISTINCT l.city
    FROM Location l
    ORDER BY l.city
""")
    List<String> findDistinctCities();

}
