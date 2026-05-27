package semestrovka.askosite.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "Пароль должен содержать цифры, большие и маленькие латинские буквы, не должен содержать пробелов. Длина от 8 до 32 символов.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
