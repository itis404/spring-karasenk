package semestrovka.askosite.exceptions;


public class AskFormInvalidException extends RuntimeException {
    public AskFormInvalidException(Iterable<String> errors){
        StringBuilder message = new StringBuilder();
        for (String error : errors){
            message.append(error);
            message.append("\n");
        }
        super(message.toString());
    }
}
