package semestrovka.askosite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import semestrovka.askosite.validation.Password;

@Data
public class RegisterRequest {
    @NotBlank(message = "Заполните поле \"Никнейм\"")
    private String nickname;

    @NotBlank(message = "Заполните поле \"Email\"")
    private String email;

    @NotBlank(message = "Заполните поле \"Пароль\"")
    @Password
    private String password;
}