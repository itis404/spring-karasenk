package semestrovka.askosite.dto;

import org.springframework.data.domain.Slice;

public record AskDto (
    String name,
    String uniqueName,
    String icon,
    Slice<AnswerDto> answers
){}
