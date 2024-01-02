import models.Investment;
import models.Loan;
import models.PermiumAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PermiumAccountTest {
    @Before
    public void setUp() throws Exception {
        PermiumAccount permiumAccount = new PermiumAccount("Name", 1E8);
        Loan loan = new Loan(permiumAccount, 1E8, 0.1f, 10);
        Investment investment = new Investment();
    }

    @Test
    public void allArgConstructorTest() {
        PermiumAccount permiumAccount = new PermiumAccount("Name", 1E8);

        Assert.assertEquals("Name", permiumAccount.getOwnName());
        Assert.assertEquals(1E8, permiumAccount.getBalance(), 0);
        Assert.assertEquals(0, permiumAccount.getInstallments_terms());
        Assert.assertEquals(0, permiumAccount.getBorrowed(), 0);
        Assert.assertEquals(null, permiumAccount.getLoan());
        Assert.assertEquals(false, permiumAccount.isHaveLoan());
        Assert.assertEquals(null, permiumAccount.getInvestment());
        Assert.assertEquals(false, permiumAccount.isHaveInvestment());
    }

    @Test
    public void premiumDespite() {
        PermiumAccount permiumAccount = new PermiumAccount("Name", 1E8);

        permiumAccount.despoite(1E8);

        Assert.assertEquals(2E8, permiumAccount.getBalance(), 0);
    }

    @Test
    public void premiumWithdrawTest() {
        PermiumAccount permiumAccount = new PermiumAccount("Name", 1E8);

        permiumAccount.withdraw(1E8);

        Assert.assertEquals(0, permiumAccount.getBalance(), 0);
    }

    @Test
    public void requestLoanTest() {
        PermiumAccount permiumAccount = new PermiumAccount("Name", 1E8);

        permiumAccount.requestLoan(1E8, 0.1f, 10);

        Assert.assertEquals(1E8, permiumAccount.getLoan().getAmount(), 0);
        Assert.assertEquals(0.1f, permiumAccount.getLoan().getRate(), 0);
        Assert.assertEquals(10, permiumAccount.getLoan().getTerms());
    }

    @Test
    public void requestInvestmentTest() {
        PermiumAccount permiumAccount = new PermiumAccount("Name", 1E8);
        Investment investment = new Investment();

        permiumAccount.requstInvesmet(investment);

        Assert.assertEquals(investment, permiumAccount.getInvestment());
    }

    @Test
    public void payInterestTest() {
        PermiumAccount permiumAccount = new PermiumAccount("Name", 1E8);
        Investment investment = new Investment();

        permiumAccount.requstInvesmet(investment);

        permiumAccount.payInterest();

        Assert.assertEquals(true, permiumAccount.isHaveInvestment());
        Assert.assertEquals(true, permiumAccount.getBalance() > 1E8);
    }
}
