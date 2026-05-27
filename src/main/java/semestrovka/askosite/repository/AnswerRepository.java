package semestrovka.askosite.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import semestrovka.askosite.entity.Answer;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    Slice<Answer> findByIsPostedTrue(Pageable pageable);
    Slice<Answer> findByPersonageId(UUID personageId, Pageable pageable);
    Slice<Answer> findByPersonage_Ask_UniqueNameAndIsPostedTrue(String uniqueName, Pageable pageable);
}
