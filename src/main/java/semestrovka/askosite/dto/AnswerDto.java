package semestrovka.askosite.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AnswerDto(
        String personageUniqueName,
        String text,
        LocalDateTime postTime,
        List<String> images
) {}
