package org.example.bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Random;

@RestController
public class MainController {
    DatabaseConnection db;

    public MainController(DatabaseConnection db) {
        this.db = db;
    }

    @PostMapping("/api/payments/card")
    public ResponseEntity<Boolean> payment(@RequestBody Payment payment) {
        if (db.searchCard(payment)) {
            LocalDate date = LocalDate.parse(payment.getExpirationDate());
            LocalDate currentDate = LocalDate.now();

            if (currentDate.isBefore(date)) {
                if (payment.getAmount() <= db.getMoney(payment) && payment.getAmount() <= db.getLimit(payment)) {
                    db.updateMoney(payment);
                    System.out.println("[Bank] Dokonano płatności.");
                    return ResponseEntity.ok(true);
                } else {
                    System.out.println("[Bank] Nie masz pieniędzy lub przekroczyłeś limit.");
                }
            } else {
                System.out.println("[Bank] Upłynął termin ważności karty.");
            }
        } else {
            System.out.println("[Bank] Nie znaleziono karty.");
        }

        return ResponseEntity.badRequest().body(false);
    }

    @GetMapping("/api/newblik/{kontoID}")
    public int newblik(@PathVariable int kontoID) {
        Random rand = new Random();
        int blikNumber = 0;
        for (int i = 0; i < 6; i++) {
            blikNumber += (int) (rand.nextInt(10) * Math.pow(10, i));
        }
        db.createNewBlik(kontoID, blikNumber);
        return blikNumber;
    }

    @PostMapping("api/payments/blik")
    public ResponseEntity<Boolean> payment(@RequestBody Blik blik){
        if(db.searchBlik(blik)){
            db.updateMoneyBlik(blik);
            System.out.println("[Bank] Dokonano płatności.");
            return ResponseEntity.ok(true);
        } else {
            System.out.println("[Bank] Nie znaleziono kodu Blik.");
        }

        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping("api/login")
    public ResponseEntity<Boolean> login(@RequestBody Login login){
        return db.checkLoginCredentials(login) ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }

        //TODO make accountData, Card and transaction endpoints to send data to mobile app,

}
