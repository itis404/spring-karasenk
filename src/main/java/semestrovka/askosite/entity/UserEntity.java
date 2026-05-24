package semestrovka.askosite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 64)
    private String nickname;

    @Column(name = "icon_url", length = 64)
    private String iconUrl = "default_user.jpg";

    @Column(length = 512)
    private String bio;

    @Column(name = "join_date")
    private LocalDate joinDate = LocalDate.now();

    @Column(name = "is_wall_private")
    private Boolean isWallPrivate = false;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserCredentialsEntity credentials;
}
