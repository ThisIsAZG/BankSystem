package CustomExceptions;

public class TransactionLimitException extends Exception {
    public TransactionLimitException() {
        super("Transaction limit exceeded");
    }
    public TransactionLimitException(String message) {
        super(message);
    }

    public TransactionLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
