package semestrovka.askosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semestrovka.askosite.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByNickname(String nickname);

    Optional<User> findByNickname(String nickname);
}