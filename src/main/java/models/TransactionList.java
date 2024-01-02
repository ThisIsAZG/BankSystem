package models;

import interfaces.IBankAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TransactionList {
    private static Hashtable<String, Transaction> transactionHashtable = new Hashtable<String, Transaction>();

    public static void addTransaction(Transaction transaction) {
        transactionHashtable.put(transaction.getTransactionId(), transaction);
    }
    public static List<Transaction> getTransactionsInDay(IBankAccount account, LocalDate date) {
        List<Transaction> transactionsInSpecialDay = new ArrayList<>();

        transactionHashtable.forEach((id, transaction) -> {
            if (transaction.getTransactionDate().isEqual(date) && transaction.getFromAccount().equals(account)) {
                transactionsInSpecialDay.add(transaction);
            }
        });

        return transactionsInSpecialDay;
    }

    public static double remainPerDay(IBankAccount bankAccount, LocalDate date) {
        double sum = getTransactionsInDay(bankAccount, date).stream().mapToDouble(Transaction::getAmount).sum();

        return bankAccount.getLIMIT() - sum;
    }

    public static List<Transaction> getAccountTransactions(IBankAccount bankAccount) {
        List<Transaction> transactions = new ArrayList<>();

        transactionHashtable.forEach((id, transaction) -> {
            if (transaction.getFromAccount().equals(bankAccount) || transaction.getToAccount().equals(bankAccount)) {
                transactions.add(transaction);
            }
        });

        return transactions;
    }

}
