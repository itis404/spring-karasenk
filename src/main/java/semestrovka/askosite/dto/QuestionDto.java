package semestrovka.askosite.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionDto {
    private String nickname;
    private String text;
}
