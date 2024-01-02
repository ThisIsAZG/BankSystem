package CustomExceptions;

public class NoLoanExistsException extends Exception {
    public NoLoanExistsException() {
        super("There is no loan exists for this account");
    }

    public NoLoanExistsException(String message) {
        super(message);
    }

    public NoLoanExistsException(String message, Throwable err) {
        super(message, err);
    }

}
