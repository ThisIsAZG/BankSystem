package models;

import CustomExceptions.LoanAlreadyExistsException;
import CustomExceptions.LoanNotApproved;
import CustomExceptions.NoEnoughBalanceException;
import CustomExceptions.NoLoanExistsException;
import interfaces.IBankAccount;

import java.util.*;
import java.util.stream.Collectors;

public class LoanList {
    public static Hashtable<UUID, Loan> loanHashtable = new Hashtable<UUID, Loan>();

    public static void addLoan(Loan loan) {
        loanHashtable.put(loan.getLoanNumber(), loan);
    }


    public static List<Loan> getLoansByAccount(long accountNumber) {
        return loanHashtable.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getAccount().getAccountNumber() == accountNumber)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public static Loan getLoan(UUID loanNumber) {
        return loanHashtable.get(loanNumber);
    }

    public static int getSize() {
        return loanHashtable.size();
    }

}
