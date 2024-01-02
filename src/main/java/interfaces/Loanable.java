package interfaces;

import models.Loan;
import models.Status;

public  interface Loanable {
    public void borrow();

    public void repay(double amount);
}
