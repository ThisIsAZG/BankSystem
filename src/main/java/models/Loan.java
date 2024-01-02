package models;

import CustomExceptions.LoanAlreadyExistsException;
import CustomExceptions.LoanNotApproved;
import CustomExceptions.NoEnoughBalanceException;
import CustomExceptions.NoLoanExistsException;
import interfaces.IBankAccount;
import interfaces.Loanable;
import models.Status.LoanStatus;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
public class Loan implements Loanable {
    private UUID loanNumber;
    private BasicAccount account;
    private double amount;
    private float rate;
    private int terms;
    private double remain;
    private LoanStatus status;

    public Loan(BasicAccount account, double amount, float rate, int terms) {
        this.setLoanNumber(UUID.randomUUID());
        this.account = account;
        this.amount = amount;
        this.rate = rate;
        this.terms = terms;
        this.remain = 0;
        this.status = LoanStatus.PENDING;
    }

    public void borrow() {
        try {
            BasicAccount account = this.getAccount();

            if (this.getAccount().isHaveLoan()) {
                throw new LoanAlreadyExistsException("You have already a loan that you haven't paid it");
            }

            this.setStatus(); // check

            if (this.getStatus() != Status.LoanStatus.APPROVED) {
                throw new LoanNotApproved("You can't borrow: " + this.getStatus());
            }

            account.despoite(this.getAmount());
            account.setHaveLoan(true);
            account.setBorrowed(this.getAmount() * (1 + this.getRate()));
        }
        catch (LoanAlreadyExistsException exception) {
            System.err.println(exception.getMessage());
        }
        catch (LoanNotApproved exception) {
            System.err.println(exception.getMessage());;
        }
    }

    public void repay(double installment) {
        try {
            BasicAccount account = this.getAccount();

            if (!this.getAccount().isHaveLoan()) {
                throw new NoLoanExistsException();
            }

            if (installment > account.getBalance()) {
                throw new NoEnoughBalanceException("You don't have enough balance to repay");
            } else {
                account.setBalance(account.getBalance() - installment);
                account.setBorrowed(account.getBorrowed() - installment);
                account.setInstallments_terms(account.getInstallments_terms() + 1);

                if (account.getBorrowed() == 0) {
                    account.setHaveLoan(false);
                    account.setInstallments_terms(0);
                }
            }

        }
        catch (NoEnoughBalanceException exception) {
            System.err.println("Error in repay: " + exception.getMessage());
        }

        catch (NoLoanExistsException exception) {
            System.err.println("Error in repay" + exception.getMessage());
        }
    }

    public void setStatus() { //set some condition for approve loan
        if (this.account instanceof BasicAccount) {
            this.setStatus(LoanStatus.REJECTED_BASIC_ACCOUNT);
        } else if (this.account instanceof PermiumAccount ) { //TODO: add business acc
            // add additional condition
            this.setStatus(LoanStatus.APPROVED);
        }
    }



}
