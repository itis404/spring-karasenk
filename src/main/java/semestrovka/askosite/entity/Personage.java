package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ask_id", nullable = false)
    private Ask ask;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_on_probation")
    private Boolean isOnProbation = false;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(name = "unique_name", nullable = false, unique = true, length = 64)
    private String uniqueName;

    @Column(length = 4096)
    private String info;

    @Column(name = "icon_url", length = 64)
    private String iconUrl = "default_personage.jpg";
}
