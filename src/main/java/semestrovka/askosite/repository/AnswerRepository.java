package semestrovka.askosite.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import semestrovka.askosite.entity.Answer;
import semestrovka.askosite.entity.Personage;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    Slice<Answer> findByIsPostedTrue(Pageable pageable);
    Slice<Answer> findByPersonageUniqueNameAndIsPostedTrue(String personageUniqueName, Pageable pageable);
    Slice<Answer> findByPersonage_Ask_UniqueNameAndIsPostedTrue(String uniqueName, Pageable pageable);

    @Query("""
            update Answer a
            set a.isPosted = false
            where a.id = :id
            """)
    @Modifying
    @Transactional
    int safeDelete(@Param("id") UUID id);
}
