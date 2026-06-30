package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // потом надо будет сделать общие вопросы которые можно добавлять
    // своим персам по нажатию плюсика в списке общих
    // это уже ManyToMany... и это ещё аск привязывать....
    @ManyToOne
    @JoinColumn(name = "personage_id", nullable = false)
    private Personage personage;

    @Column(nullable = false, length = 128)
    private String text;

}
