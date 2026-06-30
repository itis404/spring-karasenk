package semestrovka.askosite.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ask_admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AskAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ask_id", nullable = false)
    private Ask ask;

    @Column(name = "info_posts_enabled")
    private Boolean infoPostsEnabled = false;
}
