package org.example.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseConnection {
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public DatabaseConnection(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean searchCard(Payment payment) {
        String query = "SELECT COUNT(*) FROM karty WHERE NumerKarty = ? AND TerminWaznosci = ? AND CVV = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, payment.getCardNumber(), payment.getExpirationDate(), payment.getCvv());
        return count == 1;
    }

    public void updateMoney(Payment payment) {
        String query = "UPDATE konta JOIN karty " +
                "ON konta.ID = karty.KontoID " +
                "SET konta.Saldo = konta.Saldo - ? " +
                "WHERE karty.NumerKarty = ? ";
        jdbcTemplate.update(query, payment.getAmount(), payment.getCardNumber());
    }

    public double getMoney(Payment payment) {
        String query = "SELECT konta.Saldo FROM konta " +
                "JOIN karty " +
                "ON konta.ID = karty.KontoID " +
                "WHERE karty.NumerKarty = ? ";
        return jdbcTemplate.queryForObject(query, Double.class, payment.getCardNumber());
    }

    public double getLimit(Payment payment) {
        String query = "SELECT `Limit` FROM karty WHERE NumerKarty = ?";
        return jdbcTemplate.queryForObject(query, Double.class, payment.getCardNumber());
    }
}
