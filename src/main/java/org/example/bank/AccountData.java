package org.example.bank;

public class AccountData {
    private double Saldo;
    private String NumerKonta;
    private String TypKonta;

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public void setNumerKonta(String numerKonta) {
        this.NumerKonta = numerKonta;
    }

    public void setTypKonta(String TypKonta) {
        this.TypKonta = TypKonta;
    }

    public double getSaldo() {
        return Saldo;
    }

    public String getNumerKonta() {
        return NumerKonta;
    }

    public String getTypKonta() {
        return TypKonta;
    }
}
