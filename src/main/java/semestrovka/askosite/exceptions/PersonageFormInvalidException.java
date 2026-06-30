package semestrovka.askosite.exceptions;

public class PersonageFormInvalidException extends RuntimeException {
    public PersonageFormInvalidException(Iterable<String> errors){
        StringBuilder message = new StringBuilder();
        for (String error : errors){
            message.append(error);
            message.append("\n");
        }
        super(message.toString());
    }
}
