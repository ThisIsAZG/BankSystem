import interfaces.IBankAccount;
import models.BasicAccount;
import models.PermiumAccount;
import models.Transaction;
import models.TransactionList;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        BasicAccount basicAccount1 = new PermiumAccount("permium", 1E8);
        BasicAccount basicAccount2 = new BasicAccount();

        Transaction transaction1 = new Transaction(1000, basicAccount1, basicAccount2);
        Transaction transaction2 = new Transaction(1200, basicAccount1, basicAccount2);
        Transaction transaction3 = new Transaction(2000, basicAccount2, basicAccount1);

        LocalDate date = LocalDate.now();
        System.out.println(date);

        System.out.println(basicAccount1);
        System.out.println(basicAccount2);

    }
}
