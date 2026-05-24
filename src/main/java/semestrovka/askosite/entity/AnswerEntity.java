package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "personage_id", nullable = false)
    private PersonageEntity personage;

    @CreationTimestamp
    @Column(name = "post_time")
    private LocalDateTime postTime;

    @Column(length = 256)
    private String text;

    @Column(nullable = false)
    private Integer likes = 0;
}
