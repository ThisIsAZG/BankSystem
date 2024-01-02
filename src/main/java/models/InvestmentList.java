package models;

import interfaces.IBankAccount;
import models.Status.InvestmentStatus;

import java.util.Hashtable;
import java.util.UUID;

public class InvestmentList {
    private static Hashtable<UUID, Investment> investmentHashtable = new Hashtable<>();

    public static InvestmentStatus addInvestors(Investment investment, IBankAccount bankAccount) {
        if (bankAccount instanceof PermiumAccount || bankAccount instanceof BusinessAccount) {
            // check some conditon

            investment.getInvestors().add(bankAccount);

            return InvestmentStatus.INVESTED;
        } else {
            return InvestmentStatus.NOT_INVESTED;
        }
    }

    public static void addInvestment(Investment investment) {
        investmentHashtable.put(investment.getInvestmentId(), investment);
    }
}
