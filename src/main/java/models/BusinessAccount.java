package models;

import interfaces.Loanable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BusinessAccount extends BasicAccount {
    public final double BUSINESS_TRANSACTION_LIMIT = 100 * BASIC_TRANSACTION_LIMIT;
    private final float BUSINESS_INTEREST_RATE = 0.15f;
    private final double BUSINESS_MINIMUM_BALANCE_INTEREST = 1E7;
    private boolean isHaveLoan; //TODO: rename variable
    private int installments_terms;
    private double borrowed;
    private Loan loan;
    private boolean isHaveInvestment;
    private Investment investment;

    public BusinessAccount(String ownName, double initalBalance) {
        super(ownName, initalBalance);
    }
    @Override
    public double getLIMIT(){
        return this.BUSINESS_TRANSACTION_LIMIT;
    }

    public void payInterest() {
        if (this.getBalance() >= this.BUSINESS_MINIMUM_BALANCE_INTEREST) {
            this.despoite(this.getBalance() * this.BUSINESS_INTEREST_RATE);
        }
    }

    public void requestLoan(double amount, float rate, int terms) {
        this.setLoan(new Loan(this, amount, rate, terms));
    }

    public void requstInvesmet(Investment investment) {
        Status.InvestmentStatus investmentStatus = InvestmentList.addInvestors(investment, this);

        if (investmentStatus == Status.InvestmentStatus.INVESTED) {
            this.setInvestment(investment);
            this.setHaveInvestment(true);
        } else {
            this.setHaveInvestment(false);
        }
    }

}
