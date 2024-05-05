package com.example.ms1user.service.auth;

import com.example.ms1user.dao.entity.UserEntity;
import com.example.ms1user.dao.repository.UserRepository;
import com.example.ms1user.mapper.UserMapper;
import com.example.ms1user.model.auth.AuthRequestDto;
import com.example.ms1user.model.auth.AuthenticationDto;
import com.example.ms1user.model.auth.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    public void register(UserRegisterRequestDto requestDto) {
        var user = UserRegisterRequestDto.builder()
                .customerCode(requestDto.getCustomerCode())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .roles(requestDto.getRoles())
                .build();

        userRepository.save(userMapper.mapRegisterRequestDtoToEntity(user));
    }

    public AuthenticationDto authenticate(AuthRequestDto authRequestDto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getCustomerCode(),
                        authRequestDto.getPassword()
                )
        );
        UserEntity user = userRepository.findUserByCustomerCode(authRequestDto.getCustomerCode()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDto.builder()
                .token(jwtToken)
                .build();
    }

    public void deleteUser(Integer userId) {
        log.info("ActionLog.deleteUser.start");
        userRepository.deleteById(userId);
        log.info("ActionLog.deleteUser.end");
    }

    public static UserEntity getUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}


