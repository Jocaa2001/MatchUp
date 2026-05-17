package com.matchup.auth.service;

import com.matchup.auth.dto.LoginUserDto;
import com.matchup.auth.dto.RegisterUserDto;
import com.matchup.exception.EmailAlreadyExistsException;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.user.entity.User;
import com.matchup.user.enums.UserRole;
import com.matchup.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) throws EmailAlreadyExistsException {

        Optional<User> userOptional = userRepository.findByEmail(input.getEmail());

        if(userOptional.isPresent()){
            throw new EmailAlreadyExistsException();
        }

        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(UserRole.valueOf(String.valueOf(UserRole.USER)))
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}