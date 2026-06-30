package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ask")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ask {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "unique_name", nullable = false, unique = true, length = 64)
    private String uniqueName;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(name = "icon_url", length = 64)
    private String iconUrl = "default_ask.jpg";
}
