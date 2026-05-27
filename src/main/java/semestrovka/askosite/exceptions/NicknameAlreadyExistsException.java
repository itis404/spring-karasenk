package semestrovka.askosite.exceptions;

public class NicknameAlreadyExistsException extends RuntimeException {
    public NicknameAlreadyExistsException() {
        super("Nickname already exists.");
    }
}
