package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ask_info_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AskInfoPostEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ask_id", nullable = false)
    private AskEntity ask;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, length = 4096)
    private String text;

    @Column(name = "for_otviches_and_admins_only")
    private Boolean forOtvichesAndAdminsOnly = false;

    @CreationTimestamp
    @Column(name = "post_time")
    private LocalDateTime postTime;
}