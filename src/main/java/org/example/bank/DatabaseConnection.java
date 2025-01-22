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
        return count > 0;
    }
}
