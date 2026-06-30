package semestrovka.askosite.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private UUID id;
    private String personageUniqueName;
    private String text;
    private LocalDateTime postTime;
    private List<String> images;
}
