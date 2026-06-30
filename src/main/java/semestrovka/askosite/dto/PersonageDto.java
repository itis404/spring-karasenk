package semestrovka.askosite.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonageDto {
    private String name;
    private String uniqueName;
    private String info;
    private String icon;
    private Boolean isOwner;
}
