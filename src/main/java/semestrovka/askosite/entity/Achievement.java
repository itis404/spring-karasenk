package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "achievement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "icon_url", length = 64)
    private String iconUrl = "default_achievement_icon.jpg";

    @Column(nullable = false, length = 32)
    private String name;

    @Column(name = "add_days")
    private Short addDays = 0;

    @Column(length = 128)
    private String info;

    @ManyToOne
    @JoinColumn(name = "ask_id", nullable = false)
    private Ask ask;
}