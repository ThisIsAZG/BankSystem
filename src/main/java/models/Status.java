package models;

public class Status {
    public enum LoanStatus {
        PENDING, APPROVED, REJECTED_BASIC_ACCOUNT, REJECTED
    }

    public enum AccountStatus {
        ACTIVE, INACTIVE
    }

    public enum TransactionStatus {
        SUCCESS, FAILED, PENDING, ROLLBACKED
    }

    public enum InvestmentStatus {
        INVESTED, NOT_INVESTED, WITHDRAWN
    }

    public enum TransactionType {
        TYPE1, TYPE2, TYPE3
    }
}
