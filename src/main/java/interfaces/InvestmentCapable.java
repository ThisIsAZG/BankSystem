package interfaces;

import models.Status.InvestmentStatus;

public interface InvestmentCapable {
    public void invest(double amount);

    public void withdrawInvestment(double amount);
}
