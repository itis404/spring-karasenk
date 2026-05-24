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
public class AchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private AskEntity ask;
}