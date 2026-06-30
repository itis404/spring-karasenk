package semestrovka.askosite.exceptions;

public class AnswerFormInvalidException extends RuntimeException {
    public AnswerFormInvalidException(Iterable<String> errors) {
        StringBuilder message = new StringBuilder();
        for (String error : errors){
            message.append(error);
            message.append("\n");
        }
        super(message.toString());
    }
}
