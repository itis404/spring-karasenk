package semestrovka.askosite.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import semestrovka.askosite.entity.User;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SecurityUserDetails implements UserDetails {
    @Getter
    private final User user;
    private final String password;

    @NullMarked
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @NullMarked
    @Override
    public String getUsername() {
        return user.getNickname();
    }

    @Override
    public boolean isEnabled() {
        return !user.getIsDeleted();
    }
}
