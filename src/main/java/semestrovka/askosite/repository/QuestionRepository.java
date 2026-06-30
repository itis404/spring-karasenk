package semestrovka.askosite.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import semestrovka.askosite.entity.Question;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    @Query("""
            select q from Question q
            where q.personage.uniqueName = :personageUniqueName
            """)
    Slice<Question> findByPersonageUniqueName(@Param("personageUniqueName") String personageUniqueName, Pageable pageable);
}
