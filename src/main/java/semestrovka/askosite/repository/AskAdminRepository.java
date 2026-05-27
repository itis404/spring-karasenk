package semestrovka.askosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semestrovka.askosite.entity.AskAdmin;
import semestrovka.askosite.entity.AskAdminId;

public interface AskAdminRepository extends JpaRepository<AskAdmin, AskAdminId> {

}
