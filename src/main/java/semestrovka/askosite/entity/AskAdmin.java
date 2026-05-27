package semestrovka.askosite.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ask_admin")
@IdClass(AskAdminId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AskAdmin {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "ask_id")
    private Ask ask;

    @Column(name = "info_posts_enabled")
    private Boolean infoPostsEnabled = false;
}
