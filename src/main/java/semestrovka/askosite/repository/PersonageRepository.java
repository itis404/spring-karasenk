package semestrovka.askosite.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import semestrovka.askosite.entity.Personage;
import semestrovka.askosite.entity.User;

import java.util.Optional;

public interface PersonageRepository extends JpaRepository<Personage, Long> {
    Optional<Personage> findByUniqueName(String uniqueName);
    Slice<Personage> findByAskUniqueNameAndIsActiveTrue(String askUniqueName, Pageable pageable);
    boolean existsByUniqueNameAndUser(@Param("personageUniqueName") String personageUniqueName, @Param("user") User user);
    @Modifying
    @Transactional
    @Query("""
            update Personage p
            set p.isActive = false
            where p.id = :id
            """)
    int safeDelete(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("""
            update Personage p
            set p.name = :name, p.info = :info, p.iconUrl = :iconUrl
            where p.id = :id
            """)
    int update(@Param("id") Long id, String name, String info, String iconUrl);
}
