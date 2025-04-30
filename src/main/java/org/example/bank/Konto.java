package org.example.bank;

public class Konto {
    private double balance;
    private int accountId;
    private int accountNumber;
    private String accountType;

    public Konto(double balance, int accountId, String accountType, int accountNumber) {
        this.balance = balance;
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }
}
