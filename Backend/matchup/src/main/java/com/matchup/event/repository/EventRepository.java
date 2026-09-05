package com.matchup.event.repository;

import com.matchup.event.entity.Event;
import com.matchup.event.enums.EventStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findByUserId(Long userId);

    @Query("""
    SELECT e
    FROM Event e
    WHERE (:cursor IS NULL OR e.id < :cursor)
      AND (:sportId IS NULL OR e.sport.id = :sportId)
      AND (
          :search IS NULL
          OR e.location.name ILIKE CONCAT('%', CAST(:search AS string), '%')
      )
      AND (:status IS NULL OR e.status = :status)
      AND (:city IS NULL OR e.location.city = :city)
    ORDER BY e.id DESC
""")
    List<Event> findEventsCursorBased(
            @Param("cursor") Long cursor,
            @Param("sportId") Long sportId,
            @Param("search") String search,
            @Param("status") EventStatus status,
            @Param("city") String city,
            Pageable pageable
    );
}
