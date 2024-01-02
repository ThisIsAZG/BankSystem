package CustomExceptions;

public class LoanAlreadyExistsException extends Exception {
    public LoanAlreadyExistsException() {
        super("You already have a loan");
    }

    public LoanAlreadyExistsException(String message) {
        super(message);
    }

    public LoanAlreadyExistsException(String message, Throwable err) {
        super(message, err);
    }
}
