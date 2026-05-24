package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrovka.askosite.dto.LoginByUsernameRequest;
import semestrovka.askosite.dto.RegisterRequest;
import semestrovka.askosite.entity.UserCredentialsEntity;
import semestrovka.askosite.entity.UserEntity;
import semestrovka.askosite.repository.UserCredentialsRepository;
import semestrovka.askosite.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserCredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new RuntimeException("Nickname already exists");
        }
        if (credentialsRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        UserEntity user = UserEntity.builder()
                .nickname(request.getNickname())
                .build();

        userRepository.save(user);

        UserCredentialsEntity credentials = UserCredentialsEntity.builder()
                .user(user)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        credentialsRepository.save(credentials);
    }

    public boolean login(LoginByUsernameRequest request){
        if (userRepository.existsByNickname(request.getNickname())){
            if
        }
    }
}