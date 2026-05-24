package semestrovka.askosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semestrovka.askosite.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByNickname(String nickname);

    Optional<UserEntity> findByNickname(String nickname);
}
