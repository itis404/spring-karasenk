package semestrovka.askosite.exceptions;

public class PersonageNotFoundException extends RuntimeException {
    public PersonageNotFoundException() {
        super("Персонаж не найден");
    }
}
