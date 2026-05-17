    package com.matchup.auth.controller;

    import com.matchup.auth.dto.LoginResponse;
    import com.matchup.auth.dto.LoginUserDto;
    import com.matchup.auth.dto.RegisterUserDto;
    import com.matchup.auth.service.AuthenticationService;
    import com.matchup.auth.service.JwtService;
    import com.matchup.user.dto.CreateUserDTO;
    import com.matchup.user.entity.User;
    import com.matchup.user.mapper.UserMapper;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RequestMapping("/auth")
    @RestController
    public class AuthenticationController {
        private final JwtService jwtService;

        private final AuthenticationService authenticationService;

        private final UserMapper userMapper;
        public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserMapper userMapper) {
            this.jwtService = jwtService;
            this.authenticationService = authenticationService;
            this.userMapper = userMapper;
        }

        @PostMapping("/register")
        public ResponseEntity<CreateUserDTO> register(@RequestBody RegisterUserDto registerUserDto) {
            CreateUserDTO registeredUser = userMapper.toDtoRegister(authenticationService.signup(registerUserDto));

            return ResponseEntity.ok(registeredUser);
        }

        @PostMapping("/login")
        public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse =  LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

            return ResponseEntity.ok(loginResponse);
        }
    }
