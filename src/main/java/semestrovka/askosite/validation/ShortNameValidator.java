package semestrovka.askosite.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ShortNameValidator implements ConstraintValidator<ShortName, String> {

    private static final Pattern SHORT_NAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]{3,64}$");
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return (name != null) && SHORT_NAME_PATTERN.matcher(name).matches();
    }
}
