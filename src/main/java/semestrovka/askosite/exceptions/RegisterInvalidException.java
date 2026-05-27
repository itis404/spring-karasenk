package semestrovka.askosite.exceptions;

import org.springframework.validation.FieldError;

public class RegisterInvalidException extends RuntimeException {
    public RegisterInvalidException(Iterable<FieldError> errors) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : errors){
            message.append(error.getDefaultMessage());
            message.append("\n");
        }
        super(message.toString());
    }
}
