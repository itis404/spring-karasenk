package semestrovka.askosite.exceptions;

public class PersonageNotFoundException extends RuntimeException {
  public PersonageNotFoundException(String message) {
    super(message);
  }
}
