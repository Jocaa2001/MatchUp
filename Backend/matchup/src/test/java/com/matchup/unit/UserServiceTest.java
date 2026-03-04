package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.user.entity.User;
import com.matchup.user.repository.UserRepository;
import com.matchup.user.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends GenericCrudServiceTest<User> {

    @Mock
    private UserRepository repository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<User> createService() {
        return new UserService(repository);
    }

    @Override
    protected User createEntity() {
        return User.builder()
                .email("test@example.com")
                .password("password")
                .build();
    }
}