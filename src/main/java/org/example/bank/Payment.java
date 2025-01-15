package org.example.bank;

import java.sql.Date;

public class Payment {
    private long cardPayment;
    private Date expirationDate;
    private int cvv;
    private double amount;

    public long getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(long cardPayment) {
        this.cardPayment = cardPayment;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment(long cardPayment, Date expirationDate, int cvv, double amount) {
        this.cardPayment = cardPayment;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.amount = amount;
    }
}
