package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrovka.askosite.dto.RegisterRequest;
import semestrovka.askosite.entity.UserCredentials;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.EmailAlreadyExistsException;
import semestrovka.askosite.exceptions.NicknameAlreadyExistsException;
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
            throw new NicknameAlreadyExistsException();
        }
        if (credentialsRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        User user = User.builder()
                .nickname(request.getNickname())
                .isDeleted(false)
                .isWallPrivate(false)
                .build();
        userRepository.save(user);

        UserCredentials credentials = UserCredentials.builder()
                .user(user)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        credentialsRepository.save(credentials);
    }
}