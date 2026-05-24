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
public class DeadlineEntity {
    @Id
    @Column(name = "personage_id")
    private Integer personageId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personage_id")
    private PersonageEntity personage;

    @Column(name = "additional_days")
    private Short additionalDays = 0;
}