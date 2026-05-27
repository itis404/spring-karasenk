package semestrovka.askosite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginByNicknameRequest {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
}
