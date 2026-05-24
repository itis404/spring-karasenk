package semestrovka.askosite.exceptions;

public class NicknameAlreadyExistsException extends RuntimeException {
    public NicknameAlreadyExistsException(String message) {
        super(message);
    }
}
