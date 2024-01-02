package models;

import java.lang.*;

import CustomExceptions.NoEnoughBalanceException;
import interfaces.IBankAccount;
import lombok.experimental.FieldDefaults;
import utils.RandomAccountNumber;

import lombok.*;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasicAccount implements IBankAccount {
    long accountNumber;
    String ownName;
    double balance;
    protected final double BASIC_TRANSACTION_LIMIT = 1E6;
    boolean isHaveLoan; //TODO: rename variable
    int installments_terms;
    double borrowed;
    Loan loan;


    public BasicAccount() {
        this.setAccountNumber(RandomAccountNumber.create());
        this.setOwnName("Unknown");
        this.balance = 0;
        this.setBalance(0);
        this.setHaveLoan(false);
        this.setInstallments_terms(0);
        this.setBorrowed(0);
        this.setLoan(null);
    }

    public BasicAccount(String ownName) {
        this.setAccountNumber(RandomAccountNumber.create());
        this.setOwnName(ownName);
        this.balance = 0;
        this.setBalance(0);
        this.setHaveLoan(false);
        this.setInstallments_terms(0);
        this.setBorrowed(0);
        this.setLoan(null);
    }

    public BasicAccount(String ownName, double initailBalance) {
        this.setAccountNumber(RandomAccountNumber.create());
        this.setOwnName(ownName);
        this.setBalance(initailBalance);
        this.setHaveLoan(false);
        this.setInstallments_terms(0);
        this.setBorrowed(0);
        this.setLoan(null);
    }

    @Override
    public void despoite(double amount) {
        this.setBalance(this.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        try {
            if (this.getBalance() < amount) {
                throw new NoEnoughBalanceException("There is no enough balance in your account");
            }else {
                this.setBalance(this.getBalance() - amount);
            }
        }
        catch (NoEnoughBalanceException exception) {
            System.err.println("Error in withdraw: " + exception.getMessage());
        }
    }

    @Override
    public double getLIMIT() {
        return this.BASIC_TRANSACTION_LIMIT;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BasicAccount basicAccount = (BasicAccount) obj;

        return this.getAccountNumber() == basicAccount.getAccountNumber();
    }


    @Override
    public int hashCode() {
        return (int) this.getAccountNumber();
    }
}
