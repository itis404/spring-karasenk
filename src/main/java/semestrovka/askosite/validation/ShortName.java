package semestrovka.askosite.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ShortNameValidator.class)
public @interface ShortName {
    String message() default "В коротком имени должны использоваться только латинские буквы, арабские цифры и _. Длина от 3 до 64 символов.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
