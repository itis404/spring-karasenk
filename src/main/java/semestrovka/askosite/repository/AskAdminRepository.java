package semestrovka.askosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import semestrovka.askosite.entity.Ask;
import semestrovka.askosite.entity.AskAdmin;
import semestrovka.askosite.entity.User;

import java.util.List;

public interface AskAdminRepository extends JpaRepository<AskAdmin, Long> {
    boolean existsByAskAndUser(Ask ask, User user);
    @Query("""
            select a
            from Ask a
            left join AskAdmin aa on aa.ask = a
            left join User u on u = aa.user
            where u.nickname = :nickname
            """)
    List<Ask> getAdminableAsks(@Param("nickname") String nickname);

    @Query("""
            select a.infoPostsEnabled
            from AskAdmin a
            where a.user.nickname = :userNickname and a.ask.uniqueName = :askUniqueName
            """)
    boolean isAdminInfopostEnabled(@Param("userNickname") String userNickname, @Param("askUniqueName") String askUniqueName);
}
