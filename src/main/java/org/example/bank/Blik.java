package org.example.bank;

public class Blik {
    private int blikCode;
    private double amount;

    public int getBlikCode() {
        return blikCode;
    }

    public void setBlikCode(int blikCode) {
        this.blikCode = blikCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Blik(int blikCode, double amount) {
        this.blikCode = blikCode;
        this.amount = amount;
    }
}
