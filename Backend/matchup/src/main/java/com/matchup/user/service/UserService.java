package com.matchup.user.service;


import com.matchup.common.service.CrudServiceImpl;
import com.matchup.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudServiceImpl<User> {

    public UserService(JpaRepository<User, Long> repository) {
        super(repository);
    }
}
