import CustomExceptions.NoEnoughBalanceException;
import models.BasicAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
public class BasicAccountTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createNonArgBasicAccount() {
        BasicAccount basicAccount = new BasicAccount();

        Assert.assertEquals("Unknown", basicAccount.getOwnName());
        Assert.assertEquals(0, basicAccount.getBalance(), 0);
        Assert.assertEquals(0, basicAccount.getInstallments_terms());
        Assert.assertEquals(0, basicAccount.getBorrowed(), 0);
        Assert.assertEquals(null, basicAccount.getLoan());
        Assert.assertEquals(false, basicAccount.isHaveLoan());
    }

    @Test
    public void createBasicAccountViaName() {
        BasicAccount basicAccount = new BasicAccount("Name");

        Assert.assertEquals("Name", basicAccount.getOwnName());
        Assert.assertEquals(0, basicAccount.getBalance(), 0);
        Assert.assertEquals(0, basicAccount.getInstallments_terms());
        Assert.assertEquals(0, basicAccount.getBorrowed(), 0);
        Assert.assertEquals(null, basicAccount.getLoan());
        Assert.assertEquals(false, basicAccount.isHaveLoan());
    }


    @Test
    public void createBasicAccountViaNameAndBalance() {
        BasicAccount basicAccount = new BasicAccount("Name", 1E8);

        Assert.assertEquals("Name", basicAccount.getOwnName());
        Assert.assertEquals(1E8, basicAccount.getBalance(), 0);
        Assert.assertEquals(0, basicAccount.getInstallments_terms());
        Assert.assertEquals(0, basicAccount.getBorrowed(), 0);
        Assert.assertEquals(null, basicAccount.getLoan());
        Assert.assertEquals(false, basicAccount.isHaveLoan());
    }

    @Test
    public void despoite() {
        BasicAccount basicAccount = new BasicAccount("Name", 1E8);

        basicAccount.despoite(1E8);

        Assert.assertEquals(2E8, basicAccount.getBalance(), 0);
    }

    @Test
    public void withdraw() {
        BasicAccount basicAccount = new BasicAccount("Name", 1E8);

        basicAccount.withdraw(1E8);

        Assert.assertEquals(0, basicAccount.getBalance(), 0);
    }

    @Test
    public void withdrawWithNoEnoughBalance() {
        BasicAccount basicAccount = new BasicAccount("Name", 1E8);

        try {
            basicAccount.withdraw(2E8);
        }
        catch (Exception exception) {
            Assert.assertEquals("Error in withdraw: There is no enough balance in your account", exception.getMessage());
        }
    }

    @Test
    public void getLIMIT() {
        BasicAccount basicAccount = new BasicAccount("Name", 1E8);

        Assert.assertEquals(1E6, basicAccount.getLIMIT(), 0);
    }

    @Test
    public void equalsAccount() {
        BasicAccount basicAccount1 = new BasicAccount();
        BasicAccount basicAccount2 = new BasicAccount();

        Assert.assertEquals(false, basicAccount1.equals(basicAccount2));
    }
}