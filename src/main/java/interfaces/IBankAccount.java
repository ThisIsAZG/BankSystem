package interfaces;

public interface IBankAccount {
    public long AccountNumber = 0;
    public void despoite(double amount);

    public void withdraw(double amount);

    public double getBalance();

    public double getLIMIT();
}
