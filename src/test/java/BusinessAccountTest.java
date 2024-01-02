import models.BasicAccount;
import models.BusinessAccount;
import models.Investment;
import models.Loan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusinessAccountTest {
    @Before
    public void setUp() throws Exception {
        BusinessAccount businessAccount = new BusinessAccount("Name", 1E8);
        Loan loan = new Loan(businessAccount, 1E8, 0.1f, 10);
        Investment investment = new Investment();
    }

    @Test
    public void allArgConstructorTest() {
        BusinessAccount account = new BusinessAccount("Name", 1E8);

        Assert.assertEquals("Name", account.getOwnName());
        Assert.assertEquals(1E8, account.getBalance(), 0);
        Assert.assertEquals(0, account.getInstallments_terms());
        Assert.assertEquals(0, account.getBorrowed(), 0);
        Assert.assertEquals(null, account.getLoan());
        Assert.assertEquals(false, account.isHaveLoan());
        Assert.assertEquals(null, account.getInvestment());
        Assert.assertEquals(false, account.isHaveInvestment());
    }

    @Test
    public void premiumDespite() {
        BasicAccount account = new BusinessAccount("Name", 1E8);

        account.despoite(1E8);

        Assert.assertEquals(2E8, account.getBalance(), 0);
    }

    @Test
    public void premiumWithdrawTest() {
        BasicAccount account = new BusinessAccount("Name", 1E8);

        account.withdraw(1E8);

        Assert.assertEquals(0, account.getBalance(), 0);
    }

    @Test
    public void requestLoanTest() {
        BusinessAccount account = new BusinessAccount("Name", 1E8);

        account.requestLoan(1E8, 0.1f, 10);

        Assert.assertEquals(1E8, account.getLoan().getAmount(), 0);
        Assert.assertEquals(0.1f, account.getLoan().getRate(), 0);
        Assert.assertEquals(10, account.getLoan().getTerms());
    }

    @Test
    public void requestInvestmentTest() {
        BusinessAccount account = new BusinessAccount("Name", 1E8);
        Investment investment = new Investment();

        account.requstInvesmet(investment);

        Assert.assertEquals(investment, account.getInvestment());
    }

    @Test
    public void payInterestTest() {
        BusinessAccount account = new BusinessAccount("Name", 1E8);
        Investment investment = new Investment();

        account.requstInvesmet(investment);

        account.payInterest();

        Assert.assertEquals(true, account.isHaveInvestment());
        Assert.assertEquals(true, account.getBalance() > 1E8);
    }
}
