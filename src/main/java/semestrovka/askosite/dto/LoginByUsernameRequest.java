package semestrovka.askosite.dto;

import lombok.Data;

@Data
public class LoginByUsernameRequest {
    private String nickname;
    private String password;
}
