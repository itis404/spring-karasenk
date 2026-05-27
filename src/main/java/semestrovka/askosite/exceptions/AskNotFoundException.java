package semestrovka.askosite.exceptions;

public class AskNotFoundException extends RuntimeException {
    public AskNotFoundException() {
        super("Аск не найден.");
    }
}
