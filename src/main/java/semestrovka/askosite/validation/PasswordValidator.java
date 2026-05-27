package semestrovka.askosite.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])" + // содержит цифры
            "(?=.*[a-z])" + // содержит маленькие буквы
            "(?=.*[A-Z])" + // содержит большие буквы
            "(?=\\S+$)" + // не содержит пробел
            ".{8,32}$" // от 8 до 32 символов
    );

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return (password != null) && PASSWORD_PATTERN.matcher(password).matches();
    }
}