package models;

import interfaces.IBankAccount;
import interfaces.InvestmentCapable;
import models.Status.InvestmentStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
public class Investment implements InvestmentCapable {
    private UUID investmentId;
    private double amount;
    private List<IBankAccount> investors;
    private float rate;

    public Investment() {
        this.setInvestmentId(UUID.randomUUID());
        this.setAmount(0);
        this.setInvestors(new ArrayList<>());
        this.setRate(0);

        InvestmentList.addInvestment(this);
    }

    public Investment(double initialAmount, float rate) {
        this.setInvestmentId(UUID.randomUUID());
        this.setAmount(initialAmount);
        this.setInvestors(new ArrayList<>());
        this.setRate(rate);

        InvestmentList.addInvestment(this);
    }

    public Investment(double initialAmount, List<IBankAccount> numberOfInvestors, float rate) {
        this.setInvestmentId(UUID.randomUUID());
        this.setAmount(initialAmount);
        this.setInvestors(numberOfInvestors);
        this.setRate(rate);

        InvestmentList.addInvestment(this);
    }

    @Override
    public void invest(double amount) {
        this.setAmount(this.getAmount() + amount);
    }

    @Override
    public void withdrawInvestment(double amount) {
        this.setAmount(this.getAmount() - amount);
    }

}
