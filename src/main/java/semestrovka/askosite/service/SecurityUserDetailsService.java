package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = repository.findByNickname(nickname).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + nickname));
        return new SecurityUserDetails(
                user,
                user.getCredentials().getPassword()
        );
    }
}