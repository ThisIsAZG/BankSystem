package interfaces;

import models.Transaction;

public interface ITransaction {
    public void execute() throws Exception;

    public void rollback(Transaction transaction) throws Exception;
}
