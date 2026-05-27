package semestrovka.askosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semestrovka.askosite.entity.Ask;

import java.util.Optional;
import java.util.UUID;

public interface AskRepository extends JpaRepository<Ask, UUID> {
    boolean existsByUniqueName(String name);
    Optional<Ask> findByUniqueName(String name);
}
