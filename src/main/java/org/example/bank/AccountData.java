package org.example.bank;

import java.util.Date;

public class AccountData {
    private double balance;
    private int accountNumber;
    private String accountType;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }
}
