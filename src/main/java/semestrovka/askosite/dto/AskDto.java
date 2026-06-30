package semestrovka.askosite.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AskDto {
    private String name;
    private String uniqueName;
    private String icon;
    private Boolean isAdmin;
}
