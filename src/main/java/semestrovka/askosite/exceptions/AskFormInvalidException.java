package semestrovka.askosite.exceptions;

import org.springframework.validation.FieldError;

public class AskFormInvalidException extends RuntimeException {
    public AskFormInvalidException(Iterable<FieldError> errors){
        StringBuilder message = new StringBuilder();
        for (FieldError error : errors){
            message.append(error.getDefaultMessage());
            message.append("\n");
        }
        super(message.toString());
    }
}
