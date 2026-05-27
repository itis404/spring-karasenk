package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deadline")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deadline {
    @Id
    @Column(name = "personage_id")
    private Long personageId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personage_id")
    private Personage personage;

    @Column(name = "additional_days")
    private Short additionalDays = 0;
}