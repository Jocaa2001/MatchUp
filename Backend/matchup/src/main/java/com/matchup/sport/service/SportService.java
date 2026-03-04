package com.matchup.sport.service;

import com.matchup.common.service.CrudService;
import com.matchup.common.service.CrudServiceImpl;
import com.matchup.sport.entity.Sport;
import com.matchup.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class SportService extends CrudServiceImpl<Sport> {

    public SportService(JpaRepository<Sport, Long> repository) {
        super(repository);
    }
}
