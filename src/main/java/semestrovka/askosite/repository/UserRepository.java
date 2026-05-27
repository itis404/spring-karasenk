package semestrovka.askosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import semestrovka.askosite.entity.Ask;
import semestrovka.askosite.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByNickname(String nickname);

    Optional<User> findByNickname(String nickname);

    @Query("""
            select a
            from Ask a
            left join AskAdmin aa on aa.ask = a
            left join User u on u = aa.user
            where u.id = :id
            """)
    List<Ask> getAdminableAsks(@Param("id") Integer id);

    @Query("""
            select count(a) > 0
            from AskAdmin a
            where a.user.id = :userId and a.ask.id = :askId
            """)
    boolean isAdmin(@Param("userId") Long userId, @Param("askId") Long askId);

    @Query("""
            select a.infoPostsEnabled
            from AskAdmin a
            where a.user.id = :userId and a.ask.id = :askId
            """)
    boolean isAdminInfopostEnabled(@Param("userId") Long userId, @Param("askId") Long askId);
}