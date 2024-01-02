package models;

import models.Status.InvestmentStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class PermiumAccount extends BasicAccount {
    public final double PREMIUM_TRANSACTION_LIMIT = 10 * BASIC_TRANSACTION_LIMIT;

    private final float PREMIUM_INTEREST_RATE = 0.1f;
    private final double PERMIUM_MINIMUM_BALANCE_INTEREST = 1E7;

    private boolean isHaveLoan; //TODO: rename variable
    private int installments_terms;
    private double borrowed;
    private Loan loan;

    private boolean isHaveInvestment;
    private Investment investment;


    public PermiumAccount(String ownName, double initalBalance) {
        super(ownName, initalBalance);
        this.setInstallments_terms(0);
        this.setBorrowed(0);
        this.setLoan(null);
        this.setHaveLoan(false);
        this.setInvestment(null);
        this.setHaveInvestment(false);
    }

    @Override
    public double getLIMIT(){
        return this.PREMIUM_TRANSACTION_LIMIT;
    }

    public void payInterest() {
        if (this.getBalance() >= this.PERMIUM_MINIMUM_BALANCE_INTEREST) {
            this.despoite(this.getBalance() * this.PREMIUM_INTEREST_RATE);
        }
    }

    public void requestLoan(double amount, float rate, int terms) {
        this.setLoan(new Loan(this, amount, rate, terms));
    }

    public void requstInvesmet(Investment investment) {
        InvestmentStatus investmentStatus = InvestmentList.addInvestors(investment, this);

        if (investmentStatus == InvestmentStatus.INVESTED) {
            this.setInvestment(investment);
            this.setHaveInvestment(true);
        } else {
            this.setHaveInvestment(false);
        }
    }


}
