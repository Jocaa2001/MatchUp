package com.matchup.event.repository;

import com.matchup.event.entity.Event;
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
    ORDER BY e.id DESC
""")
    List<Event> findEventsCursorBased(
            @Param("cursor") Long cursor,
            @Param("sportId") Long sportId,
            Pageable pageable
    );
}
