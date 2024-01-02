import interfaces.IBankAccount;
import models.Investment;
import models.PermiumAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InvestmentListTest {
    List<IBankAccount> investors;
    PermiumAccount permiumAccountTest1;
    PermiumAccount permiumAccountTest2;
    PermiumAccount permiumAccountTest3;
    @Before
    public void setup() throws Exception {
        investors = new ArrayList<>();
        permiumAccountTest1 = new PermiumAccount("Test1", 0);
        permiumAccountTest2 = new PermiumAccount("Test2", 1E8);
        permiumAccountTest3 = new PermiumAccount("Test3", 34E7);

        investors.add(permiumAccountTest1);
        investors.add(permiumAccountTest2);
        investors.add(permiumAccountTest3);
    }

    @Test
    public void noArgInvestmentTest() {
        Investment investment = new Investment();

        Assert.assertEquals(0, investment.getAmount(), 0);
        Assert.assertEquals(new ArrayList<IBankAccount>(), investment.getInvestors());
        Assert.assertEquals(0, investment.getRate(), 0);
    }

    @Test
    public void InvestmentWithAmountAndRate() {
        Investment investment = new Investment(1E7, 0.2f);

        Assert.assertEquals(1E7, investment.getAmount(), 0);
        Assert.assertEquals(new ArrayList<IBankAccount>(), investment.getInvestors());
        Assert.assertEquals(0.2f, investment.getRate(), 0);
    }

    @Test
    public void AllArgConstructor() {
        Investment investment = new Investment(1E6, investors, 0.17f);

        Assert.assertEquals(1E6, investment.getAmount(), 0);
        Assert.assertEquals(investors, investment.getInvestors());
        Assert.assertEquals(0.17f, investment.getRate(), 0);
    }

    @Test
    public void investTest() {
        Investment investment = new Investment(1E7, 0.2f);
        investment.invest(3E6);
        Assert.assertEquals(1E7+3E6, investment.getAmount(), 0);
    }

    @Test
    public void withdrawTest() {
        Investment investment = new Investment(1E7, 0.2f);
        investment.withdrawInvestment(3E6);
        Assert.assertEquals(1E7-3E6, investment.getAmount(), 0);
    }

}
