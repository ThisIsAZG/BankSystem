package CustomExceptions;

public class NoEnoughBalanceException extends Exception {
    public NoEnoughBalanceException() {
        super("There is no enough balance in your account");
    }

    public NoEnoughBalanceException(String message) {
        super(message);
    }

    public NoEnoughBalanceException(String message, Throwable err) {
        super(message, err);
    }
}
