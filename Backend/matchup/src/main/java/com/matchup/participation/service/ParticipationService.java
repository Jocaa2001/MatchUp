package com.matchup.participation.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class ParticipationService extends CrudServiceImpl<Participation> {
    public ParticipationService(JpaRepository<Participation, Long> repository) {
        super(repository);
    }
}
