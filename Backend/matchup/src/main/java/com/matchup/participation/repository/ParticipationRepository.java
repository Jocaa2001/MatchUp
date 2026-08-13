package com.matchup.participation.repository;

import com.matchup.event.entity.Event;
import com.matchup.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation,Long> {

    List<Participation> findByEventId(Long eventId);

    long countParticipantsByEventId(Long eventId);

    @Query("SELECT p.event FROM Participation p WHERE p.user.id = :userId")
    List<Event> findEventsByUserId(@Param("userId") Long userId);
}
