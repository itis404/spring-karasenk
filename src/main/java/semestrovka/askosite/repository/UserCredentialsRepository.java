package semestrovka.askosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semestrovka.askosite.entity.UserCredentialsEntity;

public interface UserCredentialsRepository extends JpaRepository<UserCredentialsEntity, Integer> {

    boolean existsByEmail(String email);

}
