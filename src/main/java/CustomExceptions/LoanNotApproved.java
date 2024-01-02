package CustomExceptions;

public class LoanNotApproved extends Exception {
    public LoanNotApproved() {
        super();
    }

    public LoanNotApproved(String message) {
        super(message);
    }

    public LoanNotApproved(String message, Throwable err) {
        super(message, err);
    }
}
