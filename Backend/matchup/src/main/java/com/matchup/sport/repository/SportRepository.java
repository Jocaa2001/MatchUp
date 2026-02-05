package com.matchup.sport.repository;

import com.matchup.sport.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SportRepository extends JpaRepository<Sport, Long> {
}
