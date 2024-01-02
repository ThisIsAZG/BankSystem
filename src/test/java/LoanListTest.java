import models.Loan;
import models.LoanList;
import models.PermiumAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoanListTest {
    PermiumAccount permiumAccount;
    Loan loan1, loan2, loan3;

    @Before
    public void setup() throws Exception {
        permiumAccount = new PermiumAccount("Name", 1E8);
        loan1 = new Loan(permiumAccount, 1E8, 0.1f, 6);
        loan2 = new Loan(permiumAccount, 2E8, 0.2f, 12);
        loan3 = new Loan(permiumAccount, 3E8, 0.22f, 24);

        LoanList.addLoan(loan1);
        LoanList.addLoan(loan2);
        LoanList.addLoan(loan3);
    }
    @Test
    public void getLoanListSizeTest() {
        Assert.assertEquals(3, LoanList.getSize());
    }

    @Test
    public void getLoansByAccountTest() {
        Assert.assertEquals(3, LoanList.getLoansByAccount(permiumAccount.getAccountNumber()).size());
    }

    @Test
    public void getLoanByLoanNumberTest() {
        Assert.assertEquals(loan1, LoanList.getLoan(loan1.getLoanNumber()));
    }

}
