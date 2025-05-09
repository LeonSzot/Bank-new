package org.example.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    public boolean searchBlik(Blik blik){
        String query = "SELECT COUNT(*) FROM blik WHERE NumerBlik = ? AND Aktywny = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, blik.getBlikCode(), true);
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

    public void createNewBlik(int accountId, int blikNumber) {
        String insertQuery = "INSERT INTO blik(KontoID, NumerBlik, Aktywny) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertQuery, accountId, blikNumber, true);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> updateBlikStatus(accountId, blikNumber), 2, TimeUnit.MINUTES);
    }

    private void updateBlikStatus(int accountId, int blikNumber) {
        String updateQuery = "UPDATE blik SET Aktywny = ? WHERE KontoID = ? AND NumerBlik = ?";
        jdbcTemplate.update(updateQuery, false, accountId, blikNumber);
    }

    public void updateMoneyBlik(Blik blik) {
        String query = "UPDATE konta JOIN blik " +
                "ON konta.ID = blik.KontoID " +
                "SET konta.Saldo = konta.Saldo - ? " +
                "WHERE blik.NumerBlik = ? AND blik.Aktywny = ?";
        jdbcTemplate.update(query, blik.getAmount(), blik.getBlikCode(), true);
    }

    public boolean checkLoginCredentials(Login login){
        String query = "SELECT COUNT(*) FROM `konta` WHERE `login` = ? AND `haslo` = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, login.getLogin(), login.getHaslo(), true);
        return count == 1;
    }

    public AccountData getAccount(String login) {
        String query = "SELECT `NumerKonta`, `TypKonta`, `Saldo` FROM `konta` WHERE `login` = ?";
        List<AccountData> results = jdbcTemplate.query(query, new Object[]{login}, (rs, rowNum) -> {
            AccountData account = new AccountData();
            account.setAccountNumber(rs.getInt("NumerKonta"));  // Użyj odpowiednich nazw kolumn
            account.setAccountType(rs.getString("TypKonta"));
            account.setBalance(rs.getDouble("Saldo"));
            return account;
        });

        return results.isEmpty() ? null : results.get(0);
    }



}
