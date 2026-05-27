package semestrovka.askosite.exceptions;

public class AskAlreadyExistsException extends RuntimeException {
    public AskAlreadyExistsException() {
        super("Ask with this unique short name already exists");
    }
}
