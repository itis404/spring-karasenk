package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "personage_id", nullable = false)
    private Personage personage;

    @CreationTimestamp
    @Column(name = "post_time")
    private LocalDateTime postTime;

    @Column(length = 256)
    private String text;

    @Column(name = "is_posted")
    private Boolean isPosted = false;

    @ElementCollection
    @CollectionTable(
            name = "answer_images",
            joinColumns = @JoinColumn(name = "answer_id")
    )
    @Column(name = "image_url", nullable = false, length = 64)
    private List<String> imageUrls = new ArrayList<>();
}
