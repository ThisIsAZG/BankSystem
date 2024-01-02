package models;

import CustomExceptions.NoEnoughBalanceException;
import CustomExceptions.TransactionLimitException;
import CustomExceptions.TransactionRollbackException;
import interfaces.IBankAccount;
import interfaces.ITransaction;
import models.Status.TransactionType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.RandomTransactionId;

import java.time.LocalDate;


@Setter
@Getter
@ToString
public class Transaction implements ITransaction {
    private String transactionId;
    private TransactionType transactionType;
    private double amount;
    private LocalDate transactionDate;
    private IBankAccount fromAccount, toAccount;
    private Status.TransactionStatus status;

    public Transaction(double amount, IBankAccount fromAccount, IBankAccount toAccount) {
        this.setTransactionId(new RandomTransactionId().create());
        this.setTransactionType(TransactionType.TYPE1);
        this.setAmount(amount);
        this.setTransactionDate(LocalDate.now());
        this.setFromAccount(fromAccount);
        this.setToAccount(toAccount);
        this.setStatus(Status.TransactionStatus.PENDING);

        TransactionList.addTransaction(this);
    }
    public Transaction(TransactionType transactionType, double amount, IBankAccount fromAccount, IBankAccount toAccount) {
        this.setTransactionId(new RandomTransactionId().create());
        this.setTransactionType(transactionType);
        this.setAmount(amount);
        this.setTransactionDate(LocalDate.now());
        this.setFromAccount(fromAccount);
        this.setToAccount(toAccount);
        this.setStatus(Status.TransactionStatus.PENDING);

        TransactionList.addTransaction(this);
    }

    @Override
    public void execute() throws Exception {
        try {
            if (this.getFromAccount().getBalance() < this.getAmount()) {
                throw new NoEnoughBalanceException("There is no enough balance in your account");
            } else if (TransactionList.remainPerDay(this.getFromAccount(), this.getTransactionDate()) < this.getAmount() && !(this.getFromAccount() instanceof BusinessAccount)) {
                throw new TransactionLimitException("You have reached your daily transaction limit");
            } else {
                this.getFromAccount().withdraw(this.getAmount());
                this.getToAccount().despoite(this.getAmount());
                this.setStatus(Status.TransactionStatus.SUCCESS);
            }
        } catch (NoEnoughBalanceException exception) {
            System.err.println("Error in execute: " + exception.getMessage());
            this.setStatus(Status.TransactionStatus.FAILED);
        }
        catch (TransactionLimitException exception) {
            System.err.println("Error in execute: " + exception.getMessage());
            this.setStatus(Status.TransactionStatus.FAILED);
        }
    }

    @Override
    public void rollback(Transaction transaction) throws Exception {
        try {
            if (transaction.getStatus() != Status.TransactionStatus.SUCCESS) {
                throw new TransactionRollbackException("Can't rollback this transaction because it's not successful");
            } else {
                this.getFromAccount().despoite(this.getAmount());
                this.getToAccount().withdraw(this.getAmount());
                this.setStatus(Status.TransactionStatus.ROLLBACKED);
            }
        } catch (Exception exception) {
            System.err.println("Error in rollback: " + exception.getMessage());
            this.setStatus(Status.TransactionStatus.FAILED);
        }
    }
}
