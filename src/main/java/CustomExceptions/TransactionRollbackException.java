package CustomExceptions;

public class TransactionRollbackException extends Exception {
    public TransactionRollbackException() {
        super("Transaction rollback failed");
    }
    public TransactionRollbackException(String message) {
        super(message);
    }

    public TransactionRollbackException(String message, Throwable err) {
        super(message, err);
    }
}
